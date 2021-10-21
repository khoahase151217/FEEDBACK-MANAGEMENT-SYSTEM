/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.users.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static final String SUCCESS = "##";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String txt1 = "";
        String txt2 = "";
        String txt3 = "";
        try {
            HttpSession session = request.getSession();
            SimpleDateFormat month_date = new SimpleDateFormat("MMM ", Locale.ENGLISH);
            Date date = new Date();
            String month_name = month_date.format(date);
            EmployeesDAO dao = new EmployeesDAO();
            List<UserDTO> list = new ArrayList<UserDTO>();
            if (month_name.contains("Jan") || month_name.contains("Feb") || month_name.contains("Mar")) {
                txt1 = "Jan";
                txt2 = "Feb";
                txt3 = "Mar";
            }
            if (month_name.contains("Apr") || month_name.contains("May") || month_name.contains("Jun")) {
                txt1 = "Apr";
                txt2 = "May";
                txt3 = "Jun";
            }
            if (month_name.contains("Jul") || month_name.contains("Aug") || month_name.contains("Sep")) {
                txt1 = "Jul";
                txt2 = "Aug";
                txt3 = "Sep";
            }

            if (month_name.contains("Oct") || month_name.contains("Nov") || month_name.contains("Dec")) {
                txt1 = "Oct";
                txt2 = "Nov";
                txt3 = "Dec";
            }
            list = dao.getListBadEMP(txt1, txt2, txt3);
            session.setAttribute("LIST_BAD_EMP", list);
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
