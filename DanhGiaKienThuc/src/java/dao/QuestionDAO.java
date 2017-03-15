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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;
import model.Dethi;

/**
 *
 * @author NTL
 */
public class QuestionDAO {
    public boolean InsertQuestion(Question q) {
        Connection connection= DBConnect.getConnecttion();
        String sql = "INSERT INTO table_" + q.getDangtoan() + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
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
        String sql = "update table_" + q.getDangtoan() + " set noidung=?, dapanA=?, dapanB=?, dapanC=?, dapanD=?, dapan=? where id=?";
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

    public boolean DeleteQuestion(String maCH) {
        Connection con = DBConnect.getConnecttion();
        String temp = maCH.substring(0, 2);
        String dangtoan = null;

        switch (temp) {
            case "HS":
                dangtoan = "hamso";
                break;
            case "LO":
                dangtoan = "loga";
                break;
            case "TP":
                dangtoan = "tichphan";
                break;
            case "SP":
                dangtoan = "sophuc";
                break;
            case "HH":
                dangtoan = "hhkg";
                break;
            case "OX":
                dangtoan = "oxyz";
                break;
        }
        
        String sql = "delete from table_" + dangtoan + " where id='" + maCH + "'";
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
    
    public List<Question> getAllQuestions(String nd, String search_id, int startPageIndex, int recordsPerPage) {
        Connection connection = DBConnect.getConnecttion();
        List<Question> list = new ArrayList();
        
        String sql = "SELECT * FROM table_" + nd + " WHERE id LIKE '%" + search_id + "' LIMIT " + startPageIndex + "," + recordsPerPage;//"SELECT * FROM table_" + nd;
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
    
    public int getQuestionCount(String nd) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_" + nd;
        
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
