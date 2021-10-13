/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.users;

import app.feedback.FeedbackDTO;
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

    public UserDTO checkLoginGoogle(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT USERID,FullName,Email,StatusID,Image,RoleID "
                        + " FROM tblUser "
                        + " WHERE Email =? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("USERID");
                    String fullName = rs.getString("FullName");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    String roleID = rs.getString("RoleID");
                    user = new UserDTO(userID, fullName, "****", email, roleID, statusID, image);
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

    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select userID, FullName, RoleID, StatusID, Image from "
                        + "tblUser where Email=? and Password=? ";
                st = conn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                rs = st.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("RoleID");
                    String statusID = rs.getString("StatusID");
                    String image = rs.getString("Image");
                    user = new UserDTO(userID, fullName, "******", email, roleID, statusID, image);
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
                String sql = "Insert into tblUser "
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
                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
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
                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
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

    public List<UserDTO> showAllStudentAsc() throws SQLException {

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
                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
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

    public List<UserDTO> showAllEmployeeAsc() throws SQLException {

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
                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
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

    public List<UserDTO> showAllListActiveUserAsc() throws SQLException {

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
                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
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

    public List<UserDTO> showAllListInactiveUserAsc() throws SQLException {

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
                String sql = "SELECT t1.*,t2.Name as statusName,t3.Name as RoleName"
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
                String sql = "SELECT t1.* , t2.Name as StatusName,t3.Name as roleName "
                        + " FROM tblUser t1 "
                        + " JOIN tblUserStatus t2 on t1.StatusID = t2.StatusID "
                        + " JOIN tblRole t3 on t1.RoleID=t3.RoleID "
                        + " WHERE t1.FullName like ?";
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
    public boolean UpdateUser(String userID, String fullName, String roleID, String statusID, FileInputStream image) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblUser "
                        + " set fullName=?,roleID=?,statusID=?,binaryimage=? "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, fullName);
                stm.setString(2, roleID);
                stm.setString(3, statusID);
                stm.setBinaryStream(4, image);
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
}
