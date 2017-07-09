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
import model.DangBaiTap;

/**
 *
 * @author NTL
 */
public class DangBaiTapDAO {
    public List GetAllDangBaiTap(String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
      
        String sql = "SELECT * FROM table_phanloaibt WHERE dangtoan='" + dangtoan + "'";
        PreparedStatement ps;
    
        List<DangBaiTap> list = new ArrayList<>();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangbt = rs.getString("dangbt");
                String dangbtTV = rs.getString("dangbtTV");
                
                DangBaiTap dangBaiTap = new DangBaiTap(dangbt, dangbtTV);
                list.add(dangBaiTap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangBaiTapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public List GetAllDangBaiTap(String dangtoan, int startPageIndex, int recordsPerPage) {
        Connection connection = DBConnect.getConnecttion();
      
        String sql = "SELECT * FROM table_phanloaibt WHERE dangtoan='" + dangtoan + "' LIMIT " + startPageIndex + "," + recordsPerPage;;
        PreparedStatement ps;
    
        List<DangBaiTap> list = new ArrayList<>();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangbt = rs.getString("dangbt");
                String dangbtTV = rs.getString("dangbtTV");

                DangBaiTap dangBaiTap = new DangBaiTap(dangbt, dangtoan, dangbtTV);
                list.add(dangBaiTap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangBaiTapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public int getDangbtCount(String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_phanloaibt WHERE dangtoan='" + dangtoan + "'";
        
	int count=0;
	try 
	{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();	
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
    
    public boolean InsertDangBT(DangBaiTap dangBaiTap) {
        Connection connection= DBConnect.getConnecttion();
        String sql = "INSERT INTO table_phanloaibt VALUES(?,?,?)";
        
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, dangBaiTap.getDangbt());
            ps.setString(2, dangBaiTap.getDangtoan());
            ps.setString(3, dangBaiTap.getDangbtTV());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateDangBT(DangBaiTap dangBaiTap) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "update table_phanloaibt set dangbt=?, dangtoan=?, dangbtTV=? where dangbt=?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall(sql);
            ps.setString(1, dangBaiTap.getDangbt());
            ps.setString(2, dangBaiTap.getDangtoan());
            ps.setString(3, dangBaiTap.getDangbtTV());
            ps.setString(4, dangBaiTap.getDangbt());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteDangBT(DangBaiTap dangBaiTap) {
        Connection con = DBConnect.getConnecttion();
        
        String sql = "delete from table_phanloaibt where dangbt='" + dangBaiTap.getDangbt() + "'";
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
    
    public String getDangtoanByBaiTap (String dangbaitap) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "SELECT * FROM table_phanloaibt WHERE dangbt='" + dangbaitap + "'";
        PreparedStatement ps;
        String res = new String();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                res = rs.getString("dangtoan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangBaiTapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
//    public static void main(String[] args) {
//        List<DangBaiTap> list = new DangBaiTapDAO().GetAllDangBaiTap("hamso12");
//        
//        for (DangBaiTap bt : list) {
//            System.out.println(bt.getDangbtTV());
//        }
//    }
}
