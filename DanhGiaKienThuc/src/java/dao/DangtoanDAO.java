/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dangtoan;

/**
 *
 * @author NTL
 */
public class DangtoanDAO {
    public List getDangtoanTheoLop(String monhoc, int lop) {
        List<Dangtoan> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "' AND malop=" + lop;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                String dangtoanTV = rs.getString("dangtoanTV");
                
                Dangtoan d = new Dangtoan(dangtoan, dangtoanTV);
                list.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    public String[] getAllDangtoanLop(String monhoc, int lop) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "' AND malop=" + lop;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                
                list.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] array = list.toArray(new String[list.size()]);
        return array;
    }
    
    public String[] getDangtoanTheoHocky(String monhoc, int lop, int hocky) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "' AND malop=" + lop + " AND hocky=" + hocky;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                
                list.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] array = list.toArray(new String[list.size()]);
        return array;        
    }
    
    public List getAllDangToan(String monhoc) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "'";
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                
                list.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;        
    }
        
    public List getAll() {
        List<Dangtoan> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan";
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                String dangtoanTV = rs.getString("dangtoanTV");
                
                Dangtoan d = new Dangtoan(dangtoan, dangtoanTV);
                list.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;          
    }
    
    public List getAll(String monhoc, int startPageIndex, int recordsPerPage) {
        List<Dangtoan> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "' LIMIT " + startPageIndex + "," + recordsPerPage;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Dangtoan dangtoan = new Dangtoan();
                dangtoan.setDangtoan(rs.getString("dangtoan"));
                dangtoan.setMadangtoan(rs.getString("madangtoan"));
                dangtoan.setDangtoanTV(rs.getString("dangtoanTV"));
                dangtoan.setMonhoc(monhoc);
                dangtoan.setMalop(rs.getInt("malop"));
                dangtoan.setHocky(rs.getInt("hocky"));
                
                list.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;          
    }    
    
    public int countAllDangToan(String monhoc, int lop) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "' AND lop=" + lop;
        
	int count=0;
	try 
	{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();	
            while (rs.next()) 
            {
                count=rs.getInt("COUNT");
            }
	} 
	catch (SQLException e) 
	{
            System.err.println(e.getMessage());
	}
	return count;
    }

    public int countAllDangToan(String monhoc) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "'";
        
	int count=0;
	try 
	{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();	
            while (rs.next()) 
            {
                count=rs.getInt("COUNT");
            }
	} 
	catch (SQLException e) 
	{
            System.err.println(e.getMessage());
	}
	return count;
    }
    
    public int GetLop (String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE dangtoan='" + dangtoan + "'";
        PreparedStatement ps;
        int lop = 12;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                lop = rs.getInt("malop");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return lop;
    }
    
    public static String GetNoidungTV(String noidung) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE dangtoan='" + noidung + "'";
        String res = null;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                res = rs.getString("dangtoanTV");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDeThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }    
    
    public static String getMaDangtoan(String dangtoanTV) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE dangtoanTV LIKE '%" + dangtoanTV + "%'";
        String res = null;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                res = rs.getString("dangtoan");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDeThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public String getDangtoanID(String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE dangtoan='" + dangtoan + "'";
        String res = null;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                res = rs.getString("madangtoan");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDeThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;        
    }
    
    public List getAllDangtoan (String monhoc, int lop) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE monhoc='" + monhoc + "' AND malop=" + lop;
        List<Dangtoan> list = new ArrayList<>();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Dangtoan dangtoan = new Dangtoan();
                dangtoan.setDangtoan(rs.getString("dangtoan"));
                dangtoan.setMadangtoan(rs.getString("madangtoan"));
                dangtoan.setDangtoanTV(rs.getString("dangtoanTV"));
                dangtoan.setMonhoc(monhoc);
                dangtoan.setMalop(lop);
                dangtoan.setHocky(rs.getInt("hocky"));
                
                list.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public boolean InsertDangtoan(Dangtoan dangtoan) {
        Connection connection= DBConnect.getConnecttion();
        String sql = "INSERT INTO table_phanloaidangtoan(`dangtoan`,`madangtoan`,`monhoc`,`malop`,`dangtoanTV`,`hocky`,`dopc_de`,`dopc_tb`,`dopc_tbk`,`dopc_kho`) VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, dangtoan.getDangtoan());
            ps.setString(2, dangtoan.getMadangtoan());
            ps.setString(3, dangtoan.getMonhoc());
            ps.setInt(4, dangtoan.getMalop());
            ps.setString(5, dangtoan.getDangtoanTV());            
            ps.setInt(6, dangtoan.getHocky());
            ps.setDouble(7, 1.0);
            ps.setDouble(8, 2.0);
            ps.setDouble(9, 3.0);
            ps.setDouble(10, 4.0);            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateDangtoan(Dangtoan dangtoan) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "update table_phanloaidangtoan set dangtoan=?, madangtoan=?, malop=?, dangtoanTV=?, hocky=? where dangtoan=?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall(sql);
            ps.setString(1, dangtoan.getDangtoan());
            ps.setString(2, dangtoan.getMadangtoan());
            ps.setInt(3, dangtoan.getMalop());
            ps.setString(4, dangtoan.getDangtoanTV());            
            ps.setInt(5, dangtoan.getHocky());
            ps.setString(6, dangtoan.getDangtoan());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteDangtoan(Dangtoan dangtoan) {
        Connection con = DBConnect.getConnecttion();
        
        String sql = "delete from table_phanloaidangtoan where dangtoan='" + dangtoan.getDangtoan() + "'";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void updateTrongSo(String dangtoan, int level) {
        Connection connection = DBConnect.getConnecttion();
        
        String str;
        switch (level) {
            case 0:
                str = "dopc_de";
                break;
            case 1:
                str = "dopc_tb";
                break;
            case 2:
                str = "dopc_tbk";
                break;
            default:
                str = "dopc_kho";
                break;
        }
        
        String sql = "UPDATE table_phanloaidangtoan SET " + str + "=(SELECT AVG(dophancach) FROM NHCHTOAN WHERE dokho=" + level + ") WHERE dangtoan='" + dangtoan + "'";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
//    public static void main(String[] args) {
//        System.out.println(DangtoanDAO.GetNoidungTV("thongke"));
//    }
}
