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
    private void SetSocauTheoNoiDung (NoiDung[] nd, double nb, double th, double vd, double vdc) {
        for (int i = 0; i < nd.length; i++) {
            nd[i].setSoCauNB( (int) Math.round(nd[i].getSoCau()*nb) );
            nd[i].setSoCauTH( (int) Math.round(nd[i].getSoCau()*th) );
            nd[i].setSoCauVD( (int) Math.round(nd[i].getSoCau()*vd) );
            nd[i].setSocauVDC( (int) Math.round(nd[i].getSoCau()*vdc) );
            Check(nd[i]);
        }
    }
    
    private void Check(NoiDung nd) {
        int tong = nd.getSoCauNB()+nd.getSoCauTH()+nd.getSoCauVD()+nd.getSocauVDC();
        int socau = nd.getSoCau();
        
        while (tong > socau) {
            int max = nd.getSoCauNB();
            
            if (max <= nd.getSoCauTH()) {
                max = nd.getSoCauTH();
                nd.setSoCauTH(max-1);
            } else {
                if (max <= nd.getSoCauVD()) {
                    max = nd.getSoCauVD();
                    nd.setSoCauVD(max-1);
                } else {
                    if (max <= nd.getSocauVDC()) {
                        max = nd.getSocauVDC();
                        nd.setSocauVDC(max-1);
                    } else {
                        nd.setSoCauNB(max-1);
                    }
                }
            }
            tong = nd.getSoCauNB()+nd.getSoCauTH()+nd.getSoCauVD()+nd.getSocauVDC();
        }
    }
    
    private String ReplaceNoidung(String noidung) {
        String temp = noidung.substring(noidung.length()-2, noidung.length());
        
        if (temp.equals("12") || temp.equals("11") || temp.equals("10")) {
            noidung = noidung.substring(0,noidung.length()-2);
        }
        
        return noidung;
    }
    
    public void TaoDe(String[] noidung, int level, int numQuestion, String username, int time) {
        Connection connection = DBConnect.getConnecttion();

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
            cacNoiDung += QuanLyDeThiDAO.GetNoidungTV(nd[i].getNoidung()) + ", ";
            tongsocau += nd[i].getSoCau();
        }
        nd[soNoiDung-1].setNoidung(noidung[soNoiDung-1]);
        nd[soNoiDung-1].setSoCau(numQuestion - tongsocau);
        cacNoiDung += QuanLyDeThiDAO.GetNoidungTV(nd[soNoiDung-1].getNoidung());
        
        switch (level) {
            case 0:
                SetSocauTheoNoiDung(nd, 0.5, 0.3, 0.1, 0.1);
                break;
            case 1:
                SetSocauTheoNoiDung(nd, 0.3, 0.4, 0.2, 0.1);
                break;
            default:
                SetSocauTheoNoiDung(nd, 0.2, 0.3, 0.3, 0.2);
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

    public void TaoDe(String[] noidung, int lop, int level, int numQuestion, String username, int time) {
        Connection connection = DBConnect.getConnecttion();

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
            cacNoiDung += QuanLyDeThiDAO.GetNoidungTV(nd[i].getNoidung()) + ", ";
            tongsocau += nd[i].getSoCau();
        }
        nd[soNoiDung-1].setNoidung(noidung[soNoiDung-1]);
        nd[soNoiDung-1].setSoCau(numQuestion - tongsocau);
        cacNoiDung += QuanLyDeThiDAO.GetNoidungTV(nd[soNoiDung-1].getNoidung());
        
        switch (level) {
            case 0:
                SetSocauTheoNoiDung(nd, 0.5, 0.3, 0.1, 0.1);
                break;
            case 1:
                SetSocauTheoNoiDung(nd, 0.3, 0.4, 0.2, 0.1);
                break;
            default:
                SetSocauTheoNoiDung(nd, 0.2, 0.3, 0.3, 0.2);
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
                String dangtoan = ReplaceNoidung(nd[i].getNoidung());
                update_dethi += "(SELECT * FROM table_" + dangtoan + " WHERE dokho=0 AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauNB() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM table_" + dangtoan + " WHERE dokho=1 AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauTH() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM table_" + dangtoan + " WHERE dokho=2 AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauVD() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM table_" + dangtoan + " WHERE dokho=3 AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSocauVDC() + ") ";
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
    
    public String GetMade(String username, String noidung) { // noidung in Vnese
        String made = null;
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_quanlydethi WHERE username='" + username + "' ORDER BY made DESC";
        
        PreparedStatement ps;
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nd = rs.getString("noidung");
                if (nd.contains(noidung)) {
                    made = rs.getString("made");
                    break;
                }
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
    
    public float ChamDiem (String made, String thisinh, List IDList, List UserAnswer) {
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql;
        
        int socau = IDList.size();
        int socaudung = 0;
        
        for (int i=0; i<socau; i++) {
            String id = IDList.get(i).toString();
            String user_select = UserAnswer.get(i).toString();
            
            sql = "SELECT * FROM table_dethi WHERE (made='" + made + "') AND (id='" + id + "')";
        
            try {
                ps = connection.prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String correct = rs.getString("dapan");
                    if (user_select == null) {
                    } else {
                        if (correct.equals(user_select)) {
                            socaudung++;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            sql =   "UPDATE table_dethi SET userchoice=?, username=? WHERE (id=? AND made=?)";
            try {
                ps = connection.prepareCall(sql);
                ps.setString(1, user_select);
                ps.setString(2, thisinh);
                ps.setString(3, id);
                ps.setString(4, made);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }            
        }
        
        float score = socaudung*((float)10/socau);
        return score;
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
            Logger.getLogger(DanhgiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
        return solanthi;
    }    
    
//    public static void main(String[] args) {
//        System.out.println(new DethiDAO().GetMade("longqnh", "hamso12"));
//    }
}
