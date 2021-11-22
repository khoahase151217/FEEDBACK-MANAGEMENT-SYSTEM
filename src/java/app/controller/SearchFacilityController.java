/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.facility.FacilityDAO;
import app.facility.FacilityDTO;
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
 * @author Admin
 */
public class SearchFacilityController extends HttpServlet {

    private static final String SUCCESS = "ManagerViewFacility.jsp";
    private static final String ERROR = "ManagerViewFacility.jsp";
    private static final String FULLNAME_REGEX = "^(?![\\s.]+$)[a-zA-Z\\s.]*$";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            String search = request.getParameter("search");
            List<FacilityDTO> list;
            if (!search.matches(FULLNAME_REGEX)) {
                list = new ArrayList<>();
                session.setAttribute("FACILTIES_LIST_ALL", list);
                request.setAttribute("SEARCH", search);
                request.setAttribute("STYLE_LIST_ALL", "active");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            FacilityDAO dao = new FacilityDAO();

            url = SUCCESS;
            list = dao.getListFacilityByName(search);
            session.setAttribute("FACILTIES_LIST_ALL", list);
            request.setAttribute("SEARCH", search);
            request.setAttribute("STYLE_LIST_ALL", "active");
        } catch (Exception e) {
            log("Error at LoadController:" + e.toString());
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
