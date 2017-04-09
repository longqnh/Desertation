/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.DangtoanDAO;
import dao.DanhgiaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dangtoan;

/**
 *
 * @author NTL
 */
public class DangtoanServlet extends HttpServlet {
    DangtoanDAO dangtoanDAO = new DangtoanDAO();
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
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        Gson gson = new Gson();
        
        int lop = Integer.parseInt(request.getParameter("lop"));
        List<Dangtoan> dangtoan = dangtoanDAO.getDangtoanTheoLop(lop);
        
        String json = new Gson().toJson(dangtoan);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
//        out.print(gson.toJson(dangtoan));
//        out.flush();
//        out.close();
//        for (Dangtoan dt : dangtoan) {
//            out.println("<option value='" + dt.getDangtoan() + "'> " + dt.getDangtoanTV() + " </option>");
//        }   
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
        
    }
}
