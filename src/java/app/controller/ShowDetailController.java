/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDetailDTO;
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
 * @author ADMIN
 */
@WebServlet(name = "ShowDetailController", urlPatterns = {"/ShowDetailController"})
public class ShowDetailController extends HttpServlet {

    private static final String SUCCESS = "ShowFeedBackController";
    private static final String ERROR = "managerFeedback.jsp";
    private static final String SEARCH = "SearchFeedbackController";

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
            FeedbackDAO dao = new FeedbackDAO();
            EmployeesDAO dao2 = new EmployeesDAO();
            String feedbackID = request.getParameter("feedbackID");
            String pipeOrList = request.getParameter("style_flag");
            request.setAttribute("style_flag", pipeOrList);
            String statusID = request.getParameter("statusID");
            String statusName = request.getParameter("statusName");
            String flag = request.getParameter("flag");
            //add thêm field cateID
            List<FeedbackDetailDTO> list = new ArrayList<>();
            if (statusID.equalsIgnoreCase("decline")) {
                list = dao.getListDeclineFeedbackDetail(feedbackID);
            } else {
                list = dao.getListFeedbackDetail(feedbackID);

            }

            if (!list.isEmpty()) {
                for (FeedbackDetailDTO detail : list) {
                    if (dao2.countDeclineResponse2(detail.getFeedbackDetailID()) != 0) {
                        String reason = dao2.getDeclineResponeForFeedback(detail.getFeedbackDetailID());
                        String userID = dao2.getUserIDDecline(detail.getFeedbackDetailID());
                        String fullName = dao2.getEmployeeDeclineResponeForFeedback(userID);
                        detail.setCheck(true);
                        detail.setDeclineReason(reason);
                        detail.setEmployeeName(fullName);
                    }
                }
                session.setAttribute("LIST_DETAIL", list);
                if (statusID.equalsIgnoreCase("pending")) {
                    request.setAttribute("CLASS_NAME", "pending");
                }
                request.setAttribute("statusID", statusID);
                request.setAttribute("statusName", statusName);
                url = SUCCESS;
            } else {
                request.setAttribute("statusID", statusID);
                request.setAttribute("statusName", statusName);
                if (dao.getFeedbackStatusID(feedbackID).equals("onGoing")) {
                    request.setAttribute("CLASS_NAME", "onGoing");
                }
                if (!Boolean.parseBoolean(flag)) {
                    if (statusID.equalsIgnoreCase("pending") && statusName.equalsIgnoreCase("pending")) {
                        request.setAttribute("statusID", "onGoing");
                        request.setAttribute("statusName", "On-Going");
                    }
                }
                list = dao.getListFeedbackDetailShowEmployee(feedbackID);
                session.setAttribute("LIST_DETAIL", list);
                url = SUCCESS;
            }
            request.setAttribute("flag", "open");
            if (pipeOrList.equalsIgnoreCase("pipe")) {
                request.setAttribute("STYLE_PIPE", "active");
                request.setAttribute("STYLE_LIST_ALL", "active");
            } else {
                request.setAttribute("STYLE_LIST", "active");
                String category = request.getParameter("style_list_category");
                request.setAttribute("style_list_category", category);
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
            String search = request.getParameter("search");
            if (!search.equals("")) {
                url = SEARCH;
            }
        } catch (Exception e) {
            log("Error at SearchController" + e.toString());
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
