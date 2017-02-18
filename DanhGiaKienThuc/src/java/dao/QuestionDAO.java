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
                socauDe = (int) 0.5*numQuestion;
                socauTB = (int) 0.3*numQuestion;
                socauTBK = (int) 0.1*numQuestion;
                socauKho = (int) 0.1*numQuestion;
                break;
            case 1:
                socauDe = (int) 0.4*numQuestion;
                socauTB = (int) 0.4*numQuestion;
                socauTBK = (int) 0.1*numQuestion;
                socauKho = (int) 0.1*numQuestion;
                break;
            default:
                socauDe = (int) 0.3*numQuestion;
                socauTB = (int) 0.3*numQuestion;
                socauTBK = (int) 0.2*numQuestion;
                socauKho = (int) 0.2*numQuestion;                
                break;
        }

        String sql = "SELECT * FROM (" +
                        "(SELECT * FROM table_hamso WHERE level=0 ORDER BY RAND() LIMIT 3) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_hamso WHERE level=1 ORDER BY RAND() LIMIT 4) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_hamso WHERE level=2 ORDER BY RAND() LIMIT 2) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_hamso WHERE level=3 ORDER BY RAND() LIMIT 2) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_loga WHERE level=0 ORDER BY RAND() LIMIT 4) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_loga WHERE level=1 ORDER BY RAND() LIMIT 4) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_loga WHERE level=2 ORDER BY RAND() LIMIT 1) " +
                        "UNION ALL " +
                        "(SELECT * FROM table_loga WHERE level=3 ORDER BY RAND() LIMIT 1) " +
                    ") AS foo;";
                
	List exam = new ArrayList();
        PreparedStatement ps;
	try {
            ps = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                
                question.setId(rs.getString("id"));
                question.setNoidung(rs.getString("noidung"));
                question.setDapanA(rs.getString("dapanA"));
                question.setDapanB(rs.getString("dapanB"));
                question.setDapanC(rs.getString("dapanC"));
                question.setDapanD(rs.getString("dapanD"));
                question.setAnswer(rs.getString("answer"));
                question.setDangtoan(rs.getString("dangtoan"));
                question.setDangbt(rs.getString("dangbt"));
                question.setLevel(rs.getInt("level"));
                question.setHasImage(rs.getInt("hasImage"));
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
