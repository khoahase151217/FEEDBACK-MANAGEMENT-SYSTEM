/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.feedback.FeedbackDAO;
import app.response.ResponseDAO;
import app.response.ResponseDTO;
import app.users.UserDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddResponseController", urlPatterns = {"/AddResponseController"})
public class AddResponseController extends HttpServlet {

    private static final String SUCCESS = "ShowFeedbackForEmpController";
    private static final String ERROR = "ShowFeedbackForEmpController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(true);
            ResponseDAO dao = new ResponseDAO();
            FeedbackDAO dao2 = new FeedbackDAO();
            FileInputStream photo = null;
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH);
            String date = sdf.format(new Date());
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_EMP");
            String feedbackDetailId = "";
            String des = "";
            String image = "";

            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (org.apache.commons.fileupload.FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                        String inputName = item.getFieldName();
                        if (inputName.equalsIgnoreCase("description")) {
                            des = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("feedbackDetailID")) {
                            feedbackDetailId = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("image")) {
                            image = item.getString();
                        }
                    }
//                    else {
//                        String tmp = item.getContentType();
//                        if (des.equals("")||!tmp.contains("image")) {
//                            url = ERROR;
//                            request.setAttribute("ADD_FAILURE", "active");
//                            request.setAttribute("SEND_FAILURE", "active");
//                            request.setAttribute("SEND_FEEDBACK_FLAG", "open");
//                            request.setAttribute("FEEDBACK_DETAIL_ID", feedbackDetailId);
//                            break;
//                        }
//                        photo = (FileInputStream) item.getInputStream();
//                    }
                }

            }
            ResponseDTO res = new ResponseDTO(feedbackDetailId, user.getUserID(), des, "done", date);

            if (des.equals("") || image.equals("")) {
                url = ERROR;
                request.setAttribute("ADD_FAILURE", "active");
                request.setAttribute("SEND_FAILURE", "active");
                request.setAttribute("SEND_FEEDBACK_FLAG", "open");
                request.setAttribute("FEEDBACK_DETAIL_ID", feedbackDetailId);
            } else {
                if (dao.countResponseFeedback(res) != 0) {
//                    dao.updateResponseFeedback(res, photo);
                    dao.updateResponseFeedback(res, image);
                    dao.updateFlagDetail(feedbackDetailId);
                    request.setAttribute("SEND_FEEDBACK_FLAG", "open");
                    request.setAttribute("SEND_SUCCESS", "active");
                    url = SUCCESS;
                } else {
//                    dao.insertResponseFeedback(res, photo);
                    dao.insertResponseFeedback(res, image);
                    dao.updateFlagDetail(feedbackDetailId);
                    request.setAttribute("SEND_FEEDBACK_FLAG", "open");
                    request.setAttribute("SEND_SUCCESS", "active");
                    url = SUCCESS;
                }

            }
            request.setAttribute("FEEDBACK_ID", dao2.getFeedbackIDByFeedbackDetailID(feedbackDetailId));
            request.setAttribute("flag", "open");
        } catch (Exception e) {
            log("Error at AddResponseController" + e.toString());
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
