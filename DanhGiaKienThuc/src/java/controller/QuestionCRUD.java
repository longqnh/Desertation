/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DangBaiTapDAO;
import dao.DangtoanDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
    private DangtoanDAO ddao;

    public QuestionCRUD() {
        qdao = new QuestionDAO();
        ddao = new DangtoanDAO();
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
        List<Question> listCH = new ArrayList<Question>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");
        String kienthuc = request.getParameter("kienthuc");
        String searchID = request.getParameter("name");
        String lop = request.getParameter("lop");
                
        if (action != null) {
            try {
                if (action.equals("list")) {
                    int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
                    int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
                    
                    listCH = qdao.getAllQuestions(kienthuc,lop,searchID,startPageIndex, recordsPerPage);
                    
                    // Get Total Record Count for Pagination
                    int questionCount = qdao.getQuestionCount(kienthuc,lop);
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", listCH);
                    JSONROOT.put("TotalRecordCount", questionCount);
                    
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("update")) {                    
                    Question q = new Question();
                                                           
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
                    
                    if (request.getParameter("dapan") != null) {
                        String dapan = request.getParameter("dapan");
                        q.setDapan(dapan);
                    }
                    
                    if (request.getParameter("monhoc") != null) {
                        String monhoc = request.getParameter("monhoc");
                        q.setMonhoc(monhoc);
                    }
                    
                    q.setDangtoan(kienthuc);

                    if (request.getParameter("dangbt") != null) {
                        String dangbt = request.getParameter("dangbt");
                        q.setDangbt(dangbt);
                    }
                                        
                    if (request.getParameter("dokho") != null) {
                        String dokho = request.getParameter("dokho");
                        q.setDokho(Integer.parseInt(dokho));
                    }

                    if (request.getParameter("dophancach") != null) {
                        String dophancach = request.getParameter("dophancach");
                        q.setDophancach(Integer.parseInt(dophancach));
                    }
                    
                    int malop = ddao.GetLop(kienthuc);
                    q.setMalop(malop);
                    
                    if (request.getParameter("hinh") != null) {
                        String hinh = request.getParameter("hinh");
                        q.setHinh(Integer.parseInt(hinh));
                    }
                    
                    q.setDao(1);
                    
                    if (action.equals("create")) {
                        // Create new record
                        String id = qdao.generateId(kienthuc);
                        q.setId(id);
                        qdao.InsertQuestion(q);
                        request.setAttribute("message", "Thêm câu hỏi thành công");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/QLKD.jsp");
                        rd.forward(request, response);                        
                    } else if (action.equals("update")) {
                        // Update existing record
                        q.setId(request.getParameter("id"));
                        qdao.updateQuestion(q);
                        request.setAttribute("message", "Cập nhật câu hỏi thành công");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/QLKD.jsp");
                        rd.forward(request, response);
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
                        qdao.DeleteQuestion(new Question(id));

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
