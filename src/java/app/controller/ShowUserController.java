/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.users.UserDAO;
import app.users.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HieuTran
 */
public class ShowUserController extends HttpServlet {

    private static final String SUCCESS = "ManagerViewUser.jsp";
    private static final String ERROR = "error.jsp";

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
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            //List user
            List<UserDTO> listAll = dao.showAllUserAsc();
            session.setAttribute("LIST_ALL_USER", listAll);

            List<UserDTO> listStudent = dao.showAllStudentAsc();
            session.setAttribute("LIST_ALL_STUDENT", listStudent);

            List<UserDTO> listEmployee = dao.showAllEmployeeAsc();
            session.setAttribute("LIST_ALL_EMPLOYEE", listEmployee);

            List<UserDTO> listUserActive = dao.showAllListActiveUserAsc();
            session.setAttribute("LIST_ALL_ACTIVE", listUserActive);

            List<UserDTO> listUserInactive = dao.showAllListInactiveUserAsc();
            session.setAttribute("LIST_ALL_INACTIVE", listUserInactive);
            
            // Phần của front-end nhé            
            String listStyle = request.getParameter("style_flag");
            if (listStyle != null) {
                switch (listStyle) {
                    case "all":
                        request.setAttribute("STYLE_LIST_ALL", "active");
                        break;
                    case "student":
                        request.setAttribute("STYLE_LIST_STUDENT", "active");
                        break;
                    case "employee":
                        request.setAttribute("STYLE_LIST_EMPLOYEE", "active");
                        break;
                    case "active":
                        request.setAttribute("STYLE_LIST_ACTIVE", "active");
                        break;
                    default:
                        request.setAttribute("STYLE_LIST_INACTIVE", "active");
                        break;
                }
            } else {
                request.setAttribute("STYLE_LIST_ALL", "active");
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ShowEmployeeController" + e.toString());
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
