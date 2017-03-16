/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connect.DBConnect;
import dao.DethiDAO;
import dao.QuanLyDeThiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.QuanLyDeThi;
import model.Question;
import model.Users;

/**
 *
 * @author NTL
 */
public class FinishExam extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        List<String> user_answer = new ArrayList<>();
        
        List IDlist = null;
        if (session.getAttribute("ID_List")!=null) {
            IDlist = (List) session.getAttribute("ID_List");
        }
        
        Users users = null;
        if (session.getAttribute("user")!=null) {
            users = (Users) session.getAttribute("user");
        }
        
        DethiDAO dethiDAO = new DethiDAO();
        String made = dethiDAO.GetMade(users.getUsername());
                            
        int socaudung = 0;
        String sql;
        PreparedStatement ps; 
        
        Connection con = DBConnect.getConnecttion();        

        for (int i=0; i<IDlist.size(); i++) {
            String temp = IDlist.get(i).toString();
            sql = "SELECT dapan FROM table_dethi WHERE (made='" + made + "') AND (id='" + temp + "')";
        
            try {
                ps = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String correct = rs.getString("dapan");
                    String user_select = request.getParameter(temp);
                    user_answer.add(user_select);
                    
                    if (user_select == null) {
                        //out.println("chua lam");
                    } else {
                        if (correct.equals(user_select)) {
                            //out.println("correct");
                            socaudung++;
                        } else {
                            //out.println("wrong");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        float score = socaudung*((float)10/IDlist.size());
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateobj = new Date();
        String ngaythi = df.format(dateobj);

        sql = "SELECT * FROM table_quanlydethi WHERE made='" + made + "'";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int socau = rs.getInt("socau");
                String noidung = rs.getString("noidung");
                int thoigian = rs.getInt("thoigian");
                int mucdo = rs.getInt("mucdo");
                QuanLyDeThi deThi = new QuanLyDeThi(made, socau, noidung, thoigian, mucdo, score, ngaythi, users.getUsername());

                QuanLyDeThiDAO qldtdao = new QuanLyDeThiDAO();
                qldtdao.CompleteInfo(deThi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
        session.setAttribute("DiemThi", score);
        session.setAttribute("UserAnswer", user_answer);
        response.sendRedirect("Thi/FinishExam.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
