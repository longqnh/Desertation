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
import model.MonHoc;

/**
 *
 * @author NTL
 */
public class MonHocDAO {
    public boolean InsertMonHoc(MonHoc monHoc) {
        Connection connection= DBConnect.getConnecttion();
        String sql = "INSERT INTO table_monhoc" + " VALUES(?,?)";
        
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, monHoc.getMonhocID());
            ps.setString(2, monHoc.getTenmonhoc());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateMonHoc(MonHoc monHoc) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "UPDATE table_monhoc set tenmonhoc=? where monhocID=?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall(sql);
            ps.setString(1, monHoc.getTenmonhoc());
            ps.setString(2, monHoc.getMonhocID());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteMonHoc(String monHocID) {
        Connection con = DBConnect.getConnecttion();
        String sql = "delete from table_monhoc where monhocID='" + monHocID + "'";
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
    
    public List GetAllMonHoc() {
        Connection connection = DBConnect.getConnecttion();
        
        List<MonHoc> dsMon = new ArrayList();
        String sql = "SELECT * FROM table_monhoc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String monhocID = rs.getString("monhocID");
                String tenmonhoc = rs.getString("tenmonhoc");
                
                MonHoc monHoc = new MonHoc(monhocID, tenmonhoc);
                dsMon.add(monHoc);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return dsMon;        
    }
    
    public int getMonHocCount() {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_monhoc";
        
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
    
    public String getTenMonHoc(String id) {
        Connection connection = DBConnect.getConnecttion();

        String res = new String();
        String sql = "SELECT * FROM table_monhoc WHERE monhocID='" + id + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res = rs.getString("tenmonhoc");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;          
    }
}
