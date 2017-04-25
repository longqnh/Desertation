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
import model.Lop;

/**
 *
 * @author NTL
 */
public class LopDAO {
    public boolean InsertLop(Lop lop) {
        Connection connection= DBConnect.getConnecttion();
        String sql = "INSERT INTO table_lop" + " VALUES(?,?)";
        
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, lop.getMalop());
            ps.setString(2, lop.getTenlop());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateLop(Lop lop) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "UPDATE table_lop set malop=?, tenlop=? where malop=?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall(sql);
            ps.setString(2, lop.getTenlop());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteLop(int malop) {
        Connection con = DBConnect.getConnecttion();
        String sql = "delete from table_lop where malop='" + malop + "'";
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
    
    public List GetAllLop() {
        Connection connection = DBConnect.getConnecttion();
        
        List<Lop> dsLop = new ArrayList();
        String sql = "SELECT * FROM table_lop"; //WHERE id LIKE '%" + search_id + "' LIMIT " + startPageIndex + "," + recordsPerPage;//"SELECT * FROM table_" + nd;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int malop = rs.getInt("malop");
                String tenlop = rs.getString("tenlop");
                
                Lop lop = new Lop(malop, tenlop);
                dsLop.add(lop);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return dsLop;        
    }
    
    public int getLopCount() {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_lop";
        
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
    
//    public static void main(String[] args) {
//        List list = GetAllLop();
//    }
}
