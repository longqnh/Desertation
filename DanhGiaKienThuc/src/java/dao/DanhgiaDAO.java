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

/**
 *
 * @author NTL
 */
public class DanhgiaDAO {
    public void DanhGiaNangLuc(String made) {
        Connection connection = DBConnect.getConnecttion();       
        String sql = "CALL thongkenoidung('" + made + "')";
        PreparedStatement ps;
        
        List<String> noidung = new ArrayList<>();
        double nangluc = 0;
        int tongsocau = 0;
                
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
        }
        
        nangluc/=tongsocau;
    }
}
