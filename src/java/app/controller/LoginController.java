/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.users.UserDAO;
import app.users.UserDTO;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HieuTran
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN_PAGE = "ShowFeedBackController";
    private static final String USER_PAGE = "ShowFacilityStudentController";
    private static final String EMPLOYEE_PAGE = "ShowFeedbackForEmpController";

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
            String email = request.getParameter("userName");
            String password = request.getParameter("password");
            FeedbackDAO dao2 = new FeedbackDAO();
            UserDAO dao = new UserDAO();
            UserDTO user = dao.checkLogin(email, password);
            HttpSession session = request.getSession();

            if (user != null) {

                if ("AD".equals(user.getRoleID())) {
                    session.setAttribute("LOGIN_ADMIN", user);

                    url = ADMIN_PAGE;
                } else if ("US".equals(user.getRoleID())) {
                    session.setAttribute("LOGIN_USER", user);

                    url = USER_PAGE;
                } else {
                    session.setAttribute("LOGIN_EMP", user);

                    url = EMPLOYEE_PAGE;
                }

            } else {
                request.setAttribute("ERROR_MESSAGE", "Incorrect UserName or Password ");
                request.setAttribute("flag", null);
                request.setAttribute("INVALID", "invalid");
            }

            if (user.getStatusID().equalsIgnoreCase("inactive")) {

                Date date = dao2.getDateWarning(user.getUserID());
                int level = dao2.getWarningLevel(user.getUserID());
                if (level == 0) {
                    request.setAttribute("flag", null);
                    request.setAttribute("INVALID", "invalid");
                    request.setAttribute("ERROR_MESSAGE", "Your account is not authorized");
                    url = ERROR;
                    return;
                }
                boolean flag = false;
                Calendar c = Calendar.getInstance();
                Date tmpDate;
                Date now = java.util.Calendar.getInstance().getTime();
                c.setTime(date);
                switch (level) {
                    case 1:
                        c.add(Calendar.MINUTE, 5);
                        tmpDate = c.getTime();
                        if (now.compareTo(tmpDate) >= 0) {
                            dao.UpdateUserStatusActive(user.getUserID(), "active");
                            flag = true;
                        }
                        break;
                    case 2:
                        c.add(Calendar.HOUR, 1);
                        tmpDate = c.getTime();
                        if (now.compareTo(tmpDate) >= 0) {
                            dao.UpdateUserStatusActive(user.getUserID(), "active");
                            flag = true;

                        }
                        break;
                    case 3:
                        c.add(Calendar.HOUR, 24);
                        tmpDate = c.getTime();
                        if (now.compareTo(tmpDate) >= 0) {
                            dao.UpdateUserStatusActive(user.getUserID(), "active");
                            flag = true;

                        }
                        break;

                }
                if (flag) {
                    if ("AD".equals(user.getRoleID())) {
                        session.setAttribute("LOGIN_ADMIN", user);
                        url = ADMIN_PAGE;
                    } else if ("US".equals(user.getRoleID())) {
                        session.setAttribute("LOGIN_USER", user);
                        url = USER_PAGE;
                    } else {
                        url = EMPLOYEE_PAGE;
                        session.setAttribute("LOGIN_EMP", user);
                    }
                } else {
                    request.setAttribute("flag", null);
                    request.setAttribute("INVALID", "invalid");
                    request.setAttribute("ERROR_MESSAGE", "Your account is not authorized");
                    url = ERROR;
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
