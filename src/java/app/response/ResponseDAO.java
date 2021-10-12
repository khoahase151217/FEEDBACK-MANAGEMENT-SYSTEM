/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.response;

import app.feedback.FeedbackDetailDTO;
import app.utils.DBUtils;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ResponseDAO {

    public boolean insertResponse(ResponseDTO response, FileInputStream image) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblResponseFeedback( FeedbackDetailID, UserID, Image, Description, StatusID ) "
                        + " VALUES(?,?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, response.getFeedbackDetailID());
                ps.setString(2, response.getUserID());
                ps.setBinaryStream(3, image);
                ps.setString(4, response.getDes());
                ps.setString(5, response.getStatusID());
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

    public boolean insertDeclineResponse(ResponseDTO response) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblResponseFeedback( FeedbackDetailID, UserID, Image, Description, StatusID ) "
                        + " VALUES(?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, response.getFeedbackDetailID());
                ps.setString(2, response.getUserID());
                ps.setString(3, response.getDes());
                ps.setString(4, response.getStatusID());
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

    public boolean updateFlagDetail(String feedbackDetailId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedbackDetail "
                        + " SET flag= 'true' "
                        + " WHERE FeedbackDetailID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackDetailId);
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

    public boolean updateStatusFeedback(String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedback "
                        + " SET statusID='pending' "
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

    public String getUserId(String feedbackId) throws SQLException {
        String userId = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT UserID FROM tblFeedback "
                        + " WHERE FeedbackID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getString("UserID");
                }
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
        return userId;
    }

    public boolean updateUserId(String feedbackId, String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblFeedbackDetail "
                        + " SET UserID=? "
                        + " WHERE FeedbackID=? AND flag='false' ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userId);
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

    public List<ResponseDTO> showListResponeDetail(String feedbackID) throws SQLException {
        List<ResponseDTO> dto = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.* ,t2.Location as location , t3.Name as deivcename , t2.feedbackDetailID as feedbackDetailID , t4.Name as userName "
                        + " FROM tblResponseFeedback t1 "
                        + " JOIN tblFeedbackDetail t2 "
                        + "  ON t1.feedbackDetailID = t2.feedbackDetailID"
                        + " JOIN tblFacilities t3 "
                        + " ON t3.FacilityID = t2.FacilityID "
                        + " JOIN tblUser t4 "
                        + " ON t1.UserID = t4.UserID "
                        + " JOIN tblFeedback t5 "
                        + "  ON t5.feedbackID = t2.feedbackID "
                        + " WHERE t2.flag ='true' AND t5.FeedbackID = ? ";
                stm = conn.prepareCall(sql);
                stm.setString(2, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String responseID = rs.getString("ResponseID");
                    String feedbackDetailID = rs.getString("feedbackDetailID");
                    String userName = rs.getString("userName");
                    String des = rs.getString("Description");
                    byte[] tmp = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(tmp);
                   String location = rs.getString("location");
                    String deviceName = rs.getString("deivcename");
                    dto.add(new ResponseDTO(feedbackDetailID, base64Image, des, responseID, deviceName, location, userName));
                    
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
        return dto;
    }

}
