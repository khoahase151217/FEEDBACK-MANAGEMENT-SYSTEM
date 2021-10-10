/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.feedback.FeedbackDetailDTO;
import app.users.UserDTO;
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
@WebServlet(name = "SearchFeedbackDetailController", urlPatterns = {"/SearchFeedbackDetailController"})
public class SearchFeedbackDetailController extends HttpServlet {

    private static final String ERROR = "welcome.html";
    private static final String SUCCESS = "EmployeeHome.jsp";
    private static final String FULL__NAME_REGEX = "^(?![\\s.]+$)[a-zA-Z\\s.]*$";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        session.setAttribute("DETAIL_LIST", null);
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        //catch trường hợp chuyển trang về mất list
        try {
            String search = request.getParameter("search");
            if (!search.matches(FULL__NAME_REGEX)) {
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            EmployeesDAO dao = new EmployeesDAO();
            List<FeedbackDetailDTO> list = dao.getListFeedback(search,user.getUserID());
            session.setAttribute("DETAIL_LIST", list);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchFeedbackDetailController:" + e.toString());
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
