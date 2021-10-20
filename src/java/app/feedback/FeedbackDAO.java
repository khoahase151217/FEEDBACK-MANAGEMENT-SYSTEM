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
}
