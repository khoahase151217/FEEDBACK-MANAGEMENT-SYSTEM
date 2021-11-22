package app.controller;
import app.employees.EmployeesDAO;
import app.response.ResponseDTO;
import app.users.UserDTO;
import java.io.IOException;
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
@WebServlet(name = "StatisticGoodEmpController", urlPatterns = {"/StatisticGoodEmpController"})
public class StatisticGoodEmpController extends HttpServlet {

    private static final String ERROR = "##";
    private static final String SUCCESS = "StatisticBadEmpController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            SimpleDateFormat month_date = new SimpleDateFormat("MMM", Locale.ENGLISH);
            SimpleDateFormat year_date = new SimpleDateFormat("YYYY", Locale.ENGLISH);
            Date date = new Date();
            String month = month_date.format(date);
            String year = year_date.format(date);
            EmployeesDAO dao = new EmployeesDAO();
            List<UserDTO> list = new ArrayList<UserDTO>();
            List<ResponseDTO> listRes = new ArrayList<ResponseDTO>();
            list = dao.getListGoodEMP(month, year);
            listRes = dao.getListRecentDoneRespone();
            session.setAttribute("LIST_GOOD_EMP", list);
            session.setAttribute("LIST_GOOD_RECENT_RESPONE_EMP", listRes);
            url = SUCCESS;
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
