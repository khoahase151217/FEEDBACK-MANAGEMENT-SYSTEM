/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDetailDTO;
import app.users.UserDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeclineFeedbackDetailController", urlPatterns = {"/DeclineFeedbackDetailController"})
public class DeclineFeedbackDetailController extends HttpServlet {

    private static final String FEEDBACK = "ShowFeedBackController";
    private static final String DETAIL = "ShowDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FEEDBACK;
        try {
            FeedbackDAO dao = new FeedbackDAO();
            UserDAO dao2 = new UserDAO();
            String feedbackDetailID = request.getParameter("feedbackDetailID");
            String ReasonFeedback = request.getParameter("declineReason");
            String feedbackID = request.getParameter("feedbackID");
            FeedbackDetailDTO detail = dao.getFeedbackDetailByID(feedbackDetailID);
            String userEmail = dao.getUserEmailByFeedbackID(feedbackID);
            String pipeOrList = request.getParameter("style_flag");
            String style_list_category = request.getParameter("style_list_category");
            if (dao.declineDetail(feedbackDetailID)) {
                dao.insertDeclineRespone(feedbackDetailID, ReasonFeedback);
                String userId = dao.getUserIDByFeedbackDetailID(feedbackDetailID);
                if (dao.checkBanned(userId)) {
                    int level = dao.getWarningLevel(userId);
                    if (level != 4) {
                        dao.increaseLevel(level + 1, userId);
                        dao2.UpdateUserStatusInactive(userId, "inactive");
                        String time = "";
                        switch (level+1) {

                            case 2:
                                time = "1 hours";
                                break;
                            case 3:
                                time = "24 hours";
                                break;
                            default:
                                time = "Permently";
                                break;
                        }
                        dao.sendBanned2(detail, userEmail, ReasonFeedback, level+1, time);

                    }
                } else {
                    if (dao.countBanned(userId) == 3) {
                        // sendbanned2
                        dao.sendBanned2(detail, userEmail, ReasonFeedback, 1, "5 minutes");

                        dao.insertWarning(userId);
                        dao2.UpdateUserStatusInactive(userId, "inactive");
                    } else {
                       dao.sendBanned(detail, userEmail, ReasonFeedback);
                    }
                }
                List<String> roleIDList = dao.getRoleID(feedbackID);
                if (dao.countInactiveDetail(feedbackID) == 0) {
                    dao.updateInactive(feedbackID);
                    if (pipeOrList.equalsIgnoreCase("pipe")) {
                        request.setAttribute("STYLE_PIPE", "active");
                    } else {
                        request.setAttribute("STYLE_LIST", "active");
                        switch (style_list_category) {
                            case "all":
                                request.setAttribute("STYLE_LIST_ALL", "active");
                                break;
                            case "pending":
                                request.setAttribute("STYLE_LIST_PENDING", "active");
                                break;
                            case "onGoing":
                                request.setAttribute("STYLE_LIST_ONGOING", "active");
                                break;
                            case "decline":
                                request.setAttribute("STYLE_LIST_DECLINE", "active");
                                break;
                            default:
                                request.setAttribute("STYLE_LIST_DONE", "active");
                                break;
                        }
                    }
                    url = FEEDBACK;
                } else if (roleIDList.isEmpty()) {
                    dao.updateStatusIDFeedback(feedbackID);

                } else {
                    url = DETAIL;
                }
            }
        } catch (Exception e) {
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
