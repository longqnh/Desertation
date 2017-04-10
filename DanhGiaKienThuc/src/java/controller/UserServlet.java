/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import tools.MD5;

/**
 *
 * @author NTL
 */
public class UserServlet extends HttpServlet {
    UsersDao usersDao = new UsersDao();
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
        request.setCharacterEncoding("utf-8");        
        String command = request.getParameter("command");
        String url = "";
        Users users = new Users();
        HttpSession session = request.getSession();        
        switch (command) {
            case "insert":
                users.setUsername(request.getParameter("username"));
                users.setPassword(request.getParameter("password"));
                users.setName(request.getParameter("name"));
                users.setEmail(request.getParameter("email"));
                users.setLop(Integer.parseInt(request.getParameter("lop")));
                users.setRole("user");
                
                usersDao.InsertUser(users);

//                session.setAttribute("user", users);
                url = "/RegSuccess.jsp";
                break;
            case "login":
                users = usersDao.login(request.getParameter("username"), MD5.encryption(request.getParameter("password")));
                if (users != null) {
                    session.setAttribute("user", users);
                    Cookie cUserName = new Cookie("cookuser", users.getUsername());
                    Cookie cPassword = new Cookie("cookpass", users.getPassword());
                    cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
                    cPassword.setMaxAge(60 * 60 * 24 * 15);
                    response.addCookie(cUserName);
                    response.addCookie(cPassword);
                    url = "/index.jsp";
                } else {
                    request.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu");
                    url="/login.jsp";
                }
                break;
            case "logout":
//                users = null;
                session.setAttribute("user", null);
                Cookie cUserName = new Cookie("cookuser", null);
                Cookie cPassword = new Cookie("cookpass", null);
                cUserName.setMaxAge(0);
                cPassword.setMaxAge(0);
                response.addCookie(cUserName);
                response.addCookie(cPassword);
                url = "/index.jsp";
                break;
            case "update":
                users.setUsername(request.getParameter("username"));
                users.setName(request.getParameter("name"));
                users.setEmail(request.getParameter("email"));
                users.setLop(Integer.parseInt(request.getParameter("lop")));
                users.setRole("user");
                
                if (request.getParameter("password").equals("")) {
//                    users.setPassword(request.getParameter("currentpass"));
                    usersDao.updateUser(users);
                } else {
                    users.setPassword(request.getParameter("password"));
                    usersDao.changePassword(users);
                }
                                
                session.setAttribute("user", users);
                session.setAttribute("SuccMessage", "Cập nhật thành công");
                
//                response.sendRedirect("Member/User.jsp");
//                return;
                url = "/Member/User.jsp";                
                break;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
        
    }
}
