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
import model.Dokho;

/**
 *
 * @author NTL
 */
public class DokhoDAO {
    public List GetAllDokho() {
        Connection connection = DBConnect.getConnecttion();
        
        List<Dokho> dsLevel = new ArrayList();
        String sql = "SELECT * FROM table_dokhode";
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
}
