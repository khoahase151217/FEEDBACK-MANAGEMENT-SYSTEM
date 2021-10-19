/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.facility;

import app.users.UserDTO;
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
public class FacilityDAO {

    public List<FacilityDTO> getListFacilityByName(String search) throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.Name like ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> sortFacilityNameAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.*, t2.Name as statusName  from tblFacilities t1 "
                        + " join tblFacilityStatus t2 on t1.StatusID = t2.StatusID "
                        + " order by Name asc";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName));

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

    public List<FacilityDTO> sortFacilityNameDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.*, t2.Name as statusName  from tblFacilities t1 "
                        + " join tblFacilityStatus t2 on t1.StatusID = t2.StatusID "
                        + " order by Name desc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName));
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

    public List<FacilityDTO> sortFacilityStatusAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.*, t2.Name as statusName  from tblFacilities t1 "
                        + " join tblFacilityStatus t2 on t1.StatusID = t2.StatusID "
                        + " order by t2.name asc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName));
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

    public List<FacilityDTO> sortFacilityStatusDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.*, t2.Name as statusName  from tblFacilities t1 "
                        + " join tblFacilityStatus t2 on t1.StatusID = t2.StatusID "
                        + " order by t2.name desc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName));
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

    public List<FacilityDTO> sortFacilityDateAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " select t1.*, t2.Name as statusName  from tblFacilities t1 "
                        + " join tblFacilityStatus t2 on t1.StatusID = t2.StatusID "
                        + " order by MaintenanceDate asc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName));
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

    public List<FacilityDTO> sortFacilityDateDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select t1.*, t2.Name as statusName  from tblFacilities t1 "
                        + " join tblFacilityStatus t2 on t1.StatusID = t2.StatusID "
                        + " order by MaintenanceDate desc ";
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName));
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

    public List<FacilityDTO> getAllListFacilityAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " Order by t1.Name asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getAllListFacilityDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " Order by t1.Name desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListFacilityAvailableAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.StatusID='AV' "
                        + " Order by t1.Name asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListFacilityAvailableDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.StatusID='AV' "
                        + " Order by t1.Name desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListFacilityUnavailableAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.StatusID='UNAV' "
                        + " Order by t1.Name asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListFacilityUnavailableDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.StatusID='UNAV' "
                        + " Order by t1.Name Desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListElectricFacilityAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID='TD' "
                        + " Order by t1.Name asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListElectricFacilityDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID='1' "
                        + " Order by t1.Name Desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListWaterFacilityAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID='TN' "
                        + " Order by t1.Name asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListWaterFacilityDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID='2' "
                        + " Order by t1.Name Desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListEnviromentFacilityAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID='EN' "
                        + " Order by t1.Name Asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListEnviromentFacilityDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID='3' "
                        + " Order by t1.Name Desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListOthersFacilityAsc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID!='TD' and t1.CategoryID!='TN' and t1.CategoryID!='EN'"
                        + " Order by t1.Name Asc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

    public List<FacilityDTO> getListOthersFacilityDesc() throws SQLException {
        List<FacilityDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT t1.*, t2.Name as statusName "
                        + " FROM tblFacilities t1 "
                        + " JOIN tblFacilityStatus t2 "
                        + " ON t1.StatusID = t2.StatusID "
                        + " WHERE t1.CategoryID!='3' and t1.CategoryID!='2' and t1.CategoryID!='1'"
                        + " Order by t1.Name Desc";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String facilityId = rs.getString("FacilityID");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    String date = rs.getString("MaintenanceDate");
                    String statusId = rs.getString("StatusID");
                    String categoryId = rs.getString("CategoryID");
                    String statusName = rs.getString("statusName");
                    String image = rs.getString("Image");
                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
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

//    Hàm mới nhé
//    public List<FacilityDTO> getAllListFacilityAsc() throws SQLException {
//        List<FacilityDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = " SELECT t1.FacilityID,t1.Name,t1.Quantity,CONVERT(varchar,t1.MaintenanceDate,105) as MaintenanceDate,t1.StatusID,t1.CategoryID, t2.Name as StatusName "
//                        + " FROM tblFacilities t1 "
//                        + " JOIN tblFacilityStatus t2 "
//                        + " ON t1.StatusID = t2.StatusID "
//                        + " Order by t1.Name asc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String facilityId = rs.getString("FacilityID");
//                    String name = rs.getString("Name");
//                    int quantity = rs.getInt("Quantity");
//                    String date = rs.getString("MaintenanceDate");
//                    String statusId = rs.getString("StatusID");
//                    String categoryId = rs.getString("CategoryID");
//                    String statusName = rs.getString("statusName");
//                    String image = rs.getString("Image");
//                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
//                }
//            }
//
//        } catch (Exception e) {
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
//
//    public List<FacilityDTO> getAllListFacilityDesc() throws SQLException {
//        List<FacilityDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = " SELECT t1.FacilityID,t1.Name,t1.Quantity,CONVERT(varchar,t1.MaintenanceDate,105) as MaintenanceDate,t1.StatusID,t1.CategoryID, t2.Name as StatusName "
//                        + " FROM tblFacilities t1 "
//                        + " JOIN tblFacilityStatus t2 "
//                        + " ON t1.StatusID = t2.StatusID "
//                        + " Order by t1.Name desc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String facilityId = rs.getString("FacilityID");
//                    String name = rs.getString("Name");
//                    int quantity = rs.getInt("Quantity");
//                    String date = rs.getString("MaintenanceDate");
//                    String statusId = rs.getString("StatusID");
//                    String categoryId = rs.getString("CategoryID");
//                    String statusName = rs.getString("statusName");
//                    String image = rs.getString("Image");
//                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
//                }
//            }
//
//        } catch (Exception e) {
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
//
//    public List<FacilityDTO> getListFacilityAvailableAsc() throws SQLException {
//        List<FacilityDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = " SELECT t1.FacilityID,t1.Name,t1.Quantity,CONVERT(varchar,t1.MaintenanceDate,105) as MaintenanceDate,t1.StatusID,t1.CategoryID, t2.Name as StatusName "
//                        + " FROM tblFacilities t1 "
//                        + " JOIN tblFacilityStatus t2 "
//                        + " ON t1.StatusID = t2.StatusID "
//                        + " WHERE t1.StatusID='AV' "
//                        + " Order by t1.Name asc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String facilityId = rs.getString("FacilityID");
//                    String name = rs.getString("Name");
//                    int quantity = rs.getInt("Quantity");
//                    String date = rs.getString("MaintenanceDate");
//                    String statusId = rs.getString("StatusID");
//                    String categoryId = rs.getString("CategoryID");
//                    String statusName = rs.getString("statusName");
//                    String image = rs.getString("Image");
//                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
//                }
//            }
//
//        } catch (Exception e) {
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
//
//    public List<FacilityDTO> getListFacilityAvailableDesc() throws SQLException {
//        List<FacilityDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = " SELECT t1.FacilityID,t1.Name,t1.Quantity,CONVERT(varchar,t1.MaintenanceDate,105) as MaintenanceDate,t1.StatusID,t1.CategoryID, t2.Name as StatusName "
//                        + " FROM tblFacilities t1 "
//                        + " JOIN tblFacilityStatus t2 "
//                        + " ON t1.StatusID = t2.StatusID "
//                        + " WHERE t1.StatusID='AV' "
//                        + " Order by t1.Name desc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String facilityId = rs.getString("FacilityID");
//                    String name = rs.getString("Name");
//                    int quantity = rs.getInt("Quantity");
//                    String date = rs.getString("MaintenanceDate");
//                    String statusId = rs.getString("StatusID");
//                    String categoryId = rs.getString("CategoryID");
//                    String statusName = rs.getString("statusName");
//                    String image = rs.getString("Image");
//                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
//                }
//            }
//
//        } catch (Exception e) {
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
//
//    public List<FacilityDTO> getListFacilityUnavailableAsc() throws SQLException {
//        List<FacilityDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = " SELECT t1.FacilityID,t1.Name,t1.Quantity,CONVERT(varchar,t1.MaintenanceDate,105) as MaintenanceDate,t1.StatusID,t1.CategoryID, t2.Name as StatusName "
//                        + " FROM tblFacilities t1 "
//                        + " JOIN tblFacilityStatus t2 "
//                        + " ON t1.StatusID = t2.StatusID "
//                        + " WHERE t1.StatusID='UN' "
//                        + " Order by t1.Name asc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String facilityId = rs.getString("FacilityID");
//                    String name = rs.getString("Name");
//                    int quantity = rs.getInt("Quantity");
//                    String date = rs.getString("MaintenanceDate");
//                    String statusId = rs.getString("StatusID");
//                    String categoryId = rs.getString("CategoryID");
//                    String statusName = rs.getString("statusName");
//                    String image = rs.getString("Image");
//                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
//                }
//            }
//
//        } catch (Exception e) {
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
//
//    public List<FacilityDTO> getListFacilityUnavailableDesc() throws SQLException {
//        List<FacilityDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = " SELECT t1.FacilityID,t1.Name,t1.Quantity,CONVERT(varchar,t1.MaintenanceDate,105) as MaintenanceDate,t1.StatusID,t1.CategoryID, t2.Name as StatusName "
//                        + " FROM tblFacilities t1 "
//                        + " JOIN tblFacilityStatus t2 "
//                        + " ON t1.StatusID = t2.StatusID "
//                        + " WHERE t1.StatusID='UN' "
//                        + " Order by t1.Name Desc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String facilityId = rs.getString("FacilityID");
//                    String name = rs.getString("Name");
//                    int quantity = rs.getInt("Quantity");
//                    String date = rs.getString("MaintenanceDate");
//                    String statusId = rs.getString("StatusID");
//                    String categoryId = rs.getString("CategoryID");
//                    String statusName = rs.getString("statusName");
//                    String image = rs.getString("Image");
//                    list.add(new FacilityDTO(facilityId, name, quantity, date, statusId, categoryId, statusName, image));
//                }
//            }
//
//        } catch (Exception e) {
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
}
