/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.facility.FacilityDAO;
import app.facility.FacilityDTO;
import app.statistic.DonutDTO;
import app.statistic.StatisticDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
 * @author ADMIN
 */
@WebServlet(name = "FacilityStatisticController", urlPatterns = {"/FacilityStatisticController"})

public class FacilityStatisticController extends HttpServlet {

    private static final String SUCCESS = "StatisticGoodEmpController";
    private static final String ERROR = "ShowFeedBackController";

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
            LocalDateTime now = LocalDateTime.now();
            int get = now.getYear();
            String year = String.valueOf(get);
            FacilityDAO dao = new FacilityDAO();
            StatisticDAO dao2 = new StatisticDAO();
            List<FacilityDTO> list = new ArrayList<FacilityDTO>();
            SimpleDateFormat month_format = new SimpleDateFormat("MMM ", Locale.ENGLISH);
            Date date = new Date();
            long total = 0;
            String month_name = month_format.format(date);
            List<DonutDTO> listDonut = dao2.selectFeedbackForDonut(month_name);
            for (DonutDTO donut : listDonut) {
                total += donut.getCount();
            }
            session.setAttribute("TOTAL", total);
            String stat = request.getParameter("search");
            if (stat == null) {
                stat = "Year";
            }
            switch (stat.toLowerCase()) {
                case "year":
                    list = dao.selectTop3ByYear(year);
                    if (!list.isEmpty()) {
                        session.setAttribute("FACILITY_STATISTIC", list);
                        url = SUCCESS;
                    }
                    break;
                case "month":
                    list = dao.selectTop3ByMonth(month_name, year);
                    if (!list.isEmpty()) {
                        session.setAttribute("FACILITY_STATISTIC", list);
                        url = SUCCESS;
                    }
                    break;
                default:
                    String txt1 = "";
                    String txt2 = "";
                    String txt3 = "";
                    int quarter = 0;

                    if (month_name.contains("Jan") || month_name.contains("Feb") || month_name.contains("Mar")) {
                        txt1 = "Jan";
                        txt2 = "Feb";
                        txt3 = "Mar";
                        quarter = 1;
                    }
                    if (month_name.contains("Apr") || month_name.contains("May") || month_name.contains("Jun")) {
                        txt1 = "Apr";
                        txt2 = "May";
                        txt3 = "Jun";
                        quarter = 2;
                    }
                    if (month_name.contains("Jul") || month_name.contains("Aug") || month_name.contains("Sep")) {
                        txt1 = "Jul";
                        txt2 = "Aug";
                        txt3 = "Sep";
                        quarter = 3;
                    }

                    if (month_name.contains("Oct") || month_name.contains("Nov") || month_name.contains("Dec")) {
                        txt1 = "Oct";
                        txt2 = "Nov";
                        txt3 = "Dec";
                        quarter = 4;
                    }
                    list = dao.selectTop3ByQuarter(txt1, txt2, txt3, year);

                    if (!list.isEmpty()) {
                        session.setAttribute("FACILITY_STATISTIC", list);
                        session.setAttribute("QUARTER_OF_YEAR", quarter);
                        url = SUCCESS;
                    }
            }
            request.setAttribute("SEARCH", stat);
        } catch (Exception e) {
            log("Error at StatisticGoodEmpController" + e.toString());
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
