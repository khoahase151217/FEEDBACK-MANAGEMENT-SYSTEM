/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.statistic;

import app.facility.FacilityDTO;
import app.feedback.FeedbackDTO;
import app.utils.DBUtils;
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
                        + "where t1.statusID='pending'\n"
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
                        + " WHERE StatusID ='pending'";
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

}
