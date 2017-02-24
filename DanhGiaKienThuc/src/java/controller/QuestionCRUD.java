/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;
import model.Users;

/**
 *
 * @author NTL
 */
public class QuestionCRUD extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
    
    private QuestionDAO qdao;

    public QuestionCRUD() {
        qdao = new QuestionDAO();
    }
    
    
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

        String action = request.getParameter("action");
        List<Question> List = new ArrayList<Question>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");
        String kienthuc = request.getParameter("kienthuc");
        
        if (action != null) {
            try {
                if (action.equals("list")) {
                    List = qdao.getAllQuestion(kienthuc);

                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", List);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("update")) {
                    Question q = new Question();
                    if (request.getParameter("id") != null) {
                        String id = request.getParameter("id");
                        q.setId(id);
                    }

                    if (request.getParameter("noidung") != null) {
                        String noidung = request.getParameter("noidung");
                        q.setNoidung(noidung);
                    }

                    if (request.getParameter("dapanA") != null) {
                        String dapanA = request.getParameter("dapanA");
                        q.setDapanA(dapanA);
                    }

                    if (request.getParameter("dapanB") != null) {
                        String dapanB = request.getParameter("dapanB");
                        q.setDapanB(dapanB);
                    }

                    if (request.getParameter("dapanC") != null) {
                        String dapanC = request.getParameter("dapanC");
                        q.setDapanC(dapanC);
                    }

                    if (request.getParameter("dapanD") != null) {
                        String dapanD = request.getParameter("dapanD");
                        q.setDapanD(dapanD);
                    }
                    
                    if (request.getParameter("answer") != null) {
                        String answer = request.getParameter("answer");
                        q.setAnswer(answer);
                    }
                    
                    if (request.getParameter("dangtoan") != null) {
                        String dangtoan = request.getParameter("dangtoan");
                        q.setDangtoan(dangtoan);
                    }

                    if (request.getParameter("dangbt") != null) {
                        String dangbt = request.getParameter("dangbt");
                        q.setDangbt(dangbt);
                    }
                                        
                    if (request.getParameter("level") != null) {
                        String level = request.getParameter("level");
                        q.setLevel(Integer.parseInt(level));
                    }

                    if (request.getParameter("hasImage") != null) {
                        String hasImage = request.getParameter("hasImage");
                        q.setHasImage(Integer.parseInt(hasImage));
                    }
                    
                    if (action.equals("create")) {
                        // Create new record
                        qdao.InsertQuestion(q);
                    } else if (action.equals("update")) {
                        // Update existing record
                        qdao.updateQuestion(q);
                    }

                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", q);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("delete")) {
                    // Delete record
                    if (request.getParameter("id") != null) {
                        String id = request.getParameter("id");
                        qdao.DeleteQuestion(id);

                        // Return in the format required by jTable plugin
                        JSONROOT.put("Result", "OK");

                        // Convert Java Object to Json
                        String jsonArray = gson.toJson(JSONROOT);
                        response.getWriter().print(jsonArray);
                    }
                }
            } catch (Exception ex) {
                JSONROOT.put("Result", "ERROR");
                JSONROOT.put("Message", ex.getMessage());
                String error = gson.toJson(JSONROOT);
                response.getWriter().print(error);
            }
        }
    }
}
