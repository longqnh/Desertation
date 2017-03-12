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
import javax.servlet.http.HttpSession;
import model.Question;
import model.Users;

/**
 *
 * @author NTL
 */
public class QuestionDAO {
    public List CreateExam(String[] noidung, int level, int numQuestion) {
        Connection connection = DBConnect.getConnecttion();
        
        int socauDe, socauTB, socauTBK, socauKho;
        
        switch (level) {
            case 0:
                socauDe = (int) (0.5*numQuestion);
                socauTB = (int) (0.3*numQuestion);
                socauTBK = (int) (0.1*numQuestion);
                socauKho = (int) (0.1*numQuestion);
                break;
            case 1:
                socauDe = (int) (0.3*numQuestion);
                socauTB = (int) (0.4*numQuestion);
                socauTBK = (int) (0.2*numQuestion);
                socauKho = (int) (0.1*numQuestion);
                break;
            default:
                socauDe = (int) (0.2*numQuestion);
                socauTB = (int) (0.3*numQuestion);
                socauTBK = (int) (0.3*numQuestion);
                socauKho = (int) (0.2*numQuestion);   
                break;
        }     
                
	List exam = new ArrayList();

	try {
            // excute multiple queries (sql1 and sql2)
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql1 = "TRUNCATE table_dethi";
            String sql2 = "INSERT INTO table_dethi(`id`,`noidung`,`dapanA`,`dapanB`,`dapanC`,`dapanD`,`dapan`,`dangtoan`,`dangbt`,`dokho`,`dophancach`,`malop`,`hinh`) " +
                        "SELECT * FROM (" +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=0 ORDER BY RAND() LIMIT " + socauDe + ") " +
                        "UNION ALL " +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=1 ORDER BY RAND() LIMIT " + socauTB + ") " +
                        "UNION ALL " +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=2 ORDER BY RAND() LIMIT " + socauTBK + ") " +
                        "UNION ALL " +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=3 ORDER BY RAND() LIMIT " + socauKho + ") " +
                        ") AS foo;";   
            connection.setAutoCommit(false);
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.executeBatch();
            connection.commit();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM table_dethi");
            
            while (rs.next()) {
                Question question = new Question();
                
                question.setId(rs.getString("id"));
                question.setNoidung(rs.getString("noidung"));
                question.setDapanA(rs.getString("dapanA"));
                question.setDapanB(rs.getString("dapanB"));
                question.setDapanC(rs.getString("dapanC"));
                question.setDapanD(rs.getString("dapanD"));
                question.setDapan(rs.getString("dapan"));
                question.setDangtoan(rs.getString("dangtoan"));
                question.setDangbt(rs.getString("dangbt"));
                question.setDokho(rs.getInt("dokho"));
                question.setDophancach(rs.getInt("dophancach"));
                question.setMalop(rs.getInt("malop"));
                question.setHinh(rs.getInt("hinh"));
                exam.add(question);
            }
            connection.close();
            return exam;
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return null;
    }
    
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
        String sql = "delete from table_ where id='" + maCH + "'";
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
