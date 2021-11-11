/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
import app.users.UserHistoryDTO;
import app.users.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ShowFeedbackStudentController", urlPatterns = {"/ShowFeedbackStudentController"})
public class ShowFeedbackStudentController extends HttpServlet {

    private static final String SUCCESS = "UserPage.jsp";
    private static final String ERROR = "welcome.html";

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
        String url = ERROR;
        try {
            FeedbackDAO dao = new FeedbackDAO();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

            List<UserHistoryDTO> listFull = dao.getListFeedbackForUser(user.getUserID());
            List<UserHistoryDTO> listFullDone = dao.getListFeedbackForUserDone(user.getUserID());
            List<UserHistoryDTO> listFullOngoing = dao.getListFeedbackForUserOngoing(user.getUserID());
            List<UserHistoryDTO> listFullDecline = dao.getListFeedbackForUserDecline(user.getUserID());
            if (!listFull.isEmpty() || !listFullDone.isEmpty() || !listFullOngoing.isEmpty() || !listFullDecline.isEmpty()) {
                String deviceNameArray = null;
                String locationArray = null;
                String imageArray = null;
                List<String> imageList = new ArrayList<String>();

                List<UserHistoryDTO> listAll = new ArrayList<>();
                List<UserHistoryDTO> listDone = new ArrayList<>();
                List<UserHistoryDTO> listOngoing = new ArrayList<>();
                List<UserHistoryDTO> listDecline = new ArrayList<>();
                String feedbackId;
                String date;
                String statusId;
                String statusName;

                // list ALL
                if (!listFull.isEmpty()) {
                    List<UserHistoryDTO> listAllCheck = dao.getListFeedbackForUserNextForCheck(user.getUserID(), listFull.size());
                    int count_flag = 0;
                    for (UserHistoryDTO userHistory : listAllCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listFull.get(listFull.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    int newAmount;
                    if (count_flag > 0) {
                        newAmount = listFull.size() + count_flag;
                        listFull = dao.getListFeedbackForUserNext(user.getUserID(), 0, newAmount);
                    } else {
                        newAmount = 10;
                    }
                    for (int i = 0; i < listFull.size() + 1; i++) {
                        if (i == 0) {
                            deviceNameArray = listFull.get(i).getDeviceName();
                            locationArray = listFull.get(i).getLocation();
                            imageArray = listFull.get(i).getImageFirebase();
//                            imageList.add(listFull.get(i).getImage());

                        } else {
                            //optimize code at final sprint
                            if (i == listFull.size()) {
                                feedbackId = listFull.get(i - 1).getFeedbackId();
                                date = listFull.get(i - 1).getDate();
                                statusId = listFull.get(i - 1).getStatusId();
                                statusName = listFull.get(i - 1).getStatusName();
//                                listAll.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                listAll.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                break;
                            }
                            if (!listFull.get(i).getFeedbackId().equals(listFull.get(i - 1).getFeedbackId())) {
                                feedbackId = listFull.get(i - 1).getFeedbackId();
                                date = listFull.get(i - 1).getDate();
                                statusId = listFull.get(i - 1).getStatusId();
                                statusName = listFull.get(i - 1).getStatusName();
                                listAll.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                deviceNameArray = listFull.get(i).getDeviceName();
                                locationArray = listFull.get(i).getLocation();
                                imageArray = listFull.get(i).getImageFirebase();
//                                imageList.add(listFull.get(i).getImage());

                            } else {
                                deviceNameArray = deviceNameArray.concat(", ");
                                deviceNameArray = deviceNameArray.concat(listFull.get(i).getDeviceName());
                                locationArray = locationArray.concat(", ");
                                locationArray = locationArray.concat(listFull.get(i).getLocation());
                                imageArray = imageArray.concat(";");
                                imageArray = imageArray.concat(listFull.get(i).getImageFirebase());
//                                imageList.add(listFull.get(i).getImage());

                            }
                        }
                    }
                    request.setAttribute("COUNT_FLAG_ALL", newAmount);
                }

                // list DONE
                if (!listFullDone.isEmpty()) {
                    List<UserHistoryDTO> listDoneCheck = dao.getListFeedbackDoneForUserNextCheck(user.getUserID(), listFullDone.size());
                    int count_flag = 0;
                    for (UserHistoryDTO userHistory : listDoneCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listFullDone.get(listFullDone.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    int newAmount;
                    if (count_flag > 0) {
                        newAmount = listFullDone.size() + count_flag;
                        listFullDone = dao.getListFeedbackDoneForUserNext(user.getUserID(), 0, newAmount);
                    } else {
                        newAmount = 10;
                    }
                    for (int i = 0; i < listFullDone.size() + 1; i++) {
                        if (i == 0) {
                            deviceNameArray = listFullDone.get(i).getDeviceName();
                            locationArray = listFullDone.get(i).getLocation();
                            imageArray = listFullDone.get(i).getImageFirebase();
//                            imageList.add(listFullDone.get(i).getImage());

                        } else {
                            //optimize code at final sprint
                            if (i == listFullDone.size()) {
                                feedbackId = listFullDone.get(i - 1).getFeedbackId();
                                date = listFullDone.get(i - 1).getDate();
                                statusId = listFullDone.get(i - 1).getStatusId();
                                statusName = listFullDone.get(i - 1).getStatusName();
                                listDone.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                break;
                            }
                            if (!listFullDone.get(i).getFeedbackId().equals(listFullDone.get(i - 1).getFeedbackId())) {
                                feedbackId = listFullDone.get(i - 1).getFeedbackId();
                                date = listFullDone.get(i - 1).getDate();
                                statusId = listFullDone.get(i - 1).getStatusId();
                                statusName = listFullDone.get(i - 1).getStatusName();
                                listDone.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                deviceNameArray = listFullDone.get(i).getDeviceName();
                                locationArray = listFullDone.get(i).getLocation();
                                imageArray = listFullDone.get(i).getImageFirebase();
//                                imageList.add(listFullDone.get(i).getImage());

                            } else {
                                deviceNameArray = deviceNameArray.concat(", ");
                                deviceNameArray = deviceNameArray.concat(listFullDone.get(i).getDeviceName());
                                locationArray = locationArray.concat(", ");
                                locationArray = locationArray.concat(listFullDone.get(i).getLocation());
                                imageArray = imageArray.concat(";");
                                imageArray = imageArray.concat(listFullDone.get(i).getImageFirebase());
//                                imageList.add(listFullDone.get(i).getImage());

                            }
                        }
                    }
                    request.setAttribute("COUNT_FLAG_DONE", newAmount);
                }

                // list ON-GOING
                if (!listFullOngoing.isEmpty()) {
                    List<UserHistoryDTO> listOnGoingCheck = dao.getListFeedbackOnGoingForUserNextCheck(user.getUserID(), listFullOngoing.size());
                    int count_flag = 0;
                    for (UserHistoryDTO userHistory : listOnGoingCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listFullOngoing.get(listFullOngoing.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    int newAmount;
                    if (count_flag > 0) {
                        newAmount = listFullOngoing.size() + count_flag;
                        listFullOngoing = dao.getListFeedbackOnGoingForUserNext(user.getUserID(), 0, newAmount);
                    } else {
                        newAmount = 10;
                    }
                    for (int i = 0; i < listFullOngoing.size() + 1; i++) {
                        if (i == 0) {
                            deviceNameArray = listFullOngoing.get(i).getDeviceName();
                            locationArray = listFullOngoing.get(i).getLocation();
                            imageArray = listFullOngoing.get(i).getImageFirebase();
//                            imageList.add(listFullOngoing.get(i).getImage());

                        } else {
                            //optimize code at final sprint
                            if (i == listFullOngoing.size()) {
                                feedbackId = listFullOngoing.get(i - 1).getFeedbackId();
                                date = listFullOngoing.get(i - 1).getDate();
                                statusId = listFullOngoing.get(i - 1).getStatusId();
                                statusName = listFullOngoing.get(i - 1).getStatusName();
                                listOngoing.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                break;
                            }
                            if (!listFullOngoing.get(i).getFeedbackId().equals(listFullOngoing.get(i - 1).getFeedbackId())) {
                                feedbackId = listFullOngoing.get(i - 1).getFeedbackId();
                                date = listFullOngoing.get(i - 1).getDate();
                                statusId = listFullOngoing.get(i - 1).getStatusId();
                                statusName = listFullOngoing.get(i - 1).getStatusName();
                                listOngoing.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                deviceNameArray = listFullOngoing.get(i).getDeviceName();
                                locationArray = listFullOngoing.get(i).getLocation();
                                imageArray = listFullOngoing.get(i).getImageFirebase();
//                                imageList.add(listFullOngoing.get(i).getImage());

                            } else {
                                deviceNameArray = deviceNameArray.concat(", ");
                                deviceNameArray = deviceNameArray.concat(listFullOngoing.get(i).getDeviceName());
                                locationArray = locationArray.concat(", ");
                                locationArray = locationArray.concat(listFullOngoing.get(i).getLocation());
                                imageArray = imageArray.concat(";");
                                imageArray = imageArray.concat(listFullOngoing.get(i).getImageFirebase());
//                                imageList.add(listFullOngoing.get(i).getImage());

                            }
                        }
                    }
                    request.setAttribute("COUNT_FLAG_ONGOING", newAmount);
                }

                // list DECLINE
                if (!listFullDecline.isEmpty()) {
                    List<UserHistoryDTO> listDenyCheck = dao.getListFeedbackDeclineForUserNextCheck(user.getUserID(), listFullDecline.size());
                    int count_flag = 0;
                    for (UserHistoryDTO userHistory : listDenyCheck) {
                        if (userHistory.getFeedbackId().equalsIgnoreCase(listFullDecline.get(listFullDecline.size() - 1).getFeedbackId())) {
                            count_flag++;
                        }
                    }
                    int newAmount = 0;
                    if (count_flag > 0) {
                        newAmount = listFullDecline.size() + count_flag;
                        listFullDecline = dao.getListFeedbackDeclineForUserNext(user.getUserID(), 0, newAmount);
                    } else {
                        newAmount = 10;
                    }
                    for (int i = 0; i < listDecline.size() + 1; i++) {
                        if (i == 0) {
                            deviceNameArray = listDecline.get(i).getDeviceName();
                            locationArray = listDecline.get(i).getLocation();
                            imageArray = listDecline.get(i).getImageFirebase();
//                            imageList.add(listDecline.get(i).getImage());

                        } else {
                            //optimize code at final sprint
                            if (i == listDecline.size()) {
                                feedbackId = listDecline.get(i - 1).getFeedbackId();
                                date = listDecline.get(i - 1).getDate();
                                statusId = listDecline.get(i - 1).getStatusId();
                                statusName = listDecline.get(i - 1).getStatusName();
                                listDecline.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                break;
                            }
                            if (!listDecline.get(i).getFeedbackId().equals(listDecline.get(i - 1).getFeedbackId())) {
                                feedbackId = listDecline.get(i - 1).getFeedbackId();
                                date = listDecline.get(i - 1).getDate();
                                statusId = listDecline.get(i - 1).getStatusId();
                                statusName = listDecline.get(i - 1).getStatusName();
                                listDecline.add(new UserHistoryDTO(feedbackId, date, "", deviceNameArray, locationArray, statusName, statusId, imageArray));
                                deviceNameArray = "";
                                locationArray = "";
                                imageArray = "";
                                imageList = new ArrayList<String>();
                                deviceNameArray = listDecline.get(i).getDeviceName();
                                locationArray = listDecline.get(i).getLocation();
                                imageArray = listDecline.get(i).getImageFirebase();
//                                imageList.add(listDecline.get(i).getImage());

                            } else {
                                deviceNameArray = deviceNameArray.concat(", ");
                                deviceNameArray = deviceNameArray.concat(listDecline.get(i).getDeviceName());
                                locationArray = locationArray.concat(", ");
                                locationArray = locationArray.concat(listDecline.get(i).getLocation());
                                imageArray = imageArray.concat(";");
                                imageArray = imageArray.concat(listDecline.get(i).getImageFirebase());
//                                imageList.add(listDecline.get(i).getImage());

                            }
                        }
                    }
                    request.setAttribute("COUNT_FLAG_DECLINE", newAmount);
                }

                session.setAttribute("HISTORY_ALL", listAll);
                session.setAttribute("HISTORY_DONE", listDone);
                session.setAttribute("HISTORY_DENY", listDecline);
                session.setAttribute("HISTORY_ONGOING", listOngoing);
                session.setAttribute("LIST_DONE_COUNT", dao.countFeedbackDoneForStudent(user.getUserID()));
                String style_pipe = (String) request.getAttribute("STYLE_PIPE");
                String style_list = (String) request.getAttribute("STYLE_LIST");
                if (style_list == null && style_pipe == null) {
                    request.setAttribute("STYLE_PIPE", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }
                if (style_pipe != null && style_list == null) {
                    request.setAttribute("STYLE_PIPE", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }
                if (style_pipe == null && style_list != null) {
                    request.setAttribute("STYLE_LIST", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }

                url = SUCCESS;
            } else {
                url = SUCCESS;
                String style_pipe = (String) request.getAttribute("STYLE_PIPE");
                String style_list = (String) request.getAttribute("STYLE_LIST");
                if (style_list == null && style_pipe == null) {
                    request.setAttribute("STYLE_PIPE", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }
                if (style_pipe != null && style_list == null) {
                    request.setAttribute("STYLE_PIPE", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }
                if (style_pipe == null && style_list != null) {
                    request.setAttribute("STYLE_LIST", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }
            }
        } catch (Exception e) {
            log("Error at ShowEmployeeController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
