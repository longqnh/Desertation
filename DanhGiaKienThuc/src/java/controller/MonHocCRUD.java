/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MonHocDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MonHoc;

/**
 *
 * @author NTL
 */
public class MonHocCRUD extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private MonHocDAO monHocDAO;
    
    public MonHocCRUD() {
        monHocDAO = new MonHocDAO();
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
        List<MonHoc> List = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");

        if (action != null) {
            try {
                if (action.equals("list")) {
                    List = monHocDAO.GetAllMonHoc();

                    // Get Total Record Count for Pagination
                    int userCount = monHocDAO.getMonHocCount();
                    
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", List);
                    JSONROOT.put("TotalRecordCount", userCount);
                    
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("update")) {
                    MonHoc monHoc = new MonHoc();
                    if (request.getParameter("monhocID") != null) {
                        String monhocID = request.getParameter("monhocID");
                        monHoc.setMonhocID(monhocID);
                    }

                    if (request.getParameter("tenmonhoc") != null) {
                        String tenmonhoc = request.getParameter("tenmonhoc");
                        monHoc.setTenmonhoc(tenmonhoc);
                    }
                                        
                    if (action.equals("create")) {
                        // Create new record
                        monHocDAO.InsertMonHoc(monHoc);
                    } else if (action.equals("update")) {
                        // Update existing record
                        monHocDAO.updateMonHoc(monHoc);
                    }

                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", monHoc);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("delete")) {
                    // Delete record
                    if (request.getParameter("monhocID") != null) {
                        String monhocID = request.getParameter("monhocID");
                        monHocDAO.DeleteMonHoc(monhocID);

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
