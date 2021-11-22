/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackLoaderDTO;
import app.users.UserDTO;
import app.users.UserHistoryDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoadFeedbackForUserControllerListStyle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String amount = request.getParameter("amount");
            String flag = request.getParameter("flag_navigation");
            String search = request.getParameter("search");
            String feeedbackID = request.getParameter("FEEDBACKID_FROM_SEARCH");
            FeedbackDAO dao = new FeedbackDAO();
            List<FeedbackLoaderDTO> listFBLoader = new ArrayList<>();
            Gson gson = new Gson();
            switch (flag) {
                case "0":
                    if (!search.equals("")) {
                        String html = "";
                        List<UserHistoryDTO> list = dao.getListFeedbackForUserForLoadingMoreData(user.getUserID());
                        int position = 0;
                        int count = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (i + 1 == list.size()) {
                                position = list.size();
                                break;
                            }
                            if (list.get(i).getFeedbackId().equals(feeedbackID) && !list.get(i + 1).getFeedbackId().equals(feeedbackID)) {
                                position = i + 1;
                                break;
                            }
                        }
                        List<UserHistoryDTO> listAll = dao.getListFeedbackForUserNextForSearch(user.getUserID(), position);
                        List<UserHistoryDTO> newlistAll = new ArrayList<>();
                        List<String> deviceName = new ArrayList<String>();
                        String deviceNameArray = null;
                        String imageArray = null;
                        String locationArray = null;
                        List<String> imageList = new ArrayList<String>();

                        String feedbackId;
                        String date;
                        String statusId;
                        String statusName;
                        if (listAll.size() != 0) {
                            for (int i = 0; i < listAll.size() + 1; i++) {
                                if (i == 0) {
                                    deviceNameArray = listAll.get(i).getDeviceName();
                                    deviceName.add(listAll.get(i).getDeviceName());
                                    locationArray = listAll.get(i).getLocation();
                                    imageArray = listAll.get(i).getImageFirebase();
//                                imageList.add(listAll.get(i).getImage());

                                } else {
                                    //optimize code at final sprint
                                    if (i == listAll.size()) {
                                        feedbackId = listAll.get(i - 1).getFeedbackId();
                                        date = listAll.get(i - 1).getDate();
                                        statusId = listAll.get(i - 1).getStatusId();
                                        statusName = listAll.get(i - 1).getStatusName();
                                        for (String device : deviceName) {
                                            if (device.toUpperCase().contains(search.toUpperCase())) {
                                                newlistAll.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                            }
                                        }

                                        break;
                                    }
                                    if (!listAll.get(i).getFeedbackId().equals(listAll.get(i - 1).getFeedbackId())) {
                                        feedbackId = listAll.get(i - 1).getFeedbackId();
                                        date = listAll.get(i - 1).getDate();
                                        statusId = listAll.get(i - 1).getStatusId();
                                        statusName = listAll.get(i - 1).getStatusName();
                                        for (String device : deviceName) {
                                            if (device.toUpperCase().contains(search.toUpperCase())) {
                                                newlistAll.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                            }
                                        }
                                        deviceNameArray = "";
                                        locationArray = "";
                                        imageArray = "";
                                        deviceName = new ArrayList<String>();
                                        imageList = new ArrayList<String>();
                                        deviceName.add(listAll.get(i).getDeviceName());
                                        deviceNameArray = listAll.get(i).getDeviceName();
                                        locationArray = listAll.get(i).getLocation();
                                        imageArray = listAll.get(i).getImageFirebase();
//                                    imageList.add(listAll.get(i).getImage());

                                    } else {
                                        deviceName.add(listAll.get(i).getDeviceName());
                                        deviceNameArray = deviceNameArray.concat(", ");
                                        deviceNameArray = deviceNameArray.concat(listAll.get(i).getDeviceName());
                                        locationArray = locationArray.concat(", ");
                                        locationArray = locationArray.concat(listAll.get(i).getLocation());
                                        imageArray = imageArray.concat(";");
                                        imageArray = imageArray.concat(listAll.get(i).getImageFirebase());
//                                    imageList.add(listAll.get(i).getImage());
                                    }
                                }
                            }
                            boolean all_flag = true;
                            Set<UserHistoryDTO> historySet = new TreeSet<UserHistoryDTO>();
                            for (UserHistoryDTO history : newlistAll) {
                                if (all_flag) {
                                    if (historySet.size() == 10) {
                                        all_flag = false;
                                    } else {
                                        historySet.add(history);
                                    }
                                }
                            }
                            List<UserHistoryDTO> withoutDuplicates = new ArrayList<UserHistoryDTO>(historySet);
                            Collections.reverse(withoutDuplicates);
                            String newAmount = "";
                            if (withoutDuplicates.size() != 0) {
                                newAmount = withoutDuplicates.get(withoutDuplicates.size() - 1).getFeedbackId();
                            } else {
                                newAmount = feeedbackID;
                            }
                            for (UserHistoryDTO UserHistory : withoutDuplicates) {
//                            String imageHtml = "";
//                            for (String image : UserHistory.getImageList()) {
//                                if (!image.equals("")) {
//                                    imageHtml += "<div class=\"pipe-item-image\">\n"
//                                            + "       <img\n"
//                                            + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                            + "         alt=\"\"\n"
//                                            + "     />\n"
//                                            + "         </div>";
//                                }
//                            }
                                html = "<div class=\"pipe-item\">\n"
                                        + "                                                    <div class=\"pipe-item-heading\">\n"
                                        + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                        + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                        + "                                                        </div>\n"
                                        + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                        + "                                                    </div>\n"
                                        + "                                                    <div class=\"image-all-wrapper List_All\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                        + "                                                    </div> "
                                        + "                                                    <div class=\"pipe-item-bottom\">\n"
                                        + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
                                        + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
                                        + "                                                    </div>\n"
                                        + "                                                </div>\n";
                                FeedbackLoaderDTO feedbackLoader = new FeedbackLoaderDTO(Integer.parseInt(newAmount), UserHistory.getFeedbackId(), html, UserHistory.getImageFirebase());
                                listFBLoader.add(feedbackLoader);

                            }
                        } else {
                            String newAmount = list.get(list.size() - 1).getFeedbackId();
                            FeedbackLoaderDTO feedbackLoader = new FeedbackLoaderDTO(Integer.parseInt(newAmount), "", "", "");
                            listFBLoader.add(feedbackLoader);
                        }
                        out.println(gson.toJson(listFBLoader));

//                        out.println("'" + newAmount + "'" + html);
                    } else {
                        List<UserHistoryDTO> listAll = dao.getListFeedbackForUserNext(user.getUserID(), Integer.parseInt(amount));
                        List<UserHistoryDTO> listAllCheck = dao.getListFeedbackForUserNextForCheck(user.getUserID(), Integer.parseInt(amount) + 10);
                        int count_flag = 0;
                        for (UserHistoryDTO userHistory : listAllCheck) {
                            if (userHistory.getFeedbackId().equalsIgnoreCase(listAll.get(listAll.size() - 1).getFeedbackId())) {
                                count_flag++;
                            }
                        }
                        int newAmount;
                        if (count_flag > 0) {
                            newAmount = Integer.parseInt(amount) + 10 + count_flag;
                            listAll = dao.getListFeedbackForUserNext(user.getUserID(), Integer.parseInt(amount), 10 + count_flag);
                        } else {
                            newAmount = Integer.parseInt(amount) + 10;
                        }
                        List<UserHistoryDTO> newlistAll = new ArrayList<>();
                        String html = "";
                        String check = "";
                        UserHistoryDTO tmpUserHistory = new UserHistoryDTO();
                        for (UserHistoryDTO UserHistory : listAll) {
                            if (UserHistory.getFeedbackId().equalsIgnoreCase(check)) {
                                List<String> imageList = tmpUserHistory.getImageList();

                                String deviceName = tmpUserHistory.getDeviceName();
                                String location = tmpUserHistory.getLocation();

                                // thêm ở đây nhé
                                String imageFirebase = tmpUserHistory.getImageFirebase();

                                if (UserHistory.getImage() != null) {
                                    imageList.add(UserHistory.getImage());
                                    tmpUserHistory.setImageList(imageList);
                                }
                                tmpUserHistory.setDeviceName(deviceName.concat(", ").concat(UserHistory.getDeviceName()));
                                tmpUserHistory.setLocation(location.concat(", ").concat(UserHistory.getLocation()));

                                // thêm ở đây nhé                                
                                tmpUserHistory.setImageFirebase(imageFirebase.concat(";").concat(UserHistory.getImageFirebase()));
                            } else {
                                List<String> imageList = new ArrayList<String>();
                                if (UserHistory.getImage() != null) {
                                    imageList.add(UserHistory.getImage());
                                    UserHistory.setImageList(imageList);
                                }
                                tmpUserHistory = UserHistory;
                                newlistAll.add(UserHistory);
                            }
                            check = UserHistory.getFeedbackId();
                        }
                        for (UserHistoryDTO UserHistory : newlistAll) {
//                            String imageHtml = "";
//
//                            for (String image : UserHistory.getImageList()) {
//                                if (!image.equals("")) {
//                                    imageHtml += "<div class=\"pipe-item-image\">\n"
//                                            + "       <img\n"
//                                            + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                            + "         alt=\"\"\n"
//                                            + "     />\n"
//                                            + "         </div>";
//                                }
//                            }
//                            html += "<div class=\"pipe-item\">\n"
//                                    + "                                                    <div class=\"pipe-item-heading\">\n"
//                                    + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
//                                    + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
//                                    + "                                                        </div>\n"
//                                    + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
//                                    + "                                                    </div>\n"
//                                    + "                                                    <div class=\"image-all-wrapper\"> " + imageHtml + ""
//                                    + "                                                    </div>\n"
//                                    + "                                                    <div class=\"pipe-item-bottom\">\n"
//                                    + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
//                                    + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
//                                    + "                                                    </div>\n"
//                                    + "                                                </div>\n";
                            html = "<div class=\"pipe-item\">\n"
                                    + "                                                    <div class=\"pipe-item-heading\">\n"
                                    + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                    + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                    + "                                                    </div>\n"
                                    + "                                                    <div class=\"image-all-wrapper List_All\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                    + "                                                    </div> "
                                    + "                                                    <div class=\"pipe-item-bottom\">\n"
                                    + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
                                    + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
                                    + "                                                    </div>\n"
                                    + "                                                </div>\n";
                            FeedbackLoaderDTO feedbackLoader = new FeedbackLoaderDTO(newAmount, UserHistory.getFeedbackId(), html, UserHistory.getImageFirebase());
                            listFBLoader.add(feedbackLoader);
                        }
//                        out.println("'" + newAmount + "'" + html);
                        out.println(gson.toJson(listFBLoader));
                    }

                    break;
                case "1":
                    List<UserHistoryDTO> listDone = dao.getListFeedbackDoneForUserNext(user.getUserID(), Integer.parseInt(amount));
                    List<UserHistoryDTO> listDoneCheck = dao.getListFeedbackDoneForUserNextCheck(user.getUserID(), Integer.parseInt(amount) + 10);
                    int count_flag = 0;
                    for (UserHistoryDTO userHistory : listDoneCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listDone.get(listDone.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    int newAmount;
                    if (count_flag > 0) {
                        newAmount = Integer.parseInt(amount) + 10 + count_flag;
                        listDone = dao.getListFeedbackDoneForUserNext(user.getUserID(), Integer.parseInt(amount), newAmount);
                    } else {
                        newAmount = Integer.parseInt(amount) + 10;
                    }
                    List<UserHistoryDTO> newListDone = new ArrayList<>();
                    String html = "";
                    String check = "";
                    UserHistoryDTO tmpUserHistory = new UserHistoryDTO();
                    for (UserHistoryDTO UserHistory : listDone) {
                        if (UserHistory.getFeedbackId().equalsIgnoreCase(check)) {
                            List<String> imageList = tmpUserHistory.getImageList();
                            String deviceName = tmpUserHistory.getDeviceName();
                            String location = tmpUserHistory.getLocation();
                            String image = tmpUserHistory.getImageFirebase();
                            if (UserHistory.getImage() != null) {
                                imageList.add(UserHistory.getImage());
                                tmpUserHistory.setImageList(imageList);
                            }
                            tmpUserHistory.setDeviceName(deviceName.concat(", ").concat(UserHistory.getDeviceName()));
                            tmpUserHistory.setLocation(location.concat(", ").concat(UserHistory.getLocation()));
                            tmpUserHistory.setImageFirebase(image.concat(";").concat(UserHistory.getImageFirebase()));
                        } else {
                            List<String> imageList = new ArrayList<String>();
                            if (UserHistory.getImage() != null) {
                                imageList.add(UserHistory.getImage());
                                UserHistory.setImageList(imageList);
                            }
                            tmpUserHistory = UserHistory;
                            newListDone.add(UserHistory);
                        }
                        check = UserHistory.getFeedbackId();
                    }
                    for (UserHistoryDTO UserHistory : newListDone) {
//                        String imageHtml = "";
//                        for (String image : UserHistory.getImageList()) {
//                            if (!image.equals("")) {
//                                imageHtml += "<div class=\"pipe-item-image\">\n"
//                                        + "       <img\n"
//                                        + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                        + "         alt=\"\"\n"
//                                        + "     />\n"
//                                        + "         </div>";
//                            }
//
//                        }
//                        html += "<div class=\"pipe-item\">\n"
//                                + "                                                    <div class=\"pipe-item-heading\">\n"
//                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
//                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
//                                + "                                                        </div>\n"
//                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
//                                + "                                                    </div>\n"
//                                + "                                                    <div class=\"image-all-wrapper\"> " + imageHtml + ""
//                                + "                                                    </div>\n"
//                                + "                                                    <div class=\"pipe-item-bottom\">\n"
//                                + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
//                                + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
//                                + "                                                    </div>\n"
//                                + "                                                </div>\n";
                        html = "<div class=\"pipe-item\">\n"
                                + "                                                    <div class=\"pipe-item-heading\">\n"
                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"image-all-wrapper list_image-all-wrapper\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                + "                                                    </div> "
                                + "                                                    <div class=\"pipe-item-bottom\">\n"
                                + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
                                + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
                                + "                                                    </div>\n"
                                + "                                                </div>\n";
                        FeedbackLoaderDTO feedbackLoader = new FeedbackLoaderDTO(newAmount, UserHistory.getFeedbackId(), html, UserHistory.getImageFirebase());
                        listFBLoader.add(feedbackLoader);
                    }
//                        out.println("'" + newAmount + "'" + html);
                    out.println(gson.toJson(listFBLoader));
                    break;
                case "2":
                    List<UserHistoryDTO> listOnGoing = dao.getListFeedbackOnGoingForUserNext(user.getUserID(), Integer.parseInt(amount));
                    List<UserHistoryDTO> listOnGoingCheck = dao.getListFeedbackOnGoingForUserNextCheck(user.getUserID(), Integer.parseInt(amount) + 10);
                    count_flag = 0;
                    for (UserHistoryDTO userHistory : listOnGoingCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listOnGoing.get(listOnGoing.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    newAmount = 0;
                    if (count_flag > 0) {
                        newAmount = Integer.parseInt(amount) + 10 + count_flag;
                        listOnGoing = dao.getListFeedbackOnGoingForUserNext(user.getUserID(), Integer.parseInt(amount), newAmount);
                    } else {
                        newAmount = Integer.parseInt(amount) + 10;
                    }
                    List<UserHistoryDTO> newlistOnGoing = new ArrayList<>();
                    html = "";
                    check = "";
                    tmpUserHistory = new UserHistoryDTO();
                    for (UserHistoryDTO UserHistory : listOnGoing) {
                        if (UserHistory.getFeedbackId().equalsIgnoreCase(check)) {
                            List<String> imageList = tmpUserHistory.getImageList();
                            String deviceName = tmpUserHistory.getDeviceName();
                            String location = tmpUserHistory.getLocation();
                            String image = tmpUserHistory.getImageFirebase();
                            if (UserHistory.getImage() != null) {
                                imageList.add(UserHistory.getImage());
                                tmpUserHistory.setImageList(imageList);
                            }
                            tmpUserHistory.setDeviceName(deviceName.concat(", ").concat(UserHistory.getDeviceName()));
                            tmpUserHistory.setLocation(location.concat(", ").concat(UserHistory.getLocation()));
                            tmpUserHistory.setImageFirebase(image.concat(";").concat(UserHistory.getImageFirebase()));
                        } else {
                            List<String> imageList = new ArrayList<String>();
                            if (UserHistory.getImage() != null) {
                                imageList.add(UserHistory.getImage());
                                UserHistory.setImageList(imageList);
                            }
                            tmpUserHistory = UserHistory;
                            newlistOnGoing.add(UserHistory);
                        }
                        check = UserHistory.getFeedbackId();
                    }
                    for (UserHistoryDTO UserHistory : newlistOnGoing) {
//                        String imageHtml = "";
//                        for (String image : UserHistory.getImageList()) {
//                            if (!image.equals("")) {
//                                imageHtml += "<div class=\"pipe-item-image\">\n"
//                                        + "       <img\n"
//                                        + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                        + "         alt=\"\"\n"
//                                        + "     />\n"
//                                        + "         </div>";
//                            }
//                        }
//                        html += "<div class=\"pipe-item\">\n"
//                                + "                                                    <div class=\"pipe-item-heading\">\n"
//                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
//                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
//                                + "                                                        </div>\n"
//                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
//                                + "                                                    </div>\n"
//                                + "                                                    <div class=\"image-all-wrapper\"> " + imageHtml + ""
//                                + "                                                    </div>\n"
//                                + "                                                    <div class=\"pipe-item-bottom\">\n"
//                                + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
//                                + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
//                                + "                                                    </div>\n"
//                                + "                                                </div>\n";
                        html = "<div class=\"pipe-item\">\n"
                                + "                                                    <div class=\"pipe-item-heading\">\n"
                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"image-all-wrapper list_image-all-wrapper\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                + "                                                    </div> "
                                + "                                                    <div class=\"pipe-item-bottom\">\n"
                                + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
                                + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
                                + "                                                    </div>\n"
                                + "                                                </div>\n";
                        FeedbackLoaderDTO feedbackLoader = new FeedbackLoaderDTO(newAmount, UserHistory.getFeedbackId(), html, UserHistory.getImageFirebase());
                        listFBLoader.add(feedbackLoader);
                    }
//                        out.println("'" + newAmount + "'" + html);
                    out.println(gson.toJson(listFBLoader));
                    break;

                default:
                    List<UserHistoryDTO> listDeny = dao.getListFeedbackDeclineForUserNext(user.getUserID(), Integer.parseInt(amount));
                    List<UserHistoryDTO> listDenyCheck = dao.getListFeedbackDeclineForUserNextCheck(user.getUserID(), Integer.parseInt(amount) + 10);
                    count_flag = 0;
                    for (UserHistoryDTO userHistory : listDenyCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listDeny.get(listDeny.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    newAmount = 0;
                    if (count_flag > 0) {
                        newAmount = Integer.parseInt(amount) + 10 + count_flag;
                        listDeny = dao.getListFeedbackDeclineForUserNext(user.getUserID(), Integer.parseInt(amount), newAmount);
                    } else {
                        newAmount = Integer.parseInt(amount) + 10;
                    }
                    List<UserHistoryDTO> newlistDeny = new ArrayList<>();
                    html = "";
                    check = "";
                    tmpUserHistory = new UserHistoryDTO();
                    for (UserHistoryDTO UserHistory : listDeny) {
                        if (UserHistory.getFeedbackId().equalsIgnoreCase(check)) {
                            List<String> imageList = tmpUserHistory.getImageList();
                            String deviceName = tmpUserHistory.getDeviceName();
                            String location = tmpUserHistory.getLocation();
                            String image = tmpUserHistory.getImageFirebase();
                            if (UserHistory.getImage() != null) {
                                imageList.add(UserHistory.getImage());
                                tmpUserHistory.setImageList(imageList);
                            }
                            tmpUserHistory.setDeviceName(deviceName.concat(", ").concat(UserHistory.getDeviceName()));
                            tmpUserHistory.setLocation(location.concat(", ").concat(UserHistory.getLocation()));
                            tmpUserHistory.setImageFirebase(image.concat(";").concat(UserHistory.getImageFirebase()));
                        } else {
                            List<String> imageList = new ArrayList<String>();
                            if (UserHistory.getImage() != null) {
                                imageList.add(UserHistory.getImage());
                                UserHistory.setImageList(imageList);
                            }
                            tmpUserHistory = UserHistory;
                            newlistDeny.add(UserHistory);
                        }
                        check = UserHistory.getFeedbackId();
                    }
                    for (UserHistoryDTO UserHistory : newlistDeny) {
//                        String imageHtml = "";
//                        for (String image : UserHistory.getImageList()) {
//                            if (!image.equals("")) {
//                                imageHtml += "<div class=\"pipe-item-image\">\n"
//                                        + "       <img\n"
//                                        + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                        + "         alt=\"\"\n"
//                                        + "     />\n"
//                                        + "         </div>";
//                            }
//
//                        }
//                        html += "<div class=\"pipe-item\">\n"
//                                + "                                                    <div class=\"pipe-item-heading\">\n"
//                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
//                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
//                                + "                                                        </div>\n"
//                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
//                                + "                                                    </div>\n"
//                                + "                                                    <div class=\"image-all-wrapper\"> " + imageHtml + ""
//                                + "                                                    </div>\n"
//                                + "                                                    <div class=\"pipe-item-bottom\">\n"
//                                + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
//                                + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
//                                + "                                                    </div>\n"
//                                + "                                                </div>\n";
                        html = "<div class=\"pipe-item\">\n"
                                + "                                                    <div class=\"pipe-item-heading\">\n"
                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"image-all-wrapper list_image-all-wrapper\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                + "                                                    </div> "
                                + "                                                    <div class=\"pipe-item-bottom\">\n"
                                + "                                                        <p class=\"pipe-bottom-item\">" + UserHistory.getDeviceName() + "</p>\n"
                                + "                                                        <p class=\"pipe-bottom-item\">Room: " + UserHistory.getLocation() + "</p>\n"
                                + "                                                    </div>\n"
                                + "                                                </div>\n";
                        FeedbackLoaderDTO feedbackLoader = new FeedbackLoaderDTO(newAmount, UserHistory.getFeedbackId(), html, UserHistory.getImageFirebase());
                        listFBLoader.add(feedbackLoader);
                    }
//                        out.println("'" + newAmount + "'" + html);
                    out.println(gson.toJson(listFBLoader));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
