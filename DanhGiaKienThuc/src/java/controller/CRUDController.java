/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Users;
import tools.MD5;

/**
 *
 * @author NTL
 */
public class CRUDController extends HttpServlet {
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private UsersDao dao;

    public CRUDController() {
        dao = new UsersDao();
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
        doPost(request, response);
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
        List<Users> List = new ArrayList<Users>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setContentType("application/json");

        if (action != null) {
            try {
                if (action.equals("list")) {
                    int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
                    int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));   
                    String search_username = request.getParameter("name");
                    
                    List = dao.getAllUsers(search_username, startPageIndex, recordsPerPage);

                    // Get Total Record Count for Pagination
                    int userCount = dao.getUserCount();
                    
                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Records", List);
                    JSONROOT.put("TotalRecordCount", userCount);
                    
                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);

                    response.getWriter().print(jsonArray);
                } else if (action.equals("create") || action.equals("update")) {
                    Users user = new Users();
                    if (request.getParameter("username") != null) {
                        String username = request.getParameter("username");
                        user.setUsername(username);
                    }

                    if (request.getParameter("password") != null) {
                        String password = request.getParameter("password");
                        user.setPassword(password);
                    }

                    if (request.getParameter("name") != null) {
                        String name = request.getParameter("name");
                        user.setName(name);
                    }
                                        
                    if (request.getParameter("email") != null) {
                        String email = request.getParameter("email");
                        user.setEmail(email);
                    }

                    if (request.getParameter("lop") != null) {
                        int lop = Integer.parseInt(request.getParameter("lop"));
                        user.setLop(lop);
                    }
                    
                    if (request.getParameter("role") != null) {
                        String role = request.getParameter("role");
                        user.setRole(role);
                    }
                                        
                    if (action.equals("create")) {
                        // Create new record
                        dao.InsertUser(user);
                    } else if (action.equals("update")) {
                        // Update existing record
                        dao.updateUser(user);
                    }

                    // Return in the format required by jTable plugin
                    JSONROOT.put("Result", "OK");
                    JSONROOT.put("Record", user);

                    // Convert Java Object to Json
                    String jsonArray = gson.toJson(JSONROOT);
                    response.getWriter().print(jsonArray);
                } else if (action.equals("delete")) {
                    // Delete record
                    if (request.getParameter("username") != null) {
                        String username = request.getParameter("username");
                        dao.deleteUser(username);

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
