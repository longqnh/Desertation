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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DangBaiTap;

/**
 *
 * @author NTL
 */
public class DangBTCRUD extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private DangBaiTapDAO dangBaiTapDAO;
    private DangtoanDAO dangtoanDAO;

    public DangBTCRUD() {
        dangBaiTapDAO = new DangBaiTapDAO();
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

        String action = request.getParameter("action");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");
        String dangtoan = request.getParameter("kienthuc");
            
        if (action != null) {
            try {
                if (action.equals("list")) {
                    int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
                    int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
                    
                    List<DangBaiTap> List = dangBaiTapDAO.GetAllDangBaiTap(dangtoan,startPageIndex,recordsPerPage);
                    // Get Total Record Count for Pagination
                    int Count = dangBaiTapDAO.getDangbtCount(dangtoan);
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", List);
                    JSONROOT.put("TotalRecordCount", Count);
                    
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                }  else if (action.equals("create") || action.equals("update")) {
                    DangBaiTap baiTap = new DangBaiTap();
                    
                    if (request.getParameter("dangbt")!=null) {
                        String dangbt = request.getParameter("dangbt");
                        baiTap.setDangbt(dangbt);
                    }
                    
                    if (request.getParameter("dangbtTV")!=null) {
                        String dangbtTV = request.getParameter("dangbtTV");
                        baiTap.setDangbtTV(dangbtTV);
                    }

                    if (request.getParameter("dangtoan")!=null) {
                        String d = request.getParameter("dangtoan");
                        baiTap.setDangtoan(d);
                    }                    
                    
                    if (action.equals("create")) {
                        // Create new record
                        dangBaiTapDAO.InsertDangBT(baiTap);                    
                    } else if (action.equals("update")) {
                        // Update existing record
                        dangBaiTapDAO.updateDangBT(baiTap);
                    }                    
                    
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", baiTap);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("delete")) {
                    // Delete record
                    if (request.getParameter("dangbt")!=null) {
                        String dangbt = request.getParameter("dangbt");
                        dangBaiTapDAO.DeleteDangBT(new DangBaiTap(dangbt));

                        // Return in the format required by jTable plugin
                        JSONROOT.put("Result", "OK");

                        // Convert Java Object to Json
                        String jsonArray = gson.toJson(JSONROOT);
                        response.getWriter().print(jsonArray);
                    }
                } else {
                    List alldangtoan = dangtoanDAO.getAllDangToan("toan");
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Options", alldangtoan);

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
