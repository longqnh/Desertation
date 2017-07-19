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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dethi;
import tools.MD5;

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
    
    DangtoanDAO dangtoanDAO;
    DokhoDAO dokhoDAO;
    MonHocDAO monHocDAO;
    
    public DethiDAO() {
        dangtoanDAO = new DangtoanDAO();
        dokhoDAO = new DokhoDAO();
        monHocDAO = new MonHocDAO();
    }
    
    private void SetSocauTheoNoiDung (NoiDung[] nd, double nb, double th, double vd, double vdc) {
        for (NoiDung noidung : nd) {
            noidung.setSoCauNB((int) Math.round(noidung.getSoCau() * nb));
            noidung.setSoCauTH((int) Math.round(noidung.getSoCau() * th));
            noidung.setSoCauVD((int) Math.round(noidung.getSoCau() * vd));
            noidung.setSocauVDC((int) Math.round(noidung.getSoCau() * vdc));
            Check(noidung);
        }
    }
    
    private void Check(NoiDung nd) {
        int tong = nd.getSoCauNB()+nd.getSoCauTH()+nd.getSoCauVD()+nd.getSocauVDC();
        int socau = nd.getSoCau();
        
        while (tong < socau) {
            int min = nd.getSoCauNB();
            
            if (min >= nd.getSoCauTH()) {
                min = nd.getSoCauTH();
                nd.setSoCauTH(min+1);
            } else {
                if (min >= nd.getSoCauVD()) {
                    min = nd.getSoCauVD();
                    nd.setSoCauVD(min+1);
                } else {
                    if (min >= nd.getSocauVDC()) {
                        min = nd.getSocauVDC();
                        nd.setSocauVDC(min+1);
                    } else {
                        nd.setSoCauNB(min+1);
                    }
                }
            }
            tong = nd.getSoCauNB()+nd.getSoCauTH()+nd.getSoCauVD()+nd.getSocauVDC();
        }
        
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
    
    public void TaoDe(String monhoc, String[] noidung, List<Integer> socau, int level, String username, int time) {
        Connection connection = DBConnect.getConnecttion();

        int soNoiDung = noidung.length;
        NoiDung nd[] = new NoiDung[soNoiDung];
        int tongsocau = 0;
        String cacNoiDung = "";
        
        for (int i=0; i < soNoiDung; i++) {
            nd[i] = new NoiDung();
        }
        
        for (int i=0; i < soNoiDung; i++) {
            nd[i].setNoidung(noidung[i]);
            nd[i].setSoCau(socau.get(i));
            cacNoiDung += DangtoanDAO.GetNoidungTV(nd[i].getNoidung()) + ", ";
            tongsocau += nd[i].getSoCau();
        }
        
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

        String made = generateMade();
        
	try {
            // excute multiple queries (sql1 and sql2)
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql1 = "TRUNCATE table_dethi";
            
            // update table_quanlydethi
            String update_qldethi = "INSERT INTO table_quanlydethi(`monhoc`,`made`,`socau`,`noidung`,`thoigian`,`mucdo`,`username`) VALUES ('" +
                                    monHocDAO.getTenMonHoc(monhoc) + "','" + made + "','" + tongsocau + "','" + cacNoiDung + "','" + time + "','" + dokhoDAO.GetDoKhoTV(level) + "','" + username + "')";
            
            // update table_dethi
            String update_dethi = "INSERT INTO table_dethi(`id`,`noidung`,`dapanA`,`dapanB`,`dapanC`,`dapanD`,`dapan`,`monhoc`,`dangtoan`,`dangbt`,`dokho`,`dophancach`,`malop`,`hinh`,`dao`,`made`) " +
                        "SELECT *, (SELECT made FROM table_quanlydethi WHERE username='" + username + "' ORDER BY made DESC LIMIT 1) FROM (";
            
            for (int i=0; i < nd.length; i++) {
                int lop = dangtoanDAO.GetLop(nd[i].getNoidung());
                String dangtoan = nd[i].getNoidung();
                update_dethi += "(SELECT * FROM NHCHTOAN WHERE dokho=0 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauNB() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM NHCHTOAN WHERE dokho=1 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauTH() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM NHCHTOAN WHERE dokho=2 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauVD() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM NHCHTOAN WHERE dokho=3 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSocauVDC() + ") ";
                if (i < nd.length - 1) {
                    update_dethi += "UNION ALL ";
                }
            }
            update_dethi += ") AS foo ORDER BY RAND()";
            
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
    
    public void TaoDe(String monhoc, String[] noidung, int level, int numQuestion, String username, int time) {
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
            cacNoiDung += DangtoanDAO.GetNoidungTV(nd[i].getNoidung()) + ", ";
            tongsocau += nd[i].getSoCau();
        }
        nd[soNoiDung-1].setNoidung(noidung[soNoiDung-1]);
        nd[soNoiDung-1].setSoCau(numQuestion - tongsocau);
        cacNoiDung += DangtoanDAO.GetNoidungTV(nd[soNoiDung-1].getNoidung());
        
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
        
        String made = generateMade();
        
	try {
            // excute multiple queries (sql1 and sql2)
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql1 = "TRUNCATE table_dethi";
            
            // update table_quanlydethi
            String update_qldethi = "INSERT INTO table_quanlydethi(`monhoc`,`made`,`socau`,`noidung`,`thoigian`,`mucdo`,`username`) VALUES ('" +
                                    monHocDAO.getTenMonHoc(monhoc) + "','" + made + "','" + numQuestion + "','" + cacNoiDung + "','" + time + "','" + dokhoDAO.GetDoKhoTV(level) + "','" + username + "')";
            
            // update table_dethi
            String update_dethi = "INSERT INTO table_dethi(`id`,`noidung`,`dapanA`,`dapanB`,`dapanC`,`dapanD`,`dapan`,`monhoc`,`dangtoan`,`dangbt`,`dokho`,`dophancach`,`malop`,`hinh`,`dao`,`made`) " +
                        "SELECT *, (SELECT made FROM table_quanlydethi WHERE username='" + username + "' ORDER BY made DESC LIMIT 1) FROM (";
            
            for (int i=0; i < nd.length; i++) {
                int lop = dangtoanDAO.GetLop(nd[i].getNoidung());
                String dangtoan = nd[i].getNoidung();
                update_dethi += "(SELECT * FROM NHCHTOAN WHERE dokho=0 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauNB() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM NHCHTOAN WHERE dokho=1 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauTH() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM NHCHTOAN WHERE dokho=2 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSoCauVD() + ") " +
                                "UNION ALL " +
                                "(SELECT * FROM NHCHTOAN WHERE dokho=3 AND monhoc='" + monhoc + "' AND dangtoan='" + dangtoan + "' AND malop=" + lop + " ORDER BY RAND() LIMIT " + nd[i].getSocauVDC() + ") ";
                if (i < nd.length - 1) {
                    update_dethi += "UNION ALL ";
                }
            }
            update_dethi += ") AS foo ORDER BY RAND()";
            
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
    
    public String generateMade() {
        String time = String.valueOf(System.currentTimeMillis());
//        String temp = MD5.encryption(time);
//        int length = temp.length();
//        
//        String made = new String();
//        Random random = new Random();
//        
//        while (true) {
//            int x = random.nextInt(length);
//            int y = random.nextInt(length);
//            
//            if (x < y && y - x >= 5 && y - x <= 10) {
//                made = temp.substring(x, y);
//                break;
//            }
//        }
        
        return time;
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
    
    private String updateLatex(String str) {
        StringBuilder sb = new StringBuilder(str);
        
        int i = 0;
        while (i < sb.length()) {
            if (sb.charAt(i)=='\\') {
                sb.insert(i, '\\');
                i++;
            }
            i++;
        }

        return sb.toString();
    }
    
    private void updateQuestion(Dethi question) {
        Connection connection = DBConnect.getConnecttion();
        
        String sql = "UPDATE table_dethi SET dapanA='" + question.getDapanA()
                    + "', dapanB='" + question.getDapanB() + "', dapanC='" + question.getDapanC()
                    + "', dapanD='" + question.getDapanD() + "', dapan='" + question.getDapan()
                    + "' WHERE (id='" + question.getId() + "') AND (made='" + question.getMade() + "')";
        
        PreparedStatement ps;
        try {
            ps = connection.prepareCall(sql);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private int GetPosition(String dapan) {
        switch (dapan) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            default:
                return 3;
        }
    }
    
    private Dethi DaoCauTraLoi (Dethi question) {
        Dethi q = question;

        String[] dapan = {"A", "B", "C", "D"};
        String[] thutu = {"A", "B", "C", "D"};
        List<String> cauTL = new ArrayList<>();
        cauTL.add(updateLatex(question.getDapanA()));
        cauTL.add(updateLatex(question.getDapanB()));
        cauTL.add(updateLatex(question.getDapanC()));
        cauTL.add(updateLatex(question.getDapanD()));
               
        Random random = new Random();
        for (int i=0; i < thutu.length; i++) {            
            int j = random.nextInt(dapan.length);
            String temp = thutu[i];
            thutu[i] = thutu[j];
            thutu[j] = temp;
        }

        q.setDapanA(cauTL.get(GetPosition(thutu[0])));
        q.setDapanB(cauTL.get(GetPosition(thutu[1])));
        q.setDapanC(cauTL.get(GetPosition(thutu[2])));
        q.setDapanD(cauTL.get(GetPosition(thutu[3])));
        
        for (int i=0; i < thutu.length; i++) {            
            if (thutu[i].equals(question.getDapan())) {
                q.setDapan(dapan[i]);
                break;
            }
        }
        
        return q;
    }
    
    public void SetDeThi(String made) {
        Connection connection = DBConnect.getConnecttion();
  
        Random random = new Random();
        
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
                question.setMonhoc(rs.getString("monhoc"));
                question.setDangtoan(rs.getString("dangtoan"));
                question.setDangbt(rs.getString("dangbt"));
                question.setDokho(rs.getInt("dokho"));
                question.setDophancach(rs.getDouble("dophancach"));
                question.setMalop(rs.getInt("malop"));
                question.setHinh(rs.getInt("hinh"));
                question.setMade(rs.getString("made"));
                question.setDao(rs.getInt("dao"));
                
                int dao = random.nextInt(2);
                if (dao==1 && question.getDao()==1) {
                    question = DaoCauTraLoi(question);
                    updateQuestion(question);
                }
            }
            connection.close();
	} catch (SQLException e) {
            e.printStackTrace();
	}   
    }
    
    public List GetDeThi(String made, int mode) { // mode = 0: tao de, mode = 1: xem dapan
        if (mode==0) {
            SetDeThi(made);
        }
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
                question.setDophancach(rs.getDouble("dophancach"));
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
            String user_select = (String) UserAnswer.get(i);
            
            sql = "UPDATE table_dethi SET userchoice=?, username=? WHERE (id=? AND made=?)";
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
        
        sql = "SELECT COUNT(*) AS socaudung FROM table_dethi WHERE made='" + made + "' AND userchoice=dapan";
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                socaudung = rs.getInt("socaudung");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
                
        float score = socaudung*((float)10/socau);
        return score;
    }
    
    public List getAllDangToan(String made) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT DISTINCT dangtoan FROM table_dethi WHERE made='" + made + "'";
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String dangtoan = rs.getString("dangtoan");
                
                list.add(dangtoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;        
    }
    
    public List getAllQuestions(String made, String dangtoan) {
        List<String> list = new ArrayList<>();
        
        Connection connection = DBConnect.getConnecttion();
        PreparedStatement ps;
        String sql = "SELECT id FROM table_dethi WHERE made='" + made + "' AND dangtoan='" + dangtoan + "'";
        
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String id = rs.getString("id");
                
                list.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangtoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;        
    }
}
