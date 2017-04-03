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
import model.QuanLyDeThi;
import model.Users;

/**
 *
 * @author NTL
 */
public class QuanLyDeThiDAO {
    public void CompleteInfo(QuanLyDeThi thi) {
        Connection connection = DBConnect.getConnecttion();

        String sql = "UPDATE table_quanlydethi SET diem=?, ngaythi=? WHERE made=" + thi.getMade();
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ps.setFloat(1, thi.getDiem());
            ps.setString(2, thi.getNgaythi());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public float GetDiem(String made) {
        float diem=0;
        
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT diem FROM table_quanlydethi WHERE made='" + made + "'";
        
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                diem =rs.getFloat("diem");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DethiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return diem;
    }
       
    public List<QuanLyDeThi> getAllDethi(Users user, String made_search, int startPageIndex, int recordsPerPage) {
        Connection connection = DBConnect.getConnecttion();
        List<QuanLyDeThi> list = new ArrayList();
        
        String sql;
        
        if (user.getRole().equals("admin")) {
            sql = "SELECT * FROM table_quanlydethi WHERE made LIKE '%" + made_search + "' LIMIT " + startPageIndex + "," + recordsPerPage;            
        } else {
            sql = "SELECT * FROM table_quanlydethi WHERE username='" + user.getUsername() + "' LIMIT " + startPageIndex + "," + recordsPerPage;            
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String made = rs.getString("made");
                int socau = rs.getInt("socau");                
                String noidung = rs.getString("noidung");
                int thoigian = rs.getInt("thoigian");
                int mucdo = rs.getInt("mucdo");
                float diem = rs.getFloat("diem");
                String ngaythi = rs.getString("ngaythi");
                String username = rs.getString("username");
                
                QuanLyDeThi deThi = new QuanLyDeThi(made, socau, noidung, thoigian, mucdo, diem, ngaythi, username);
                list.add(deThi);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;        
    }

    public int getDethiCount(Users user) {
        Connection connection = DBConnect.getConnecttion();
        String sql;
        
        if (user.getRole().equals("admin")) {
            sql = "SELECT COUNT(*) AS COUNT FROM table_quanlydethi";
        } else {
            sql = "SELECT COUNT(*) AS COUNT FROM table_quanlydethi WHERE username='" + user.getUsername() + "'";
        }
        
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

    public boolean DeleteDethi(String made) {
        Connection con = DBConnect.getConnecttion();
        
        String sql = "DELETE FROM table_quanlydethi WHERE made='" + made + "'";
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
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDeThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
}
