/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.feedback.FeedbackDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ShowFeedbackResponeForManagerController", urlPatterns = {"/ShowFeedbackResponeForManagerController"})
public class ShowFeedbackResponeForManagerController extends HttpServlet {

    private static final String ERROR = "ShowFeedbackResponeDetailForManagerController";
    private static final String SUCCESS = "ShowFeedbackResponeDetailForManagerController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<FeedbackDTO> listRespone;
        HttpSession session = request.getSession();
        String url = ERROR;
        try {
            EmployeesDAO dao = new EmployeesDAO();
            listRespone = dao.showListFeedbackResponse();
            String feedbackResponeID = "";

            if (!listRespone.isEmpty()) {
                feedbackResponeID = listRespone.get(0).getFeedbackID();
            }
            request.setAttribute("FEEDBACK_RESPONE_ID", feedbackResponeID);
            session.setAttribute("FEEDBACK_RESPONE_LIST", listRespone);
            session.setAttribute("COUNT", listRespone.size());
            url = SUCCESS;

        } catch (Exception e) {
            log("Error at ShowFeedbackResponeForManagerController:" + e.toString());
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
