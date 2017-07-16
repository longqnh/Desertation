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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuanLyDeThi;
import model.Users;
import tools.DanhGiaKienThuc;

/**
 *
 * @author NTL
 */
public class QuanLyDeThiDAO {
    private void CompleteInfo(QuanLyDeThi thi) {
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

    public float GetDiem(String made) {
        float diem=0;
        
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT diem FROM table_quanlydethi WHERE made='" + made + "'";
        
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                diem =rs.getFloat("diem");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DethiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return diem;
    }
       
    public List<QuanLyDeThi> getAllDethi(Users user, String made_search, int startPageIndex, int recordsPerPage) {
        Connection connection = DBConnect.getConnecttion();
        List<QuanLyDeThi> list = new ArrayList();
        
        String sql;
        
        if (user.getRole().equals("admin")) {
            sql = "SELECT * FROM table_quanlydethi WHERE made LIKE '%" + made_search + "' LIMIT " + startPageIndex + "," + recordsPerPage;            
        } else {
            sql = "SELECT * FROM table_quanlydethi WHERE username='" + user.getUsername() + "' LIMIT " + startPageIndex + "," + recordsPerPage;            
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String made = rs.getString("made");
                int socau = rs.getInt("socau");        
                String monhoc = rs.getString("monhoc");
                String noidung = rs.getString("noidung");
                int thoigian = rs.getInt("thoigian");
                String mucdo = rs.getString("mucdo");
                float diem = rs.getFloat("diem");
                String ngaythi = null;
                if (rs.getString("ngaythi") != null) {
                    ngaythi = convertTimeToDate(Long.parseLong(rs.getString("ngaythi")));
                }                
                String username = rs.getString("username");
                
                QuanLyDeThi deThi = new QuanLyDeThi(made, socau, monhoc, noidung, thoigian, mucdo, diem, ngaythi, username);
                list.add(deThi);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;        
    }

    public int getDethiCount(Users user) {
        Connection connection = DBConnect.getConnecttion();
        String sql;
        
        if (user.getRole().equals("admin")) {
            sql = "SELECT COUNT(*) AS COUNT FROM table_quanlydethi";
        } else {
            sql = "SELECT COUNT(*) AS COUNT FROM table_quanlydethi WHERE username='" + user.getUsername() + "'";
        }
        
	int count=0;
	try 
	{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();	
            while (rs.next()) 
            {
                count=rs.getInt("COUNT");
            }
	} 
	catch (SQLException e) 
	{
            System.err.println(e.getMessage());
	}
	return count;        
    }

    public List GetAllMade (String thisinh, String noidung) { //noidung in VNese
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_quanlydethi WHERE username='" + thisinh + "'";
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String made = rs.getString("made");
                String cacnoidung = rs.getString("noidung");
                if (cacnoidung.contains(noidung)) {
                    list.add(made);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhGiaKienThuc.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        return list;
    }
    
    public boolean DeleteDethi(String made) {
        Connection con = DBConnect.getConnecttion();
        
        String sql = "DELETE FROM table_quanlydethi WHERE made='" + made + "'";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;        
    }
    
    private String convertTimeToDate(long time) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateobj = new Date(time);
        String ngaythi = df.format(dateobj);
        
        return ngaythi;
    }
    
    public void updateInfo(String made, String thisinh, float diem) {
        Connection con = DBConnect.getConnecttion();
        PreparedStatement ps;
        
        String ngaythi = Long.toString(System.currentTimeMillis());
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date dateobj = new Date(milliseconds);
//        String ngaythi = df.format(dateobj);

        String sql = "SELECT * FROM table_quanlydethi WHERE made='" + made + "'";

        try {
            ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int socau = rs.getInt("socau");
                String monhoc = rs.getString("monhoc");
                String noidung = rs.getString("noidung");
                int thoigian = rs.getInt("thoigian");
                String mucdo = rs.getString("mucdo");
                QuanLyDeThi deThi = new QuanLyDeThi(made, socau, monhoc, noidung, thoigian, mucdo, diem, ngaythi, made);

                QuanLyDeThiDAO qldtdao = new QuanLyDeThiDAO();
                qldtdao.CompleteInfo(deThi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    public static int GetSolanthi(String username, String noidung) { // noidung in VNese
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
            Logger.getLogger(DanhGiaKienThuc.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
        return solanthi;
    }        
    
    public int GetExamTime (String made) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_quanlydethi WHERE made='" + made + "'";
        PreparedStatement ps;
        
        int time=0;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                time = rs.getInt("thoigian");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhGiaKienThuc.class.getName()).log(Level.SEVERE, null, ex);
        }

        return time;        
    }
    
//    public static void main(String[] args) {
//        System.out.println(QuanLyDeThiDAO.GetSolanthi("longqnh", DangtoanDAO.GetNoidungTV("thongke")));
//    }
}
