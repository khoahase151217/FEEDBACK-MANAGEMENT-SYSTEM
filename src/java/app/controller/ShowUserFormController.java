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
 * @author Admin
 */
public class ShowUserFormController extends HttpServlet {

    private static final String ERROR = "#";
    private static final String USER_PAGE = "ShowFacilityStudentController";
    private static final String USER_PAGE_SEARCH = "SearchUserFeedbackController";
    private static final String ADMIN_PAGE = "ShowFeedBackController";
    private static final String ADMIN_USER_PAGE = "ShowUserController";
    private static final String ADMIN_FACILITY_PAGE = "ShowFacilitiesController";
    private static final String ADMIN_STATICTIS_PAGE = "FacilityStatisticController";
    private static final String EMPLOYEE_PAGE = "ShowFeedbackForEmpController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            String user = (String) request.getParameter("user");
            UserDTO login_user = new UserDTO();
            if(user==null){
                user=(String) request.getAttribute("user");
            }
            switch (user) {
                case "admin":
                    login_user = (UserDTO) session.getAttribute("LOGIN_ADMIN");
                    break;
                case "user":
                    login_user = (UserDTO) session.getAttribute("LOGIN_USER");
                    break;
                default:
                    login_user = (UserDTO) session.getAttribute("LOGIN_EMP");
                    break;
            }
            String userID = login_user.getUserID();
            String password = login_user.getPassword();
            String fullName = login_user.getFullName();
            String image = login_user.getImage();
            String email = login_user.getEmail();
            String statusID = login_user.getStatusID();
            String statusName = login_user.getStatusName();
            String roleID = login_user.getRoleID();
            String roleName = login_user.getRoleName();
            String position = request.getParameter("position");
            request.setAttribute("USER_UPDATE", new UserDTO(userID, fullName, password, email, roleID, statusID, image, roleName, statusName));
            switch (login_user.getRoleID()) {
                case "US":
                    request.setAttribute("flag", "open");
                    String style_pipe = request.getParameter("style_pipe");
                    String style_list = request.getParameter("style_list");
                    if (style_pipe == null) {
                        style_pipe = (String) request.getAttribute("style_pipe");
                    }
                    if (style_list == null) {
                        style_list = (String) request.getAttribute("style_list");
                    }
                    if (style_list != null && (style_pipe == null || style_pipe.equals(""))) {
                        request.setAttribute("STYLE_LIST", "active");
                    } else {
                        request.setAttribute("STYLE_PIPE", "active");
                    }
                    url = USER_PAGE;
                    String search = request.getParameter("search");
                    if (!search.equals("")) {
                        url = USER_PAGE_SEARCH;
                    }
                    break;
                case "AD":
                    if (position != null) {
                        request.setAttribute("position", position);
                    } else {
                        position = (String) request.getAttribute("position");
                    }
                    switch (position) {
                        case "adminPage":
                            request.setAttribute("edit_flag", "open");
                            url = ADMIN_PAGE;
                            break;
                        case "ManagerUserPage":
                            request.setAttribute("edit_flag", "open");
                            url = ADMIN_USER_PAGE;
                            break;
                        case "ManagerFacilityPage":
                            request.setAttribute("edit_flag", "open");
                            url = ADMIN_FACILITY_PAGE;
                            break;
                        default:
                            request.setAttribute("edit_flag", "open");
                            url = ADMIN_STATICTIS_PAGE;
                            break;
                    }
                    break;
                default:
                    url = EMPLOYEE_PAGE;
                    request.setAttribute("edit_flag", "open");
                    break;
            }

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
