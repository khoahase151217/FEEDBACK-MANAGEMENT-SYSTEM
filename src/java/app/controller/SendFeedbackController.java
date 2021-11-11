/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDAO;
import app.feedback.FeedbackDetailDTO;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SendFeedbackController", urlPatterns = {"/SendFeedbackController"})
public class SendFeedbackController extends HttpServlet {

    private static final String SUCCESS = "ShowFeedbackStudentController";
    private static final String ERROR = "ShowFeedbackStudentController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        String url = ERROR;
        try {
            FeedbackDAO Fdao = new FeedbackDAO();
            FeedbackDetailDTO detail;
            HttpSession session = request.getSession();
            FileInputStream photo = null;
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH);
            String date = sdf.format(new Date());
            Fdao.insertFeedback(user.getUserID(), date);
            String feedbackId = Fdao.getFeedbackID(user.getUserID());
            String tmp;
            String description = "";
            String facilityID = "";
            String quantity = "";
            String reason = "";
            String location = "";
            String image1 = "";
            String image2 = "";
            String image3 = "";
            String image4 = "";
            int count = 0;
            int check = 0; //3
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
                // gui count =3;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                        String inputName = item.getFieldName();

                        if (inputName.equalsIgnoreCase("check")) {
                            check = Integer.parseInt(item.getString());
                        }
                        if (inputName.equalsIgnoreCase("Tmpdescription")) {
                            description = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("Tmpdevice")) {
                            facilityID = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("Tmpquantity")) {
                            quantity = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("Tmpreason")) {
                            reason = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("Tmplocation")) {

                            location = item.getString();
                        }
                        if (inputName.equalsIgnoreCase("image-1")) {

                            image1 = item.getString();
                            if (facilityID.equals("") || quantity.equals("") || reason.equals("") || location.equals("")) {
                                Fdao.deleteFeedback(feedbackId);
//                                Fdao.deleteDetail(feedbackId);
                                url = ERROR;
                                request.setAttribute("SEND_FAILURE", "active");
                                request.setAttribute("SEND_SUCCESS", "");
                                break;
                            } else {
                                if (!image1.equals("")) {
                                    detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                    Fdao.insertFeedbackDetail(feedbackId, detail, image1);
                                    count++;
                                    request.setAttribute("SEND_SUCCESS", "active");
                                    request.setAttribute("SEND_FAILURE", "");
                                    url = SUCCESS;
                                } else {
                                    detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                    Fdao.insertFeedbackDetail(feedbackId, detail, null);
                                    count++;
                                    request.setAttribute("SEND_SUCCESS", "active");
                                    request.setAttribute("SEND_FAILURE", "");
                                    url = SUCCESS;
                                }

                            }
                        }
                        if (inputName.equalsIgnoreCase("image-2")) {
                            if (check >= 2) {
                                image2 = item.getString();
                                if (facilityID.equals("") || quantity.equals("") || reason.equals("") || location.equals("")) {
                                    Fdao.deleteDetail(feedbackId);
                                    Fdao.deleteFeedback(feedbackId);
                                    url = ERROR;
                                    request.setAttribute("SEND_FAILURE", "active");
                                    request.setAttribute("SEND_SUCCESS", "");
                                    break;
                                } else {
                                    if (!image2.equals("")) {
                                        detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                        Fdao.insertFeedbackDetail(feedbackId, detail, image2);
                                        count++;
                                        request.setAttribute("SEND_SUCCESS", "active");
                                        request.setAttribute("SEND_FAILURE", "");
                                        url = SUCCESS;
                                    } else {
                                        detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                        Fdao.insertFeedbackDetail(feedbackId, detail, null);
                                        count++;
                                        request.setAttribute("SEND_SUCCESS", "active");
                                        request.setAttribute("SEND_FAILURE", "");
                                        url = SUCCESS;
                                    }

                                }
                            }
                        }
                        if (inputName.equalsIgnoreCase("image-3")) {
                            if (check >= 3) {
                                image3 = item.getString();
                                if (facilityID.equals("") || quantity.equals("") || reason.equals("") || location.equals("")) {
                                    Fdao.deleteDetail(feedbackId);
                                    Fdao.deleteFeedback(feedbackId);
                                    url = ERROR;
                                    request.setAttribute("SEND_FAILURE", "active");
                                    request.setAttribute("SEND_SUCCESS", "");
                                    break;
                                } else {
                                    if (!image3.equals("")) {
                                        detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                        Fdao.insertFeedbackDetail(feedbackId, detail, image3);
                                        count++;
                                        request.setAttribute("SEND_SUCCESS", "active");
                                        request.setAttribute("SEND_FAILURE", "");
                                        url = SUCCESS;
                                    } else {
                                        detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                        Fdao.insertFeedbackDetail(feedbackId, detail, null);
                                        count++;
                                        request.setAttribute("SEND_SUCCESS", "active");
                                        request.setAttribute("SEND_FAILURE", "");
                                        url = SUCCESS;
                                    }
                                }
                            }
                        }
                        if (inputName.equalsIgnoreCase("image-4")) {
                            if (check >= 4) {
                                image4 = item.getString();
                                if (facilityID.equals("") || quantity.equals("") || reason.equals("") || location.equals("")) {
                                    Fdao.deleteDetail(feedbackId);
                                    Fdao.deleteFeedback(feedbackId);
                                    url = ERROR;
                                    request.setAttribute("SEND_FAILURE", "active");
                                    request.setAttribute("SEND_SUCCESS", "");
                                    break;
                                } else {
                                    if (!image4.equals("")) {
                                        detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                        Fdao.insertFeedbackDetail(feedbackId, detail, image4);
                                        count++;
                                        request.setAttribute("SEND_SUCCESS", "active");
                                        request.setAttribute("SEND_FAILURE", "");
                                        url = SUCCESS;
                                    } else {
                                        detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
                                        Fdao.insertFeedbackDetail(feedbackId, detail, null);
                                        count++;
                                        request.setAttribute("SEND_SUCCESS", "active");
                                        request.setAttribute("SEND_FAILURE", "");
                                        url = SUCCESS;
                                    }

                                }
                            }
                        }

                        // Front - end 
                        if (inputName.equalsIgnoreCase("style_pipe")) {
                            String styleTmp = item.getString();
                            if (!styleTmp.equals("")) {
                                request.setAttribute("STYLE_PIPE", "active");
                            }
                        }
                        if (inputName.equalsIgnoreCase("style_list")) {
                            String styleTmp = item.getString();
                            if (!styleTmp.equals("")) {
                                request.setAttribute("STYLE_LIST", "active");
                            }
                        }
                    }
//                     else {
//                        if (count == check) {
//                            break;
//                        }
//                        if (facilityID.equals("") || quantity.equals("") || reason.equals("") || location.equals("")) {
//                            Fdao.deleteFeedback(feedbackId);
//                            Fdao.deleteDetail(feedbackId);
//                            url = ERROR;
//                            request.setAttribute("SEND_FAILURE", "active");
//                            request.setAttribute("SEND_SUCCESS", "");
//                            break;
//                        } else {
//                            tmp = item.getContentType();
//                            if (tmp.contains("image")) {
//                                photo = (FileInputStream) item.getInputStream();
//                                detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
//                                Fdao.insertFeedbackDetail(feedbackId, detail, photo);
//                                count++;
//                                request.setAttribute("SEND_SUCCESS", "active");
//                                request.setAttribute("SEND_FAILURE", "");
//                                url = SUCCESS;
//                            } else {
//                                detail = new FeedbackDetailDTO(facilityID, user.getUserID(), feedbackId, quantity, reason, location, false, description);
//                                Fdao.insertFeedbackDetail(feedbackId, detail, null);
//                                count++;
//                                request.setAttribute("SEND_SUCCESS", "active");
//                                request.setAttribute("SEND_FAILURE", "");
//                                url = SUCCESS;
//                            }
//
//                        }
//                    }
                }

            }
            request.setAttribute("SEND_FEEDBACK_FLAG", "open");

        } catch (Exception e) {

            log("Error at OrderController" + e.toString());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
