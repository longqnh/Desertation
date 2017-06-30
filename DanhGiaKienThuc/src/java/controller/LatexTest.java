/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;

/**
 *
 * @author NTL
 */
public class LatexTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String dangtoan = request.getParameter("dangtoan");
        String id = request.getParameter("id");
        QuestionDAO qdao = new QuestionDAO();
        
        List<Question> exam = qdao.getAllQuestionByDangToan(dangtoan,id);
                
        request.setAttribute("exam", exam);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/TestLatexPage.jsp");
        rd.forward(request, response);
    }
}
