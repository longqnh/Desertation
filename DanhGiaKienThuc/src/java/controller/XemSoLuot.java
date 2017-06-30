/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NTL
 */
public class XemSoLuot extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        QuestionDAO questionDAO = new QuestionDAO();
        Map thongke = questionDAO.ThongKeSoLuot(id);
                
        request.setAttribute("dung", thongke.get("dung"));
        request.setAttribute("tong", thongke.get("tong"));
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/QLCH.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
