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
import java.sql.SQLException;

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
                        + " SET flag=true "
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
}
