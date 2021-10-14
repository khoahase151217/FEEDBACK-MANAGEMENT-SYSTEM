/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static final String ERROR="##";
    private static final String SUCCESS="##";
    
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
        String url=ERROR;
        try {
            String feedbackDetailID = request.getParameter("feedbackDetailID");
            FeedbackDAO dao = new FeedbackDAO();
            String pipeStyle = request.getParameter("stylePipe");
            String listStyle = request.getParameter("styleList");
            if (!pipeStyle.equals("")) {
                request.setAttribute("STYLE_PIPE", "active");
            }

            if (!listStyle.equals("")) {
                request.setAttribute("STYLE_LIST", "active");
            }

            String categoryAll = request.getParameter("style_list_category_all");
            String categoryPending = request.getParameter("style_list_category_pending");
            String categoryOnGoing = request.getParameter("style_list_category_onGoing");
            if (dao.declineDetail(feedbackDetailID)) {
                if (!categoryAll.equals("")) {
                    request.setAttribute("STYLE_LIST_ALL", "active");
                }
                if (!categoryPending.equals("")) {
                    request.setAttribute("STYLE_LIST_PENDING", "active");
                }
                if (!categoryOnGoing.equals("")) {
                    request.setAttribute("STYLE_LIST_ONGOING", "active");
                }
                url = SUCCESS;
            }
            if(dao.declineDetail(feedbackDetailID)){
                url=SUCCESS;
            }
        } catch (Exception e) {
        }finally{
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
