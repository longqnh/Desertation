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
import model.Users;

/**
 *
 * @author NTL
 */
public class DanhgiaDAO {
    private static double round(double d) {
        return Math.round(d * 100.0)/100.0;
    }
    
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
    
        return round(nangluc);
    }
    
    public void DanhGiaNangLuc(String made, String[] noidung, Users thisinh) {
        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
        
        for (String nd: noidung) {
            int solanthi = danhgiaDAO.GetSolanthi(thisinh.getUsername(), QuanLyDeThiDAO.GetNoidungTV(nd));
            if (solanthi > 0) {
                double nangluc = danhgiaDAO.DanhGiaNangLuc(made, nd);
                danhgiaDAO.updateKyVong(thisinh, nd, solanthi, nangluc);
                danhgiaDAO.updatePhuongSai(thisinh, nd, nangluc);                    
            }
        }       
    }
    
    public HashMap UocLuong(Users user, String noidung) {
        Connection connection = DBConnect.getConnecttion();
        
        String table_kyvong="", table_phuongsai="";
        double kyvong=0, phuongsai=0;       
        
        switch (user.getLop()) {
            case 12:
                table_kyvong = "table_kyvong12";
                table_phuongsai = "table_phuongsai12";
                break;
            case 11:
                table_kyvong = "table_kyvong11";       
                table_phuongsai = "table_phuongsai11";
                break;
            case 10:
                table_kyvong = "table_kyvong10";                
                table_phuongsai = "table_phuongsai10";
                break;
        }
        
        String sql = "SELECT * FROM " + table_kyvong + " WHERE username='" + user.getUsername() + "'";
        PreparedStatement ps;

        HashMap<String, Double> khoanguocluong = new HashMap<>();        
                
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                kyvong = rs.getDouble(noidung);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "SELECT * FROM " + table_phuongsai + " WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String str = rs.getString(noidung);
                String []sp = str.split(" ");
                List<Double> arr = new ArrayList<>();
                for (String s : sp) {
                    arr.add(round(Double.parseDouble(s)));
                }
                
                for (int i = 0; i < arr.size(); i++) {
                    phuongsai += ((arr.get(i) - kyvong)*(arr.get(i) - kyvong));                    
                }
                phuongsai/=arr.size();
                phuongsai=round(Math.sqrt(phuongsai));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        khoanguocluong.put("max", kyvong + phuongsai);
        khoanguocluong.put("min", kyvong - phuongsai);
        return khoanguocluong;
    }
    
    public void updatePhuongSai (Users user, String noidung, double ability) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String table_phuongsai = "";
        
        switch (user.getLop()) {
            case 12:
                table_phuongsai = "table_phuongsai12";
                break;
            case 11:
                table_phuongsai = "table_phuongsai11";
                break;
            case 10:
                table_phuongsai = "table_phuongsai10";
                break;
        }        

        String nangluc = "";
        String sql = "SELECT * FROM " + table_phuongsai + " WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                nangluc = rs.getString(noidung) + " " + Double.toString(round(ability));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        sql = "UPDATE " + table_phuongsai + " SET " + noidung + "='" + nangluc + "' WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
    public void updateKyVong(Users user, String noidung, int solanthi, double nangluc) {
        Connection connection = DBConnect.getConnecttion();
        
        String table_kyvong="";
        double kyvong = 0;
        
        switch (user.getLop()) {
            case 12:
                table_kyvong = "table_kyvong12";
                break;
            case 11:
                table_kyvong = "table_kyvong11"; 
                break;
            case 10:
                table_kyvong = "table_kyvong10";
                break;
        }
        
        String sql = "SELECT * FROM " + table_kyvong + " WHERE username='" + user.getUsername() + "'";
        PreparedStatement ps;      

        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                kyvong = (rs.getDouble(noidung) + nangluc/solanthi-1)*(solanthi-1/solanthi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "UPDATE " + table_kyvong + " SET " + noidung + "='" + kyvong + "' WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ps.execute(sql);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void main(String[] args) {
//        System.out.println(new DanhgiaDAO().GetSolanthi("longqnh", "Hàm số"));
//        String noidung = "hamso";
//        Users user = new Users("longqnh", null, null, null, null, 12);
//        int solanthi = new DanhgiaDAO().GetSolanthi(user.getUsername(), "Hàm số");
//        double nangluc = new DanhgiaDAO().DanhGiaNangLuc("00003", noidung);
//        new DanhgiaDAO().updateKyVong(user, noidung, solanthi, nangluc);
//        new DanhgiaDAO().updatePhuongSai(user, noidung, nangluc);
//        new DanhgiaDAO().UocLuong(user, noidung, solanthi);
    }
}
