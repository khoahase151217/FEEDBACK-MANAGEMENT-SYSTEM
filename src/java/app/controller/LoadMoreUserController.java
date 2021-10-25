/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.users.UserDAO;
import app.users.UserDTO;
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
public class LoadMoreUserController extends HttpServlet {

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
            UserDAO dao = new UserDAO();
            switch (flag) {
                case "0":
                    if (!search.equals("")) {
                        List<UserDTO> list = dao.getListStudentNext(search,Integer.parseInt(amount));
                        for (UserDTO userDTO : list) {
                            if (userDTO.getImage().startsWith("http")) {
                                out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                        + "                                                        <div class=\"user-item-heading\">\n"
                                        + "                                                            <div class=\"user-item-image\">\n"
                                        + "                                                                        <img\n"
                                        + "                                                                            src=\"" + userDTO.getImage() + "\"\n"
                                        + "                                                                            alt=\"\"\n"
                                        + "                                                                            />\n"
                                        + "                                                            </div>\n"
                                        + "                                                            <div class=\"user-item-showcase\">\n"
                                        + "                                                                <h4 class=\"user-item-name\">\n"
                                        + "                                                                    " + userDTO.getFullName() + "\n"
                                        + "                                                                </h4>\n"
                                        + "                                                                <div class=\"user-item-email\">\n"
                                        + "                                                                    " + userDTO.getEmail() + "\n"
                                        + "                                                                </div>\n"
                                        + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                        + "                                                            </div>\n"
                                        + "                                                        </div>\n"
                                        + "                                                        <div class=\"user-item-bottom\">\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                        + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                        + "                                                                >Inactivate\n"
                                        + "                                                            </a>\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                        + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                        + "                                                                >Activate\n"
                                        + "                                                            </a>\n"
                                        + "                                                        </div>\n"
                                        + "                                                    </div>");
                            } else {
                                out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                        + "                                                        <div class=\"user-item-heading\">\n"
                                        + "                                                            <div class=\"user-item-image\">\n"
                                        + "                                                                        <img\n"
                                        + "                                                                            src=\"data:image/jpg/png;base64," + userDTO.getImage() + "\"\n"
                                        + "                                                                            alt=\"\"\n"
                                        + "                                                                            />\n"
                                        + "                                                            </div>\n"
                                        + "                                                            <div class=\"user-item-showcase\">\n"
                                        + "                                                                <h4 class=\"user-item-name\">\n"
                                        + "                                                                    " + userDTO.getFullName() + "\n"
                                        + "                                                                </h4>\n"
                                        + "                                                                <div class=\"user-item-email\">\n"
                                        + "                                                                    " + userDTO.getEmail() + "\n"
                                        + "                                                                </div>\n"
                                        + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                        + "                                                            </div>\n"
                                        + "                                                        </div>\n"
                                        + "                                                        <div class=\"user-item-bottom\">\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                        + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                        + "                                                                >Inactivate\n"
                                        + "                                                            </a>\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                        + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                        + "                                                                >Activate\n"
                                        + "                                                            </a>\n"
                                        + "                                                        </div>\n"
                                        + "                                                    </div>");
                            }
                        }
                    } else {
                        List<UserDTO> list = dao.showAllUserNext(Integer.parseInt(amount));
                        for (UserDTO userDTO : list) {
                            if (userDTO.getImage().startsWith("http")) {
                                out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                        + "                                                        <div class=\"user-item-heading\">\n"
                                        + "                                                            <div class=\"user-item-image\">\n"
                                        + "                                                                        <img\n"
                                        + "                                                                            src=\"" + userDTO.getImage() + "\"\n"
                                        + "                                                                            alt=\"\"\n"
                                        + "                                                                            />\n"
                                        + "                                                            </div>\n"
                                        + "                                                            <div class=\"user-item-showcase\">\n"
                                        + "                                                                <h4 class=\"user-item-name\">\n"
                                        + "                                                                    " + userDTO.getFullName() + "\n"
                                        + "                                                                </h4>\n"
                                        + "                                                                <div class=\"user-item-email\">\n"
                                        + "                                                                    " + userDTO.getEmail() + "\n"
                                        + "                                                                </div>\n"
                                        + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                        + "                                                            </div>\n"
                                        + "                                                        </div>\n"
                                        + "                                                        <div class=\"user-item-bottom\">\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                        + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                        + "                                                                >Inactivate\n"
                                        + "                                                            </a>\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                        + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                        + "                                                                >Activate\n"
                                        + "                                                            </a>\n"
                                        + "                                                        </div>\n"
                                        + "                                                    </div>");
                            } else {
                                out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                        + "                                                        <div class=\"user-item-heading\">\n"
                                        + "                                                            <div class=\"user-item-image\">\n"
                                        + "                                                                        <img\n"
                                        + "                                                                            src=\"data:image/jpg/png;base64," + userDTO.getImage() + "\"\n"
                                        + "                                                                            alt=\"\"\n"
                                        + "                                                                            />\n"
                                        + "                                                            </div>\n"
                                        + "                                                            <div class=\"user-item-showcase\">\n"
                                        + "                                                                <h4 class=\"user-item-name\">\n"
                                        + "                                                                    " + userDTO.getFullName() + "\n"
                                        + "                                                                </h4>\n"
                                        + "                                                                <div class=\"user-item-email\">\n"
                                        + "                                                                    " + userDTO.getEmail() + "\n"
                                        + "                                                                </div>\n"
                                        + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                        + "                                                            </div>\n"
                                        + "                                                        </div>\n"
                                        + "                                                        <div class=\"user-item-bottom\">\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                        + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                        + "                                                                >Inactivate\n"
                                        + "                                                            </a>\n"
                                        + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                        + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                        + "                                                                >Activate\n"
                                        + "                                                            </a>\n"
                                        + "                                                        </div>\n"
                                        + "                                                    </div>");
                            }
                        }
                    }

                    break;
                case "1":
                    List<UserDTO> list_student = dao.showAllStudentNext(Integer.parseInt(amount));
                    for (UserDTO userDTO : list_student) {
                        if (userDTO.getImage().startsWith("http")) {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"" + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        } else {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"data:image/jpg/png;base64," + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        }
                    }
                    break;
                case "2":
                    List<UserDTO> list_employee = dao.showAllEmployeeNext(Integer.parseInt(amount));
                    for (UserDTO userDTO : list_employee) {
                        if (userDTO.getImage().startsWith("http")) {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"" + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        } else {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"data:image/jpg/png;base64," + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        }
                    }
                    break;
                case "3":
                    List<UserDTO> list_active = dao.showAllActiveNext(Integer.parseInt(amount));
                    for (UserDTO userDTO : list_active) {
                        if (userDTO.getImage().startsWith("http")) {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"" + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        } else {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"data:image/jpg/png;base64," + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        }
                    }
                    break;
                default:
                    List<UserDTO> list_inActive = dao.showAllInactiveNext(Integer.parseInt(amount));
                    for (UserDTO userDTO : list_inActive) {
                        if (userDTO.getImage().startsWith("http")) {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"" + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        } else {
                            out.println("<div class=\"user-item " + userDTO.getStatusID() + "\">\n"
                                    + "                                                        <div class=\"user-item-heading\">\n"
                                    + "                                                            <div class=\"user-item-image\">\n"
                                    + "                                                                        <img\n"
                                    + "                                                                            src=\"data:image/jpg/png;base64," + userDTO.getImage() + "\"\n"
                                    + "                                                                            alt=\"\"\n"
                                    + "                                                                            />\n"
                                    + "                                                            </div>\n"
                                    + "                                                            <div class=\"user-item-showcase\">\n"
                                    + "                                                                <h4 class=\"user-item-name\">\n"
                                    + "                                                                    " + userDTO.getFullName() + "\n"
                                    + "                                                                </h4>\n"
                                    + "                                                                <div class=\"user-item-email\">\n"
                                    + "                                                                    " + userDTO.getEmail() + "\n"
                                    + "                                                                </div>\n"
                                    + "                                                                <a href=\"#\" class=\"btn btn--" + userDTO.getStatusID() + "\">" + userDTO.getStatusName() + "</a>\n"
                                    + "                                                            </div>\n"
                                    + "                                                        </div>\n"
                                    + "                                                        <div class=\"user-item-bottom\">\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link inactive\">\n"
                                    + "                                                                <ion-icon name=\"close-outline\"></ion-icon\n"
                                    + "                                                                >Inactivate\n"
                                    + "                                                            </a>\n"
                                    + "                                                            <a href=\"AUController?userID=" + userDTO.getUserID() + "&StatusID=" + userDTO.getStatusID() + "&style_flag=all\" class=\"user-item-link active\">\n"
                                    + "                                                                <ion-icon name=\"checkmark-outline\"></ion-icon\n"
                                    + "                                                                >Activate\n"
                                    + "                                                            </a>\n"
                                    + "                                                        </div>\n"
                                    + "                                                    </div>");
                        }
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
