/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.feedback;

import app.users.UserHistoryDTO;
import app.utils.DBUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
public class FeedbackDAO {

    public boolean sendBanned(FeedbackDetailDTO detail, String userEmail, String banReason) {
        String email;
        Boolean check = false;
        String name = "Facility's Feedback System";
        email = userEmail;
        String subject = "Facility's Feedback System Alert";

        final String username = "fptfacilityfeedback@gmail.com";//your email id
        final String password = "Hoangtuquan2";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //set nguoi nhan 

            StringBuffer body = new StringBuffer("<html>");
            String deviceName = detail.getDeviceName();
            String quantity = detail.getQuanity();
            String location = detail.getLocation();
            String date = detail.getDate();
            String reason = detail.getReason();
            String banreason = banReason;
            body.append("<!DOCTYPE ><html style=\"font-size: 62.5%\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> <title>Demystifying Email Design</title> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /> <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700;800&display=swap\" rel=\"stylesheet\" /> <link href=\"https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap\" rel=\"stylesheet\" /> <script type=\"module\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js\" ></script> <script nomodule src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js\" ></script> </head> <body style=\"margin: 0; padding: 0\"> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"table-main\" > <!-- Header --> <tr> <td align=\"center\" bgcolor=\"#e6bb7a\" style=\"padding: 2rem 0\"> <img src=\"https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png\" alt=\"Creating Email Magic\" width=\"80\" height=\"80\" style=\"display: block\" class=\"logo\" /> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"80%\" style=\"padding: 1.5rem 0 0\" > <tr> <td valign=\"top\" align=\"center\" style=\" font-size: 2rem; font-weight: 700; letter-spacing: 1rem; font-family: 'Poppins', sans-serif; \" > Traversy </td> </tr> <tr> <td valign=\"top\" align=\"center\" style=\" font-family: 'Dancing Script', cursive; font-size: 3.5rem; font-weight: 600; \" > <span style=\"color: #1f6a7e; font-family: 'Dancing Script', cursive\" >Announcement</span > Email </td> </tr> </table> </td> </tr> <!-- Body (Main Content)--> <tr> <td bgcolor=\"#fff\" style=\"padding: 20px\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\" color: #151111; font-weight: 600; font-size: 30px; font-family: 'Poppins', sans-serif; \" > Hello ! </td> </tr> <tr> <td style=\" color: #151111; font-weight: 500; font-size: 14px; padding: 10px 0; font-family: 'Poppins', sans-serif; \" > This email is sent automatically from the <a href=\"http://localhost:8084/SWP391_PROJECT/\" style=\" font-weight: 800; text-decoration: none; color: #151111; \" >Traversy</a > system, notifying your feedback has been denied </td> </tr> <tr> <td style=\"padding: 10px 0\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#EBEBEB\" style=\"border-radius: 10px; padding: 10px 0\" > <tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding: 0 10px\" > <tr> "
                    + "<td style=\" font-weight: 600; font-size: 18px; line-height: 0; font-family: 'Poppins', sans-serif; \" >" + deviceName + "</td> "
                    + "<td align=\"right\" style=\" color: #a7a7a7; font-weight: 500; font-size: 10px; font-family: 'Poppins', sans-serif; \" >" + date + "</td>"
                    + " </tr> </table> </td> </tr> <tr> "
                    + "<td style=\" font-size: 14px; padding: 0 10px; font-family: 'Poppins', sans-serif; \" >Room: " + location + "</td> "
                    + "</tr> <tr> "
                    + "<td style=\" font-size: 14px; padding: 0 10px; font-family: 'Poppins', sans-serif; \" >Quantity: " + quantity + "</td> "
                    + "</tr> <tr> "
                    + "<td style=\" font-size: 14px; padding: 0 10px; font-family: 'Poppins', sans-serif; \" >Reason: " + reason + " </td> "
                    + "</tr> <tr> "
                    + "<td style=\" font-size: 14px; padding: 30px 10px 0; font-family: 'Poppins', sans-serif; \" > <span style=\"font-weight: 600\">Ban Reason: </span>" + banreason + "</td> "
                    + "</tr> </table> </td> </tr> </table> </td> </tr> <!-- Footer --> <tr> <td bgcolor=\"#e6bb7a\" style=\"padding: 30px 30px 30px 30px\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family: 'Poppins', sans-serif\" > <tr> <td width=\"75%\" style=\"font-size: 12px\"> Copyright &copy; 2021 Traversy<br /> Designed by <a href=\"http://localhost:8084/SWP391_PROJECT/\" style=\" color: #615d58; text-decoration: none; font-weight: 600; \" >MinhDuc</a > All Rights Reserved. </td> <td align=\"right\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> <tr> <td> <a href=\"https://www.facebook.com/\" style=\"color: #151111; font-size: 25px\" > <img\n"
                    + "                          src=\"https://toppng.com/uploads/preview/facebook-logo-black-facebook-logo-png-black-115636506480t2wll0es7.png\"\n"
                    + "                          width=\"25\"\n"
                    + "                          height=\"25\"\n"
                    + "                          alt=\"\"\n"
                    + "                          style=\"border-radius: 50%;object-fit: cover\"\n"
                    + "                        /> </a> </td> <td style=\"font-size: 0; line-height: 0\" width=\"20\"> &nbsp; </td> <td> <a href=\"http://www.twitter.com/\" style=\"color: #151111; font-size: 25px\" > <img\n"
                    + "                          src=\"https://pngimg.com/uploads/twitter/twitter_PNG2.png\"\n"
                    + "                          width=\"25\"\n"
                    + "                          height=\"25\"\n"
                    + "                          alt=\"\"\n"
                    + "                          style=\"object-fit: cover\"\n"
                    + "                        /> </a> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </body></html>");

            message.setContent(body.toString(), "text/html");
            message.setSubject(subject);
            Transport.send(message);
            check = true;

        } catch (Exception e) {
        }

        return check;
    }

    public boolean sendBanned2(FeedbackDetailDTO detail, String userEmail, String banReason, int level, String time) {
        String email;
        Boolean check = false;
        String name = "Facility's Feedback System";
        email = userEmail;
        String subject = "Facility's Feedback System Alert";

        final String username = "fptfacilityfeedback@gmail.com";//your email id
        final String password = "Hoangtuquan2";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //set nguoi nhan 

            StringBuffer body = new StringBuffer("<html>");
            String deviceName = detail.getDeviceName();
            String quantity = detail.getQuanity();
            String location = detail.getLocation();
            String date = detail.getDate();
            String reason = detail.getReason();
            body.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Demystifying Email Design</title><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">"
                    + "<script type=\"module\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js\"></script><script nomodule=\"\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js\"></script></head><body style=\"margin:0;padding:0\"><style></style><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"630\" class=\"table-main\"><tbody><tr><td align=\"center\" bgcolor=\"#e6bb7a\" style=\"padding:20px 0\"><img src=\"https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png\" alt=\"Creating Email Magic\" width=\"80\" height=\"80\" style=\"display:block\" class=\"logo\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"60%\" style=\"padding:15px 0 0\"><tbody>"
                    + "<tr><td valign=\"top\" align=\"center\" style=\"font-size:20px;font-weight:700;letter-spacing:1rem;font-family:Poppins,sans-serif\">Traversy</td></tr><tr><td valign=\"top\" align=\"center\" style=\"font-family:'Dancing Script',cursive;font-size:35px;font-weight:600\"><span style=\"color:#1f6a7e;font-family:'Dancing Script',cursive\">Announcement</span> Email</td></tr></tbody></table></td></tr><tr><td bgcolor=\"#fff\" style=\"padding:20px\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr><td style=\"color:#151111;font-weight:600;font-size:30px;font-family:Poppins,sans-serif\">Hello !</td></tr><tr><td style=\"color:#151111;font-weight:500;font-size:14px;padding:10px 0;font-family:Poppins,sans-serif\">This email is sent automatically from the <a href=\"http://localhost:8084/SWP391_PROJECT/\" style=\"font-weight:800;text-decoration:none;color:#151111\">Traversy</a> system, notifying your feedback has been denied</td>"
                    + "</tr><tr><td style=\"padding:10px 0\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#EBEBEB\" style=\"border-radius:10px;padding:10px 0\"><tbody><tr><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding:0 10px\"><tbody><tr><td style=\"font-weight:600;font-size:20px;line-height:0;font-family:Poppins,sans-serif\">" + detail.getDeviceName() + "</td><td align=\"right\" style=\"color:#a7a7a7;font-weight:500;font-size:13.5px;font-family:Poppins,sans-serif\">Oct 21 2021</td></tr></tbody></table></td></tr><tr><td style=\"font-size:14px;padding:0 10px;font-family:Poppins,sans-serif\">Room " + detail.getLocation() + "</td></tr><tr><td style=\"font-size:14px;padding:0 10px;font-family:Poppins,sans-serif\">Quantity: " + detail.getQuanity() + "</td></tr><tr><td style=\"font-size:14px;padding:0 10px;font-family:Poppins,sans-serif\">Reason: Broken</td></tr><tr><td style=\"font-size:14px;padding:30px 10px 0;font-family:Poppins,sans-serif\">"
                    + "<span style=\"font-weight:600\">Ban Reason:</span> " + banReason + "</td></tr></tbody></table></td></tr><tr><td style=\"color:#151111;font-weight:500;font-size:14px;padding:10px 0;font-family:Poppins,sans-serif\">Your account have been <span style=\"font-weight:800;text-decoration:none;color:#151111\">warning at level " + level + "</span> , so that it have been locked " + time + " from now on</td></tr></tbody></table></td></tr><tr><td bgcolor=\"#e6bb7a\" style=\"padding:30px 30px 30px 30px\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family:Poppins,sans-serif\"><tbody><tr><td width=\"75%\" style=\"font-size:12px\">Copyright © 2021 Traversy<br>Designed by <a href=\"#\" style=\"color:#615d58;text-decoration:none;font-weight:600\">MinhDuc</a> All Rights Reserved.</td><td align=\"right\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><a href=\"https://www.facebook.com/\" style=\"color:#151111;font-size:25px\"><img\n"
                    + "                          src=\"https://toppng.com/uploads/preview/facebook-logo-black-facebook-logo-png-black-115636506480t2wll0es7.png\"\n"
                    + "                          width=\"25\"\n"
                    + "                          height=\"25\"\n"
                    + "                          alt=\"\"\n"
                    + "                          style=\"border-radius: 50%\"\n"
                    + "                        /></a></td><td style=\"font-size:0;line-height:0\" width=\"20\">&amp;nbsp;</td><td>"
                    + "<a href=\"http://www.twitter.com/\" style=\"color:#151111;font-size:25px\">"
                    + "<img\n"
                    + "                          src=\"https://pngimg.com/uploads/twitter/twitter_PNG2.png\"\n"
                    + "                          width=\"25\"\n"
                    + "                          height=\"25\"\n"
                    + "                          alt=\"\"\n"
                    + "                          style=\"object-fit: cover\"\n"
                    + "                        /></a></td></tr></tbody></table></td></tr></tbody>"
                    + "</table></td></tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</body>"
                    + "</html>");

            message.setContent(body.toString(), "text/html");
            message.setSubject(subject);
            Transport.send(message);
            check = true;

        } catch (Exception e) {
        }

        return check;
    }

    public boolean sendLastWarning(String userEmail) {
        String email;
        Boolean check = false;
        String name = "Facility's Feedback System";
        email = userEmail;
        String subject = "Facility's Feedback System Alert";

        final String username = "fptfacilityfeedback@gmail.com";//your email id
        final String password = "Hoangtuquan2";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //set nguoi nhan 

            StringBuffer body = new StringBuffer("<html>");

            body.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Demystifying Email Design</title><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><script type=\"module\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js\"></script><script nomodule=\"\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js\"></script></head><body style=\"margin:0;padding:0\"><style></style><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"630\" class=\"table-main\"><tbody><tr><td align=\"center\" bgcolor=\"#e6bb7a\" style=\"padding:20px 0\"><img src=\"https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png\" alt=\"Creating Email Magic\" width=\"80\" height=\"80\" style=\"display:block\" class=\"logo\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"60%\" style=\"padding:15px 0 0\"><tbody><tr><td valign=\"top\" align=\"center\" style=\"font-size:20px;font-weight:700;letter-spacing:1rem;font-family:Poppins,sans-serif\">Traversy</td></tr><tr><td valign=\"top\" align=\"center\" style=\"font-family:'Dancing Script',cursive;font-size:35px;font-weight:600\"><span style=\"color:#1f6a7e;font-family:'Dancing Script',cursive\">Violation</span> Email</td></tr></tbody></table></td></tr><tr><td bgcolor=\"#fff\" style=\"padding:20px\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr><td style=\"color:#151111;font-weight:600;font-size:30px;font-family:Poppins,sans-serif\">Hello !</td></tr><tr><td style=\"color:#151111;font-weight:500;font-size:14px;padding:10px 0;font-family:Poppins,sans-serif\">I am a member of <a href=\"http://localhost:8084/SWP391_PROJECT\" style=\"font-weight:800;color:#151111;text-decoration:none\">Traversy</a> System, and I am reaching out to you regarding your email for activate account.</td></tr><tr><td style=\"color:#151111;font-weight:500;font-size:14px;font-family:Poppins,sans-serif\"><span style=\"font-weight:800\">As a one-time exception</span>, we've granted you another chance to rejoin the <a href=\"http://localhost:8084/SWP391_PROJECT\" style=\"font-weight:800;color:#151111;text-decoration:none\">Facilities Feedback System</a></td></tr><tr><td style=\"color:#151111;font-weight:500;font-size:14px;padding:10px 0;font-family:Poppins,sans-serif\">Thank you for helping us uphold a strong platform of integrity.</td></tr></tbody></table></td></tr><tr><td bgcolor=\"#e6bb7a\" style=\"padding:30px 30px 30px 30px\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family:Poppins,sans-serif\"><tbody><tr><td width=\"75%\" style=\"font-size:12px\">Copyright © 2021 Traversy<br>Designed by <a href=\"#\" style=\"color:#615d58;text-decoration:none;font-weight:600\">MinhDuc</a> All Rights Reserved.</td><td align=\"right\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><a href=\"https://www.facebook.com/\" style=\"color:#151111;font-size:25px\"><img src=\"https://toppng.com/uploads/preview/facebook-logo-black-facebook-logo-png-black-115636506480t2wll0es7.png\" width=\"25\" height=\"25\" alt=\"\" style=\"border-radius:50%\"></a></td><td style=\"font-size:0;line-height:0\" width=\"20\">&amp;nbsp;</td><td><a href=\"http://www.twitter.com/\" style=\"color:#151111;font-size:25px\"><img src=\"https://pngimg.com/uploads/twitter/twitter_PNG2.png\" width=\"25\" height=\"25\" alt=\"\" style=\"object-fit:cover\"></a></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");

            message.setContent(body.toString(), "text/html");
            message.setSubject(subject);
            Transport.send(message);
            check = true;

        } catch (Exception e) {
        }

        return check;
    }

    public boolean sendDone(List<FeedbackDetailDTO> list, String userEmail) {
        String email;
        Boolean check = false;
        String name = "Facility's Feedback System";
        email = userEmail;
        String subject = "Facility's Feedback System Notification";

        final String username = "fptfacilityfeedback@gmail.com";//your email id
        final String password = "Hoangtuquan2";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //set nguoi nhan 

            StringBuffer body = new StringBuffer("<html>");
            body.append("<!DOCTYPE ><html style=\"font-size: 62.5%\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> <title>Demystifying Email Design</title> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /> <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700;800&display=swap\" rel=\"stylesheet\" /> <link href=\"https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&display=swap\" rel=\"stylesheet\" /> <script type=\"module\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js\" ></script> <script nomodule src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js\" ></script> </head> <body style=\"margin: 0; padding: 0\"> <table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" align=\"center\" border=\"0\" > <!-- Header --> <tr> <td align=\"center\" bgcolor=\"#e6bb7a\" style=\"padding: 2rem 0\"> <img src=\"https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png\" alt=\"\" width=\"80\" height=\"80\" style=\"display: block\" class=\"logo\" /> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"80%\" style=\"padding: 1.5rem 0 0\" > <tr> <td valign=\"top\" align=\"center\" style=\" font-size: 2rem; font-weight: 700; letter-spacing: 1rem; font-family: 'Poppins', sans-serif; \" > Traversy </td> </tr> <tr> <td valign=\"top\" align=\"center\" style=\" font-family: 'Dancing Script', cursive; font-size: 3.5rem; font-weight: 600; \" > <span style=\"color: #1f6a7e; font-family: 'Dancing Script', cursive\" >Announcement</span > Email </td> </tr> </table> </td> </tr> <!-- Body (Main Content)--> <tr> <td bgcolor=\"#fff\" style=\"padding: 20px\"> "
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\" color: #151111; font-weight: 800; font-size: 30px; font-family: 'Poppins', sans-serif; \" > Hello ! </td> </tr> <tr> <td style=\" color: #151111; font-weight: 600; font-size: 14px; padding: 10px 0; font-family: 'Poppins', sans-serif; \" > Thank you for all your assistance, your feedback has been already processed. Detail below: </td> </tr> <!-- Back end infomation --> <!-- Start --> "
                    + "<!-- Mấy anh điền thông tin của feedback tổng vào đây. Nếu mấy a kh sử dụng tới nó thì xóa luôn cái tr ở dưới đây luôn nhé. Lưu ý hãy thu nhỏ lại r xóa để tránh nhầm lẫn --> "
                    + " <!-- Mấy anh điền thông tin của feedback detail vào đây 1. Ở bên trong mấy anh sẽ thấy 1 cái table, mấy anh mở cái table đó ra sẽ thấy 2 tr "
                    + "(1 cái tr không có style và 1 cái tr có 1 style inline) thì mấy a sẽ lập 2 cái tr đó trong vòng lặp detail của mấy anh. "
                    + "2. Lưu ý: ở phần tử cuối cùng thì cái tr có style inline vì đơn giản nó là cái khoảng cách vì trong email k đẩy margin được v nên tui dùng cách này."
                    + " Nếu phần tử cuối mấy a vẫn để cái tr đó thì khi gửi lên email mấy a sẽ thấy phần body (màu trắng) sẽ cách header (màu vàng) và phần nội dung ở dưới không giống nhau và sẽ rất xấu --> "
                    + "<tr> "
                    + "<td style=\"padding: 10px 0\"> "
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\" border-radius: 10px; padding: 0 0 0 50px; font-size: 12px; font-family: 'Poppins', sans-serif; \" > ");
            for (FeedbackDetailDTO detail : list) {
                String deviceName = detail.getDeviceName();
                String quantity = detail.getQuanity();
                String location = detail.getLocation();
                String date = detail.getDate();
                String reason = detail.getReason();
                String des = detail.getDescription();
                body.append("<tr> <td> "
                        + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#EBEBEB\" style=\" padding: 10px; font-size: 12px; border-radius: 6px; font-family: 'Poppins', sans-serif; \" > "
                        + "<tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family: 'Poppins', sans-serif\" > <tr> "
                        + "<td style=\" font-size: 18px; font-weight: 600; line-height: 0; \" >" + deviceName + "</td> "
                        + "<td align=\"right\" style=\" font-size: 11px; font-weight: 600; color: #a7a7a7; \" >" + date + " </td> "
                        + "</tr> </table> </td> </tr> <tr>"
                        + " <td style=\"padding: 5px 0 0\">Room: " + location + "</td>"
                        + " </tr> <tr> "
                        + "<td>Quantity: " + quantity + "</td> "
                        + "</tr> <tr> "
                        + "<td>Reason: " + reason + "</td>"
                        + " </tr> <tr> "
                        + "<td style=\"padding: 20px 0 0\"> <span style=\"font-weight: 700\">Description:  </span> " + des + " </td>"
                        + "</tr> </table> </td> </tr> <tr style=\"font-size: 0; line-height: 0\" height=\"20\"></tr>");
            }
            body.append("</table> </td> </tr>"
                    + " <!-- End --> "
                    + "<tr> <td style=\" color: #151111; font-weight: 500; font-size: 14px; padding: 10px 0; font-family: 'Poppins', sans-serif; \" > On behalf of the customer experience team. I'm here for you as an expert on fit. please feel free to reach out <a href=\"http://localhost:8084/SWP391_PROJECT/\" style=\" color: #151111; font-weight: 800; text-decoration: none; font-family: 'Poppins', sans-serif; \" >Traversy</a > if you ever have any questions, curiosities or feedback down the track </td> </tr> </table> </td> </tr> <!-- Footer --> <tr> <td bgcolor=\"#e6bb7a\" style=\"padding: 30px 30px 30px 30px\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family: 'Poppins', sans-serif\" > <tr> <td width=\"75%\" style=\"font-size: 12px\"> Copyright &copy; 2021 Traversy<br /> Designed by <a href=\"#\" style=\" color: #615d58; text-decoration: none; font-weight: 600; \" >MinhDuc</a > All Rights Reserved. </td> <td align=\"right\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> <tr> <td> <a href=\"https://www.facebook.com/\" style=\"color: #151111; font-size: 2.5rem\" > <img\n"
                    + "                          src=\"https://toppng.com/uploads/preview/facebook-logo-black-facebook-logo-png-black-115636506480t2wll0es7.png\"\n"
                    + "                          width=\"25\"\n"
                    + "                          height=\"25\"\n"
                    + "                          alt=\"\"\n"
                    + "                          style=\"border-radius: 50%;object-fit: cover\"\n"
                    + "                        /> </a> </td> <td style=\"font-size: 0; line-height: 0\" width=\"20\"> &nbsp; </td> <td> <a href=\"http://www.twitter.com/\" style=\"color: #151111; font-size: 2.5rem\" > <img\n"
                    + "                          src=\"https://pngimg.com/uploads/twitter/twitter_PNG2.png\"\n"
                    + "                          width=\"25\"\n"
                    + "                          height=\"25\"\n"
                    + "                          alt=\"\"\n"
                    + "                          style=\"object-fit: cover\"\n"
                    + "                        /> </a> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table>"
                    + " </body></html>");

            message.setContent(body.toString(), "text/html");
            message.setSubject(subject);
            Transport.send(message);
            check = true;

        } catch (Exception e) {
        }

        return check;
    }

    public FeedbackDetailDTO getFeedbackDetailByID(String feedbackDetailID) throws SQLException, IOException {
        FeedbackDetailDTO detail = new FeedbackDetailDTO();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName  "
                        + " FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " WHERE t1.FeedbackDetailID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String quantity = rs.getString("Quantity");
                    String location = rs.getString("Location");
                    String date = rs.getString("date");
                    String reason = rs.getString("Reason");
                    String facilityName = rs.getString("FacilityName");
                    detail = new FeedbackDetailDTO("", "", "", "", quantity, reason, location, "", false, facilityName, date);

                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return detail;
    }

    public List<FeedbackDetailDTO> getListFeedbackDetailForMail(String feedbackID) throws SQLException, IOException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName  "
                        + " FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " WHERE t1.FeedbackID = ?  and t1.StatusID ='active' ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String quantity = rs.getString("Quantity");
                    String location = rs.getString("Location");
                    String date = rs.getString("date");
                    String reason = rs.getString("Reason");
                    String facilityName = rs.getString("FacilityName");
                    String des = rs.getString("Description");
                    list.add(new FeedbackDetailDTO("", "", "", "", quantity, reason, location, "", false, facilityName, date, des));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public String getUserEmailByFeedbackID(String feedbackID) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.Email from tblUser t1 "
                        + " join tblFeedback t2 on t1.UserID = t2.UserID "
                        + " where FeedbackID = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("Email");
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean insertFeedback(String userID, String date) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblFeedback( UserID, Date, statusID ) "
                        + " VALUES(?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, date);
                ps.setString(3, "pending");
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String getFeedbackID(String userId) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT TOP 1 FeedbackID "
                        + " FROM tblFeedback "
                        + " WHERE UserID = ? "
                        + " ORDER BY FeedbackID DESC ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("FeedbackID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public String getFeedbackIDByFeedbackDetailID(String feedbackDetailID) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FeedbackID "
                        + " FROM tblFeedbackDetail "
                        + " WHERE FeedbackID in (SELECT FeedbackID "
                        + " FROM tblFeedbackDetail "
                        + " WHERE feedbackDetailID = ?) and flag = 'false' and statusID = 'active' "
                        + " group by FeedbackID";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("FeedbackID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean insertFeedbackDetail(String feedbackID, FeedbackDetailDTO detail, FileInputStream image) throws SQLException, ClassNotFoundException, IOException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblFeedbackDetail( FacilityID, Quantity, Reason, Location, Image, FeedbackID, UserID, flag, Description, StatusID ) "
                        + " VALUES(?,?,?,?,?,?,?,?,?,'active') ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, detail.getFacilityID());
                ps.setString(2, detail.getQuanity());
                ps.setString(3, detail.getReason());
                ps.setString(4, detail.getLocation());
                ps.setBinaryStream(5, image);
                ps.setString(6, feedbackID);
                ps.setString(7, detail.getUserID());
                ps.setBoolean(8, detail.isFlag());
                ps.setString(9, detail.getDescription());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public List<FeedbackDTO> getListFeedbackForManager(String search) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT TOP 10 t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblFeedbackDetail t5"
                        + " ON t1.FeedbackID=t5.FeedbackID"
                        + " JOIN tblFacilities t3 "
                        + " ON t5.FacilityID = t3.FacilityID"
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t3.Name like N'" + "%" + search + "%" + "' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
                        + " order by t1.Date desc ";
                ps = conn.prepareStatement(sql);
//                ps.setString(1, '%' + search + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackForManagerNext(String search, int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblFeedbackDetail t5"
                        + " ON t1.FeedbackID=t5.FeedbackID"
                        + " JOIN tblFacilities t3 "
                        + " ON t5.FacilityID = t3.FacilityID"
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t3.Name like N'" + "%" + search + "%" + "' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
                        + " order by t1.Date desc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusDoneForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='done' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
                        + " order by t1.Date desc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusDoneAscForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT TOP 10 t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='done' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name,t1.TrashDate "
                        + " order by t1.Date asc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusDoneNext(int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='done' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
                        + " order by t1.Date asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusFixingForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='onGoing' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
                        + " order by t1.Date desc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusFixingAscForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT TOP 10 t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='onGoing' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name,t1.TrashDate "
                        + " order by t1.Date asc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusFixingNext(int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='onGoing' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
                        + " order by t1.Date asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusPendingForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='pending' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
                        + " order by t1.Date desc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusPendingAscForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT TOP 10 t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='pending'  "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name,t1.TrashDate  "
                        + " order by t1.Date asc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<FeedbackDTO> getListFeedbackByStatusPendingAscForManagerFull() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='pending'  "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name,t1.TrashDate  "
                        + " order by t1.Date asc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    String TrashDate = rs.getString("TrashDate");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName,TrashDate));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusPendingNext(int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='pending'  "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
                        + " order by t1.Date asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getAllListFeedbackByStatusForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
                        + " order by t1.Date desc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getAllListFeedbackByStatusAscForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT TOP 10 t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name,t1.TrashDate  "
                        + " order by t1.Date asc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getAllListFeedbackByStatusNext(int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
                        + " order by t1.Date asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDetailDTO> getListFeedbackDetail(String feedbackID) throws SQLException, IOException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        Blob blob = null;
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName ,t3.CategoryID as categoryID "
                        + " FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " JOIN tblUser t5 ON t1.UserID = t5.UserID "
                        + " WHERE t1.FeedbackID = ? AND t5.RoleID in ('AD','US') AND t1.StatusID ='active' ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackDetailId = rs.getString("FeedbackDetailID");
                    String facilityID = rs.getString("FacilityID");
                    String userId = rs.getString("UserID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    String des = rs.getString("Description");
                    String categoryID = rs.getString("categoryID");
                    byte[] tmp = rs.getBytes("Image");
                    if (tmp != null) {
                        base64Image = Base64.getEncoder().encodeToString(tmp);
                    } else {
                        base64Image = "";
                    }
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    list.add(new FeedbackDetailDTO(feedbackDetailId, facilityID, userId, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date, "", "", des, categoryID));

                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (inputStream != null) {
                inputStream.reset();
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.reset();
                outputStream.close();
            }
            if (blob != null) {
                blob.free();
            }
        }
        return list;
    }

    public List<FeedbackDetailDTO> getListFeedbackDetailShowEmployee(String feedbackID) throws SQLException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName, t5.FullName as fullName, t3.categoryID as categoryID "
                        + " FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " JOIN tblUser t5 ON t1.UserID = t5.UserID "
                        + " WHERE t1.FeedbackID = ? AND t5.RoleID not in ('AD','US') AND t1.StatusID ='active' ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackDetailId = rs.getString("FeedbackDetailID");
                    String facilityID = rs.getString("FacilityID");
                    String userId = rs.getString("UserID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    String des = rs.getString("Description");
                    String categoryID = rs.getString("categoryID");
                    byte[] tmp = rs.getBytes("Image");
                    if (tmp != null) {
                        base64Image = Base64.getEncoder().encodeToString(tmp);
                    } else {
                        base64Image = "";
                    }
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String fullName = rs.getString("fullName");
                    list.add(new FeedbackDetailDTO(feedbackDetailId, facilityID, userId, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date, fullName, "", des, categoryID));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteFeedback(String feedbackID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE tblFeedback "
                        + " WHERE FeedbackID=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                result = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public boolean deleteDetail(String feedbackID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE tblFeedbackDetail "
                        + " WHERE FeedbackID=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                result = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public boolean assignEmployee(String feedbackDetailId, String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedbackDetail "
                        + " SET UserID=?,AssignDate=CURRENT_TIMESTAMP "
                        + " WHERE FeedbackDetailID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userId);
                ps.setString(2, feedbackDetailId);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<String> getRoleID(String feedbackId) throws SQLException {
        List<String> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select tblUser.RoleID as roleID from tblFeedbackDetail "
                        + " join tblUser on tblFeedbackDetail.UserID = tblUser.UserID "
                        + " where FeedbackID = ? and tblUser.RoleID in ('ad','us') and tblFeedbackDetail.statusID = 'active'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result.add(rs.getString("roleID"));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean updateStatusIDFeedback(String feedbackId) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblFeedback "
                        + " SET statusID='onGoing' "
                        + " WHERE FeedbackID= ? and statusID = 'pending'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackId);
                result = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public List<FeedbackDTO> sortFeedbackAsc() throws SQLException {
        List<FeedbackDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t4.Email as email "
                        + "FROM tblFeedback t1 "
                        + " JOIN tblUser t4 "
                        + "  ON t1.UserID = t4.UserID "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email"
                        + " order by t1.Date asc";

                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> sortFeedbackDesc() throws SQLException {
        List<FeedbackDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t4.Email as email "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblUser t4 "
                        + "  ON t1.UserID = t4.UserID "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email "
                        + " order by t1.Date desc";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public String getFeedbackStatusID(String feedbackID) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select statusID from tblFeedback where feedbackID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("statusID");
                }

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public List<FeedbackDTO> getListFeedbackByStatusDenyAscForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT TOP 10 t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='decline' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name,t1.TrashDate  "
                        + " order by t1.Date asc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusDenyNext(int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='decline' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
                        + " order by t1.Date asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> getListFeedbackByStatusDenyForManager() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='decline' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
                        + " order by t1.Date desc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String statusId = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullname, statusName));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> searchListFeedback(String userID, String search) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT t1.*,t3.Email as email ,t3.FullName as fullName ,t4.Name as statusName FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblUser t3    ON t1.UserID = t3.UserID "
                        + " JOIN tblFeedbackStatus t4 ON t1.statusID = t4.FeedbackStatusID "
                        + " JOIN tblFacilities t5 "
                        + "  ON t5.FacilityID = t2.FacilityID "
                        + " WHERE t2.UserID = ? AND t5.Name like ? AND t2.flag= 'false' AND t1.statusID != 'decline' "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name "
                        + " ORDER BY t1.DATE";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackID = rs.getString("FeedbackID");
                    String date = rs.getString("date");
                    String statusID = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullName = rs.getString("fullName");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackID, userID, date, email, statusID, fullName, statusName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<FeedbackDTO> searchHistoryFeedback(String userID, String search) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT t1.*,t3.Email as email ,t3.FullName as fullName ,t4.Name as statusName FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblUser t3    ON t1.UserID = t3.UserID "
                        + " JOIN tblFeedbackStatus t4 ON t1.statusID = t4.FeedbackStatusID "
                        + " JOIN tblFacilities t5 "
                        + "  ON t5.FacilityID = t2.FacilityID "
                        + " WHERE t2.UserID = ? AND t5.Name like ? AND t2.flag= 'true' AND t1.statusID in ('decline','done','onGoing') "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name "
                        + " ORDER BY t1.DATE";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackID = rs.getString("FeedbackID");
                    String date = rs.getString("date");
                    String statusID = rs.getString("statusID");
                    String email = rs.getString("email");
                    String fullName = rs.getString("fullName");
                    String statusName = rs.getString("statusName");
                    list.add(new FeedbackDTO(feedbackID, userID, date, email, statusID, fullName, statusName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean updateDone(String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedback "
                        + " SET statusID='done' "
                        + " WHERE FeedbackID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackId);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateDecline(String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedback "
                        + " SET statusID='decline' "
                        + " WHERE FeedbackID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackId);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateInactive(String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedback "
                        + " SET statusID='inactive' "
                        + " WHERE FeedbackID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackId);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insertDeclineRespone(String feedbackId, String ReasonFeedback) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " Insert into tblBannedFeedbackDetail (BanReason,FeedbackDetailID) "
                        + " VALUES (?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, ReasonFeedback);
                ps.setString(2, feedbackId);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean declineDetail(String feedbackDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedbackDetail "
                        + " SET statusID='inactive' "
                        + " WHERE FeedbackDetailID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackDetailID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int countInactiveDetail(String feedbackID) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(FeedbackDetailID) as count"
                        + " FROM tblFeedbackDetail  "
                        + " WHERE FeedbackID = ? AND StatusID in ('active')";
                stm = conn.prepareCall(sql);
                stm.setString(1, feedbackID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public List<UserHistoryDTO> getListFeedbackForUser(String userId) throws SQLException {
        List<UserHistoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Image as image, t2.Location as location, t3.Name as deviceName, t5.Name as statusName  "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t2.FacilityID = t3.FacilityID"
                        + " JOIN tblUser t4 "
                        + "  ON t1.UserID = t4.UserID "
                        + " JOIN tblFeedbackStatus t5 "
                        + "  ON t1.StatusID = t5.FeedbackStatusID"
                        + " WHERE t1.UserID = ? AND t2.StatusID = 'active' "
                        + " Order by t1.Date";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    String statusName = rs.getString("statusName");
                    String deviceName = rs.getString("deviceName");
                    String location = rs.getString("location");
                    byte[] tmp = rs.getBytes("Image");
                    if (tmp != null) {
                        base64Image = Base64.getEncoder().encodeToString(tmp);
                    } else {
                        base64Image = "";
                    }
                    list.add(new UserHistoryDTO(feedbackId, date, base64Image, deviceName, location, statusName, statusId));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<UserHistoryDTO> getListUserFeedbackForUser(String search, String userID) throws SQLException {
        List<UserHistoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // lấy list detail bằng facilityName, join 2 bảng bằng facilityID
                String sql = "SELECT t1.*, t2.Image as image, t2.Location as location, t3.Name as deviceName, t5.Name as statusName  "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t2.FacilityID = t3.FacilityID"
                        + " JOIN tblUser t4 "
                        + "  ON t1.UserID = t4.UserID "
                        + " JOIN tblFeedbackStatus t5 "
                        + "  ON t1.StatusID = t5.FeedbackStatusID"
                        + " WHERE t1.UserID = ? AND t2.StatusID = 'active' AND t3.Name like N'" + "%" + search + "%" + "'  "
                        + " Order by t1.Date";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    String statusName = rs.getString("statusName");
                    String deviceName = rs.getString("deviceName");
                    String location = rs.getString("location");
                    byte[] tmp = rs.getBytes("Image");
                    if (tmp != null) {
                        base64Image = Base64.getEncoder().encodeToString(tmp);
                    } else {
                        base64Image = "";
                    }
                    list.add(new UserHistoryDTO(feedbackId, date, base64Image, deviceName, location, statusName, statusId));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean updateDecline(String feedbackDetailId, String userId, String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedbackDetail "
                        + " SET UserID=?, flag='false' "
                        + " WHERE FeedbackDetailID=? "
                        + " UPDATE tblFeedback "
                        + " SET StatusID = 'pending' "
                        + " WHERE FeedbackID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userId);
                ps.setString(2, feedbackDetailId);
                ps.setString(3, feedbackId);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String getUserIDByFeedbackDetailID(String feedbackDetailId) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.UserID from tblFeedback t1 "
                        + " join tblFeedbackDetail t2 on t1.FeedbackID = t2.FeedbackID "
                        + " where FeedbackDetailID = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackDetailId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("UserID");
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public String getFeedbackIDByFeedbackDetailID2(String feedbackDetailID) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT FeedbackID "
                        + " FROM tblFeedbackDetail  "
                        + " WHERE FeedbackDetailID = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("FeedbackID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public int countBanned(String userId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select Count(t1.FeedbackDetailID) as count \n"
                        + "from tblBannedFeedbackDetail t1\n"
                        + "join tblFeedbackDetail t2 on t1.FeedbackDetailID=t2.FeedbackDetailID\n"
                        + "join tblFeedback t3 on t2.FeedbackID=t3.FeedbackID\n"
                        + "where t3.UserID=?";
                stm = conn.prepareCall(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public boolean checkBanned(String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select UserID \n"
                        + "from tblWarning\n"
                        + "where UserID=?";
                stm = conn.prepareCall(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int getWarningLevel(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int level = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select WarningLevel \n"
                        + "from tblWarning\n"
                        + "where UserID=?";
                stm = conn.prepareCall(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    level = rs.getInt("WarningLevel");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return level;
    }

    public void increaseLevel(int level, String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "update tblWarning set WarningLevel=? ,date = CURRENT_TIMESTAMP \n"
                        + "where UserID=?";
                stm = conn.prepareCall(sql);
                stm.setInt(1, level);
                stm.setString(2, userId);
                stm.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void insertWarning(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "insert into tblWarning(UserID,WarningLevel,Date) values(?,1,CURRENT_TIMESTAMP)\n";
                stm = conn.prepareCall(sql);
                stm.setString(1, userId);
                stm.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Date getDateWarning(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Date date = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select Date \n"
                        + "from tblWarning\n"
                        + "where UserID=?";
                stm = conn.prepareCall(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    date = rs.getDate("Date");
                    Timestamp timestamp = rs.getTimestamp("Date");
                    if (timestamp != null) {
                        date = new java.util.Date(timestamp.getTime());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return date;
    }
}
