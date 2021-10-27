/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
import app.users.Comparator;
import app.users.UserDTO;
import app.users.UserHistoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HieuTran
 */
public class SearchUserFeedbackController extends HttpServlet {

    private static final String SUCCESS = "UserPage.jsp";
    private static final String ERROR = "UserPage.jsp";
    private static final String FULL__NAME_REGEX = "^(?![\\s.]+$)[a-zA-Z\\s.]*$";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String userID = user.getUserID();
            String search = request.getParameter("search");

            // front-end case [62 - 78]            
            String amount_done = request.getParameter("amount_done");
            String amount_decline = request.getParameter("amount_decline");
            String amount_onGoing = request.getParameter("amount_onGoing");
            String amount_all = request.getParameter("amount_all");
            boolean all_flag = true;
            if (!amount_done.equals("")) {
                request.setAttribute("COUNT_FLAG_DONE", amount_done);
            }
            if (!amount_decline.equals("")) {
                request.setAttribute("COUNT_FLAG_DECLINE", amount_decline);
            }
            if (!amount_onGoing.equals("")) {
                request.setAttribute("COUNT_FLAG_ONGOING", amount_onGoing);
            }
            if (!amount_all.equals("")) {
                request.setAttribute("COUNT_FLAG_ALL", amount_all);
            }
            FeedbackDAO dao = new FeedbackDAO();
            List<UserHistoryDTO> list = dao.getListFeedbackForUser(userID);
            if (!search.matches(FULL__NAME_REGEX)) {
                list = new ArrayList<>();
                session.setAttribute("HISTORY_ALL", list);
                request.setAttribute("STYLE_LIST_ALL", "active");
                request.setAttribute("STYLE_LIST", "active");
            } else {
                if (!list.isEmpty()) {
                    List<String> deviceName = new ArrayList<String>();
                    String deviceNameArray = null;
                    String locationArray = null;
                    List<String> imageList = new ArrayList<String>();

                    List<UserHistoryDTO> listAll = new ArrayList<>();

                    String feedbackId;
                    String date;
                    String statusId;
                    String statusName;
                    for (int i = 0; i < list.size() + 1; i++) {
                        if (i == 0) {
                            deviceNameArray = list.get(i).getDeviceName();
                            deviceName.add(list.get(i).getDeviceName());
                            locationArray = list.get(i).getLocation();
                            imageList.add(list.get(i).getImage());

                        } else {
                            //optimize code at final sprint
                            if (i == list.size()) {
                                feedbackId = list.get(i - 1).getFeedbackId();
                                date = list.get(i - 1).getDate();
                                statusId = list.get(i - 1).getStatusId();
                                statusName = list.get(i - 1).getStatusName();
                                for (String device : deviceName) {
                                    if (device.toUpperCase().contains(search.toUpperCase())) {
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
                                for (String device : deviceName) {
                                    if (device.toUpperCase().contains(search.toUpperCase())) {
                                        listAll.add(new UserHistoryDTO(feedbackId, date, imageList, deviceNameArray, locationArray, statusName, statusId));
                                    }
                                }
                                deviceNameArray = "";
                                locationArray = "";
                                deviceName = new ArrayList<String>();
                                imageList = new ArrayList<String>();
                                deviceName.add(list.get(i).getDeviceName());
                                deviceNameArray = list.get(i).getDeviceName();
                                locationArray = list.get(i).getLocation();
                                imageList.add(list.get(i).getImage());

                            } else {
                                deviceName.add(list.get(i).getDeviceName());
                                deviceNameArray = deviceNameArray.concat(", ");
                                deviceNameArray = deviceNameArray.concat(list.get(i).getDeviceName());
                                locationArray = locationArray.concat(", ");
                                locationArray = locationArray.concat(list.get(i).getLocation());
                                imageList.add(list.get(i).getImage());
                            }
                        }
                    }
                    Set<UserHistoryDTO> historySet = new TreeSet<UserHistoryDTO>(new Comparator());
                    for (UserHistoryDTO history : listAll) {
                        if (all_flag) {
                            if (historySet.size() == 10) {
                                all_flag = false;
                            } else {
                                historySet.add(history);
                            }
                        }
                    }
                    List<UserHistoryDTO> withoutDuplicates = new ArrayList<UserHistoryDTO>(historySet);
                    if (!withoutDuplicates.isEmpty()) {
                        request.setAttribute("FEEDBACKID_FROM_SEARCH", withoutDuplicates.get(withoutDuplicates.size() - 1).getFeedbackId());
                    }
                    session.setAttribute("HISTORY_ALL", withoutDuplicates);
                    request.setAttribute("SEARCH", search);
                    String style_pipe = (String) request.getAttribute("STYLE_PIPE");
                    String style_list = (String) request.getAttribute("STYLE_LIST");
                    if (style_list == null && style_pipe == null) {
                        request.setAttribute("STYLE_LIST", "active");
                    }
                    request.setAttribute("STYLE_LIST_ALL", "active");
                    url = SUCCESS;
                } else {
                    url = SUCCESS;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchUserFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchUserFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
