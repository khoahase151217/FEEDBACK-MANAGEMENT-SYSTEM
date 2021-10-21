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
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' AND ROLEID ='OT' ";
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
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US'AND ROLEID ='TD' ";
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
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' AND ROLEID ='TN' ";
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
                        + " WHERE NOT RoleID = 'AD' AND statusID= 'active' AND NOT ROLEID ='US' AND ROLEID ='EN' ";
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
                    byte[] tmp = rs.getBytes("Image");
                    if (tmp != null) {
                        base64Image = Base64.getEncoder().encodeToString(tmp);
                    } else {
                        base64Image = "";
                    }
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String feedbackDetailID = rs.getString("feedbackDetailID");
                    dto.add(new FeedbackDetailDTO(feedbackDetailID, facilityID, userID, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date, des));
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
        String base64Image;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Date as Date, t3.Name as FacilityName, t4.Description as des, t4.Image as img "
                        + "FROM tblFeedbackDetail t1 "
                        + " JOIN tblFeedback t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t1.FacilityID = t3.FacilityID "
                        + " JOIN tblResponseFeedback t4 "
                        + " ON t1.FeedbackDetailID = t4.FeedbackDetailID "
                        + " WHERE t1.UserID = ? AND t1.flag='true' AND t1.FeedbackID = ? AND t1.StatusID ='active' AND t4.StatusID='done' ";
                stm = conn.prepareCall(sql);
                stm.setString(1, userID);
                stm.setString(2, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String facilityID = rs.getString("FacilityID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    byte[] tmp = rs.getBytes("img");
                    if (tmp != null) {
                        base64Image = Base64.getEncoder().encodeToString(tmp);
                    } else {
                        base64Image = "";
                    }
                    boolean flag = rs.getBoolean("flag");
                    String date = rs.getString("date");
                    String facilityName = rs.getString("FacilityName");
                    String feedbackDetailID = rs.getString("feedbackDetailID");
                    String des = rs.getString("des");
                    dto.add(new FeedbackDetailDTO(feedbackDetailID, facilityID, userID, feedbackID, quantity, reason, location, base64Image, flag, facilityName, date, "", des));
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
                        + " WHERE t2.UserID = ? AND t2.flag= 'true'  "
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
                        + " JOIN tblUser t3 ON t1.UserID = t3.UserID "
                        + " JOIN tblFeedbackStatus t4 ON t1.statusID = t4.FeedbackStatusID "
                        + " WHERE t2.flag = 'true' AND t1.statusID not in ('decline', 'done') "
                        + " group by t1.Date ,t1.FeedbackID,t1.statusID, t1.UserID, t3.Email, t3.FullName, t4.Name "
                        + " ORDER BY t1.DATE ";
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

    public String getDeclineReason(int responseId) throws SQLException {
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
                ps.setInt(1, responseId);
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

    public int getResponseID(String feedbackDetailID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int responseId = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " select top 1 responseid from tblResponseFeedback where FeedbackDetailID=? order by ResponseID desc";
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

    public List<UserDTO> getListGoodEMP(String txt, String txt2, String txt3) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 3 t1.*,COUNT(t1.UserID) as count FROM tblUser t1 \n"
                        + "JOIN tblResponseFeedback t2 on t1.UserID =t2.UserID \n"
                        + "WHERE (t1.UserID = t2.UserID AND t2.StatusID='done' AND t2.Date like ? ) OR (t2.Date like ? AND t1.UserID = t2.UserID AND t2.StatusID='done')  OR (t2.Date like ? AND t1.UserID = t2.UserID AND t2.StatusID='done')\n"
                        + "GROUP BY t1.UserID,t1.BinaryImage,t1.Email,t1.FullName,t1.Image,t1.Password,t1.Rating,t1.RoleID,t1.StatusID \n"
                        + "ORDER BY COUNT(t1.UserID) DESC";

                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + txt + "%");
                ps.setString(2, "%" + txt2 + "%");
                ps.setString(3, "%" + txt3 + "%");
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

    public List<UserDTO> getListBadEMP(String txt, String txt2, String txt3) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (conn != null) {
                String sql = "SELECT TOP 3 t1.*,COUNT(t1.UserID) as count\n"
                        + " FROM tblUser t1\n"
                        + "JOIN tblResponseFeedback t2 on t1.UserID =t2.UserID\n"
                        + "JOIN tblDeclinedResponse t3 on t2.ResponseID = t3.ResponseID\n"
                        + "WHERE t1.UserID = t2.UserID AND t3.ResponseID = t2.ResponseID AND ( t2.Date like ? OR t2.Date like ? OR t2.Date like ? ) \n"
                        + "GROUP BY t1.UserID,t1.BinaryImage,t1.Email,t1.FullName,t1.Image,t1.Password,t1.Rating,t1.RoleID,t1.StatusID";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + txt + "%");
                ps.setString(2, "%" + txt2 + "%");
                ps.setString(3, "%" + txt3 + "%");
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
}
