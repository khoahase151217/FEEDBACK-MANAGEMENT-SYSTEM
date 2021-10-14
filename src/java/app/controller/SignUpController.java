/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.users.UserDAO;
import app.users.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HieuTran
 */
public class SignUpController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "ShowFacilityStudentController";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    private static final String FULL__NAME_REGEX ="^[A-Za-z.-]+(\\s*[A-Za-z.-]+)*$";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            request.setAttribute("FULLNAME_MESSAGE", null);
            request.setAttribute("PASSWORD_MESSAGE", null);
            request.setAttribute("CONFIRM_PASSWORD_MESSAGE", null);
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            request.setAttribute("email", user.getEmail());
            boolean check = true;
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (fullName == null || ((fullName.length() < 5 || fullName.length() > 40)) || !fullName.matches(FULL__NAME_REGEX)) {
                request.setAttribute("FULLNAME_MESSAGE", "Please choose a correct format for fullname");
                request.setAttribute("flag", "sign-up-mode");
                request.setAttribute("INVALID", "invalid");
                request.setAttribute("FULLNAME_FLAG", "invalid");
                check = false;

            }
            if (!password.matches(PASSWORD_REGEX) && check != false) {
                request.setAttribute("PASSWORD_MESSAGE", "Please choose a stronger password. Try a mix of letters, numbers and symbols.");
                request.setAttribute("flag", "sign-up-mode");
                request.setAttribute("INVALID", "invalid");
                request.setAttribute("PASSWORD_FLAG", "invalid");
                check = false;

            }
            if (!password.equals(confirmPassword) && check != false) {
                request.setAttribute("CONFIRM_PASSWORD_MESSAGE", "Your password is mismatch");
                request.setAttribute("flag", "sign-up-mode");
                request.setAttribute("INVALID", "invalid");
                request.setAttribute("CONFIRM_PASSWORD_FLAG", "invalid");
                check = false;

            }
            if (check) {
                UserDAO dao = new UserDAO();
                if (dao.signUp(new UserDTO(fullName, password, user.getEmail(), "US", "active", user.getImage()))) {
                    url = SUCCESS;
                    UserDTO member= dao.GetMemberSignup(new UserDTO(fullName, password, user.getEmail(), "US", "active", user.getImage()));
                    session.setAttribute("LOGIN_USER", member);
                }
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
