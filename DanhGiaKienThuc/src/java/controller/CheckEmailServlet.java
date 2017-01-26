/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NTL
 */
public class CheckEmailServlet extends HttpServlet {

    UsersDao usersDao = new UsersDao();
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
        
        String parameter = request.getParameter("email");
        if (!parameter.equals("")) {
            if (usersDao.checkEmail(parameter)) {
                response.setContentType("text/html; charset=UTF-8");            
                PrintWriter out = response.getWriter();
                out.print("<p style=\"color: red\";>Email đã có người sử dụng</p>");
            }   
            else {
                response.setContentType("text/html; charset=UTF-8");            
                PrintWriter out = response.getWriter();
                out.print("<p style=\"color: green\";>Email hợp lệ</p>");
            }
        }
    }

}
