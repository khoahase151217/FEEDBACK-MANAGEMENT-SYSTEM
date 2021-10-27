/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDTO;
import app.statistic.StatisticDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author ADMIN
 */
@WebServlet(name = "NotificationController", urlPatterns = {"/NotificationController"})
public class NotificationController extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            StatisticDAO dao = new StatisticDAO();
            List<FeedbackDTO> list = new ArrayList<FeedbackDTO>();
            String count = request.getParameter("notification");
            int notification = 0;
            int check = Integer.parseInt(count);
            int check2 = dao.countForNotification();
            if (check2 > check) {
                notification = check2 - check;
                list = dao.getListFeedbackForNotification(notification);
            }
            PrintWriter out = response.getWriter();
            if (list.size() > 0) {
                out.println(list.size());
            }
            for (FeedbackDTO feedback : list) {
                out.println("<div class=\"notification-item\" onclick=\"handleReloadPage(event)\">\n"
                        + "                                                <div class=\"pipe-item-heading\">\n"
                        + "                                                    <div class=\"pipe-item-title-wrapper\">\n"
                        + "                                                        <h3 class=\"pipe-item-title\">Feedback " + feedback.getFeedbackID() + "</h3>\n"
                        + "                                                        <p class=\"pipe-item-desc\">\n"
                        + "                                                            <strong>Name:</strong> " + feedback.getFullName() + "\n"
                        + "                                                        </p>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"pipe-item-date\">" + feedback.getDate() + "</div>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"pipe-item-bottom\">\n"
                        + "                                                    <p class=\"pipe-bottom-item\">\n"
                        + "                                                        <strong>Send by</strong>\n"
                        + "                                                        " + feedback.getEmail() + "\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>");
            }
        } catch (Exception e) {
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
