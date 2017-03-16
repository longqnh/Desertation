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
import model.Dethi;

/**
 *
 * @author NTL
 */
public class DethiDAO {
    public void TaoDe(String[] noidung, int level, int numQuestion, String username, int time) {
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

	try {
            // excute multiple queries (sql1 and sql2)
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql1 = "TRUNCATE table_dethi";
            
            // update table_quanlydethi
            String update_qldethi = "INSERT INTO table_quanlydethi(`socau`,`noidung`,`thoigian`,`mucdo`,`username`) VALUES ('" +
                                    numQuestion + "','" + noidung[0] + "','" + time + "','" + level + "','" + username + "')";
            
            // update table_dethi
            String update_dethi = "INSERT INTO table_dethi(`id`,`noidung`,`dapanA`,`dapanB`,`dapanC`,`dapanD`,`dapan`,`dangtoan`,`dangbt`,`dokho`,`dophancach`,`malop`,`hinh`,`made`) " +
                        "SELECT *, (SELECT made FROM table_quanlydethi WHERE username='" + username + "' ORDER BY made DESC LIMIT 1) " +
                        "FROM ((SELECT * FROM table_" + noidung[0] + " WHERE dokho=0 ORDER BY RAND() LIMIT " + socauDe + ") " +
                        "UNION ALL " +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=1 ORDER BY RAND() LIMIT " + socauTB + ") " +
                        "UNION ALL " +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=2 ORDER BY RAND() LIMIT " + socauTBK + ") " +
                        "UNION ALL " +
                        "(SELECT * FROM table_" + noidung[0] + " WHERE dokho=3 ORDER BY RAND() LIMIT " + socauKho + ") " +
                        ") AS foo;";   
            connection.setAutoCommit(false);
            
            // excute queries
            statement.addBatch(update_qldethi);
            statement.addBatch(update_dethi);
            statement.executeBatch();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
	}
    }
    
    public String GetMade(String username) {
        String made = null;
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT made FROM table_quanlydethi WHERE username='" + username + "' ORDER BY made DESC LIMIT 1";
        
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                made =rs.getString("made");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DethiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return made;
    }
    
    public List GetDeThi(String made) {
        Connection connection = DBConnect.getConnecttion();
  
	List exam = new ArrayList();

	try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            ResultSet rs = statement.executeQuery("SELECT * FROM table_dethi WHERE made='" + made + "'");
            
            while (rs.next()) {
                Dethi question = new Dethi();
                
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
                question.setMade(rs.getString("made"));
                exam.add(question);
            }
            connection.close();
            return exam;
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return null;
    }
}
