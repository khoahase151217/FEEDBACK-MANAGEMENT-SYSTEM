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
            List<UserHistoryDTO> list = dao.getListFeedbackForUser(user.getUserID());
            if (!list.isEmpty()) {
                String deviceNameArray = null;
                String locationArray = null;
                List<String> imageList = new ArrayList<String>();

                List<UserHistoryDTO> listAll = new ArrayList<>();
                List<UserHistoryDTO> listDone = new ArrayList<>();
                List<UserHistoryDTO> listOngoing = new ArrayList<>();
                List<UserHistoryDTO> listDecline = new ArrayList<>();

                String feedbackId;
                String date;
                String statusId;
                String statusName;
                int count = 0;
                for (int i = 0; i < list.size() + 1; i++) {
                    if (i == 0) {
                        deviceNameArray = list.get(i).getDeviceName();
                        locationArray = list.get(i).getLocation();
                        imageList.add(list.get(i).getImage());

                    } else {
                        //optimize code at final sprint
                        if (i == list.size()) {
                            feedbackId = list.get(i - 1).getFeedbackId();
                            date = list.get(i - 1).getDate();
                            statusId = list.get(i - 1).getStatusId();
                            statusName = list.get(i - 1).getStatusName();
                            switch (statusId) {
                                case "done":
                                    if (listDone.size() == 10) {
                                        continue;
                                    }
                                    listDone.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                                case "decline":
                                    if (listDecline.size() == 10) {
                                        continue;
                                    }
                                    listDecline.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                                case "onGoing":
                                    if (listOngoing.size() == 10) {
                                        continue;
                                    }
                                    listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                                default:
                                    if (listOngoing.size() == 10) {
                                        continue;
                                    }
                                    listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                            }
                            if (listAll.size() == 10) {
                                continue;
                            }
                            listAll.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                            break;
                        }
                        if (!list.get(i).getFeedbackId().equals(list.get(i - 1).getFeedbackId())) {
                            feedbackId = list.get(i - 1).getFeedbackId();
                            date = list.get(i - 1).getDate();
                            statusId = list.get(i - 1).getStatusId();
                            statusName = list.get(i - 1).getStatusName();
                            switch (statusId) {
                                case "done":
                                    if (listDone.size() == 10) {
                                        continue;
                                    }
                                    listDone.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                                case "decline":
                                    if (listDecline.size() == 10) {
                                        continue;
                                    }
                                    listDecline.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                                case "onGoing":
                                    if (listOngoing.size() == 10) {
                                        continue;
                                    }
                                    listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                                default:
                                    if (listOngoing.size() == 10) {
                                        continue;
                                    }
                                    listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    break;
                            }
                            if (listAll.size() == 10) {
                                continue;
                            } else {
                                listAll.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                deviceNameArray = "";
                                locationArray = "";
                                imageList = new ArrayList<String>();
                                deviceNameArray = list.get(i).getDeviceName();
                                locationArray = list.get(i).getLocation();
                                imageList.add(list.get(i).getImage());
                            }

                        } else {
                            deviceNameArray = deviceNameArray.concat(", ");
                            deviceNameArray = deviceNameArray.concat(list.get(i).getDeviceName());
                            locationArray = locationArray.concat(", ");
                            locationArray = locationArray.concat(list.get(i).getLocation());
                            imageList.add(list.get(i).getImage());

                        }
                    }
                }

                session.setAttribute("HISTORY_ALL", listAll);
                session.setAttribute("HISTORY_DONE", listDone);
                session.setAttribute("HISTORY_DENY", listDecline);
                session.setAttribute("HISTORY_ONGOING", listOngoing);
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
