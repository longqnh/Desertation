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
    
    public double DanhGiaNangLuc(String made, String noidung, int solanthi) {
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

//                tongsocau += socau;
                tongsocau += dopc;
                nangluc += ((double) socaudung/socau) * dopc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        nangluc/=tongsocau;  
//        nangluc/=solanthi;
        
        return round(nangluc);
    }    
    
//    public void DanhGiaNangLuc(String made, String[] noidung, Users thisinh) {
//        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
//        
//        for (String nd: noidung) {
//            int solanthi = QuanLyDeThiDAO.GetSolanthi(thisinh.getUsername(), DangtoanDAO.GetNoidungTV(nd));
//            if (solanthi > 0) {
//                double nangluc = danhgiaDAO.DanhGiaNangLuc(made, nd);
//                danhgiaDAO.updateKyVong(thisinh, nd, solanthi, nangluc);
//                danhgiaDAO.updatePhuongSai(thisinh, nd, nangluc);                    
//            }
//        }       
//    }
        
    public HashMap UocLuong(String username, String dangtoan) {
        DangtoanDAO dangtoanDAO = new DangtoanDAO();
        HashMap<String, Double> khoanguocluong = new HashMap<>();        

        double kyvong = dangtoanDAO.GetKyVong(username, dangtoan);
        double phuongsai = dangtoanDAO.GetPhuongSai(username, dangtoan);
        
        int solanthi = QuanLyDeThiDAO.GetSolanthi(username, DangtoanDAO.GetNoidungTV(dangtoan));
        double epsilon;
        if (solanthi==1) {
            kyvong = 0.5;
            epsilon = 0.5;
        } else {
            epsilon = 1.96 * (phuongsai/Math.sqrt(solanthi));
        }
        
        khoanguocluong.put("max", kyvong + epsilon);
        khoanguocluong.put("min", kyvong - epsilon);
        return khoanguocluong;
    }
    
//    public void updatePhuongSai (Users user, String noidung, double ability) {
//        Connection connection = DBConnect.getConnecttion();
//        PreparedStatement ps;
//
//        String nangluc = "";
//        String sql = "SELECT * FROM table_phuongsai WHERE username='" + user.getUsername() + "'";
//        try {
//            ps = connection.prepareCall(sql);
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()) {
//                nangluc = rs.getString(noidung) + " " + Double.toString(round(ability));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }      
//        
//        sql = "UPDATE table_phuongsai SET " + noidung + "='" + nangluc + "' WHERE username='" + user.getUsername() + "'";
//        try {
//            ps = connection.prepareCall(sql);
//            ps.execute(sql);            
//            connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }             
//    }
//    
//    public void updateKyVong(Users user, String noidung, int solanthi, double nangluc) {
//        Connection connection = DBConnect.getConnecttion();
//        
//        double kyvong = 0;
//        
//        String sql = "SELECT * FROM table_kyvong WHERE username='" + user.getUsername() + "'";
//        PreparedStatement ps;      
//
//        try {
//            ps = connection.prepareCall(sql);
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()) {
//                kyvong = (rs.getDouble(noidung) + nangluc/solanthi-1)*(solanthi-1/solanthi);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        sql = "UPDATE table_kyvong SET " + noidung + "='" + kyvong + "' WHERE username='" + user.getUsername() + "'";
//        try {
//            ps = connection.prepareCall(sql);
//            ps.execute(sql);
//            connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//    }
    
    public void updateKyVong(Users user, String noidung) {
        Connection connection = DBConnect.getConnecttion();
        
        DethiDAO dethiDAO = new DethiDAO();
        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
        
        int solanthi = QuanLyDeThiDAO.GetSolanthi(user.getUsername(), DangtoanDAO.GetNoidungTV(noidung));
        String made = dethiDAO.GetMade(user.getUsername(), DangtoanDAO.GetNoidungTV(noidung));

        if (solanthi==0 || made==null) {
            return;
        }

        double nangluc = danhgiaDAO.DanhGiaNangLuc(made, noidung);

        double kyvong = 0;

        String sql = "SELECT * FROM table_kyvong WHERE username='" + user.getUsername() + "'";
        PreparedStatement ps;      

        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (solanthi==1) {
                    kyvong = (nangluc/solanthi);
                } else {
                    kyvong = (rs.getDouble(noidung) + nangluc/solanthi-1)*(solanthi-1/solanthi);
                }
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
    
    public void updatePhuongSai (Users user, String noidung) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;

        DethiDAO dethiDAO = new DethiDAO();
        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
        
        String made = dethiDAO.GetMade(user.getUsername(), DangtoanDAO.GetNoidungTV(noidung));
        if (made==null) {
            return;
        }
            
        double ability = danhgiaDAO.DanhGiaNangLuc(made, noidung);

        String nangluc = "";
        String sql = "SELECT * FROM table_phuongsai WHERE username='" + user.getUsername() + "'";
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String tmp = rs.getString(noidung);
                if (tmp.equals("0")) {
                    nangluc = Double.toString(round(ability));
                } else {
                    nangluc = rs.getString(noidung) + " " + Double.toString(round(ability));
                }
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
    
//    public static void main(String[] args) {
//        new DanhgiaDAO().updateKyVong(new Users("longqnh"));
//        new DanhgiaDAO().updatePhuongSai(new Users("longqnh"));
//    }
}
