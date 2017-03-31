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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NTL
 */
public class DanhgiaDAO {
    public int GetSolanthi(String username, String noidung) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_quanlydethi WHERE username='" + username + "'";
        PreparedStatement ps;
        
        int solanthi=0;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String nd = rs.getString("noidung");
                if (nd.contains(noidung)) {
                    solanthi++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
        return solanthi;
    }
    
    public double DanhGiaNangLuc(String made, String noidung) {
        Connection connection = DBConnect.getConnecttion();       
        PreparedStatement ps;
        String sql = "CALL thongkedokho('" + made + "','" + noidung + "')";
        
        double nangluc=0;
        int tongsocau=0;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int socau = rs.getInt("socau");
                int socaudung = rs.getInt("socaudung");
                int dopc = rs.getInt("dopc");

                tongsocau += socau;
                nangluc += ((double) socaudung/socau) * dopc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        nangluc/=tongsocau;  
    
        return nangluc;
    }
    
    public HashMap UocLuong(String username, String noidung, int solanthi, double nangluc) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_nangluc WHERE username='" + username + "'";
        PreparedStatement ps;

        HashMap<String, Double> map = new HashMap<>();        
                
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                double kyvong = (rs.getDouble(noidung) + nangluc/solanthi-1)*(solanthi-1/solanthi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        map.put("max", 1.2);
        map.put("min", 1.4);
        return map;
    }
    
    public static void main(String[] args) {
        new DanhgiaDAO().DanhGiaNangLuc("00003", "hamso");
    }
}
