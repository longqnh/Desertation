/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DangtoanDAO;
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
import javax.servlet.http.HttpSession;
import model.Dangtoan;
import model.Users;

/**
 *
 * @author NTL
 */
public class DangToanCRUD extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    DangtoanDAO dangtoanDAO;
    
    public DangToanCRUD() {
        dangtoanDAO = new DangtoanDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Users users = null;
        if (session.getAttribute("user")!=null) {
            users = (Users) session.getAttribute("user");
        }
        
        String action = request.getParameter("action");
        String monhoc = request.getParameter("monhoc");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");
                
        if (action != null) {
            try {
                if (action.equals("list")) {
                    int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
                    int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
                    List<Dangtoan> List = new ArrayList<>();
                    int Count;
                    if (request.getParameter("lop").equals("")) {
                        List = dangtoanDAO.getAll(monhoc,startPageIndex,recordsPerPage);
                        Count = dangtoanDAO.countAllDangToan(monhoc);                     
                    } else {   
                        int lop = Integer.parseInt(request.getParameter("lop"));
                        List = dangtoanDAO.getAllDangtoan(monhoc,lop);
                        Count = dangtoanDAO.countAllDangToan(monhoc,lop);   
                    }
                    
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", List);
                    JSONROOT.put("TotalRecordCount", Count);
                    
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("delete")) {
                    Dangtoan d = new Dangtoan();
                    
                    if (request.getParameter("dangtoan")!=null) {
                        String dangtoan = request.getParameter("dangtoan");
                        d.setDangtoan(dangtoan);
                    }
                    
                    if (request.getParameter("dangtoanTV")!=null) {
                        String dangtoanTV = request.getParameter("dangtoanTV");
                        d.setDangtoanTV(dangtoanTV);
                    }

                    if (request.getParameter("madangtoan")!=null) {
                        String madangtoan = request.getParameter("madangtoan");
                        d.setDangtoan(madangtoan);
                    }
                    
                    if (request.getParameter("malop")!=null) {
                        int malop = Integer.parseInt(request.getParameter("malop"));
                        d.setMalop(malop);
                    }

                    if (request.getParameter("hocky")!=null) {
                        int hocky = Integer.parseInt(request.getParameter("hocky"));
                        d.setHocky(hocky);
                    }
                    
                    if (action.equals("create")) {
                        // Create new record
                        dangtoanDAO.InsertDangtoan(d);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/QLKD.jsp");
                        rd.forward(request, response);                        
                    } else if (action.equals("update")) {
                        // Update existing record
                        dangtoanDAO.updateDangtoan(d);
                        request.setAttribute("message", "Cập nhật câu hỏi thành công");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/QLKD.jsp");
                        rd.forward(request, response);
                    }                    
                } else if (action.equals("delete")) {
                    // Delete record
                    if (request.getParameter("dangtoan") != null) {
                        String dangtoan = request.getParameter("dangtoan");
                        dangtoanDAO.DeleteDangtoan(new Dangtoan(dangtoan));

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
