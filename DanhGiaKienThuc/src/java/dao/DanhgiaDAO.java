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
    public HashMap DanhGiaNangLuc(String made) {
        Connection connection = DBConnect.getConnecttion();       
        String sql = "CALL thongkenoidung('" + made + "')";
        PreparedStatement ps;
        
        List<String> noidung = new ArrayList<>();
        double nangluc;
        int tongsocau;
        HashMap<String, Double> NangLuc = new HashMap<>();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("madangtoan");
                noidung.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (String nd : noidung) {
            nangluc = 0;
            tongsocau = 0;
            try {
                ps = connection.prepareCall("CALL thongkedokho('" + made + "','" + nd + "')");
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
            NangLuc.put(nd, nangluc);
        }        
        return NangLuc;
    }
    
    public HashMap UocLuong(String username, HashMap<String, Double> nangluc) {
        Connection connection = DBConnect.getConnecttion();
        
        HashMap<String, Double> map = new HashMap<>();        
        double kyvong, phuongsai;
        int solanthi=0;
        
        String sql = "SELECT * FROM table_nangluc WHERE username='" + username + "'";
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                double hamso = (rs.getDouble("hamso") + nangluc.get("hamso")/solanthi)*(solanthi/solanthi+1);
                double loga = rs.getDouble("loga");
                double tichphan = rs.getDouble("tichphan");
                double sophuc = rs.getDouble("sophuc");
                double hhkg = rs.getDouble("hhkg");
                double oxyz = rs.getDouble("oxyz");
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        map.put("max",1.2);
        map.put("min", 1.4);
        return map;
    }
    
    public static void main(String[] args) {
        HashMap map = new DanhgiaDAO().DanhGiaNangLuc("00002");
    }
}
