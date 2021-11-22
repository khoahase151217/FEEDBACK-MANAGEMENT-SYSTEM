/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.response.ResponseDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateFeedbackDeclineController", urlPatterns = {"/UpdateFeedbackDeclineController"})
public class UpdateFeedbackDeclineController extends HttpServlet {

    private static final String SUCCESS = "ShowFeedBackController";
    private static final String ERROR = "##";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String feedbackDetailID = request.getParameter("feedbackDetailID");
            String responseID = request.getParameter("responseID");
            String declineReason = request.getParameter("declineReason");
            FeedbackDAO dao = new FeedbackDAO();
            ResponseDAO dao2 = new ResponseDAO();
            String employeeId = dao2.getEmployeeId(responseID);
            String userId = dao.getUserIDByFeedbackDetailID(feedbackDetailID);
            String feedbackId = dao.getFeedbackIDByFeedbackDetailID2(feedbackDetailID);
            if (dao.updateDecline(feedbackDetailID, userId, feedbackId)) {
                dao2.updateResponseStatus(feedbackDetailID, employeeId);
                if (dao2.countDeclineResponse(responseID) != 0) {
                    dao2.updateDeclineResponse(declineReason, responseID);
                    url = SUCCESS;
                } else {
                    dao2.insertDeclinedResponse(declineReason, responseID);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at UpdateFeedbackDeclineController:" + e.toString());
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
