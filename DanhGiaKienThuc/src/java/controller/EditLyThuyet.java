/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LythuyetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NTL
 */
public class EditLyThuyet extends HttpServlet {
    LythuyetDAO lythuyetDAO = new LythuyetDAO();
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
        
        String noidung = request.getParameter("kienthuc");
        String content = lythuyetDAO.getContent(noidung);
        
        request.setAttribute("content", content);
        request.setAttribute("kienthuc", noidung);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/QLLT.jsp");
        rd.forward(request, response);
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
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String content = request.getParameter("contentEdited");
        String kienthuc = request.getParameter("kienthuc");
        
        lythuyetDAO.updateContent(kienthuc, content);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/OnLyThuyet/Lop12.jsp");
        rd.forward(request, response);        
    }
}
