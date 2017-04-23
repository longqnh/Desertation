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
    public static double round(double d) {
        return Math.round(d * 100.0)/100.0;
    }
    
//    private Danhgia GetNangLuc(String username) {       
//        DangtoanDAO dangtoanDAO = new DangtoanDAO();
//        List<String> alldangtoan = dangtoanDAO.getAllDangToan();
//        List<Dangtoan> listSoLieu = new ArrayList<>();
//        
//        for (String dt : alldangtoan) {
//            double kyvong = dangtoanDAO.GetKyVong(username, dt);
//            double phuongsai = dangtoanDAO.GetPhuongSai(username, dt);
//            Dangtoan dangtoan = new Dangtoan(dt, kyvong, phuongsai);
//            listSoLieu.add(dangtoan);
//        }
//        
//        Danhgia danhgia = new Danhgia(username, listSoLieu);
//        
//        return danhgia;
//    }
    
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
            int solanthi = DethiDAO.GetSolanthi(thisinh.getUsername(), QuanLyDeThiDAO.GetNoidungTV(nd));
            if (solanthi > 0) {
                double nangluc = danhgiaDAO.DanhGiaNangLuc(made, nd);
                danhgiaDAO.updateKyVong(thisinh, nd, solanthi, nangluc);
                danhgiaDAO.updatePhuongSai(thisinh, nd, nangluc);                    
            }
        }       
    }
        
    public HashMap UocLuong(String username, String dangtoan) {
        DangtoanDAO dangtoanDAO = new DangtoanDAO();
        HashMap<String, Double> khoanguocluong = new HashMap<>();        

        double kyvong = dangtoanDAO.GetKyVong(username, dangtoan);
        double phuongsai = dangtoanDAO.GetPhuongSai(username, dangtoan);
        
        khoanguocluong.put("max", kyvong + phuongsai);
        khoanguocluong.put("min", kyvong - phuongsai);
        return khoanguocluong;
    }
    
    public void updatePhuongSai (Users user, String noidung, double ability) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;

        String nangluc = "";
        String sql = "SELECT * FROM table_phuongsai WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                nangluc = rs.getString(noidung) + " " + Double.toString(round(ability));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        sql = "UPDATE table_phuongsai SET " + noidung + "='" + nangluc + "' WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ps.execute(sql);            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
    public void updateKyVong(Users user, String noidung, int solanthi, double nangluc) {
        Connection connection = DBConnect.getConnecttion();
        
        double kyvong = 0;
        
        String sql = "SELECT * FROM table_kyvong WHERE username='" + user.getUsername() + "'";
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
        
        sql = "UPDATE table_kyvong SET " + noidung + "='" + kyvong + "' WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ps.execute(sql);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void updateKyVong(Users user) {
        Connection connection = DBConnect.getConnecttion();
        
        DethiDAO dethiDAO = new DethiDAO();
        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
        
        List<String> allDangtoan = new DangtoanDAO().getAllDangToan();
        
        for (String noidung : allDangtoan) {
            int solanthi = DethiDAO.GetSolanthi(user.getUsername(), QuanLyDeThiDAO.GetNoidungTV(noidung));
            String made = dethiDAO.GetMade(user.getUsername(), noidung);
            
            if (solanthi==0 || made==null) {
                continue;
            }
            double nangluc = danhgiaDAO.DanhGiaNangLuc(made, noidung);
            
            double kyvong = 0;

            String sql = "SELECT * FROM table_kyvong WHERE username='" + user.getUsername() + "'";
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

            sql = "UPDATE table_kyvong SET " + noidung + "='" + kyvong + "' WHERE username='" + user.getUsername() + "'";
            try {
                ps = connection.prepareCall(sql);
                ps.execute(sql);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }    
    
    public void updatePhuongSai (Users user) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;

        DethiDAO dethiDAO = new DethiDAO();
        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
        
        List<String> allDangtoan = new DangtoanDAO().getAllDangToan();
        
        for (String noidung : allDangtoan) {  
            String made = dethiDAO.GetMade(user.getUsername(), noidung);
            if (made==null) {
                continue;
            }
            
            double ability = danhgiaDAO.DanhGiaNangLuc(made, noidung);
            
            String nangluc = "";
            String sql = "SELECT * FROM table_phuongsai WHERE username='" + user.getUsername() + "'";
            try {
                ps = connection.prepareCall(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    nangluc = rs.getString(noidung) + " " + Double.toString(round(ability));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }      

            sql = "UPDATE table_phuongsai SET " + noidung + "='" + nangluc + "' WHERE username='" + user.getUsername() + "'";
            try {
                ps = connection.prepareCall(sql);
                ps.execute(sql);            
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
    }   
    
//    public static void main(String[] args) {
//        new DanhgiaDAO().updateKyVong(new Users("longqnh"));
//        new DanhgiaDAO().updatePhuongSai(new Users("longqnh"));
//    }
}
