/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.users.UserDAO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
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
 * @author HieuTran
 */
public class UpdateUserController extends HttpServlet {

    private static final String SUCCESS = "ShowUserFormController";
    private static final String ERROR = "#";

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
            boolean flag = false;
            FileInputStream photo = null;
            String userID = "";
            String fullName = "";
            String roleID = "";
            String statusID = "";
            String password = "";
            String position = "";
            UserDAO dao = new UserDAO();

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
                        if (inputName.equalsIgnoreCase("userID")) {
                            userID = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("password")) {
                            password = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("fullName")) {
                            fullName = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("roleID")) {
                            roleID = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("StatusID")) {
                            statusID = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("position")) {
                            position = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("style_list")) {
                            if (!item.getString().equals("")) {
                                request.setAttribute("style_list", "active");
                            }
                        }
                        if (inputName.equalsIgnoreCase("style_pipe")) {
                            if (!item.getString().equals("")) {
                                request.setAttribute("style_pipe", "active");
                            }
                        }

                    } else {
                        if (item.getContentType().contains("image")) {
                            photo = (FileInputStream) item.getInputStream();
                            flag = true;
                        }
                    }
                }
            }

            if (fullName.equals("") || password.equals("")) {
                url = ERROR;
                request.setAttribute("ADD_FAILURE", "active");
                request.setAttribute("SEND_FAILURE", "active");
            } else {
                if (flag) {
                    dao.UpdateUser(userID, fullName, roleID, statusID, password, photo);
                } else {
                    dao.UpdateUserNoPhoto(userID, fullName, roleID, statusID, password);
                }
                HttpSession session = request.getSession();
                switch (roleID) {
                    case "US":
                        request.setAttribute("user", "user");
                        session.setAttribute("LOGIN_USER", dao.getUserIdByUserID(userID));
                        break;
                    case "AD":
                        request.setAttribute("user", "admin");
                        session.setAttribute("LOGIN_ADMIN", dao.getUserIdByUserID(userID));
                        break;
                    default:
                        request.setAttribute("user", "employee");
                        session.setAttribute("LOGIN_EMP", dao.getUserIdByUserID(userID));
                        break;
                }
                if (position != null) {
                    request.setAttribute("position", position);
                }
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at Update Controller" + e.toString());
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
