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

    private static final String ERROR = "ShowFeedbackDetailForEmpController";//ShowFeedbackDetailForEmpController";
    private static final String SUCCESS = "ShowFeedbackDetailForEmpController";
    private static final String FULL__NAME_REGEX = "[A-Za-z . ]+(\\\\s*[A-Za-z .-]+)*$";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<FeedbackDTO> list = null;
        List<FeedbackDTO> historyList = null;
        HttpSession session = request.getSession();
        FeedbackDAO dao = new FeedbackDAO();
        EmployeesDAO dao2 = new EmployeesDAO();
        String url = ERROR;
        String history = "";
        String feedback = "";
        String feedbackDetailActive = request.getParameter("FEEDBACK_DETAIL_ACTIVE");
        String historyActive = request.getParameter("HISTORY_DETAIL_ACTIVE");
        try {
            String search = request.getParameter("search");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_EMP");
            String task = request.getParameter("LIST_STYLE_TASK");
            String his = request.getParameter("LIST_STYLE_HISTORY");
            
            if (search.equals("")) {
                list = dao2.showListFeedback(user.getUserID());
                historyList = dao2.showHistoryFeedback(user.getUserID());
                //active
                
                session.setAttribute("HISTORY", historyList.get(0).getFeedbackID());
                session.setAttribute("FEEDBACK", list.get(0).getFeedbackID());
                //
                session.setAttribute("LIST_HISTORY", historyList);
                session.setAttribute("LIST_FEEDBACK", list);
                session.setAttribute("SEARCH", search);
                url = SUCCESS;
                return;
            }else if (!search.matches(FULL__NAME_REGEX) && task != null) { //search sai
                //task rỗng
                list = new ArrayList<>();
                session.setAttribute("LIST_FEEDBACK", list);

                //history full
                historyList = dao2.showHistoryFeedback(user.getUserID());
                session.setAttribute("LIST_HISTORY", historyList);
                // active history
                session.setAttribute("HISTORY", historyList.get(0).getFeedbackID());
                session.setAttribute("SEARCH", search);
                
                //request.getRequestDispatcher(url).forward(request, response);
                return;
            } //else
            if (!search.matches(FULL__NAME_REGEX) && task == null) {
                //task full
                list = dao2.showListFeedback(user.getUserID());
                session.setAttribute("LIST_FEEDBACK", list);
                //history rỗng
                historyList = new ArrayList<>();
                session.setAttribute("LIST_HISTORY", historyList);
                //active task
                session.setAttribute("FEEDBACK", list.get(0).getFeedbackID());
                session.setAttribute("SEARCH", search);
                return;
            }

            if (task != null && his == null) {
                list = dao.searchListFeedback(user.getUserID(), search);
            } else {
                historyList = dao.searchHistoryFeedback(user.getUserID(), search);
            }

            if (historyList != null) {
                if (!historyList.isEmpty()) {
                    history = historyList.get(0).getFeedbackID();
                }
                session.setAttribute("HISTORY", history);
                list = dao2.showListFeedback(user.getUserID());
                if (!feedbackDetailActive.equals("")) {
                    session.setAttribute("FEEDBACK", feedbackDetailActive);
                } else {
                    session.setAttribute("FEEDBACK", list.get(0).getFeedbackID());
                }

                request.setAttribute("LIST_STYLE_HISTORY", "active");
            } else {
                if (!list.isEmpty()) {
                    feedback = list.get(0).getFeedbackID();
                }
                session.setAttribute("FEEDBACK", feedback);
                historyList = dao2.showHistoryFeedback(user.getUserID());
                if (!historyActive.equals("")) {
                    session.setAttribute("HISTORY", historyActive);
                } else {
                    session.setAttribute("HISTORY", historyList.get(0).getFeedbackID());
                }
            }
            session.setAttribute("LIST_HISTORY", historyList);
            session.setAttribute("LIST_FEEDBACK", list);
            session.setAttribute("SEARCH", search);
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
