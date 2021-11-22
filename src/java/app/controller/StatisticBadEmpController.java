/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.response.ResponseDTO;
import app.users.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
@WebServlet(name = "StatisticBadEmpController", urlPatterns = {"/StatisticBadEmpController"})
public class StatisticBadEmpController extends HttpServlet {

    private static final String ERROR = "##";
    private static final String SUCCESS = "StatisticBadUSERController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            SimpleDateFormat month_date = new SimpleDateFormat("MMM", Locale.ENGLISH);
            SimpleDateFormat year_date = new SimpleDateFormat("YYYY", Locale.ENGLISH);
            Date date = new Date();
            int listCheck = 0;
            int finalCount = 0;
            String month = month_date.format(date);
            String year = year_date.format(date);
            EmployeesDAO dao = new EmployeesDAO();
            List<UserDTO> list = new ArrayList<UserDTO>();
            List<UserDTO> listRating = new ArrayList<UserDTO>();
            List<ResponseDTO> listRes = new ArrayList<ResponseDTO>();

            //Check list base rating >=-20
            listRating = dao.getListBadEMPBaseOnRating(month, year);
            listCheck = listRating.size();
            if (listCheck == 3) {
                //Get all list feedback not in declinefeedback
                listRes = dao.getListRecentNotDeclineResponeRating();
                session.setAttribute("LIST_BAD_RECENT_RESPONE_EMP", listRes);
                session.setAttribute("LIST_BAD_EMP", listRating);
                url = SUCCESS;
                return;
            } else if (listCheck < 3) {
                
                finalCount = 3 - listCheck;
                //Get other list by top finalcount
                list = dao.getListBadEMPOtherRating(finalCount, month, year);
                List<UserDTO> finalList = new ArrayList<UserDTO>(list);
                //List with all emp
                finalList.addAll(listRating);
                listRes = dao.getListAllRecentRespone();
                //Final list rating
                session.setAttribute("LIST_BAD_EMP", finalList);
                session.setAttribute("LIST_BAD_RECENT_RESPONE_EMP", listRes);

            } else if (listCheck == 0) {
                list = dao.getListBadEMP(month, year);
                listRes = dao.getListRecentDeclineRespone();
                session.setAttribute("LIST_BAD_EMP", list);
                session.setAttribute("LIST_BAD_RECENT_RESPONE_EMP", listRes);
            }

            url = SUCCESS;
        } catch (Exception e) {
            log("Error at StatisticBadEmpController" + e.toString());

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
