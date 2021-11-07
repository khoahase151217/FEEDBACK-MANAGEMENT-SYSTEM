/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
import java.io.IOException;
import java.util.ArrayList;
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
public class ShowFeedBackController extends HttpServlet {

    private static final String SUCCESS = "ShowFeedbackResponeForManagerController";
    private static final String ERROR = "error.jsp";
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
            FeedbackDAO dao = new FeedbackDAO();
            HttpSession session = request.getSession();
            List<FeedbackDTO> listAll = dao.getAllListFeedbackByStatusAscForManager();
            session.setAttribute("FEEDBACK_LIST_ALL", listAll);
            List<FeedbackDTO> listDone = dao.getListFeedbackByStatusDoneAscForManager();
            session.setAttribute("FEEDBACK_LIST_DONE", listDone);
            List<FeedbackDTO> listFixing = dao.getListFeedbackByStatusFixingAscForManager();
            session.setAttribute("FEEDBACK_LIST_FIXING", listFixing);
            List<FeedbackDTO> listPending = dao.getListFeedbackByStatusPendingAscForManager();
            List<FeedbackDTO> listPendingFull = dao.getListFeedbackByStatusPendingAscForManagerFull();
            List<FeedbackDTO> listPendingTrash =new ArrayList<>();
            List<FeedbackDTO> listPendingUser =new ArrayList<>();
            for (FeedbackDTO pending : listPendingFull) {
                if(pending.getTrashDate()!=null){
                    listPendingTrash.add(pending);
                }
                else{
                    listPendingUser.add(pending);
                }
            }
            session.setAttribute("FEEDBACK_LIST_PENDING", listPending);
            session.setAttribute("PENDING_TRASH_COUNT", listPendingTrash.size());
            session.setAttribute("PENDING_COUNT", listPendingUser.size());

            List<FeedbackDTO> listDeny = dao.getListFeedbackByStatusDenyAscForManager();
            session.setAttribute("FEEDBACK_LIST_DENY", listDeny);

            session.setAttribute("COUNT_OF_SUM", listFixing.size() + listPending.size());

            String pipeStyle = (String) request.getAttribute("STYLE_PIPE");
            String listStyle = (String) request.getAttribute("STYLE_LIST");
//            String style = (String) request.getAttribute("style_comment");
            if (pipeStyle == null && listStyle == null) {
                request.setAttribute("STYLE_PIPE", "active");
                request.setAttribute("STYLE_LIST_ALL", "active");
            }
//            if (style != null) {
//                request.setAttribute("STYLE_COMMENT", "active");
//            } else {
//                request.setAttribute("STYLE_TASK", "active");
//            }
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
