/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DangtoanDAO;
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

/**
 *
 * @author NTL
 */
public class Practice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String monhoc = request.getParameter("monhoc");
        String lop = request.getParameter("lop");
        int dokho = Integer.parseInt(request.getParameter("dokho"));
        int socau = Integer.parseInt(request.getParameter("socau"));
        String []noidung = request.getParameterValues("kienthuc");
        
        List<Dangtoan> list = new ArrayList<>();
        
        for (String nd : noidung) {
            String TV = DangtoanDAO.GetNoidungTV(nd);
            Dangtoan d = new Dangtoan(nd, TV);
            
            list.add(d);
        }
        
        HttpSession session = request.getSession();
        
        session.setAttribute("cacND", list);
        request.setAttribute("socau", socau);
        request.setAttribute("lop", lop);
        request.setAttribute("dokho", dokho);
        request.setAttribute("monhoc", monhoc);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Thi/Practice-new.jsp");
        rd.forward(request, response);
    }
}
