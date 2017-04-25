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
    
//    public static void main(String[] args) {
//        List<DangBaiTap> list = new DangBaiTapDAO().GetAllDangBaiTap("hamso12");
//        
//        for (DangBaiTap bt : list) {
//            System.out.println(bt.getDangbtTV());
//        }
//    }
}
