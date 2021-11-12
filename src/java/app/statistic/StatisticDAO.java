/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.statistic;

import app.feedback.FeedbackDTO;
import app.feedback.FeedbackDetailDTO;
import app.response.ResponseDTO;
import app.utils.DBUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StatisticDAO {

    public List<StatisticDTO> feedbackStatistic(String year) throws SQLException {
        List<StatisticDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String month = "";
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "  SELECT   'Jan' as' Month',SUM(CASE datepart(month,Date) WHEN 1 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "  Union All\n"
                        + "SELECT   'Feb' as' Month',SUM(CASE datepart(month,Date) WHEN 2 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "  Union All\n"
                        + "SELECT   'Mar' as' Month',SUM(CASE datepart(month,Date) WHEN 3 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Apr' as' Month',SUM(CASE datepart(month,Date) WHEN 4 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'May' as' Month',SUM(CASE datepart(month,Date) WHEN 5 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Jun' as' Month',SUM(CASE datepart(month,Date) WHEN 6 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Jul' as' Month',SUM(CASE datepart(month,Date) WHEN 7 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Aug' as' Month',SUM(CASE datepart(month,Date) WHEN 8 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Sep' as' Month',SUM(CASE datepart(month,Date) WHEN 9 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Oct' as' Month',SUM(CASE datepart(month,Date) WHEN 10 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Nov' as' Month',SUM(CASE datepart(month,Date) WHEN 11 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n"
                        + "   Union All\n"
                        + "SELECT   'Dec' as' Month',SUM(CASE datepart(month,Date) WHEN 12 THEN 1 ELSE 0 END) AS 'Count'\n"
                        + "FROM\n"
                        + "    tblFeedback\n"
                        + "WHERE\n"
                        + "  Date like ? AND StatusID!='inactive'\n";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + year + "%");
                ps.setString(2, "%" + year + "%");
                ps.setString(3, "%" + year + "%");
                ps.setString(4, "%" + year + "%");
                ps.setString(5, "%" + year + "%");
                ps.setString(6, "%" + year + "%");
                ps.setString(7, "%" + year + "%");
                ps.setString(8, "%" + year + "%");
                ps.setString(9, "%" + year + "%");
                ps.setString(10, "%" + year + "%");
                ps.setString(11, "%" + year + "%");
                ps.setString(12, "%" + year + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    month = rs.getString(1);
                    count = rs.getInt(2);
                    list.add(new StatisticDTO(month, count));
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

    public List<DonutDTO> selectFeedbackForDonut(String month) throws SQLException {
        List<DonutDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " select P.FeedbackStatusID as status,( CASE WHEN A.count IS NOT NULL THEN A.count ELSE 0 END) as count \n"
                        + "from \n"
                        + "(SELECT FeedbackStatusID\n"
                        + "FROM tblFeedbackStatus) P\n"
                        + "LEFT JOIN \n"
                        + "(select statusID, count(*) as count\n"
                        + "from tblFeedback \n"
                        + "where Date like ? and statusID !='inactive'\n"
                        + "group by statusID) A\n"
                        + "ON P.FeedbackStatusID = A.statusID\n"
                        + "where P.FeedbackStatusID !='inactive'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + month + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String status = rs.getString("status");
                    int count = rs.getInt("count");
                    list.add(new DonutDTO(status, count));
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

    public List<FeedbackDTO> getListFeedbackForNotification(int check) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DECLARE @check int;\n"
                        + "set @check = ?;\n"
                        + "select top (@check) * ,t2.FullName,t2.Email\n"
                        + "from tblFeedback t1\n"
                        + "JOIN tblUser t2 on t1.UserID = t2.UserID\n"
                        + "where t1.statusID='pending' and t1.TrashDate IS  NULL \n"
                        + "order by t1.FeedbackID desc ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, check);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName));
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
    public FeedbackDTO getRecentFeedback() throws SQLException {
        FeedbackDTO feedback = new FeedbackDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = 
                        "select top 1 * ,t2.FullName,t2.Email \n"
                        + "from tblFeedback t1\n"
                        + "JOIN tblUser t2 on t1.UserID = t2.UserID\n"
                        + "where t1.statusID='pending' and t1.TrashDate IS  NULL \n"
                        + "order by t1.FeedbackID desc ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    feedback=new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName);
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
        return feedback;
    }
    public FeedbackDTO getRecentFeedbackEmp(String userID) throws SQLException {
        FeedbackDTO feedback = new FeedbackDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = 
                        "select top 1 * ,t2.FullName,t2.Email\n"
                        + "from tblFeedback t1\n"
                        + "JOIN tblUser t2 on t1.UserID = t2.UserID\n"
                        + "JOIN tblFeedbackDetail t3 on t1.feedbackID = t3.feedbackID\n"
                        + "where t3.userID = ?  \n"
                        + "order by t3.AssignDate desc ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    feedback=new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName);
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
        return feedback;
    }
    public FeedbackDTO getRecentFeedbackDone() throws SQLException {
        FeedbackDTO feedback = new FeedbackDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = 
                        "select top 1 * ,t2.FullName,t2.Email\n"
                        + "from tblFeedback t1\n"
                        + "JOIN tblUser t2 on t1.UserID = t2.UserID\n"
                        + "where t1.statusID='done' \n"
                        + "order by t1.DoneDate desc ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    feedback=new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName);
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
        return feedback;
    }
    public FeedbackDTO getRecentTrash() throws SQLException {
        FeedbackDTO feedback = new FeedbackDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = 
                        "select top 1 * ,t2.FullName,t2.Email\n"
                        + "from tblFeedback t1\n"
                        + "JOIN tblUser t2 on t1.UserID = t2.UserID\n"
                        + "where t1.statusID='pending' and t1.TrashDate IS NOT NULL \n"
                        + "order by t1.TrashDate desc ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    feedback=new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName);
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
        return feedback;
    }
    public String getFeedbackIDOfRecentResponse() throws SQLException {
        String id ="";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = 
                        " select top 1 * ,t2.FeedbackID as feedbackId\n"
                        + "from tblResponseFeedback t1\n"
                        + "JOIN tblFeedbackDetail t2 on t2.FeedbackDetailID = t1.FeedbackDetailID\n"
                        + "where t1.StatusID='done' \n"
                        + "order by t1.ResponseID desc ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getString("feedbackId");
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
        return id;
    }
    public String getRecentResponseID() throws SQLException {
        String id ="";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = 
                        " select top 1 * \n"
                        + "from tblResponseFeedback t1\n"
                        + "where t1.StatusID='done' \n"
                        + "order by t1.ResponseID desc ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getString("ResponseID");
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
        return id;
    }

    public List<FeedbackDTO> getListFeedbackForNotificationTrash(int check) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DECLARE @check int;\n"
                        + "set @check = ?;\n"
                        + "select top (@check) * ,t2.FullName,t2.Email\n"
                        + "from tblFeedback t1\n"
                        + "JOIN tblUser t2 on t1.UserID = t2.UserID\n"
                        + "where t1.statusID='pending' and t1.trashDate is not null  \n"
                        + "order by t1.TrashDate desc ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, check);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName));
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

    public List<FeedbackDTO> getListFeedbackForNotificationUser(int check, String userId) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " declare @check int;\n"
                        + "set @check =?;\n"
                        + "select top (@check) t1.*, t2.Email, t2.FullName from tblFeedback t1 \n"
                        + "join tblUser t2 on t1.UserID = t2.UserID\n"
                        + "where t1.statusID='done' and t1.UserID=? \n"
                        + "order by t1.FeedbackID desc ";
                stm = conn.prepareCall(sql);
                stm.setInt(1, check);
                stm.setString(2, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    list.add(new FeedbackDTO(feedbackId, userId, date, email, statusId, fullName));
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

    public List<FeedbackDetailDTO> getListFeedbackDetailForNotification(int check, String userId) throws SQLException {
        List<FeedbackDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DECLARE @check int;\n"
                        + "set @check = ?;\n"
                        + "select top (@check) * \n"
                        + "from tblFeedbackDetail \n"
                        + "where UserID=?\n"
                        + "order by AssignDate desc ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, check);
                stm.setString(2, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    list.add(new FeedbackDetailDTO("", "", feedbackId, "", "", "", false));
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

    public List<ResponseDTO> getListFeedbackDetailForNotificationResponse(int check) throws SQLException {
        List<ResponseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DECLARE @check int;\n"
                        + "set @check = ?;\n"
                        + "select top (@check) t1.* , t2.FeedbackID \n"
                        + "from tblResponseFeedback t1\n"
                        + "join tblFeedbackDetail t2 on t1.FeedbackDetailID=t2.FeedbackDetailID\n"
                        + "where t1.StatusID = 'done'\n"
                        + "order by t1.Realtime desc ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, check);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("FeedbackID");
                    list.add(new ResponseDTO("", "", "", "", "", "", "", "", "", "", "", false, false, "", feedbackId));
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

    public int countForNotification() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(FeedbackID) as count"
                        + " FROM tblFeedback  "
                        + " WHERE StatusID ='pending' and trashDate is null";
                stm = conn.prepareCall(sql);
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

    public int countForNotificationTrash() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(FeedbackID) as count"
                        + " FROM tblFeedback  "
                        + " WHERE StatusID ='pending' and trashDate is not null";
                stm = conn.prepareCall(sql);
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

    public int countForNotificationUser(String userId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select Count(feedbackid) as count "
                        + " from tblFeedback "
                        + " where statusID='done' and UserID=? ";
                stm = conn.prepareStatement(sql);
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

    public int countForNotificationEmployee(String userId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " select count(FeedbackDetailID)as count from tblFeedbackDetail "
                        + " where UserID=? and flag='false' ";
                stm = conn.prepareStatement(sql);
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

    public int countForNotificationEmployeeResponse() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " Select count(feedbackdetailID) as count from tblResponseFeedback\n"
                        + " where StatusID='done' ";
                stm = conn.prepareStatement(sql);
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

    public FeedbackDTO getFeedbackByID(String feedbackID,String responseId) throws SQLException, IOException {
        FeedbackDTO feedback = new FeedbackDTO();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.* , t2.Email, t5.FullName as fullName "
                        + " FROM tblFeedback t1  "
                        + " JOIN tblUser t2 "
                        + "  ON t1.UserID = t2.UserID "
                        + " JOIN tblFeedbackDetail t3 "
                        + "  ON t3.FeedbackID = t1.FeedbackID "
                        + " JOIN tblResponseFeedback t4 "
                        + "  ON t4.FeedbackDetailID = t3.FeedbackDetailID "
                        + " JOIN tblUser t5 "
                        + "  ON t5.UserID = t4.UserID "
                        + " WHERE t1.FeedbackID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    feedback = new FeedbackDTO(feedbackID, userId, date, email, responseId, fullName);
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
        return feedback;
    }

    public FeedbackDTO getFeedbackByIDResponse(String feedbackID) throws SQLException, IOException {
        FeedbackDTO feedback = new FeedbackDTO();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.* , t2.Email, t2.FullName, t5.FullName as emp\n"
                        + "FROM tblFeedback t1  \n"
                        + "JOIN tblUser t2 \n"
                        + "ON t1.UserID = t2.UserID \n"
                        + " JOIN tblFeedbackDetail t3 on t1.feedbackID =t3.feedbackID\n"
                        + "JOIN tblUser t5\n"
                        + "ON t5.UserID = t3.UserID\n"
                        + "WHERE t1.FeedbackID=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, feedbackID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userId = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String date = rs.getString("Date");
                    String statusId = rs.getString("statusID");
                    String emp = rs.getString("emp");
                    feedback = new FeedbackDTO(feedbackID, userId, date, email, statusId, fullName, "", "", emp);

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
        return feedback;
    }
}
