/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DanhgiaDAO;
import dao.ThongkeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Thongke;
import model.Users;

/**
 *
 * @author NTL
 */
public class thongke extends HttpServlet {
    ThongkeDAO thongkeDAO = new ThongkeDAO();
    DanhgiaDAO danhgiaDAO = new DanhgiaDAO();
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
        HttpSession session = request.getSession();

        Users users = null;
        if (session.getAttribute("user")!=null) {
            users = (Users) session.getAttribute("user");
        }    
        
        String kienthuc;
        if (request.getParameter("kienthuc") != null) {
            kienthuc = request.getParameter("kienthuc");                                   
        } else {
            kienthuc = "hamso";
        }
        
        // Thong ke + ve bieu do
        Thongke tk = thongkeDAO.thongkekienthuc(users.getUsername(), kienthuc);
        int socaudung = tk.getSocaudung();
        int socausai = tk.getSocau() - tk.getSocaudung();
        String noidung = tk.getDangtoan();
        
        request.setAttribute("noidung", noidung);
        request.setAttribute("socaudung", socaudung);
        request.setAttribute("socausai", socausai);
        
        // Danh gia + goi y
        int solanthi = danhgiaDAO.GetSolanthi(users.getUsername(), noidung);
        if (solanthi > 0) {
            // tinh nang luc lan thi gan nhat co noi dung do
            // uocluong khoang
            // ket luan => setAttribute("Message","ket luan")
            // cap nhat ky vong
            // cap nhat phuong sai
        }
                
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Member/DanhGia.jsp");
        rd.forward(request, response);
    }
}
