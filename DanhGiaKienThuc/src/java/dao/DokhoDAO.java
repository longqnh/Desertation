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
import model.Dokho;

/**
 *
 * @author NTL
 */
public class DokhoDAO {
    public List GetAllDokho() {
        Connection connection = DBConnect.getConnecttion();
        
        List<Dokho> dsLevel = new ArrayList();
        String sql = "SELECT * FROM table_dokhoDE";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int dokho = rs.getInt("dokho");
                String mucdo = rs.getString("mucdo");
                
                Dokho dkdt = new Dokho(dokho, mucdo);
                dsLevel.add(dkdt);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return dsLevel;  
    }
    
    public String GetDoKhoTV (int dokho) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "SELECT * FROM table_dokhoDE WHERE dokho=" + dokho;
        PreparedStatement ps;
        String mucdo = null;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                mucdo = rs.getString("mucdo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return mucdo;        
    }
    
    public List GetAllDokhoCH() {
        Connection connection = DBConnect.getConnecttion();
        
        List<Dokho> dsLevel = new ArrayList();
        String sql = "SELECT * FROM table_dokhoCH";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int dokho = rs.getInt("dokho");
                String mucdo = rs.getString("mucdo");
                
                Dokho dkdt = new Dokho(dokho, mucdo);
                dsLevel.add(dkdt);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return dsLevel;  
    }
    
//    public static void main(String[] args) {
//        System.out.println(new DokhoDAO().GetDoKhoTV(2));
//    }
}
