/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.users.GooglePojo;
import app.users.UserDAO;
import app.users.UserDTO;
import app.utils.GoogleUtils;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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
            FeedbackDAO dao2 = new FeedbackDAO();
            UserDAO dao = new UserDAO();
            try {
                UserDTO user = dao.checkLoginGoogle(googlePojo.getEmail());
                if (user == null) {
                    session.setAttribute("LOGIN_USER", new UserDTO("", "", googlePojo.getEmail(), "", "", googlePojo.getPicture()));
                    request.setAttribute("flag", "sign-up-mode");
                    url = SIGN_UP;
                } else {
                    boolean checkStatus = user.getStatusID().equalsIgnoreCase("inactive");
                    if (checkStatus == true) {
                        Date date = dao2.getDateWarning(user.getUserID());
                        int level = dao2.getWarningLevel(user.getUserID());
                        if(level == 0){
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
                                url = ADMIN;
                            } else if ("US".equals(user.getRoleID())) {
                                session.setAttribute("LOGIN_USER", user);
                                url = STUDENT;
                            } else {
                                url = EMPLOYEE;
                                session.setAttribute("LOGIN_EMP", user);
                            }
                        } else {
                            request.setAttribute("flag", null);
                            request.setAttribute("INVALID", "invalid");
                            request.setAttribute("ERROR_MESSAGE", "Your account is not authorized");
                            url = ERROR;
                        }
                    }else{
                        if ("AD".equals(user.getRoleID())) {
                                session.setAttribute("LOGIN_ADMIN", user);
                                url = ADMIN;
                            } else if ("US".equals(user.getRoleID())) {
                                session.setAttribute("LOGIN_USER", user);
                                url = STUDENT;
                            } else {
                                url = EMPLOYEE;
                                session.setAttribute("LOGIN_EMP", user);
                            }
                    }
                }
            } catch (Exception ex) {
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
