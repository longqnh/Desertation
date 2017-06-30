/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.LopDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lop;

/**
 *
 * @author NTL
 */
public class LopCRUD extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private LopDAO lopDAO;
    
    public LopCRUD() {
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
        
        List<Lop> List = new ArrayList<>();
        String action = request.getParameter("action");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");
            
        if (action != null) {
            try {
                if (action.equals("list")) {
                    int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
                    int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
                    
                    List = lopDAO.GetAllLop();
                    // Get Total Record Count for Pagination
                    int Count = lopDAO.getLopCount();
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", List);
                    JSONROOT.put("TotalRecordCount", Count);
                    
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("update")) {
                    
                    int malop = Integer.parseInt(request.getParameter("malop"));
                    String tenlop = request.getParameter("tenlop");
                    Lop lop = new Lop(malop, tenlop);
                    
                    if (action.equals("create")) {
                        // Create new record
                        lopDAO.InsertLop(lop);                        
                    } else if (action.equals("update")) {
                        // Update existing record
                        lopDAO.updateLop(lop);
                    }                    

                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", lop);
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("delete")) {
                    // Delete record
                    if (request.getParameter("malop") != null) {
                        int malop = Integer.parseInt(request.getParameter("malop"));
                        lopDAO.DeleteLop(malop);

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
