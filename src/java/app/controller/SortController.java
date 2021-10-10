/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.facility.FacilityDAO;
import app.facility.FacilityDTO;
import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
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
public class SortController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ManagerViewUser.jsp";
    private static final String SUCCESS2 = "ManagerViewEmployee.jsp";
    private static final String SUCCESS3 = "ManagerViewFB.jsp";
    private static final String SUCCESS4 = "ManagerViewFacility.jsp";
    private static final String SUCCESS5 = "UserViewFB.jsp";

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
            UserDAO dao = new UserDAO();
            FeedbackDAO fbdao = new FeedbackDAO();
            FacilityDAO fdao = new FacilityDAO();
            String sort = request.getParameter("sort");
            List<UserDTO> list;
            List<FeedbackDTO> listFB;
            List<FacilityDTO> listFM;
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO)session.getAttribute("LOGIN_USER");
            switch (sort) {
                //Sort Manager view User
                case "Status":
                    list = dao.sortStatusAsc();
                    if (list != null) {
                        session.setAttribute("STUDENT_LIST", list);
                        url = SUCCESS;
                    }
                    break;
                case "Status desc":
                    list = dao.sortStatusDesc();
                    if (list != null) {
                        session.setAttribute("STUDENT_LIST", list);
                        url = SUCCESS;
                    }
                    break;
                case "Name":
                    list = dao.sortUserNameAsc();
                    if (list != null) {
                        session.setAttribute("STUDENT_LIST", list);
                        url = SUCCESS;
                    }
                    break;
                case "Name desc":
                    list = dao.sortUserNameDesc();
                    if (list != null) {
                        session.setAttribute("STUDENT_LIST", list);
                        url = SUCCESS;
                    }
                    break;
                    //Sort Manager view Employee
                case "NameEP":
                    list = dao.sortEmployeeNameAsc();
                    if (list != null) {
                        session.setAttribute("EMPLOYEE_LIST", list);
                        url = SUCCESS2;
                    }
                    break;
                case "NameEP desc":
                    list = dao.sortEmployeeNameDesc();
                    if (list != null) {
                        session.setAttribute("EMPLOYEE_LIST", list);
                        url = SUCCESS2;
                    }
                    break;
                case "StatusEP":
                    list = dao.sortStatusEmployeeAsc();
                    if (list != null) {
                        session.setAttribute("EMPLOYEE_LIST", list);
                        url = SUCCESS2;
                    }
                    break;
                case "StatusEP desc":
                    list = dao.sortStatusEmployeeDesc();
                    if (list != null) {
                        session.setAttribute("EMPLOYEE_LIST", list);
                        url = SUCCESS2;
                    }
                    break;
                
                    //Sort Manager view FeedBack by date
                    
                case "DateFBM":
                    listFB = fbdao.sortFeedbackDesc();
                    if (listFB != null) {
                        session.setAttribute("FEEDBACK_LIST", listFB);
                        url = SUCCESS3;
                    }
                    break;
                case "DateFBM desc":
                    listFB = fbdao.sortFeedbackDesc();
                    if (listFB != null) {
                        session.setAttribute("FEEDBACK_LIST", listFB);
                        url = SUCCESS3;
                    }
                    break;
                    
                    //Sort Manager view Facilities 
                case "NameFM":
                    listFM = fdao.sortFacilityNameAsc();
                    if (listFM != null) {
                        session.setAttribute("LIST_FACILITIES", listFM);
                        url = SUCCESS4;
                    }
                    break;
                case "NameFM desc":
                    listFM = fdao.sortFacilityNameDesc();
                    if (listFM != null) {
                        session.setAttribute("LIST_FACILITIES", listFM);
                        url = SUCCESS4;
                    }
                    break;
                case "StatusFM":
                    listFM = fdao.sortFacilityStatusAsc();
                    if (listFM != null) {
                        session.setAttribute("LIST_FACILITIES", listFM);
                        url = SUCCESS4;
                    }
                    break;
                case "StatusFM desc":
                    listFM = fdao.sortFacilityStatusDesc();
                    if (listFM != null) {
                        session.setAttribute("LIST_FACILITIES", listFM);
                        url = SUCCESS4;
                    }
                    break;
                case "DateFM":
                    listFM = fdao.sortFacilityDateAsc();
                    if (listFM != null) {
                        session.setAttribute("LIST_FACILITIES", listFM);
                        url = SUCCESS4;
                    }
                    break;
                case "DateFM desc":
                    listFM = fdao.sortFacilityDateDesc();
                    if (listFM != null) {
                        session.setAttribute("LIST_FACILITIES", listFM);
                        url = SUCCESS4;
                    }    
                    break;
                    //Sort User view Feedback
                case "NameFBU":
                    listFB = dao.sortFeedbackAsc(user);
                    if (listFB != null) {
                        session.setAttribute("FEEDBACK_LIST", listFB);
                        url = SUCCESS5;
                    }
                    break;
                case "NameFBU desc":
                    listFB = dao.sortFeedbackDesc(user);
                    if (listFB != null) {
                        session.setAttribute("FEEDBACK_LIST", listFB);
                        url = SUCCESS5;
                    }
                    break;
                default:
                    session.setAttribute("ERROR_MESSAGE", "Function is not avaiable!");
                    break;
            }

        } catch (Exception e) {
            log("Error at Sort Controller:" + e.toString());
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
