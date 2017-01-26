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
    public List CreateExam(String[] noidung, String level) {
        Connection connection = DBConnect.getConnecttion();
        
        String nd = "(";
        for (int i = 0; i < noidung.length; i++) {
            String temp = "dangtoan='";
            temp+=noidung[i];
            temp+="'";
            if (i<noidung.length-1) {
                temp+=" or ";
            }
            nd+=temp;
        }
        nd+=") ";
        
        String lv = "and level=" + level;
        
        String sql = "select * from table_cauhoi where " + nd + lv; //where dangtoan='" + dangtoan + "' and type='" + type + "'";
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
