/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DangtoanDAO;
import dao.DethiDAO;
import dao.DokhoDAO;
import dao.QuanLyDeThiDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Dokho;
import model.Users;

/**
 *
 * @author NTL
 */
public class FinishExam extends HttpServlet {
    
    DethiDAO dethiDAO = new DethiDAO();
    QuanLyDeThiDAO qldtdao = new QuanLyDeThiDAO();
    QuestionDAO qdao = new QuestionDAO();
    DangtoanDAO dangtoanDAO = new DangtoanDAO();
    DokhoDAO dokhoDAO = new DokhoDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException,IOException{
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
        
        HttpSession session = request.getSession();
        
        List<String> user_answer = new ArrayList<>();
        
        List IDlist = null;
        if (session.getAttribute("ID_List")!=null) {
            IDlist = (List) session.getAttribute("ID_List");
        }
        
        Users users = null;
        if (session.getAttribute("user")!=null) {
            users = (Users) session.getAttribute("user");
        }
        
        String made = null;
        if (session.getAttribute("made")!=null) {
            made = (String) session.getAttribute("made");
        }
        
        List<Dokho> list = dokhoDAO.GetAllDokhoCH();
        
        // lay bai lam cua thi sinh
        for (int i=0; i<IDlist.size(); i++) {
            String id = IDlist.get(i).toString();
            String user_select = request.getParameter(id);
            user_answer.add(user_select);
        }

        float score = dethiDAO.ChamDiem(made, users.getUsername(), IDlist, user_answer);
        qldtdao.updateInfo(made, users.getUsername(), score);
        
        List<String> allDangtoan = dethiDAO.getAllDangToan(made);
        
        // update dokho + dophancach
        for (String dangtoan : allDangtoan) {
            List<String> listQuestions = dethiDAO.getAllQuestions(made, dangtoan);
            for (String questionID : listQuestions) {
                if (qdao.getSoLuotLamCH(questionID) % 10 == 0) {
                    qdao.updateDokho(questionID);
                    qdao.updateDoPhanCach(questionID);
                    
                    if (qdao.getSoLuotLamCH(questionID) % 20 == 0) {
                        for (Dokho d : list) {
                            dangtoanDAO.updateTrongSo(dangtoan, d.getDokho());
                        }
                    }
                }
            }
        }
                
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Thi/FinishExam.jsp?made=" + made);
        rd.forward(request, response);
    }
}