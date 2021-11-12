package app.controller;

import app.users.UserDAO;
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
@WebServlet(name = "SearchStudentController", urlPatterns = {"/SearchStudentController"})
public class SearchStudentController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ManagerViewUser.jsp";
    private static final String FULL__NAME_REGEX = "[A-Za-z . ]+(\\\\s*[A-Za-z .-]+)*$";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        session.setAttribute("STUDENT_LIST", null);
        List<UserDTO> list;
        UserDAO dao = new UserDAO();
        //catch trường hợp chuyển trang về mất list
        try {
            String search = request.getParameter("search");
            if (search.equals("")) {
                list = dao.showAllUserAsc();
                session.setAttribute("LIST_ALL_USER", list);
                request.setAttribute("SEARCH", search);
                url = SUCCESS;
            } else if (!search.matches(FULL__NAME_REGEX)) { //sai vo day
                list = new ArrayList<>();
                session.setAttribute("LIST_ALL_USER", list);
                request.setAttribute("SEARCH", search);
                request.setAttribute("STYLE_LIST", "active");
                request.setAttribute("STYLE_LIST_ALL", "active");
                //request.getRequestDispatcher(url).forward(request, response);
                // list = dao.showAllUserAsc();
                url = SUCCESS;
                //return;
            } else {
                list = dao.getListStudent(search);
                session.setAttribute("LIST_ALL_USER", list);
                request.setAttribute("SEARCH", search);
                url = SUCCESS;
            }

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
            log("Error at SearchStudentController:" + e.toString());
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
