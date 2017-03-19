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
import model.Thongke;

/**
 *
 * @author NTL
 */
public class ThongkeDAO {
    public List thongketheodokho(String made) {
        List<Thongke> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        String sql = "CALL Thongke('" + made + "')";
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                String mucdo = rs.getString("mucdo");
                int socau = rs.getInt("socau");
                int socaudung = rs.getInt("socaudung");
                float dopc = rs.getFloat("dopc");
                
                Thongke thongke = new Thongke(dangtoan, mucdo, socau, socaudung, dopc);
                list.add(thongke);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongkeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
