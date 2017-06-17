/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DangBaiTapDAO;
import dao.DangtoanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DangBaiTap;
import model.Dangtoan;

/**
 *
 * @author NTL
 */
public class getDangBT extends HttpServlet {
    DangBaiTapDAO baiTapDAO = new DangBaiTapDAO();
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
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");        
        PrintWriter out = response.getWriter();   
        
        String monhoc = request.getParameter("monhoc");
        
        DangtoanDAO dangtoanDAO = new DangtoanDAO();
        List<Dangtoan> allDangtoan = dangtoanDAO.getAll();
        for (Dangtoan dt : allDangtoan) {
            out.println("<option value='" + dt.getDangtoan() + "'> " + dt.getDangtoanTV() + " </option>");
        }        
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

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");        
        PrintWriter out = response.getWriter();   
        
        String dangtoan = request.getParameter("dangtoan");
        List<DangBaiTap> dangBT = baiTapDAO.GetAllDangBaiTap(dangtoan);
        
        for (DangBaiTap bt : dangBT) {
            out.println("<option value='" + bt.getDangbt() + "'> " + bt.getDangbtTV() + " </option>");
        }
    }
}
