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
import model.Question;

/**
 *
 * @author NTL
 */
public class QuestionDAO {    
    public QuestionDAO() {
    }
        
    public boolean InsertQuestion(Question q) {
        Connection connection= DBConnect.getConnecttion();
        String sql = "INSERT INTO NHCHTOAN VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, q.getId());
            ps.setString(2, q.getNoidung());
            ps.setString(3, q.getDapanA());
            ps.setString(4, q.getDapanB());
            ps.setString(5, q.getDapanC());
            ps.setString(6, q.getDapanD());
            ps.setString(7, q.getDapan());
            ps.setString(8, q.getDangtoan());
            ps.setString(9, q.getDangbt());
            ps.setInt(10, q.getDokho());
            ps.setInt(11, q.getDophancach());
            ps.setInt(12, q.getMalop());
            ps.setInt(13, q.getHinh());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateQuestion(Question q) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "update NHCHTOAN set noidung=?, dapanA=?, dapanB=?, dapanC=?, dapanD=?, dapan=?, dangtoan=?, dangbt=?, dokho=?, malop=?, hinh=? where id=?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall(sql);
            ps.setString(1, q.getNoidung());
            ps.setString(2, q.getDapanA());
            ps.setString(3, q.getDapanB());
            ps.setString(4, q.getDapanC());
            ps.setString(5, q.getDapanD());
            ps.setString(6, q.getDapan());
            ps.setString(7, q.getDangtoan());
            ps.setString(8, q.getDangbt());
            ps.setInt(9, q.getDokho());
            ps.setInt(10, q.getMalop());
            ps.setInt(11, q.getHinh());
            ps.setString(12, q.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteQuestion(Question q) {
        Connection con = DBConnect.getConnecttion();
        
        String sql = "delete from NHCHTOAN where id='" + q.getId() + "'";
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
    
    public List<Question> getAllQuestions(String nd, String lop, String search_id, int startPageIndex, int recordsPerPage) {
        Connection connection = DBConnect.getConnecttion();
        List<Question> list = new ArrayList();
        
        String sql = "SELECT * FROM NHCHTOAN WHERE dangtoan='" + nd + "' AND malop=" + lop + " AND id LIKE '%" + search_id + "' LIMIT " + startPageIndex + "," + recordsPerPage;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String id = rs.getString("id");
                String noidung = rs.getString("noidung");
                String dapanA = rs.getString("dapanA");
                String dapanB = rs.getString("dapanB");
                String dapanC = rs.getString("dapanC");
                String dapanD = rs.getString("dapanD");
                String dapan = rs.getString("dapan");
                String dangtoan = rs.getString("dangtoan");
                String dangbt = rs.getString("dangbt");
                int dokho = rs.getInt("dokho");
                int dophancach = rs.getInt("dophancach");
                int malop = rs.getInt("malop");
                int hinh = rs.getInt("hinh");
                
                Question q = new Question(id, noidung, dapanA, dapanB, dapanC, dapanD, dapan, dangtoan, dangbt, dokho, dophancach, malop, hinh);
                list.add(q);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public int getQuestionCount(String nd, String lop) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM NHCHTOAN WHERE dangtoan='" + nd + "' AND malop=" + lop;
        
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

    public Question getQuestionById(String id) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "SELECT * FROM NHCHTOAN WHERE id='" + id + "'";
        Question q = new Question(id);
        
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                q.setNoidung(rs.getString("noidung"));
                q.setDapanA(rs.getString("dapanA"));
                q.setDapanB(rs.getString("dapanB"));
                q.setDapanC(rs.getString("dapanC"));
                q.setDapanD(rs.getString("dapanD"));
                q.setDapan(rs.getString("dapan"));
                q.setDangtoan(rs.getString("dangtoan"));
                q.setDangbt(rs.getString("dangbt"));
                q.setDokho(rs.getInt("dokho"));
                q.setDophancach(rs.getInt("dophancach"));
                q.setMalop(rs.getInt("malop"));
                q.setHinh(rs.getInt("hinh"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }     
        return q;
    }
    
    public String generateId(String dangtoan) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        
        String dangtoanId = new DangtoanDAO().getDangtoanID(dangtoan);
        
        String sql = "SELECT * FROM NHCHTOAN WHERE id LIKE '" + dangtoanId + "%' ORDER BY id DESC LIMIT 1";
        String id = null;
        String res = new String();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                id = rs.getString("id");
            }
            
            String temp = id.substring(id.length()-3);
            int num = Integer.parseInt(temp) + 1;
            if (num < 100) {
                temp = "0" + num;
            } else {
                temp = Integer.toString(num);
            }
            
            res = dangtoanId + temp;
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDeThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return res;
    }
    
    public String getMucdo(String id) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
                
        String sql = "SELECT mucdo FROM table_dokhoch WHERE dokho = (SELECT dokho FROM nhchtoan WHERE id='" + id + "')";
        String res = new String();
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                res = rs.getString("mucdo");
            }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDeThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;        
    }
    
    private void updateDokho(String id, int dokho) {
        Connection connection = DBConnect.getConnecttion();        
        String sql = "UPDATE NHCHTOAN SET dokho='" + dokho + "' WHERE id='" + id + "'";
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getDokho(int lamdung, int dalam) {
        double dokho = (double) lamdung/dalam;
        double dokhotb = (1+0.25)/2;
        double temp = dokhotb/2;
        int res;
        
        if (dokho <= temp) {
            res = 0;
        } else {
            if (temp<dokho && dokho<=dokhotb) {
                res = 1;
            } else {
                if (dokho>dokhotb && dokho>=dokhotb+temp) {
                    res = 2;
                } else {
                    res = 3;
                }
            }
        }
        
        return res;
    }
    
    public void updateDokho(String dangtoan, String id) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "CALL thongkesoluong('" + dangtoan + "','" + id + "')";
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int dalam = rs.getInt("dalam");
                int lamdung = rs.getInt("lamdung");
                
                if (dalam%10==0) {
                    int dokho = getDokho(lamdung, dalam);
                    updateDokho(id, dokho);
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongkeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
