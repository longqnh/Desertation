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
import java.io.PrintWriter;
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
public class getLop extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private LopDAO lopDAO;
    
    public getLop() {
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

        List<Lop> List = lopDAO.GetAllLop();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");

        try {
            // Return in the format required by jTable plugin
            JSONROOT.put("Result", "OK");
            JSONROOT.put("Options", List);

            // Convert Java Object to Json
            String jsonArray = gson.toJson(JSONROOT);

            response.getWriter().print(jsonArray);            
        } catch (Exception ex) {
            JSONROOT.put("Result", "ERROR");
            JSONROOT.put("Message", ex.getMessage());
            String error = gson.toJson(JSONROOT);
            response.getWriter().print(error);
        }
    }
}