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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NTL
 */
public class LythuyetDAO {
    public String getContent(String chude) {
        String content = "";
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT * FROM table_lythuyet WHERE kienthuc='" + chude + "'";
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                content = rs.getString("noidung");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LythuyetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return content;
    }
    
    public void updateContent(String chude, String noidung) {        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "UPDATE table_lythuyet SET noidung='" + noidung + "' WHERE kienthuc='" + chude + "'";
        
        try {
            ps = connection.prepareCall(sql);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(LythuyetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
