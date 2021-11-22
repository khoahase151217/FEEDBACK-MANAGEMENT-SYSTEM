/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.facility.FacilityDAO;
import app.facility.FacilityDTO;
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
public class LoadMoreFacilitiesController extends HttpServlet {

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
            FacilityDAO dao = new FacilityDAO();
            switch (flag) {
                case "0":
                    if (!search.equals("")) {
                        List<FacilityDTO> list = dao.getListFacilityByNameNext(search, Integer.parseInt(amount));
                        for (FacilityDTO facility : list) {
                            out.println("<div class=\"pipe-item\">\n"
                                    + "     <div class=\"pipe-item-heading\">\n"
                                    + "              <img\n"
                                    + "             src=\"" + facility.getImage() + "\"\n"
                                    + "              alt=\"\"\n"
                                    + "               />\n"
                                    + "           </div>\n"
                                    + "                 <div class=\"pipe-item-bottom\">\n"
                                    + "               <div class=\"pipe-item-bottom-heading\">\n"
                                    + "                            <h2 class=\"pipe-item-title\">\n"
                                    + "                                 " + facility.getFacilityName() + "\n"
                                    + "                                       </h2>\n"
                                    + "                   <p class=\"pipe-item-desc\">Quantity: " + facility.getQuantity() + "</p>\n"
                                    + "                </div>\n"
                                    + "      <div class=\"pipe-item-bottom-footer\">\n"
                                    + "             <p class=\"pipe-item-desc pipe-item-date\">\n"
                                    + "                Maintain Date: " + facility.getMaintenanceDate() + "\n"
                                    + "         </p>\n"
                                    + "           </div>\n"
                                    + "                 </div>\n"
                                    + "      </div>");
                        }
                    } else {
                        List<FacilityDTO> list = dao.getAllListFacilityNext(Integer.parseInt(amount));
                        for (FacilityDTO facility : list) {
                            out.println("<div class=\"pipe-item\">\n"
                                    + "     <div class=\"pipe-item-heading\">\n"
                                    + "              <img\n"
                                    + "             src=\"" + facility.getImage() + "\"\n"
                                    + "              alt=\"\"\n"
                                    + "               />\n"
                                    + "           </div>\n"
                                    + "                 <div class=\"pipe-item-bottom\">\n"
                                    + "               <div class=\"pipe-item-bottom-heading\">\n"
                                    + "                            <h2 class=\"pipe-item-title\">\n"
                                    + "                                 " + facility.getFacilityName() + "\n"
                                    + "                                       </h2>\n"
                                    + "                   <p class=\"pipe-item-desc\">Quantity: " + facility.getQuantity() + "</p>\n"
                                    + "                </div>\n"
                                    + "      <div class=\"pipe-item-bottom-footer\">\n"
                                    + "             <p class=\"pipe-item-desc pipe-item-date\">\n"
                                    + "                Maintain Date: " + facility.getMaintenanceDate() + "\n"
                                    + "         </p>\n"
                                    + "           </div>\n"
                                    + "                 </div>\n"
                                    + "      </div>");
                        }
                    }

                    break;
                case "1":
                    List<FacilityDTO> list_elctronic = dao.getListElectricFacilityNext(Integer.parseInt(amount));
                    for (FacilityDTO facility : list_elctronic) {
                        out.println("<div class=\"pipe-item\">\n"
                                + "     <div class=\"pipe-item-heading\">\n"
                                + "              <img\n"
                                + "             src=\"" + facility.getImage() + "\"\n"
                                + "              alt=\"\"\n"
                                + "               />\n"
                                + "           </div>\n"
                                + "                 <div class=\"pipe-item-bottom\">\n"
                                + "               <div class=\"pipe-item-bottom-heading\">\n"
                                + "                            <h2 class=\"pipe-item-title\">\n"
                                + "                                 " + facility.getFacilityName() + "\n"
                                + "                                       </h2>\n"
                                + "                   <p class=\"pipe-item-desc\">Quantity: " + facility.getQuantity() + "</p>\n"
                                + "                </div>\n"
                                + "      <div class=\"pipe-item-bottom-footer\">\n"
                                + "             <p class=\"pipe-item-desc pipe-item-date\">\n"
                                + "                Maintain Date: " + facility.getMaintenanceDate() + "\n"
                                + "         </p>\n"
                                + "           </div>\n"
                                + "                 </div>\n"
                                + "      </div>");
                    }
                    break;
                case "2":
                    List<FacilityDTO> list_water = dao.getListWaterFacilityNext(Integer.parseInt(amount));
                    for (FacilityDTO facility : list_water) {
                        out.println("<div class=\"pipe-item\">\n"
                                + "     <div class=\"pipe-item-heading\">\n"
                                + "              <img\n"
                                + "             src=\"" + facility.getImage() + "\"\n"
                                + "              alt=\"\"\n"
                                + "               />\n"
                                + "           </div>\n"
                                + "                 <div class=\"pipe-item-bottom\">\n"
                                + "               <div class=\"pipe-item-bottom-heading\">\n"
                                + "                            <h2 class=\"pipe-item-title\">\n"
                                + "                                 " + facility.getFacilityName() + "\n"
                                + "                                       </h2>\n"
                                + "                   <p class=\"pipe-item-desc\">Quantity: " + facility.getQuantity() + "</p>\n"
                                + "                </div>\n"
                                + "      <div class=\"pipe-item-bottom-footer\">\n"
                                + "             <p class=\"pipe-item-desc pipe-item-date\">\n"
                                + "                Maintain Date: " + facility.getMaintenanceDate() + "\n"
                                + "         </p>\n"
                                + "           </div>\n"
                                + "                 </div>\n"
                                + "      </div>");
                    }
                    break;
                case "3":
                    List<FacilityDTO> list_environment = dao.getListEnviromentFacilityNext(Integer.parseInt(amount));
                    for (FacilityDTO facility : list_environment) {
                        out.println("<div class=\"pipe-item\">\n"
                                + "     <div class=\"pipe-item-heading\">\n"
                                + "              <img\n"
                                + "             src=\"" + facility.getImage() + "\"\n"
                                + "              alt=\"\"\n"
                                + "               />\n"
                                + "           </div>\n"
                                + "                 <div class=\"pipe-item-bottom\">\n"
                                + "               <div class=\"pipe-item-bottom-heading\">\n"
                                + "                            <h2 class=\"pipe-item-title\">\n"
                                + "                                 " + facility.getFacilityName() + "\n"
                                + "                                       </h2>\n"
                                + "                   <p class=\"pipe-item-desc\">Quantity: " + facility.getQuantity() + "</p>\n"
                                + "                </div>\n"
                                + "      <div class=\"pipe-item-bottom-footer\">\n"
                                + "             <p class=\"pipe-item-desc pipe-item-date\">\n"
                                + "                Maintain Date: " + facility.getMaintenanceDate() + "\n"
                                + "         </p>\n"
                                + "           </div>\n"
                                + "                 </div>\n"
                                + "      </div>");
                    }
                    break;
                default:
                    List<FacilityDTO> list_others = dao.getListOthersFacilityNext(Integer.parseInt(amount));
                    for (FacilityDTO facility : list_others) {
                        out.println("<div class=\"pipe-item\">\n"
                                + "     <div class=\"pipe-item-heading\">\n"
                                + "              <img\n"
                                + "             src=\"" + facility.getImage() + "\"\n"
                                + "              alt=\"\"\n"
                                + "               />\n"
                                + "           </div>\n"
                                + "                 <div class=\"pipe-item-bottom\">\n"
                                + "               <div class=\"pipe-item-bottom-heading\">\n"
                                + "                            <h2 class=\"pipe-item-title\">\n"
                                + "                                 " + facility.getFacilityName() + "\n"
                                + "                                       </h2>\n"
                                + "                   <p class=\"pipe-item-desc\">Quantity: " + facility.getQuantity() + "</p>\n"
                                + "                </div>\n"
                                + "      <div class=\"pipe-item-bottom-footer\">\n"
                                + "             <p class=\"pipe-item-desc pipe-item-date\">\n"
                                + "                Maintain Date: " + facility.getMaintenanceDate() + "\n"
                                + "         </p>\n"
                                + "           </div>\n"
                                + "                 </div>\n"
                                + "      </div>");
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
