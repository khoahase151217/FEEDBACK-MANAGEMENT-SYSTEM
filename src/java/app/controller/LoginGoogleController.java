/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.users.GooglePojo;
import app.users.UserDAO;
import app.users.UserDTO;
import app.utils.GoogleUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginGoogleController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String ADMIN = "ShowFeedbackController";
    private static final String STUDENT = "ShowFacilityStudentController";
    private static final String EMPLOYEE = "ShowFeedbackForEmpController";
    private static final String ERROR = "login.jsp";
    private static final String SIGN_UP = "login.jsp";

    public LoginGoogleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String url = ERROR;
        HttpSession session = request.getSession();
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            request.setAttribute("id", googlePojo.getId());
            request.setAttribute("email", googlePojo.getEmail());
            session.setAttribute("picture", googlePojo.getPicture());
            if (googlePojo.getEmail().contains("fpt.edu.vn") == false) {
                //  Important Validation Attribute             
                request.setAttribute("ERROR_MESSAGE", "Your Email is not supported");
                request.setAttribute("flag", null);
                request.setAttribute("INVALID", "invalid");
                RequestDispatcher dis = request.getRequestDispatcher(url);
                dis.forward(request, response);
                return;
            }
            UserDAO dao = new UserDAO();
            try {
                UserDTO user = dao.checkLoginGoogle(googlePojo.getEmail());
                if (user == null) {
                    session.setAttribute("LOGIN_USER", new UserDTO("", "", googlePojo.getEmail(), "", "", googlePojo.getPicture()));
                    request.setAttribute("flag", "sign-up-mode");
                    url = SIGN_UP;
                } else {
                    boolean checkStatus = user.getStatusID().equalsIgnoreCase("inactive");
                    if (checkStatus != true) {
                        session.setAttribute("LOGIN_USER", user);
                        if (user.getRoleID().equals("US")) {
                            url = STUDENT;
                        } else if (user.getRoleID().equals("AD")) {
                            url = ADMIN;
                        } else {
                            url = EMPLOYEE;
                        }
                    } else {
                        session.setAttribute("MESSAGE", "inactive-mode");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginGoogleController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
