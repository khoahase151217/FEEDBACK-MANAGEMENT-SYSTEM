/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.users;

import app.feedback.FeedbackDTO;
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
 * @author ASUS
 */
public class UserDAO {
//Login User

    public String getUserIdByName(String fullname) throws SQLException {
        String result = "";
        int count = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID FROM tblUser "
                        + " WHERE FullName = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, fullname);
                result = rs.getString("userID");
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

    public String getUserEmailByID(String userID) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT Email FROM tblUser \n"
                        + "WHERE userID = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("Email");
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

    public UserDTO getUserIdByUserID(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select FullName,email, password, tblUser.RoleID as RoleID, tblRole.name as roleName, tblUser.StatusID as StatusID, tblUserStatus.Name as statusName, Image, BinaryImage from "
                        + " tblUser join tblRole on tblUser.roleID = tblRole.roleID "
                        + " join tblUserStatus on tblUser.statusID = tblUserStatus.statusID "
                        + " WHERE userID = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String roleName = rs.getString("roleName");
                    String statusName = rs.getString("statusName");
                    String password = rs.getString("password");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    String image = rs.getString("Image");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        user = new UserDTO(userID, fullName, password, email, roleID, statusID, base64Image, roleName, statusName);
                    } else {
                        user = new UserDTO(userID, fullName, password, email, roleID, statusID, image, roleName, statusName);
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
        return user;
    }

    public UserDTO checkLoginGoogle(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select userID, FullName, password, tblUser.RoleID as RoleID, tblRole.name as roleName, tblUser.StatusID as StatusID, tblUserStatus.Name as statusName, Image, BinaryImage from "
                        + " tblUser join tblRole on tblUser.roleID = tblRole.roleID "
                        + " join tblUserStatus on tblUser.statusID = tblUserStatus.statusID "
                        + " WHERE Email =? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String roleName = rs.getString("roleName");
                    String statusName = rs.getString("statusName");
                    String password = rs.getString("password");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    String image = rs.getString("Image");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        user = new UserDTO(userID, fullName, password, email, roleID, statusID, base64Image, roleName, statusName);
                    } else {
                        user = new UserDTO(userID, fullName, password, email, roleID, statusID, image, roleName, statusName);
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

        return user;
    }

    public boolean checkDuplicateByEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT email "
                        + "FROM tblUser "
                        + "WHERE email=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
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

    public boolean InsertGoogleInformation(String email, String image) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //phải có dữ liệu mới add được email ,vì bị bắt constraint không được null
                String sql = "INSERT INTO tblUser(Email,RoleID,StatusID,Image) "
                        + " VALUES (?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, "1");
                ps.setString(3, "1");
                ps.setString(4, image);
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

    public String getStatusIdFromLogin(String email, String password) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT StatusID FROM tblUser "
                        + " WHERE email = ? and password = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("statusID");
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

    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select userID, FullName, tblUser.RoleID as RoleID, tblRole.name as roleName, tblUser.StatusID as StatusID, tblUserStatus.Name as statusName, Image, BinaryImage from "
                        + "tblUser join tblRole on tblUser.roleID = tblRole.roleID "
                        + " join tblUserStatus on tblUser.statusID = tblUserStatus.statusID "
                        + " where Email=? and Password=? ";
                st = conn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                rs = st.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String roleName = rs.getString("roleName");
                    String statusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    String image = rs.getString("Image");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        user = new UserDTO(userID, fullName, password, email, roleID, statusID, base64Image, roleName, statusName);
                    } else {
                        user = new UserDTO(userID, fullName, password, email, roleID, statusID, image, roleName, statusName);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return user;
    }

    public boolean signUp(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Insert into tblUser(UserID, FullName, Password, Email, RoleID, StatusID, Image) "
                        + " values(default,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, "US");
                ps.setString(5, user.getStatusID());
                ps.setString(6, user.getImage());
                if (ps.executeUpdate() > 0) {
                    return true;
                }
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
        return false;
    }

    //Begin of sort User
    public List<UserDTO> sortUserNameAsc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from tblUser "
                        + " where roleID = 'US' "
                        + " order by FullName Asc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO(fullName, "", email, roleID, statusID, image));

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

    public UserDTO GetMemberSignup(UserDTO member) throws SQLException {
        UserDTO user = new UserDTO();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from tblUser "
                        + " where Email=? and FullName=? and roleID=? and statusID=? and password=? and image=? ";

                st = conn.prepareStatement(sql);
                st.setString(1, member.getEmail());
                st.setString(2, member.getFullName());
                st.setString(3, member.getRoleID());
                st.setString(4, member.getStatusID());
                st.setString(5, member.getPassword());
                st.setString(6, member.getImage());
                rs = st.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    String userID = rs.getString("userID");
                    user = new UserDTO(userID, fullName, "****", email, roleID, statusID, image);
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
        return user;
    }

    public List<UserDTO> sortUserNameDesc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from tblUser"
                        + " where roleID = 'US' "
                        + " order by FullName desc";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO(fullName, "", email, roleID, statusID, image));

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

    public List<UserDTO> sortStatusAsc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from tblUser"
                        + " where roleID = 'US' "
                        + " order by StatusID asc";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO(fullName, "", email, roleID, statusID, image));

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

    public List<UserDTO> sortStatusDesc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select * from tblUser "
                        + " where roleID = 'US' "
                        + " order by StatusID desc";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO(fullName, "", email, roleID, statusID, image));

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

    //Begin of sort Employee
    public List<UserDTO> sortEmployeeNameAsc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*,b.Name as RoleName "
                        + " FROM tblUser a "
                        + " JOIN tblRole b on a.RoleID = b.RoleID "
                        + " WHERE NOT a.RoleID = 'AD' AND NOT a.ROLEID ='US' "
                        + " order by a.FullName asc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String roleName = rs.getString("RoleName");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO("", fullName, statusID, email, roleID, statusID, image, roleName));

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

    public List<UserDTO> sortEmployeeNameDesc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*,b.Name as RoleName "
                        + " FROM tblUser a "
                        + " JOIN tblRole b on a.RoleID = b.RoleID "
                        + " WHERE NOT a.RoleID = 'AD' AND NOT a.ROLEID ='US' "
                        + " order by a.FullName desc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String roleName = rs.getString("RoleName");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO("", fullName, statusID, email, roleID, statusID, image, roleName));

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

    public List<UserDTO> sortStatusEmployeeAsc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*,b.Name as RoleName "
                        + " FROM tblUser a "
                        + " JOIN tblRole b on a.RoleID = b.RoleID "
                        + " WHERE NOT a.RoleID = 'AD' AND NOT a.ROLEID ='US' "
                        + " order by a.StatusID asc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String roleName = rs.getString("RoleName");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO("", fullName, statusID, email, roleID, statusID, image, roleName));

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

    public List<UserDTO> sortStatusEmployeeDesc() throws SQLException {
        List<UserDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*,b.Name as RoleName "
                        + " FROM tblUser a "
                        + " JOIN tblRole b on a.RoleID = b.RoleID "
                        + " WHERE NOT a.RoleID = 'AD' AND NOT a.ROLEID ='US' "
                        + " order by a.StatusID desc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("RoleID");
                    String roleName = rs.getString("RoleName");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    list.add(new UserDTO("", fullName, statusID, email, roleID, statusID, image, roleName));

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
//begin of sort student view feedback

    public List<FeedbackDTO> sortFeedbackAsc(UserDTO user) throws SQLException {
        List<FeedbackDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.FeedbackID, t1.UserID, t1.Date, t4.Email as email "
                        + "FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t2.FacilityID = t3.FacilityID "
                        + " JOIN tblUser t4 "
                        + "  ON t1.UserID = t4.UserID "
                        + " WHERE t1.UserID=? "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t4.Email "
                        + " order by t1.Date asc";
                st = conn.prepareStatement(sql);
                st.setString(1, user.getUserID());
                rs = st.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String email = rs.getString("email");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email));
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

    public List<FeedbackDTO> sortFeedbackDesc(UserDTO user) throws SQLException {
        List<FeedbackDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.FeedbackID, t1.UserID, t1.Date, t4.Email as email "
                        + "FROM tblFeedback t1 "
                        + " JOIN tblFeedbackDetail t2 "
                        + "  ON t1.FeedbackID = t2.FeedbackID "
                        + " JOIN tblFacilities t3 "
                        + "  ON t2.FacilityID = t3.FacilityID "
                        + " JOIN tblUser t4 "
                        + "  ON t1.UserID = t4.UserID "
                        + " WHERE t1.UserID=? "
                        + " group by t1.FeedbackID,t1.UserID,t1.Date,t4.Email "
                        + " order by t1.Date desc";
                st = conn.prepareStatement(sql);
                st.setString(1, user.getUserID());
                rs = st.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String date = rs.getString("Date");
                    String userId = rs.getString("UserID");
                    String email = rs.getString("email");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email));
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
//Show list user

    public List<UserDTO> showAllUserAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " order by t1.FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllUserDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " order by t1.FullName Desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
                    }
                }
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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

    public List<UserDTO> showAllUserNext(int amount) throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " order by t1.FullName asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY ";
                stm = conn.prepareCall(sql);
                stm.setInt(1, amount);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllStudentAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.RoleID = 'US' "
                        + " order by t1.FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllStudentDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.RoleID = 'US' "
                        + " order by t1.FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllStudentNext(int amount) throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.RoleID = 'US' "
                        + " order by t1.FullName asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY ";
                stm = conn.prepareCall(sql);
                stm.setInt(1, amount);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllEmployeeAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.RoleID != 'US' And t1.RoleID != 'AD' "
                        + " order by t1.FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllEmployeeDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.RoleID != 'US' And t1.RoleID != 'AD' "
                        + " order by t1.FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllEmployeeNext(int amount) throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.RoleID != 'US' And t1.RoleID != 'AD' "
                        + " order by t1.FullName asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY ";
                stm = conn.prepareCall(sql);
                stm.setInt(1, amount);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllListActiveUserAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.StatusID ='active' "
                        + " order by t1.FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllListActiveDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.StatusID ='active' "
                        + " order by t1.FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllActiveNext(int amount) throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.StatusID ='active' "
                        + " order by t1.FullName asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY ";
                stm = conn.prepareCall(sql);
                stm.setInt(1, amount);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllListInactiveUserAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.StatusID ='inactive' "
                        + " order by t1.FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllListInactiveUserDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.StatusID ='inactive' "
                        + " order by t1.FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllInactiveNext(int amount) throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 "
                        + " ON t1.StatusID=t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID = t3.RoleID "
                        + " WHERE t1.StatusID ='inactive' "
                        + " order by t1.FullName asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY ";
                stm = conn.prepareCall(sql);
                stm.setInt(1, amount);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllStudentListAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblUser "
                        + " WHERE RoleID = 'US' "
                        + " order by FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showAllStudentListDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblUser "
                        + " WHERE RoleID = 'US' "
                        + " order by FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showStudentListActiveAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblUser "
                        + " WHERE RoleID = 'US' and StatusID ='AC' "
                        + " order by FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showStudentListActiveDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblUser "
                        + " WHERE RoleID = 'US' and StatusID ='AC'"
                        + " order by FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showStudentListInactiveAsc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblUser "
                        + " WHERE RoleID = 'US' and StatusID ='IN'"
                        + " order by FullName asc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> showStudentListInactiveDesc() throws SQLException {

        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblUser "
                        + " WHERE RoleID = 'US' and StatusID ='IN'"
                        + " order by FullName desc ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    //
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> getListStudent(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 15 t1.* , t2.Name as StatusName,t3.Name as roleName "
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 on t1.StatusID = t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID=t3.RoleID "
                        + " WHERE t1.FullName like ?"
                        + " order by t1.FullName asc ";
                stm = conn.prepareCall(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<UserDTO> getListStudentNext(String search, int amount) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.* , t2.Name as StatusName,t3.Name as roleName "
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 on t1.StatusID = t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID=t3.RoleID "
                        + " WHERE t1.FullName like ? "
                        + " order by t1.FullName asc "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT 10 ROWS ONLY ";
                stm = conn.prepareCall(sql);
                stm.setString(1, "%" + search + "%");
                stm.setInt(2, amount);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String name = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String StatusID = rs.getString("StatusID");
                    String Image = rs.getString("Image");
                    String RoleName = rs.getString("roleName");
                    String StatusName = rs.getString("statusName");
                    byte[] tmp = rs.getBytes("BinaryImage");
                    if (tmp != null) {
                        String base64Image = Base64.getEncoder().encodeToString(tmp);
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, base64Image, RoleName, StatusName)));
                    } else {
                        list.add((new UserDTO(userID, name, "*****", email, RoleID, StatusID, Image, RoleName, StatusName)));
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
        return list;
    }

    public List<RoleDTO> getListRoleID() throws SQLException {
        List<RoleDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * from tblRole";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roleID = rs.getString("roleID");
                    String roleName = rs.getString("name");
                    list.add(new RoleDTO(roleID, roleName));
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

    //Update user
    public boolean UpdateUser(String userID, String fullName, String roleID, String statusID, String password, FileInputStream image) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblUser "
                        + " set fullName=?,roleID=?,statusID=?,binaryimage=?,password=? "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, fullName);
                stm.setString(2, roleID);
                stm.setString(3, statusID);
                stm.setBinaryStream(4, image);
                stm.setString(5, password);
                stm.setString(6, userID);
                check = stm.executeUpdate() > 0;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean UpdateUserNoPhoto(String userID, String fullName, String roleID, String statusID, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblUser "
                        + " set fullName=?,roleID=?,statusID=?,password=? "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, fullName);
                stm.setString(2, roleID);
                stm.setString(3, statusID);
                stm.setString(4, password);
                stm.setString(5, userID);
                check = stm.executeUpdate() > 0;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    //Update User Status Fast
    public boolean UpdateUserStatusActive(String userID, String statusID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " Update tblUser set StatusID='active' "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                check = stm.executeUpdate() > 0;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean UpdateUserStatusInactive(String userID, String statusID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " Update tblUser set StatusID='inactive' "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                check = stm.executeUpdate() > 0;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    //Top 3 Ban User
    public List<UserDTO> getListBadUser(String month, String year) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select top 3 a.*,count(c.FeedbackDetailID) as Count from tblUser a \n"
                        + "join tblFeedbackDetail b on a.UserID=b.UserID\n"
                        + "join tblBannedFeedbackDetail c on c.FeedbackDetailID=b.FeedbackDetailID\n"
                        + "join tblFeedback d on d.FeedbackID=b.FeedbackID\n"
                        + "where d.statusID = 'decline' and d.Date like ? and d.Date like ?\n"
                        + "group by a.BinaryImage,a.Email,a.FullName,a.Image,a.Password,a.Rating,a.RoleID,a.StatusID,a.UserID\n"
                        + "Having Count(c.FeedbackDetailID) >= 3 \n"
                        + "order by Count desc";
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

    public List<FeedbackDetailDTO> getListRecentDeclineResponeForUser() throws SQLException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*,t3.Name as FacilityName ,t4.Date as Date "
                        + "  FROM tblFeedbackDetail t1 "
                        + "  JOIN tblBannedFeedbackDetail t2 on t1.FeedbackDetailID =t2.FeedbackDetailID "
                        + "  JOIN tblFacilities t3 on t1.FacilityID = t3.FacilityID "
                        + "  JOIN tblFeedback t4 on t1.FeedbackID = t4.FeedbackID "
                        + "  GROUP BY t1.Description,t1.FacilityID,t1.FeedbackDetailID,t1.FeedbackID,t1.AssignDate "
                        + "  ,t1.flag,t1.Image,t1.Location,t1.Quantity,t1.Reason,t1.StatusID,t1.UserID,"
                        + "  t3.Name,t3.Quantity,t4.Date,t1.ImageFirebase "
                        + "  ORDER BY t4.Date desc ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String feedbackDetailID = rs.getString("FeedbackDetailID");
                    String facilityID = rs.getString("FacilityID");
                    String feedbackID = rs.getString("FeedbackID");
                    String quantity = rs.getString("Quantity");
                    String reason = rs.getString("Reason");
                    String location = rs.getString("Location");
                    String deviceName = rs.getString("FacilityName");
                    String date = rs.getString("Date");
                    String description = rs.getString("Description");
                    String userID = rs.getString("UserID");

                    list.add(new FeedbackDetailDTO(feedbackDetailID, facilityID, userID, feedbackID, quantity, reason, location, "", false, deviceName, date, "", description));
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

    //Rating 
    public int getRating(String userID) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT Rating FROM tblUser \n"
                        + "WHERE userID = ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("rating");
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

    public boolean UpdateRating(String userID, int rating) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblUser "
                        + " set Rating = ? "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, rating);
                stm.setString(2, userID);
                check = stm.executeUpdate() > 0;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}
