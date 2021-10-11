/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.employees;

import app.feedback.FeedbackDTO;
import app.feedback.FeedbackDetailDTO;
import app.users.UserDTO;
import app.utils.DBUtils;
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
public class EmployeesDAO {

    public List<UserDTO> showEmployeesList() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*,b.Name as RoleName "
                        + " FROM tblUser a "
                        + " JOIN tblRole b on a.RoleID = b.RoleID "
                        + " WHERE NOT a.RoleID = 'AD' AND NOT a.ROLEID ='US' ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String password = rs.getString("Password");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    String RoleName = rs.getString("RoleName");
                    list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName)));
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

    public List<UserDTO> showEmployeesListWithCondition() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FullName,UserID,RoleID "
                        + " FROM tblUser "
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String roleID = rs.getString("RoleID");
                    list.add(new UserDTO(userID, name, roleID));
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

    public List<UserDTO> getListEmp(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT a.*,b.Name as RoleName "
                        + " FROM tblUser a "
                        + " JOIN tblRole b on a.RoleID = b.RoleID "
                        + " WHERE FullName like ? AND NOT a.RoleID = 'AD' AND NOT a.ROLEID ='US'";
                stm = conn.prepareCall(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String password = rs.getString("Password");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    String RoleName = rs.getString("RoleName");
                    list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName)));
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

    public List<FeedbackDetailDTO> getListFeedback(String search, String userID) throws SQLException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName   "
                        + "FROM tblFeedbackDetail t1 "
                        + "JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + "JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " WHERE t3.Name like N'" + "%" + search + "%" + "' AND t1.UserID = ? AND NOT t1.FeedbackStatusID='done'";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackDetailId = rs.getString("FeedbackDetailID");
                    String facilityID = rs.getString("FacilityID");
                    String userId = rs.getString("UserID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    String image = rs.getString("Image");
                    String feedbackID = rs.getString("FeedbackID");
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    list.add(new FeedbackDetailDTO(feedbackDetailId, facilityID, userID, feedbackID, quantity, reason, location, image, flag, facilityName, date));
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

    public List<FeedbackDetailDTO> showListFeedbackDetail(String userID, String feedbackID) throws SQLException {
        List<FeedbackDetailDTO> dto = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName "
                        + "FROM tblFeedbackDetail t1 "
                        + "JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + "JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " WHERE t1.UserID = ? AND t1.flag='false' AND t1.FeedbackID = ?";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String facilityID = rs.getString("FacilityID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    byte[] tmp = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(tmp);
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String feedbackDetailID = rs.getString("feedbackDetailID");
                    dto.add(new FeedbackDetailDTO(feedbackDetailID, facilityID, userID, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date));
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
    
    public List<FeedbackDetailDTO> showHistoryListFeedbackDetail(String userID, String feedbackID) throws SQLException {
        List<FeedbackDetailDTO> dto = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName "
                        + "FROM tblFeedbackDetail t1 "
                        + "JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + "JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " WHERE t1.UserID = ? AND t1.flag='true' AND t1.FeedbackID = ?";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String facilityID = rs.getString("FacilityID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    byte[] tmp = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(tmp);
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String feedbackDetailID = rs.getString("feedbackDetailID");
                    dto.add(new FeedbackDetailDTO(feedbackDetailID, facilityID, userID, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date));
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

    public List<FeedbackDTO> showListFeedback(String userID) throws SQLException {
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
                        + " WHERE t2.UserID = ? AND t2.flag= 'false' AND t1.statusID != 'decline' "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name "
                        + " ORDER BY t1.DATE";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
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

    public List<FeedbackDTO> showHistoryFeedback(String userID) throws SQLException {
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
                        + " WHERE t2.UserID = ? AND t2.flag= 'true' AND t1.statusID in ('decline','done','onGoing') "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name "
                        + " ORDER BY t1.DATE";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
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
    
     public List<FeedbackDTO> showListFeedbackResponse() throws SQLException {
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
                        + " WHERE t2.flag = 'true' AND t1.statusID != 'decline' "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name "
                        + " ORDER BY t1.DATE";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackID = rs.getString("FeedbackID");
                    String userID = rs.getString("UserID");
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
}
