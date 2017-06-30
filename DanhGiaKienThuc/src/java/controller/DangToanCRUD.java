/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DangtoanDAO;
import dao.LopDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dangtoan;

/**
 *
 * @author NTL
 */
public class DangToanCRUD extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private DangtoanDAO dangtoanDAO;
    private LopDAO lopDAO;
    
    public DangToanCRUD() {
        dangtoanDAO = new DangtoanDAO();
        lopDAO = new LopDAO();
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
                } else if (action.equals("create") || action.equals("update")) {
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
                        d.setMadangtoan(madangtoan);
                    }
                    
                    if (request.getParameter("malop")!=null) {
                        int malop = Integer.parseInt(request.getParameter("malop"));
                        d.setMalop(malop);
                    }

                    if (request.getParameter("hocky")!=null) {
                        int hocky = Integer.parseInt(request.getParameter("hocky"));
                        d.setHocky(hocky);
                    }
                    
                    d.setMonhoc("toan");
                    
                    if (action.equals("create")) {
                        // Create new record
                        dangtoanDAO.InsertDangtoan(d);                     
                    } else if (action.equals("update")) {
                        // Update existing record
                        dangtoanDAO.updateDangtoan(d);
                    }                 
                    
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", d);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
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
                } else {
                    List allLop = lopDAO.convertToMap();
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Options", allLop);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray); 
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
