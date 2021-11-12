    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.employees.EmployeesDAO;
import app.feedback.FeedbackDTO;
import app.feedback.FeedbackDetailDTO;
import app.response.ResponseDTO;
import app.users.UserDTO;
import java.io.IOException;
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
@WebServlet(name = "ShowFeedbackDetailForEmpController", urlPatterns = {"/ShowFeedbackDetailForEmpController"})
public class ShowFeedbackDetailForEmpController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "EmployeeHome.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            EmployeesDAO dao = new EmployeesDAO();
            String declineReason = "";
            String responseId = "";
            String feedbackID = (String) request.getParameter("feedbackID");
            String history = (String) request.getParameter("history");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_EMP");
            String feedbackDetailID = (String) request.getAttribute("FEEDBACK_DETAIL_ID");
            List<FeedbackDTO> listFB = (List<FeedbackDTO>)  session.getAttribute("LIST_FEEDBACK");
            int count=0;
            for (FeedbackDTO FB : listFB) {
                List<FeedbackDetailDTO> dto = dao.showListFeedbackDetail(FB.getUserID(), FB.getFeedbackID());
                count+=dto.size();
            }
             session.setAttribute("COUNT_DETAIL_NOTIFICATION", count);
            if (feedbackID == null) {
                feedbackID = (String) session.getAttribute("FEEDBACK");
            }
            if (history == null || history.equals("")) {
                history = (String) session.getAttribute("HISTORY");
            }
            List<FeedbackDetailDTO> dto = dao.showListFeedbackDetail(user.getUserID(), feedbackID);
            for (FeedbackDetailDTO detail : dto) {
                if (dao.countDeclineResponse(detail.getFeedbackDetailID(), user.getUserID()) != 0) {
                    detail.setCheck(true);
                    responseId = dao.getResponseID(detail.getFeedbackDetailID(),user.getUserID());
                    declineReason = dao.getDeclineReason(responseId);
                    detail.setDeclineReason(declineReason);
                }
            }
            List<ResponseDTO> his = dao.showHistoryListFeedbackDetail(user.getUserID(), history);
            for (ResponseDTO detail : his) {
                if(dao.checkDone(detail.getFeedbackDetailID()).equalsIgnoreCase("done")){
                    detail.setCheckDone(true);
                }
                else{
                if (dao.countDeclineResponse(detail.getFeedbackDetailID(), user.getUserID()) != 0) {
                    detail.setCheck(true);
                    declineReason = dao.getDeclineReason(detail.getResponseID());
                    detail.setDeclineReason(declineReason);
                }
                }
            }
            session.setAttribute("DETAIL", dto);
            request.setAttribute("FEEDBACK_ACTIVE", feedbackID);
            session.setAttribute("HISTORY_DETAIL", his);
            request.setAttribute("HISTORY_ACTIVE", history);
            if (feedbackDetailID != null) {
                request.setAttribute("FEEDBACK_DETAIL_ACTIVE", feedbackDetailID);
            }

            String styleList = request.getParameter("style_list");
            if (styleList != null) {
                switch (styleList) {
                    case "history":
                        request.setAttribute("LIST_STYLE_HISTORY", "active");
                        break;
                    default:
                        request.setAttribute("LIST_STYLE_TASK", "active");
                        break;
                }
            } else {
                request.setAttribute("LIST_STYLE_TASK", "active");
            }

            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ShowFeedbackDetailForEmp" + e.toString());
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
