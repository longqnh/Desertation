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
import model.QuanLyDeThi;

/**
 *
 * @author NTL
 */
public class QuanLyDeThiDAO {
    public void CompleteInfo(QuanLyDeThi thi) {
        Connection connection = DBConnect.getConnecttion();

        String sql = "UPDATE table_quanlydethi SET diem=?, ngaythi=? WHERE made=" + thi.getMade();
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ps.setFloat(1, thi.getDiem());
            ps.setString(2, thi.getNgaythi());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
