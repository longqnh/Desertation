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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dethi;

/**
 *
 * @author NTL
 */
class NoiDung{
    private String noidung;
    private int soCau, soCauNB, soCauTH, soCauVD, socauVDC;

    public NoiDung() {
    }

    public NoiDung(String noidung, int soCau, int soCauNB, int soCauTH, int soCauVD, int socauVDC) {
        this.noidung = noidung;
        this.soCau = soCau;
        this.soCauNB = soCauNB;
        this.soCauTH = soCauTH;
        this.soCauVD = soCauVD;
        this.socauVDC = socauVDC;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getSoCau() {
        return soCau;
    }

    public void setSoCau(int soCau) {
        this.soCau = soCau;
    }

    public int getSoCauNB() {
        return soCauNB;
    }

    public void setSoCauNB(int soCauNB) {
        this.soCauNB = soCauNB;
    }

    public int getSoCauTH() {
        return soCauTH;
    }

    public void setSoCauTH(int soCauTH) {
        this.soCauTH = soCauTH;
    }

    public int getSoCauVD() {
        return soCauVD;
    }

    public void setSoCauVD(int soCauVD) {
        this.soCauVD = soCauVD;
    }

    public int getSocauVDC() {
        return socauVDC;
    }

    public void setSocauVDC(int socauVDC) {
        this.socauVDC = socauVDC;
    }
}

public class DethiDAO {
    public void SetSocauTheoNoiDung (NoiDung[] nd, double nb, double th, double vd, double vdc) {
        for (int i = 0; i < nd.length; i++) {
            nd[i].setSoCauNB( (int) Math.round(nd[i].getSoCau()*nb) );
            nd[i].setSoCauTH( (int) Math.round(nd[i].getSoCau()*th) );
            nd[i].setSoCauVD( (int) Math.round(nd[i].getSoCau()*vd) );
            nd[i].setSocauVDC( (int) Math.round(nd[i].getSoCau()*vdc) );            
        }
    }
    
    public void TaoDe(String[] noidung, int level, int numQuestion, String username, int time) {
        Connection connection = DBConnect.getConnecttion();
        
        Map<String, String> map = new HashMap<>();
        map.put("hamso", "Hàm số");
        map.put("loga", "Lũy thừa - Mũ - Logarith");
        map.put("tichphan", "Tích phân");
        map.put("sophuc", "Số phức");
        map.put("hhkg", "Hình học không gian");
        map.put("oxyz", "Oxyz");
        
        int soNoiDung = noidung.length;
        NoiDung nd[] = new NoiDung[soNoiDung];
        int tongsocau = 0;
        String cacNoiDung = "";
        
        for (int i=0; i < soNoiDung; i++) {
            nd[i] = new NoiDung();
        }
        
        for (int i=0; i < soNoiDung - 1; i++) {
            nd[i].setNoidung(noidung[i]);
            nd[i].setSoCau(numQuestion/soNoiDung);
            cacNoiDung += map.get(nd[i].getNoidung()) + ", ";
            tongsocau += nd[i].getSoCau();
        }
        nd[soNoiDung-1].setNoidung(noidung[soNoiDung-1]);
        nd[soNoiDung-1].setSoCau(numQuestion - tongsocau);
        cacNoiDung += map.get(nd[soNoiDung-1].getNoidung());
        
        switch (level) {
            case 0:
                SetSocauTheoNoiDung(nd, 0.5, 0.3, 0.1, 0.1);
                break;
            case 1:
                SetSocauTheoNoiDung(nd, 0.3, 0.4, 0.2, 0.1);
                break;
            default:
                SetSocauTheoNoiDung(nd, 0.2, 0.3, 0.3, 0.1);
                break;
        }     

	try {
            // excute multiple queries (sql1 and sql2)
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql1 = "TRUNCATE table_dethi";
            
            // update table_quanlydethi
            String update_qldethi = "INSERT INTO table_quanlydethi(`socau`,`noidung`,`thoigian`,`mucdo`,`username`) VALUES ('" +
                                    numQuestion + "','" + cacNoiDung + "','" + time + "','" + level + "','" + username + "')";
            
            // update table_dethi
            String update_dethi = "INSERT INTO table_dethi(`id`,`noidung`,`dapanA`,`dapanB`,`dapanC`,`dapanD`,`dapan`,`dangtoan`,`dangbt`,`dokho`,`dophancach`,`malop`,`hinh`,`made`) " +
                        "SELECT *, (SELECT made FROM table_quanlydethi WHERE username='" + username + "' ORDER BY made DESC LIMIT 1) FROM (";
            
            for (int i=0; i < nd.length; i++) {
                update_dethi += "(SELECT * FROM table_" + nd[i].getNoidung() + " WHERE dokho=0 ORDER BY RAND() LIMIT " + nd[i].getSoCauNB() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM table_" + nd[i].getNoidung() + " WHERE dokho=1 ORDER BY RAND() LIMIT " + nd[i].getSoCauTH() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM table_" + nd[i].getNoidung() + " WHERE dokho=2 ORDER BY RAND() LIMIT " + nd[i].getSoCauVD() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM table_" + nd[i].getNoidung() + " WHERE dokho=3 ORDER BY RAND() LIMIT " + nd[i].getSocauVDC() + ") ";
                if (i < nd.length - 1) {
                    update_dethi += "UNION ALL ";
                }
            }
            update_dethi += ") AS foo;";
            
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
    
    public List GetUserchoice(String made) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT userchoice FROM table_dethi WHERE made='" + made + "'";
        
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String choice = rs.getString("userchoice");
                list.add(choice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DethiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return list;
    }    
}