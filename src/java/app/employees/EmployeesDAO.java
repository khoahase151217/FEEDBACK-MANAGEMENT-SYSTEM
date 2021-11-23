/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.employees;

import app.feedback.FeedbackDTO;
import app.feedback.FeedbackDetailDTO;
import app.response.ResponseDTO;
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

    public List<UserDTO> showEmployeesListOther() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FullName,UserID,RoleID "
                        + " FROM tblUser "
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' AND ROLEID ='OT' order by fullname asc";
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

    public List<UserDTO> showEmployeesListElectric() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FullName,UserID,RoleID "
                        + " FROM tblUser "
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US'AND ROLEID ='TD' order by fullname asc";
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

    public List<UserDTO> showEmployeesListWater() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FullName,UserID,RoleID "
                        + " FROM tblUser "
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' AND ROLEID ='TN' order by fullname asc";
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

    public List<UserDTO> showEmployeesListEnv() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FullName,UserID,RoleID "
                        + " FROM tblUser "
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' AND ROLEID ='EN' order by fullname asc";
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
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName "
                        + "FROM tblFeedbackDetail t1 "
                        + "JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + "JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " WHERE t1.UserID = ? AND t1.flag='false' AND t1.FeedbackID = ? AND t1.StatusID ='active' ";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String facilityID = rs.getString("FacilityID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    String des = rs.getString("Description");
                    String image = rs.getString("ImageFirebase");
                    if (image == null) {
                        image = "";
                    }

//                    byte[] tmp = rs.getBytes("Image");
//                    if (tmp != null) {
//                        base64Image = Base64.getEncoder().encodeToString(tmp);
//                    } else {
//                        base64Image = "";
//                    }
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String feedbackDetailID = rs.getString("feedbackDetailID");
                    dto.add(new FeedbackDetailDTO(feedbackDetailID, facilityID, userID, feedbackID, quantity, reason, location, flag, facilityName, date, des, image));
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

    public List<ResponseDTO> showHistoryListFeedbackDetail(String userID, String feedbackID) throws SQLException {
        List<ResponseDTO> dto = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t4.Name as FacilityName, t2.Location as location, t2.Quantity as quantity\n"
                        + "                        FROM tblResponseFeedback t1 \n"
                        + "						join tblFeedbackDetail t2 \n"
                        + "                         ON t1.FeedbackDetailID = t2.FeedbackDetailID \n"
                        + "                         JOIN tblFeedback t3 \n"
                        + "                          ON t2.FeedbackID = t3.FeedbackID \n"
                        + "                         JOIN tblFacilities t4 \n"
                        + "                          ON t2.FacilityID = t4.FacilityID \n"
                        + "						  \n"
                        + "                         WHERE t1.UserID =? AND t2.FeedbackID = ? ";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String quantity = rs.getString("quantity");
                    String location = rs.getString("location");
                    String image = rs.getString("ImageFirebase");
//                    byte[] tmp = rs.getBytes("Image");
//                    if (tmp != null) {
//                        base64Image = Base64.getEncoder().encodeToString(tmp);
//                    } else {
//                        base64Image = "";
//                    }
                    String date = rs.getString("Date");
                    String facilityName = rs.getString("FacilityName");
                    String statusID = rs.getString("StatusID");
                    String des = rs.getString("Description");
                    String detailId = rs.getString("FeedbackDetailID");
                    String responseId = rs.getString("ResponseID");
                    dto.add(new ResponseDTO(detailId, userID, image, des, statusID, responseId, facilityName, location, "", quantity, date, ""));
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
                        + " WHERE t2.UserID = ? AND t2.flag= 'false' "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name,t1.TrashDate,t1.comment,t1.DoneDate  "
                        + " ORDER BY t1.feedbackID desc";
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
                        + " join tblResponseFeedback t5 on t2.FeedbackDetailID=t5.FeedbackDetailID "
                        + " JOIN tblUser t3    ON t1.UserID = t3.UserID "
                        + " JOIN tblFeedbackStatus t4 ON t1.statusID = t4.FeedbackStatusID "
                        + " WHERE t5.UserID = ?   "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name,t1.TrashDate,t1.comment,t1.DoneDate  "
                        + " ORDER BY t1.feedbackID desc";
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
                        + " JOIN tblUser t3 ON t1.UserID = t3.UserID "
                        + " JOIN tblFeedbackStatus t4 ON t1.statusID = t4.FeedbackStatusID "
                        + " WHERE t2.flag = 'true' AND t1.statusID not in ('decline', 'done') "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name,t1.TrashDate,t1.comment,t1.DoneDate  "
                        + " ORDER BY t1.FeedbackID desc";
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

    public List<FeedbackDTO> showListFeedbackResponseNext(int amount) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT t1.*,t3.Email as email ,t3.FullName as fullName ,t4.Name as statusName FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblUser t3 ON t1.UserID = t3.UserID "
                        + " JOIN tblFeedbackStatus t4 ON t1.statusID = t4.FeedbackStatusID "
                        + " WHERE t2.flag = 'true' AND t1.statusID not in ('decline', 'done') "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name,t1.comment,t1.DoneDate  "
                        + " ORDER BY t1.DATE "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY";
                stm = conn.prepareCall(sql);
                stm.setInt(1, amount);
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

    public int countDeclineResponse(String feedbackDetailID, String userID) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT COUNT(ResponseID) as count "
                        + " FROM tblResponseFeedback "
                        + " WHERE FeedbackDetailID = ? AND StatusID='decline' AND UserID=? ";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                ps.setString(2, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
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
        return count;
    }

    public int countDeclineResponse2(String feedbackDetailID) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT COUNT(ResponseID) as count "
                        + " FROM tblResponseFeedback "
                        + " WHERE FeedbackDetailID = ? AND StatusID='decline' ";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
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
        return count;
    }

    public String getDeclineResponeForFeedback(String feedbackDetailID) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String reason = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "  SELECT top 1 t2.DeclinedReason as reason\n"
                        + " FROM tblResponseFeedback t1\n"
                        + " JOIN tblDeclinedResponse t2 on t2.ResponseID =t1.ResponseID\n"
                        + " WHERE t1.FeedbackDetailID = ? AND t1.StatusID='decline' \n"
                        + " GROUP BY t2.DeclinedReason \n"
                        + " Order by t2.DeclinedReason desc";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    reason = rs.getString("reason");
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
        return reason;
    }

    public String getUserIDDecline(String feedbackDetailID) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userID = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT top 1 t1.UserID\n"
                        + "FROM tblResponseFeedback t1\n"
                        + "JOIN tblDeclinedResponse t2 on t2.ResponseID =t1.ResponseID\n"
                        + "WHERE t1.FeedbackDetailID = ? AND t1.StatusID='decline' \n"
                        + "GROUP BY t1.UserID ,t1.realtime\n"
                        + "Order by t1.realtime desc";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    userID = rs.getString("UserID");
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
        return userID;
    }

    public String getEmployeeDeclineResponeForFeedback(String userID) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String fullName = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " select t1.FullName\n"
                        + "from tblUser t1\n"
                        + "where UserID = ? ";
                ps = conn.prepareCall(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("FullName");
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
        return fullName;
    }

    public String getDeclineReason(String responseId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String declineReason = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT DeclinedReason "
                        + " FROM tblDeclinedResponse "
                        + " WHERE ResponseID = ? ";
                ps = conn.prepareCall(sql);
                ps.setString(1, responseId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    declineReason = rs.getString("DeclinedReason");
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
        return declineReason;
    }

    public int getResponseID2(String feedbackDetailID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int responseId = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DECLARE @N INT;\n"
                        + "SET @N=2;\n"
                        + "\n"
                        + "SELECT TOP 1 responseid\n"
                        + "  FROM (SELECT DISTINCT TOP ( @N ) responseid\n"
                        + "        --The distinct keyword is used to remove duplicates\n"
                        + "          FROM tblResponseFeedback\n"
                        + "		  where FeedbackDetailID=?\n"
                        + "        ORDER BY ResponseID DESC\n"
                        + "		 \n"
                        + "		) MAXTwo\n"
                        + "		\n"
                        + "ORDER BY ResponseID ASC;\n"
                        + " ";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    responseId = rs.getInt("responseid");
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
        return responseId;
    }

    public String getResponseID(String feedbackDetailID, String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String responseId = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " select responseid from tblResponseFeedback where FeedbackDetailID=? and UserID=?  order by ResponseID desc";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                ps.setString(2, userId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    responseId = rs.getString("responseid");
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
        return responseId;
    }

    public String checkDone(String feedbackDetailID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String status = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT t1.StatusID "
                        + " FROM tblFeedback t1"
                        + " join tblFeedbackDetail t2 on t1.FeedbackID=t2.FeedbackID "
                        + " WHERE t2.FeedbackDetailID = ? AND t1.StatusID='done' ";
                ps = conn.prepareCall(sql);
                ps.setString(1, feedbackDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    status = rs.getString("StatusID");
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
        return status;
    }

    public List<UserDTO> getListGoodEMP(String month, String year) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 3 t1.*,COUNT(t1.UserID) as count FROM tblUser t1\n"
                        + "JOIN tblResponseFeedback t2 on t1.UserID =t2.UserID \n"
                        + "WHERE t1.UserID = t2.UserID AND t2.StatusID='done' AND t2.Date like ? AND t2.Date like ? AND t1.Rating >=20 AND t1.UserID not in \n"
                        + "(select t6.UserID FROM tblDeclinedResponse t5 JOIN tblResponseFeedback t6 on t6.ResponseID=t5.ResponseID) \n"
                        + "GROUP BY t1.UserID,t1.BinaryImage,t1.Email,t1.FullName,t1.Image,t1.Password,t1.Rating,t1.RoleID,t1.StatusID \n"
                        + "ORDER BY COUNT(t1.UserID) DESC";

                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + month + "%");
                ps.setString(2, "%" + year + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    int count = rs.getInt("count");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, "", "", count)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, "", "", count)));
                    }
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
        return list;
    }

    public List<UserDTO> getListBadEMP(String month, String year) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 3 t1.*,COUNT(t1.UserID) as count\n"
                        + " FROM tblUser t1\n"
                        + " JOIN tblResponseFeedback t2 on t1.UserID =t2.UserID\n"
                        + " JOIN tblDeclinedResponse t3 on t2.ResponseID = t3.ResponseID\n"
                        + " WHERE t1.UserID = t2.UserID AND t3.ResponseID = t2.ResponseID AND  t2.Date like ? AND  t2.Date like ? \n"
                        + " GROUP BY t1.UserID,t1.BinaryImage,t1.Email,t1.FullName,t1.Image,t1.Password,t1.Rating,t1.RoleID,t1.StatusID";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + month + "%");
                ps.setString(2, "%" + year + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    int count = rs.getInt("count");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, "", "", count)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, "", "", count)));
                    }
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
        return list;
    }

    public List<UserDTO> getListBadEMPOtherRating(int check, String month, String year) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "declare @check int;\n"
                        + "set @check = ?;\n"
                        + "SELECT TOP (@check) t1.* ,COUNT(t1.UserID) as count\n"
                        + "FROM tblUser t1\n"
                        + "JOIN tblResponseFeedback t2 on t1.UserID =t2.UserID\n"
                        + "JOIN tblDeclinedResponse t3 on t2.ResponseID = t3.ResponseID\n"
                        + "WHERE t1.UserID = t2.UserID AND t3.ResponseID = t2.ResponseID  AND  t2.Date like ? AND  t2.Date like ? AND t1.rating>-20 \n"
                        + "GROUP BY t1.UserID,t1.BinaryImage,t1.Email,t1.FullName,t1.Image,t1.Password,t1.Rating,t1.RoleID,t1.StatusID";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, check);
                ps.setString(2, "%" + month + "%");
                ps.setString(3, "%" + year + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    int count = rs.getInt("count");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    int rating = rs.getInt("Rating");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, "", "", count, rating)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, "", "", count, rating)));
                    }
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
        return list;
    }

    public List<UserDTO> getListBadEMPBaseOnRating(String month, String year) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 3 t1.*\n"
                        + "FROM tblUser t1\n"
                        + "JOIN tblResponseFeedback t2 on t1.UserID =t2.UserID\n"
                        + "WHERE t1.Rating<=-20 AND  t2.Date like ? AND  t2.Date like ? \n"
                        + "GROUP BY t1.UserID,t1.BinaryImage,t1.Email,t1.FullName,t1.Image,t1.Password,t1.Rating,t1.RoleID,t1.StatusID,t1.Rating";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + month + "%");
                ps.setString(2, "%" + year + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    int rating = rs.getInt("Rating");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, "", "", 0, rating)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, "", "", 0, rating)));
                    }
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
        return list;
    }

    public List<ResponseDTO> getListRecentDeclineRespone() throws SQLException {
        List<ResponseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*,t4.Name as FacilityName ,t2.Quantity as quantity ,t2.Location as Location\n"
                        + "FROM tblResponseFeedback t1\n"
                        + "JOIN tblFeedbackDetail t2 on t1.FeedbackDetailID =t2.FeedbackDetailID\n"
                        + "JOIN tblDeclinedResponse t3 on t3.ResponseID =t1.ResponseID\n"
                        + "JOIN tblFacilities t4 on t2.FacilityID = t4.FacilityID\n"
                        + "GROUP BY t1.Date,t1.Description,t1.FeedbackDetailID,t1.Image,t1.ResponseID,t1.StatusID,t1.UserID,t1.realtime,t4.Name,t2.Quantity,t2.Location,t1.ImageFirebase\n"
                        + "ORDER BY t1.Date desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String responeID = rs.getString("ResponseID");
                    String feedbackdetailID = rs.getString("FeedbackDetailID");
                    String description = rs.getString("Description");
                    String date = rs.getString("Date");
                    String facilityName = rs.getString("FacilityName");
                    String userID = rs.getString("UserID");
                    String location = rs.getString("Location");
                    String quantity = rs.getString("quantity");
                    String statusID = rs.getString("StatusID");
                    list.add(new ResponseDTO(feedbackdetailID, userID, "", description, statusID, responeID, facilityName, location, "", quantity, date, ""));

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
        return list;
    }

    public List<ResponseDTO> getListAllRecentRespone() throws SQLException {
        List<ResponseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*,t4.Name as FacilityName ,t2.Quantity as quantity ,t2.Location as Location\n"
                        + "FROM tblResponseFeedback t1\n"
                        + "JOIN tblFeedbackDetail t2 on t1.FeedbackDetailID =t2.FeedbackDetailID\n"
                        + "JOIN tblFacilities t4 on t2.FacilityID = t4.FacilityID\n"
                        + "GROUP BY t1.Date,t1.Description,t1.FeedbackDetailID,t1.Image,t1.ResponseID,t1.StatusID,t1.UserID,t1.realtime,t4.Name,t2.Quantity,t2.Location,t1.ImageFirebase\n"
                        + "ORDER BY t1.Date desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String responeID = rs.getString("ResponseID");
                    String feedbackdetailID = rs.getString("FeedbackDetailID");
                    String description = rs.getString("Description");
                    String date = rs.getString("Date");
                    String facilityName = rs.getString("FacilityName");
                    String userID = rs.getString("UserID");
                    String location = rs.getString("Location");
                    String quantity = rs.getString("quantity");
                    String statusID = rs.getString("StatusID");
                    list.add(new ResponseDTO(feedbackdetailID, userID, "", description, statusID, responeID, facilityName, location, "", quantity, date, ""));

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
        return list;
    }

    public List<ResponseDTO> getListRecentNotDeclineResponeRating() throws SQLException {
        List<ResponseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT t1.*,t4.Name as FacilityName ,t2.Quantity as quantity ,t2.Location as Location\n"
                        + "FROM tblResponseFeedback t1\n"
                        + "JOIN tblFeedbackDetail t2 on t1.FeedbackDetailID =t2.FeedbackDetailID\n"
                        + "JOIN tblFacilities t4 on t2.FacilityID = t4.FacilityID\n"
                        + "WHERE t1.ResponseID NOT IN (Select t6.ResponseID from tblDeclinedResponse t6)\n"
                        + "GROUP BY t1.Date,t1.Description,t1.FeedbackDetailID,t1.Image,t1.ResponseID,t1.StatusID,t1.UserID,t1.realtime,t4.Name,t2.Quantity,t2.Location,t1.ImageFirebase\n"
                        + "ORDER BY t1.Date desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String responeID = rs.getString("ResponseID");
                    String feedbackdetailID = rs.getString("FeedbackDetailID");
                    String description = rs.getString("Description");
                    String date = rs.getString("Date");
                    String facilityName = rs.getString("FacilityName");
                    String userID = rs.getString("UserID");
                    String location = rs.getString("Location");
                    String quantity = rs.getString("quantity");
                    String statusID = rs.getString("StatusID");
                    list.add(new ResponseDTO(feedbackdetailID, userID, "", description, statusID, responeID, facilityName, location, "", quantity, date, ""));

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
        return list;
    }

    public List<ResponseDTO> getListRecentDoneRespone() throws SQLException {
        List<ResponseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*,t2.Quantity as quantity,t3.Name as FacilityName,t2.Location as Location\n"
                        + "FROM tblResponseFeedback t1\n"
                        + "JOIN tblFeedbackDetail t2 on t1.FeedbackDetailID =t2.FeedbackDetailID\n"
                        + "JOIN tblFacilities t3 on t2.FacilityID = t3.FacilityID\n"
                        + "WHERE t1.StatusID='done' "
                        + "GROUP BY t1.Date,t1.Description,t1.FeedbackDetailID,t1.Image,t1.ResponseID,t1.StatusID,t1.UserID,t1.realtime,t3.Name,t2.Quantity,t2.Location,t1.ImageFirebase\n"
                        + "ORDER BY t1.Date desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String responeID = rs.getString("ResponseID");
                    String feedbackdetailID = rs.getString("FeedbackDetailID");
                    String description = rs.getString("Description");
                    String date = rs.getString("Date");
                    String userID = rs.getString("UserID");
                    String facilityName = rs.getString("FacilityName");
                    String location = rs.getString("Location");
                    String quantity = rs.getString("quantity");
                    String statusID = rs.getString("StatusID");
                    list.add(new ResponseDTO(feedbackdetailID, userID, "", description, statusID, responeID, facilityName, location, "", quantity, date, ""));
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
        return list;
    }
}
