/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class LoadFeedbackController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String amount = request.getParameter("amount");
            String flag = request.getParameter("flag_navigation");
            String search = request.getParameter("search");
            FeedbackDAO dao = new FeedbackDAO();
            switch (flag) {
                case "0":
                    if (!search.equals("")) {
                        List<FeedbackDTO> listAllSearch = dao.getListFeedbackForManagerNext(search,Integer.parseInt(amount));
                        for (FeedbackDTO feedback : listAllSearch) {
                            out.println("<a href=\"ShowEmployeeActiveController?feedbackID=" + feedback.getFeedbackID() + "&email=" + feedback.getEmail() + "&date=" + feedback.getDate() + "&statusID=" + feedback.getStatusId() + "&statusName=" + feedback.getStatusName() + "&style_flag=pipe&search=" + search + "\">\n"
                                    + "       <div class=\"pipe-item\">\n"
                                    + "           <div class=\"pipe-item-heading\">\n"
                                    + "                <div class=\"pipe-item-title-wrapper\">\n"
                                    + "                        <h3 class=\"pipe-item-title\">\n"
                                    + "                            Feedback " + feedback.getFeedbackID() + "\n"
                                    + "                              </h3>\n"
                                    + "                                 <p class=\"pipe-item-desc\">\n"
                                    + "                                 <strong>Name:</strong> " + feedback.getFullName() + "\n"
                                    + "                           </p>\n"
                                    + "                            </div>\n"
                                    + "                     <div class=\"pipe-item-date\">\n"
                                    + "        " + feedback.getDate() + "\n"
                                    + "                  </div>\n"
                                    + "                            </div>\n"
                                    + "                               <div class=\"pipe-item-bottom\">\n"
                                    + "                                   <p class=\"pipe-bottom-item\">\n"
                                    + "                                            <strong>Send by</strong>\n"
                                    + "                               " + feedback.getEmail() + "\n"
                                    + "                 </p>\n"
                                    + "                             </div>\n"
                                    + "                   </div>\n"
                                    + "              </a>");
                        }
                    } else {
                        List<FeedbackDTO> listAll = dao.getAllListFeedbackByStatusNext(Integer.parseInt(amount));
                        for (FeedbackDTO feedback : listAll) {
                            out.println("<a href=\"ShowEmployeeActiveController?feedbackID=" + feedback.getFeedbackID() + "&email=" + feedback.getEmail() + "&date=" + feedback.getDate() + "&statusID=" + feedback.getStatusId() + "&statusName=" + feedback.getStatusName() + "&style_flag=pipe&search=" + search + "\">\n"
                                    + "       <div class=\"pipe-item\">\n"
                                    + "           <div class=\"pipe-item-heading\">\n"
                                    + "                <div class=\"pipe-item-title-wrapper\">\n"
                                    + "                        <h3 class=\"pipe-item-title\">\n"
                                    + "                            Feedback " + feedback.getFeedbackID() + "\n"
                                    + "                              </h3>\n"
                                    + "                                 <p class=\"pipe-item-desc\">\n"
                                    + "                                 <strong>Name:</strong> " + feedback.getFullName() + "\n"
                                    + "                           </p>\n"
                                    + "                            </div>\n"
                                    + "                     <div class=\"pipe-item-date\">\n"
                                    + "        " + feedback.getDate() + "\n"
                                    + "                  </div>\n"
                                    + "                            </div>\n"
                                    + "                               <div class=\"pipe-item-bottom\">\n"
                                    + "                                   <p class=\"pipe-bottom-item\">\n"
                                    + "                                            <strong>Send by</strong>\n"
                                    + "                               " + feedback.getEmail() + "\n"
                                    + "                 </p>\n"
                                    + "                             </div>\n"
                                    + "                   </div>\n"
                                    + "              </a>");
                        }
                    }

                    break;
                case "1":
                    List<FeedbackDTO> listPending = dao.getListFeedbackByStatusPendingNext(Integer.parseInt(amount));
                    for (FeedbackDTO feedback : listPending) {
                        out.println("<a href=\"ShowEmployeeActiveController?feedbackID=" + feedback.getFeedbackID() + "&email=" + feedback.getEmail() + "&date=" + feedback.getDate() + "&statusID=" + feedback.getStatusId() + "&statusName=" + feedback.getStatusName() + "&style_flag=pipe&search=" + search + "\">\n"
                                + "       <div class=\"pipe-item\">\n"
                                + "           <div class=\"pipe-item-heading\">\n"
                                + "                <div class=\"pipe-item-title-wrapper\">\n"
                                + "                        <h3 class=\"pipe-item-title\">\n"
                                + "                            Feedback " + feedback.getFeedbackID() + "\n"
                                + "                              </h3>\n"
                                + "                                 <p class=\"pipe-item-desc\">\n"
                                + "                                 <strong>Name:</strong> " + feedback.getFullName() + "\n"
                                + "                           </p>\n"
                                + "                            </div>\n"
                                + "                     <div class=\"pipe-item-date\">\n"
                                + "        " + feedback.getDate() + "\n"
                                + "                  </div>\n"
                                + "                            </div>\n"
                                + "                               <div class=\"pipe-item-bottom\">\n"
                                + "                                   <p class=\"pipe-bottom-item\">\n"
                                + "                                            <strong>Send by</strong>\n"
                                + "                               " + feedback.getEmail() + "\n"
                                + "                 </p>\n"
                                + "                             </div>\n"
                                + "                   </div>\n"
                                + "              </a>");
                    }
                    break;
                case "2":
                    List<FeedbackDTO> listFixing = dao.getListFeedbackByStatusFixingNext(Integer.parseInt(amount));
                    for (FeedbackDTO feedback : listFixing) {
                        out.println("<a href=\"ShowEmployeeActiveController?feedbackID=" + feedback.getFeedbackID() + "&email=" + feedback.getEmail() + "&date=" + feedback.getDate() + "&statusID=" + feedback.getStatusId() + "&statusName=" + feedback.getStatusName() + "&style_flag=pipe&search=" + search + "\">\n"
                                + "       <div class=\"pipe-item\">\n"
                                + "           <div class=\"pipe-item-heading\">\n"
                                + "                <div class=\"pipe-item-title-wrapper\">\n"
                                + "                        <h3 class=\"pipe-item-title\">\n"
                                + "                            Feedback " + feedback.getFeedbackID() + "\n"
                                + "                              </h3>\n"
                                + "                                 <p class=\"pipe-item-desc\">\n"
                                + "                                 <strong>Name:</strong> " + feedback.getFullName() + "\n"
                                + "                           </p>\n"
                                + "                            </div>\n"
                                + "                     <div class=\"pipe-item-date\">\n"
                                + "        " + feedback.getDate() + "\n"
                                + "                  </div>\n"
                                + "                            </div>\n"
                                + "                               <div class=\"pipe-item-bottom\">\n"
                                + "                                   <p class=\"pipe-bottom-item\">\n"
                                + "                                            <strong>Send by</strong>\n"
                                + "                               " + feedback.getEmail() + "\n"
                                + "                 </p>\n"
                                + "                             </div>\n"
                                + "                   </div>\n"
                                + "              </a>");
                    }
                    break;
                case "3":
                    List<FeedbackDTO> listDeny = dao.getListFeedbackByStatusDenyNext(Integer.parseInt(amount));
                    for (FeedbackDTO feedback : listDeny) {
                        out.println("<a href=\"ShowEmployeeActiveController?feedbackID=" + feedback.getFeedbackID() + "&email=" + feedback.getEmail() + "&date=" + feedback.getDate() + "&statusID=" + feedback.getStatusId() + "&statusName=" + feedback.getStatusName() + "&style_flag=pipe&search=" + search + "\">\n"
                                + "       <div class=\"pipe-item\">\n"
                                + "           <div class=\"pipe-item-heading\">\n"
                                + "                <div class=\"pipe-item-title-wrapper\">\n"
                                + "                        <h3 class=\"pipe-item-title\">\n"
                                + "                            Feedback " + feedback.getFeedbackID() + "\n"
                                + "                              </h3>\n"
                                + "                                 <p class=\"pipe-item-desc\">\n"
                                + "                                 <strong>Name:</strong> " + feedback.getFullName() + "\n"
                                + "                           </p>\n"
                                + "                            </div>\n"
                                + "                     <div class=\"pipe-item-date\">\n"
                                + "        " + feedback.getDate() + "\n"
                                + "                  </div>\n"
                                + "                            </div>\n"
                                + "                               <div class=\"pipe-item-bottom\">\n"
                                + "                                   <p class=\"pipe-bottom-item\">\n"
                                + "                                            <strong>Send by</strong>\n"
                                + "                               " + feedback.getEmail() + "\n"
                                + "                 </p>\n"
                                + "                             </div>\n"
                                + "                   </div>\n"
                                + "              </a>");
                    }
                    break;
                default:
                    List<FeedbackDTO> listDone = dao.getListFeedbackByStatusDoneNext(Integer.parseInt(amount));
                    for (FeedbackDTO feedback : listDone) {
                        out.println("<a href=\"ShowEmployeeActiveController?feedbackID=" + feedback.getFeedbackID() + "&email=" + feedback.getEmail() + "&date=" + feedback.getDate() + "&statusID=" + feedback.getStatusId() + "&statusName=" + feedback.getStatusName() + "&style_flag=pipe&search=" + search + "\">\n"
                                + "       <div class=\"pipe-item\">\n"
                                + "           <div class=\"pipe-item-heading\">\n"
                                + "                <div class=\"pipe-item-title-wrapper\">\n"
                                + "                        <h3 class=\"pipe-item-title\">\n"
                                + "                            Feedback " + feedback.getFeedbackID() + "\n"
                                + "                              </h3>\n"
                                + "                                 <p class=\"pipe-item-desc\">\n"
                                + "                                 <strong>Name:</strong> " + feedback.getFullName() + "\n"
                                + "                           </p>\n"
                                + "                            </div>\n"
                                + "                     <div class=\"pipe-item-date\">\n"
                                + "        " + feedback.getDate() + "\n"
                                + "                  </div>\n"
                                + "                            </div>\n"
                                + "                               <div class=\"pipe-item-bottom\">\n"
                                + "                                   <p class=\"pipe-bottom-item\">\n"
                                + "                                            <strong>Send by</strong>\n"
                                + "                               " + feedback.getEmail() + "\n"
                                + "                 </p>\n"
                                + "                             </div>\n"
                                + "                   </div>\n"
                                + "              </a>");
                    }
                    break;
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
