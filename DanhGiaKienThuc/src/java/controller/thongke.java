/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DangtoanDAO;
import dao.DethiDAO;
import dao.QuanLyDeThiDAO;
import dao.ThongkeDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Thongke;
import model.Users;
import tools.DanhGiaKienThuc;

/**
 *
 * @author NTL
 */
public class thongke extends HttpServlet {
    ThongkeDAO thongkeDAO = new ThongkeDAO();
    DanhGiaKienThuc danhgia = new DanhGiaKienThuc();
    DethiDAO dethiDAO = new DethiDAO();
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
            kienthuc = "hamso12";
        }
        
        // Thong ke + ve bieu do
        List<Thongke> list = thongkeDAO.thongkekienthuc(users.getUsername(), kienthuc);
        String noidung = DangtoanDAO.GetNoidungTV(kienthuc);
        
        request.setAttribute("noidung", noidung);
        request.setAttribute("thongkedata", list);
        
        // Danh gia + goi y
        int solanthi = QuanLyDeThiDAO.GetSolanthi(users.getUsername(), noidung);
        if (solanthi > 0) {
            double nangluc = danhgia.DanhGiaNangLuc(users.getUsername(), kienthuc);
            String nhanxet = danhgia.DanhGiaTongQuat(users.getUsername(), noidung, nangluc, solanthi);            
            request.setAttribute("Message",nhanxet);
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Member/DanhGia.jsp");
        rd.forward(request, response);
    }
}
