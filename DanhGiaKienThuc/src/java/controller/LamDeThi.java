/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DangtoanDAO;
import dao.DanhgiaDAO;
import dao.DethiDAO;
import dao.QuanLyDeThiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author NTL
 */
public class LamDeThi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Users users = null;
        if (session.getAttribute("user")!=null) {
            users = (Users) session.getAttribute("user");
        }
        
        DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
        DethiDAO dethiDAO = new DethiDAO();
        DangtoanDAO dangtoanDAO = new DangtoanDAO();
        
        String[] noidung;
        int level, time, numQuestion;
        
        if (request.getParameter("dethi")==null) { // mode practice
            noidung = request.getParameterValues("kienthuc");
            level = Integer.parseInt(request.getParameter("dokho"));
            time = Integer.parseInt(request.getParameter("time")); 
            numQuestion = (time == 15 ? 10 : (time == 60 ? 40 : 50));
        } else { // mode mock test
            String dethi = request.getParameter("dethi");
            int lop = Integer.parseInt(request.getParameter("lop"));
            level = Integer.parseInt(request.getParameter("dokho"));

            switch (dethi) {
                case "hk1":
                    time = 60; 
                    numQuestion = 40;
                    noidung = dangtoanDAO.getDangtoanTheoHocky(lop,1);                    
                    break;
                case "hk2":
                    time = 60; 
                    numQuestion = 40;
                    noidung = dangtoanDAO.getDangtoanTheoHocky(lop,2);                    
                    break;
                case "canam":
                    time = 60; 
                    numQuestion = 40;
                    noidung = dangtoanDAO.getAllDangtoanLop(lop);                    
                    break;
                default:
                    time = 90; 
                    numQuestion = 50;
                    noidung = dangtoanDAO.getAllDangtoanLop(lop);                    
                    break;
            }
        }
        //
        
        // update kv, ps
//        for (String nd: noidung) {
//            String lanthicuoi = dethiDAO.GetMade(users.getUsername(), nd);
//            int solanthi = danhgiaDAO.GetSolanthi(users.getUsername(), QuanLyDeThiDAO.GetNoidungTV(nd));
//            if (lanthicuoi!=null && solanthi > 0) {
//                double nangluc = danhgiaDAO.DanhGiaNangLuc(lanthicuoi, nd);
//                danhgiaDAO.updateKyVong(users, nd, solanthi, nangluc);
//                danhgiaDAO.updatePhuongSai(users, nd, nangluc);                    
//            }
//        }
        //
        
        // tao de
        dethiDAO.TaoDe(noidung, level, numQuestion, users.getUsername(), time);
        String made = dethiDAO.GetMade(users.getUsername());
        List exam = dethiDAO.GetDeThi(made);
        
        session.setAttribute("made", made);
        request.setAttribute("time", time);
        request.setAttribute("exam", exam);
        //
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Thi/LamDeThi.jsp");
        rd.forward(request, response);
    }
}
