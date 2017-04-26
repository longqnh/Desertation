/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.SendMail;

/**
 *
 * @author NTL
 */
public class Contact extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String hoten = request.getParameter("hoten");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        
        String emailContent = hoten + "\n" + email + "\n" + sdt + "\n" + content;
        if (SendMail.sendMail("ntlong0208@gmail.com", subject, emailContent)) {
            request.setAttribute("Thankyou", "Chúng tôi trân trọng cảm ơn những lời góp ý từ bạn");
        } else {
            request.setAttribute("Thankyou", "Hệ thống đang gặp sự cố, vui lòng thử lại sau!");
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/contact.jsp");
        rd.forward(request, response);
    }
}
