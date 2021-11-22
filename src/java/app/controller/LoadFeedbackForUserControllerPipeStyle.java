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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoadFeedbackForUserControllerPipeStyle extends HttpServlet {

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
            FeedbackDAO dao = new FeedbackDAO();
            List<FeedbackLoaderDTO> listFBLoader = new ArrayList<>();
            Gson gson = new Gson();
            switch (flag) {
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
//                        int count = 1;
//                        for (String image : UserHistory.getImageList()) {
//                            if (!image.equals("") && (count == 1 || count == 2)) {
//                                imageHtml += "<div class=\"pipe-item-image\">\n"
//                                        + "       <img\n"
//                                        + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                        + "         alt=\"\"\n"
//                                        + "     />\n"
//                                        + "         </div>";
//                                count++;
//                            }
//
//                        }
//                        if (UserHistory.getImageList().size() == 3) {
//                            imageHtml += "<div class=\"more-wrapper\">\n"
//                                    + "                                                            <div class=\"img-more active\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                        </div>\n"
//                                    + "                                                        <div class=\"img-more\">\n"
//                                    + "                                                            <img\n"
//                                    + "                                                                src=\"https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png\"\n"
//                                    + "                                                                alt=\"\"\n"
//                                    + "                                                                />\n"
//                                    + "                                                        </div>";
//                        } else if (UserHistory.getImageList().size() == 4) {
//                            imageHtml += "<div class=\"more-wrapper\">\n"
//                                    + "                                                            <div class=\"img-more\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                            <div class=\"img-more active\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                        </div>";
//                        }
                        html = "<div class=\"pipe-item\">\n"
                                + "                                                    <div class=\"pipe-item-heading\">\n"
                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"image-all-wrapper pipe_image-all-wrapper\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                + "                                                    </div>\n"
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
//                        int count = 1;
//                        for (String image : UserHistory.getImageList()) {
//                            if (!image.equals("") && (count == 1 || count == 2)) {
//                                imageHtml += "<div class=\"pipe-item-image\">\n"
//                                        + "       <img\n"
//                                        + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                        + "         alt=\"\"\n"
//                                        + "     />\n"
//                                        + "         </div>";
//                                count++;
//                            }
//                        }
//                        if (UserHistory.getImageList().size() == 3) {
//                            imageHtml += "<div class=\"more-wrapper\">\n"
//                                    + "                                                            <div class=\"img-more active\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                        </div>\n"
//                                    + "                                                        <div class=\"img-more\">\n"
//                                    + "                                                            <img\n"
//                                    + "                                                                src=\"https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png\"\n"
//                                    + "                                                                alt=\"\"\n"
//                                    + "                                                                />\n"
//                                    + "                                                        </div>";
//                        } else if (UserHistory.getImageList().size() == 4) {
//                            imageHtml += "<div class=\"more-wrapper\">\n"
//                                    + "                                                            <div class=\"img-more\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                            <div class=\"img-more active\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                        </div>";
//                        }
                        html = "<div class=\"pipe-item\">\n"
                                + "                                                    <div class=\"pipe-item-heading\">\n"
                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"image-all-wrapper pipe_image-all-wrapper\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                + "                                                    </div>\n"
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
//                        int count = 1;
//                        for (String image : UserHistory.getImageList()) {
//                            if (!image.equals("") && (count == 1 || count == 2)) {
//                                imageHtml += "<div class=\"pipe-item-image\">\n"
//                                        + "       <img\n"
//                                        + "     src=\"data:image/jpg/png;base64," + image + "\"\n"
//                                        + "         alt=\"\"\n"
//                                        + "     />\n"
//                                        + "         </div>";
//                                count++;
//                            }
//
//                        }
//                        if (UserHistory.getImageList().size() == 3) {
//                            imageHtml += "<div class=\"more-wrapper\">\n"
//                                    + "                                                            <div class=\"img-more active\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                        </div>\n"
//                                    + "                                                        <div class=\"img-more\">\n"
//                                    + "                                                            <img\n"
//                                    + "                                                                src=\"https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png\"\n"
//                                    + "                                                                alt=\"\"\n"
//                                    + "                                                                />\n"
//                                    + "                                                        </div>";
//                        } else if (UserHistory.getImageList().size() == 4) {
//                            imageHtml += "<div class=\"more-wrapper\">\n"
//                                    + "                                                            <div class=\"img-more\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                            <div class=\"img-more active\">\n"
//                                    + "                                                                <img\n"
//                                    + "                                                                    src=\"https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png\"\n"
//                                    + "                                                                    alt=\"\"\n"
//                                    + "                                                                    />\n"
//                                    + "                                                            </div>\n"
//                                    + "                                                        </div>";
//                        }
                        html = "<div class=\"pipe-item\">\n"
                                + "                                                    <div class=\"pipe-item-heading\">\n"
                                + "                                                        <div class=\"pipe-item-title-wrapper\">\n"
                                + "                                                            <h3 class=\"pipe-item-title\">Feedback " + UserHistory.getFeedbackId() + "</h3>\n"
                                + "                                                        </div>\n"
                                + "                                                        <div class=\"pipe-item-date\">" + UserHistory.getDate() + "</div>\n"
                                + "                                                    </div>\n"
                                + "                                                    <div class=\"image-all-wrapper pipe_image-all-wrapper\" data-id=\"" + UserHistory.getFeedbackId() + "\">"
                                + "                                                    </div>\n"
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
