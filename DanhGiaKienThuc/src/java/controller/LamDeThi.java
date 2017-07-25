/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DangtoanDAO;
import dao.DethiDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Dangtoan;
import model.Users;
import tools.DanhGiaKienThuc;

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
        
        DethiDAO dethiDAO = new DethiDAO();
        DangtoanDAO dangtoanDAO = new DangtoanDAO();
        DanhGiaKienThuc danhgia = new DanhGiaKienThuc();
        
        String[] noidung;
        int lop, level, time, numQuestion, type = 0;
        List<Integer> socau = new ArrayList<>();

        String monhoc = request.getParameter("monhoc");
        
        if (request.getParameter("dethi")==null) { // mode practice
            noidung = request.getParameterValues("kienthuc");
            lop = Integer.parseInt(request.getParameter("lop"));
            
            if (request.getParameter("dokho") != null) {
                level = Integer.parseInt(request.getParameter("dokho"));
            } else {
                double nangluc = danhgia.DanhGiaNangLuc(users.getUsername(), noidung[0]);
                if (nangluc <= 0.5) {
                    level = 0;
                } else if (nangluc <=8) {
                    level = 1;
                } else {
                    level = 2;
                }
            }
            
            if (request.getParameter("time") != null) {
                time = Integer.parseInt(request.getParameter("time"));
                numQuestion = (time == 15 ? 10 : (time == 60 ? 40 : 50));                
            } else {                
                numQuestion = Integer.parseInt(request.getParameter("socau"));
                time = (int)(numQuestion*1.5);
                
                if (request.getParameter("dokho") != null) {
                    List<Dangtoan> list = (List) session.getAttribute("cacND");

                    if (list != null) {
                        noidung = new String[list.size()];
                        int i = 0;
                        for (Dangtoan dangtoan : list) {
                            String nd = dangtoan.getDangtoan();
                            noidung[i++] = nd;
                            int n = Integer.parseInt(request.getParameter(nd));
                            socau.add(n);
                        }
                        type = 1;
                    }
                }
            }
        } else { // mode mock test
            String dethi = request.getParameter("dethi");
            lop = Integer.parseInt(request.getParameter("lop"));
            level = Integer.parseInt(request.getParameter("dokho"));

            switch (dethi) {
                case "hk1":
                    time = 60; 
                    numQuestion = 40;
                    noidung = dangtoanDAO.getDangtoanTheoHocky(monhoc,lop,1);                    
                    break;
                case "hk2":
                    time = 60; 
                    numQuestion = 40;
                    noidung = dangtoanDAO.getDangtoanTheoHocky(monhoc,lop,2);                    
                    break;
                case "canam":
                    time = 60; 
                    numQuestion = 40;
                    noidung = dangtoanDAO.getAllDangtoanLop(monhoc,lop);                    
                    break;
                default:
                    time = 90; 
                    numQuestion = 50;
                    noidung = dangtoanDAO.getAllDangtoanLop(monhoc,12);
                    break;
            }
        }
        //

        // tao de
        if (type==0) {
            dethiDAO.TaoDe(monhoc,noidung, level, numQuestion, users.getUsername(), time);
        } else {
            dethiDAO.TaoDe(monhoc, noidung, socau, level, users.getUsername(), time);
        }
        
        String made = dethiDAO.GetMade(users.getUsername());
        List exam = dethiDAO.GetDeThi(made,0);
        
        session.setAttribute("made", made);
        request.setAttribute("time", time);
        request.setAttribute("exam", exam);
        //
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Thi/LamDeThi.jsp");
        rd.forward(request, response);
    }
}
