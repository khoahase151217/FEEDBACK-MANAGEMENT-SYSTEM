/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AssignController", urlPatterns = {"/AssignController"})
public class AssignController extends HttpServlet {

    private static final String SUCCESS = "ShowEmployeeActiveController";
    private static final String ERROR = "managerDetail.jsp";

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
            HttpSession session = request.getSession(true);
            FeedbackDAO dao = new FeedbackDAO();
            String feedbackDetailID = request.getParameter("feedbackDetailID");
            String userId = request.getParameter("employee");
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
            if (dao.assignEmployee(feedbackDetailID, userId)) {
                
                String feedbackID = request.getParameter("feedbackID");
                List<String> roleIDList = dao.getRoleID(feedbackID);
                if (roleIDList.isEmpty()) {
                    dao.updateStatusIDFeedback(feedbackID);
                }

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
        } catch (Exception e) {
            log("Error at SearchController" + e.toString());
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
