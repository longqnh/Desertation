/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import static tools.DanhGiaKienThuc.round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dangtoan;
import tools.DanhGiaKienThuc;

/**
 *
 * @author NTL
 */
public class DangtoanDAO {
    public List getDangtoanTheoLop(int lop) {
        List<Dangtoan> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE malop=" + lop;
        
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

    public String[] getAllDangtoanLop(int lop) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE malop=" + lop;
        
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
    
    public String[] getDangtoanTheoHocky(int lop, int hocky) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan WHERE malop=" + lop + " AND hocky=" + hocky;
        
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
    
    public List getAllDangToan() {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_phanloaidangtoan";
        
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
        
    public int countAllDangToan() {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_phanloaidangtoan";
        
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
    
    public double GetKyVong(String username, String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "SELECT * FROM table_kyvong WHERE username='" + username + "'";
        PreparedStatement ps;
        double kyvong = 0;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                kyvong = rs.getDouble(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kyvong;
    }
    
    public double GetPhuongSai(String username, String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "SELECT * FROM table_phuongsai WHERE username='" + username + "'";
        PreparedStatement ps;
        double phuongsai = 0;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String str = rs.getString(dangtoan);
                String []arr_phuongsai = str.split(" ");
                List<Double> arr = new ArrayList<>();
                for (String s : arr_phuongsai) {
                    arr.add(round(Double.parseDouble(s)));
                }
                
                double kyvong = GetKyVong(username, dangtoan);
                for (int i = 0; i < arr.size(); i++) {
                    phuongsai += ((arr.get(i) - kyvong)*(arr.get(i) - kyvong));                    
                }
                phuongsai/=arr.size();
                phuongsai=round(Math.sqrt(phuongsai));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhGiaKienThuc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phuongsai;
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
    
//    public static void main(String[] args) {
//        System.out.println(DangtoanDAO.GetNoidungTV("thongke"));
//    }
}
