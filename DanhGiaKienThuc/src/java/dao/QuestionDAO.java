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
    DangtoanDAO dangtoanDAO;
    
    public QuestionDAO() {
        dangtoanDAO = new DangtoanDAO();
    }
        
    public boolean InsertQuestion(Question q) {
        Connection connection= DBConnect.getConnecttion();
//        String sql = "INSERT INTO table_" + q.getDangtoan() + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql = "INSERT INTO NHCHTOAN" + dangtoanDAO.GetLop(q.getDangtoan()) + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
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
//        String sql = "update table_" + q.getDangtoan() + " set noidung=?, dapanA=?, dapanB=?, dapanC=?, dapanD=?, dapan=? where id=?";
        String sql = "update NHCHTOAN" + dangtoanDAO.GetLop(q.getDangtoan()) + " set noidung=?, dapanA=?, dapanB=?, dapanC=?, dapanD=?, dapan=? where id=?";
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall(sql);
            ps.setString(1, q.getNoidung());
            ps.setString(2, q.getDapanA());
            ps.setString(3, q.getDapanB());
            ps.setString(4, q.getDapanC());
            ps.setString(5, q.getDapanD());
            ps.setString(6, q.getDapan());
            ps.setString(7, q.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteQuestion(Question q) {
        Connection con = DBConnect.getConnecttion();
        
//        String sql = "delete from table_" + dangtoan + " where id='" + maCH + "'";

        String sql = "delete from NHCHTOAN" + dangtoanDAO.GetLop(q.getDangtoan()) + " where id='" + q.getId() + "'";
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
        
//        String sql = "SELECT * FROM table_" + ReplaceNoidung(nd) + " WHERE malop=" + lop + " AND id LIKE '%" + search_id + "' LIMIT " + startPageIndex + "," + recordsPerPage;//"SELECT * FROM table_" + nd;
        String sql = "SELECT * FROM NHCHTOAN" + lop + " WHERE dangtoan='" + nd + "' AND id LIKE '%" + search_id + "' LIMIT " + startPageIndex + "," + recordsPerPage;

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
//        String sql = "SELECT COUNT(*) AS COUNT FROM table_" + ReplaceNoidung(nd) + " WHERE malop=" + lop;
        String sql = "SELECT COUNT(*) AS COUNT FROM NHCHTOAN" + lop + " WHERE dangtoan='" + nd + "'";
        
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
}
