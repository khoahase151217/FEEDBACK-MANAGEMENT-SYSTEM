/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SIGN_UP = "SignUpController";
    private static final String SEND = "SendFeedbackController";
    private static final String SHOW_FEEDBACK = "ShowFeedbackController";
    private static final String SHOW_DETAIL = "ShowDetailController";
    private static final String SHOW_EMP = "ShowEmployeeController";
    private static final String SEARCH_EMP = "SearchEmployeeController";
    private static final String SHOW_STUDENT = "ShowStudentController";
    private static final String SEARCH_STUDENT = "SearchStudentController";

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
            String action = request.getParameter("action");
            if (null != action) {
                switch (action) {
                    case "Login":
                        url = LOGIN;
                        break;
                    case "Sign Up":
                        url = SIGN_UP;
                        break;
                    case "Send Report":
                        url = SEND;
                        break;
                    case "ShowEmployee":
                        url = SHOW_EMP;
                        break;
                    case "ShowStudent":
                        url = SHOW_STUDENT;
                        break;
                    case "SearchStudent":
                        url = SEARCH_STUDENT;
                        break;
                    case "SearchEmployee":
                        url = SEARCH_EMP;
                        break;
                    case "ShowFeedback":
                        url = SHOW_FEEDBACK;
                        break;
                    case "ShowDetail":
                        url = SHOW_DETAIL;
                        break;
                    default:
                        HttpSession session = request.getSession();
                        session.setAttribute("ERROR_MESSAGE", "Function is not avaiable!");
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at Main Controller:" + e.toString());
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
