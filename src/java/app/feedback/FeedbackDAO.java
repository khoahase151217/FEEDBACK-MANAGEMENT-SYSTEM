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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FeedbackDAO {

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
                        + " WHERE feedbackDetailID = ?) and flag = 'false' "
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
                String sql = " INSERT INTO tblFeedbackDetail( FacilityID, Quantity, Reason, Location, Image, FeedbackID, UserID, flag ) "
                        + " VALUES(?,?,?,?,?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, detail.getFacilityID());
                ps.setString(2, detail.getQuanity());
                ps.setString(3, detail.getReason());
                ps.setString(4, detail.getLocation());
                ps.setBinaryStream(5, image);
                ps.setString(6, feedbackID);
                ps.setString(7, detail.getUserID());
                ps.setBoolean(8, detail.isFlag());
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
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='done' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
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
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='onGoing' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name"
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
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='pending' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
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
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
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

    public List<UserHistoryDTO> getListFeedbackForUser(String userId) throws SQLException {
        List<UserHistoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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
                        + " WHERE t1.UserID = ? "
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
                    Blob blob = rs.getBlob("image");
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
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

    public List<FeedbackDetailDTO> getListFeedbackDetail(String feedbackID) throws SQLException, IOException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        Blob blob = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName "
                        + " FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " JOIN tblUser t5 ON t1.UserID = t5.UserID "
                        + " WHERE t1.FeedbackID = ? AND t5.RoleID in ('AD','US')";
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
                    byte[] tmp = rs.getBytes("Image");

//                    while ((bytesRead = inputStream.read(buffer)) >= 0) {
//                        for (int i = 0; i < bytesRead; i++) {
//                            outputStream.write(buffer, 0, bytesRead);
//                        }
                    String base64Image = Base64.getEncoder().encodeToString(tmp);

//                    inputStream.close();
//                    outputStream.close();
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    list.add(new FeedbackDetailDTO(feedbackDetailId, facilityID, userId, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date));

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
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName, t5.FullName as fullName "
                        + " FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " JOIN tblUser t5 ON t1.UserID = t5.UserID "
                        + " WHERE t1.FeedbackID = ? AND t5.RoleID not in ('AD','US')";
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
                    Blob blob = rs.getBlob("Image");
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String fullName = rs.getString("fullName");
                    list.add(new FeedbackDetailDTO(feedbackDetailId, facilityID, userId, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date, fullName));

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
                        + " SET UserID=? "
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
                        + " where FeedbackID = ? and tblUser.RoleID in ('ad','us')";
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
                String sql = "SELECT t1.*, t4.Email as email, t4.FullName as fullname, t2.Name as statusName "
                        + " FROM tblFeedback t1 "
                        + " JOIN tblFeedbackStatus t2 "
                        + " ON t1.StatusID=t2.FeedbackStatusID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " WHERE t1.statusID='decline' "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t1.statusID,t4.Email,t4.FullName,t2.Name "
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
}
