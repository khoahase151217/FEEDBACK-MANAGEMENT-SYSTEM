/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SearchFeedbackController", urlPatterns = {"/SearchFeedbackController"})
public class SearchFeedbackController extends HttpServlet {

    private static final String SUCCESS = "adminPage.jsp";
    private static final String ERROR = "#";
    private static final String FULL__NAME_REGEX = "^(?![\\s.]+$)[a-zA-Z\\s.]*$";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            FeedbackDAO dao = new FeedbackDAO();
            List<FeedbackDTO> list;
            HttpSession session = request.getSession();
            if (search == null) {
                search = "";
            } else if (!search.matches(FULL__NAME_REGEX)) {
                list = new ArrayList<>();
                session.setAttribute("FEEDBACK_LIST_ALL", list);
                session.setAttribute("COUNT", list.size());
                request.setAttribute("STYLE_LIST_ALL", "active");

                // Phần của front - end nhé                
                request.setAttribute("SEARCH", search);
                String styleFlag = request.getParameter("style_flag");
                if (styleFlag != null) {
                    if (!styleFlag.equals("pipe")) {
                        request.setAttribute("STYLE_LIST", "active");
                        request.setAttribute("STYLE_LIST_ALL", "active");
                        String category = request.getParameter("style_list_category");
                        if (category != null) {
                            request.setAttribute("STYLE_LIST_ALL", "");
                            switch (category) {
                                case "all":
                                    request.setAttribute("STYLE_LIST_ALL", "active");
                                    break;
                                case "pending":
                                    request.setAttribute("STYLE_LIST_PENDING", "active");
                                    break;
                                case "onGoing":
                                    request.setAttribute("STYLE_LIST_ONGOING", "active");
                                    break;
                                case "decline":
                                    request.setAttribute("STYLE_LIST_DECLINE", "active");
                                    break;
                                default:
                                    request.setAttribute("STYLE_LIST_DONE", "active");
                                    break;
                            }
                        }
                    } else {
                        request.setAttribute("STYLE_PIPE", "active");
                    }
                    request.setAttribute("STYLE_TASK", "active");
                } else {
                    request.setAttribute("STYLE_LIST", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                    request.setAttribute("STYLE_TASK", "active");
                }
                url = SUCCESS;
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            list = dao.getListFeedbackForManager(search);
            session.setAttribute("FEEDBACK_LIST_ALL", list);
            session.setAttribute("COUNT", list.size());

            // Phần của front - end nhé   
            request.setAttribute("SEARCH", search);
            String styleFlag = request.getParameter("style_flag");
            if (styleFlag != null) {
                if (!styleFlag.equals("pipe")) {
                    request.setAttribute("STYLE_LIST", "active");
                    request.setAttribute("STYLE_LIST_ALL", "active");
                    String category = request.getParameter("style_list_category");
                    if (category != null) {
                        request.setAttribute("STYLE_LIST_ALL", "");
                        switch (category) {
                            case "all":
                                request.setAttribute("STYLE_LIST_ALL", "active");
                                break;
                            case "pending":
                                request.setAttribute("STYLE_LIST_PENDING", "active");
                                break;
                            case "onGoing":
                                request.setAttribute("STYLE_LIST_ONGOING", "active");
                                break;
                            case "decline":
                                request.setAttribute("STYLE_LIST_DECLINE", "active");
                                break;
                            default:
                                request.setAttribute("STYLE_LIST_DONE", "active");
                                break;
                        }
                    }
                } else {
                    request.setAttribute("STYLE_PIPE", "active");
                }
                request.setAttribute("STYLE_TASK", "active");
            } else {
                request.setAttribute("STYLE_LIST", "active");
                request.setAttribute("STYLE_LIST_ALL", "active");
                request.setAttribute("STYLE_TASK", "active");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
