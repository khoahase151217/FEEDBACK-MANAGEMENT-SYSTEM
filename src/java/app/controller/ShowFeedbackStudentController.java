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

                //  Front-end case [67- 74]           
                int count_done_flag = 0;
                int count_onGoing_flag = 0;
                int count_decline_flag = 0;
                int count_all_flag = 0;
                boolean all_flag = true;
                boolean done_flag = true;
                boolean decline_flag = true;
                boolean onGoing_flag = true;

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
                                    // Front-end case [92 - 98]  
                                    if (done_flag) {
                                        if (listDone.size() == 10 && feedbackId.equalsIgnoreCase(listDone.get(listDone.size() - 1).getFeedbackId())) {
                                            count_done_flag++;
                                        } else if (listDone.size() == 10 && !feedbackId.equalsIgnoreCase(listDone.get(listDone.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_DONE", i - 1 + count_done_flag);
                                            done_flag = false;
                                        } else {
                                            listDone.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                                case "decline":
                                    // Front-end case [104 - 111]  
                                    if (decline_flag) {
                                        if (listDecline.size() == 10 && feedbackId.equalsIgnoreCase(listDecline.get(listDecline.size() - 1).getFeedbackId())) {
                                            count_decline_flag++;
                                        } else if (listDecline.size() == 10 && !feedbackId.equalsIgnoreCase(listDecline.get(listDecline.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_DECLINE", i - 1 + count_decline_flag);
                                            decline_flag = false;
                                        } else {
                                            listDecline.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                                case "onGoing":
                                    // Front-end case [118 - 125]                                
                                    if (onGoing_flag) {
                                        if (listOngoing.size() == 10 && feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            count_onGoing_flag++;
                                        } else if (listOngoing.size() == 10 && !feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_ONGOING", i - 1 + count_onGoing_flag);
                                            onGoing_flag = false;

                                        } else {
                                            listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                                default:
                                    // Front-end case [132 - 138]                                
                                    if (onGoing_flag) {
                                        if (listOngoing.size() == 10 && feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            count_onGoing_flag++;
                                        } else if (listOngoing.size() == 10 && !feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_ONGOING", i - 1 + count_onGoing_flag);
                                            onGoing_flag = false;
                                        } else {
                                            listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                            }
                            // Front-end case [145 - 151]
                            if (all_flag) {
                                if (listAll.size() == 10 && feedbackId.equalsIgnoreCase(listAll.get(listAll.size() - 1).getFeedbackId())) {
                                    count_all_flag++;
                                } else if (listAll.size() == 10 && !feedbackId.equalsIgnoreCase(listAll.get(listAll.size() - 1).getFeedbackId())) {
                                    request.setAttribute("COUNT_FLAG_ALL", i - 1 + count_all_flag);
                                    all_flag = false;
                                } else {
                                    listAll.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                }

                            }
                            break;
                        }
                        if (!list.get(i).getFeedbackId().equals(list.get(i - 1).getFeedbackId())) {
                            feedbackId = list.get(i - 1).getFeedbackId();
                            date = list.get(i - 1).getDate();
                            statusId = list.get(i - 1).getStatusId();
                            statusName = list.get(i - 1).getStatusName();
                            switch (statusId) {
                                case "done":
                                    // Front-end case [166 - 172]  
                                    if (done_flag) {
                                        if (listDone.size() == 10 && feedbackId.equalsIgnoreCase(listDone.get(listDone.size() - 1).getFeedbackId())) {
                                            count_done_flag++;
                                        } else if (listDone.size() == 10 && !feedbackId.equalsIgnoreCase(listDone.get(listDone.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_DONE", i - 1 + count_done_flag);
                                            done_flag = false;
                                        } else {
                                            listDone.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                                case "decline":
                                    // Front-end case [179 - 185] 
                                    if (decline_flag) {
                                        if (listDecline.size() == 10 && feedbackId.equalsIgnoreCase(listDecline.get(listDecline.size() - 1).getFeedbackId())) {
                                            count_decline_flag++;
                                        } else if (listDecline.size() == 10 && !feedbackId.equalsIgnoreCase(listDecline.get(listDecline.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_DECLINE", i - 1 + count_decline_flag);
                                            decline_flag = false;
                                        } else {
                                            listDecline.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                                case "onGoing":
                                    // Front-end case [192 - 198] 
                                    if (onGoing_flag) {
                                        if (listOngoing.size() == 10 && feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            count_onGoing_flag++;
                                        } else if (listOngoing.size() == 10 && !feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_ONGOING", i - 1 + count_onGoing_flag);
                                            onGoing_flag = false;
                                        } else {
                                            listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                                default:
                                    // Front-end case [205 - 211]   
                                    if (onGoing_flag) {
                                        if (listOngoing.size() == 10 && feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            count_onGoing_flag++;
                                        } else if (listOngoing.size() == 10 && !feedbackId.equalsIgnoreCase(listOngoing.get(listOngoing.size() - 1).getFeedbackId())) {
                                            request.setAttribute("COUNT_FLAG_ONGOING", i - 1 + count_onGoing_flag);
                                            onGoing_flag = false;
                                        } else {
                                            listOngoing.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                        }
                                    }
                                    break;
                            }
                            // Front-end case [218 - 239] 
                            if (all_flag) {
                                if (listAll.size() == 10 && feedbackId.equalsIgnoreCase(listAll.get(listAll.size() - 1).getFeedbackId())) {
                                    count_all_flag++;
                                } else if (listAll.size() == 10 && !feedbackId.equalsIgnoreCase(listAll.get(listAll.size() - 1).getFeedbackId())) {
                                    deviceNameArray = "";
                                    locationArray = "";
                                    imageList = new ArrayList<String>();
                                    deviceNameArray = list.get(i).getDeviceName();
                                    locationArray = list.get(i).getLocation();
                                    imageList.add(list.get(i).getImage());
                                    request.setAttribute("COUNT_FLAG_ALL", i - 1 + count_all_flag);
                                    all_flag = false;
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
                
                // Front-end case [263 - 279] 
                if (listAll.size() < 10) {
                    List<UserHistoryDTO> listAllCheck = dao.getListFeedbackForUserNextFull(user.getUserID(), Integer.parseInt("0"));
                    request.setAttribute("COUNT_FLAG_ALL", listAllCheck.size());
                }
                if (listDone.size() < 10) {
                    List<UserHistoryDTO> listDoneCheck = dao.getListFeedbackDoneForUserNextFull(user.getUserID(), Integer.parseInt("0"));
                    request.setAttribute("COUNT_FLAG_DONE", listDoneCheck.size());
                }
                if (listDecline.size() < 10) {
                    List<UserHistoryDTO> listDeclineCheck = dao.getListFeedbackDeclineForUserNextFull(user.getUserID(), Integer.parseInt("0"));
                    request.setAttribute("COUNT_FLAG_DECLINE", listDeclineCheck.size());
                }
                if (listOngoing.size() < 10) {
                    List<UserHistoryDTO> listOnGoingCheck = dao.getListFeedbackOnGoingForUserNextFull(user.getUserID(), Integer.parseInt("0"));
                    request.setAttribute("COUNT_FLAG_ONGOING", listOnGoingCheck.size());
                }
                
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
