/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
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
 * @author ASUS
 */
@WebServlet(name = "SearchTaskEmp", urlPatterns = {"/SearchTaskEmpController"})
public class SearchTaskEmpController extends HttpServlet {

    private static final String ERROR = "ShowFeedbackDetailForEmpController";
    private static final String SUCCESS = "ShowFeedbackDetailForEmpController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<FeedbackDTO> list = new ArrayList<>();
        List<FeedbackDTO> historyList = new ArrayList<>();
        HttpSession session = request.getSession();
        FeedbackDAO dao = new FeedbackDAO();
        EmployeesDAO dao2 = new EmployeesDAO();
        String url = ERROR;
        String history = "";
        String feedback = "";
        try {
            String search = request.getParameter("search");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String task = request.getParameter("LIST_STYLE_TASK");
            String his = request.getParameter("LIST_STYLE_HISTORY");

            String feedbackID = (String) request.getAttribute("FEEDBACK_ID");
            String historyID = request.getParameter("history");

            if (task != null && his == null) {
                list = dao.searchListFeedback(user.getUserID(), search);
            } else {
                historyList = dao.searchHistoryFeedback(user.getUserID(), search);
            }

            if (!historyList.isEmpty() && historyID == null) {
                history = historyList.get(0).getFeedbackID();
            } else if (!list.isEmpty() && historyID != null) {
                history = historyID;
            }
            if (!list.isEmpty() && (feedbackID == null || feedbackID.equals(""))) {
                feedback = list.get(0).getFeedbackID();
            } else if (!list.isEmpty() && feedbackID != null) {
                feedback = feedbackID;
            }

            session.setAttribute("HISTORY", history);
            session.setAttribute("FEEDBACK", feedback);
            session.setAttribute("LIST_HISTORY", historyList);
            session.setAttribute("LIST_FEEDBACK", list);
            session.setAttribute("COUNT", list.size());
            url = SUCCESS;
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
