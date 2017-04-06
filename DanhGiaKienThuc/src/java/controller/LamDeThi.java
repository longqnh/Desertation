/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
        
        // thong tin de thi
        String[] noidung = request.getParameterValues("kienthuc");
        int level = Integer.parseInt(request.getParameter("dokho"));
        int time = Integer.parseInt(request.getParameter("time")); 
        int numQuestion = (time == 15 ? 10 : (time == 60 ? 40 : 50));
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
