<%-- 
    Document   : Admin
    Created on : Feb 2, 2017, 12:39:58 PM
    Author     : NTL
--%>

<%@page import="model.Lop"%>
<%@page import="java.util.List"%>
<%@page import="dao.LopDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN LÝ TÀI KHOẢN</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
    </head>
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>
        
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            } else {
                response.sendRedirect("../login.jsp");
            }
        %>
        
        <div class="container">
            <div id="main-left">
                <div id="main-left-top">
                        <h2 style="text-align: center; text-transform: uppercase; margin-top: 5px; font-family:'Roboto';">Tìm kiếm</h2>
                        <div id="search">
                            <form>
                                <input type="text" placeholder="Search this site..." id="textsearch"/>
                                <input type="submit" id="search-button" value=""/>
                            </form>
                        </div>                            
                </div>

                <div id="main-left-bottom">
<!--                            <ul class="submnu">
                                <li><a href="#"> Sửa thông tin </a></li>
                                <li><a href="#"> Đổi mật khẩu </a></li>
                            </ul>-->
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/Member/User.jsp"> Thông tin tài khoản </a></li>
                        <li><a href="<%=request.getContextPath()%>/Member/QuanLyHocTap.jsp"> Quản lý học tập</a></li>
                        <li><a href="<%=request.getContextPath()%>/Member/DanhGia.jsp"> Đánh giá kiến thức</a></li>
                        <%
                            if (users.getRole().equals("admin")) { %>                     
                                <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp"> Quản lý kho đề</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDT.jsp">Quản lý các bài thi</a></li>                                
                        <%  } %>
                    </ul>                    
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>Thông tin tài khoản</h2>
                
                <form action="${pageContext.request.contextPath}/UserServlet" method="POST">
                    <div class="edit-info">
                        <div class="edit-field">
                            <label>Tên tài khoản: </label>
                            <input type="text" name="username" id="username" readonly value="<%=users.getUsername()%>">
                        </div>

                        <div class="edit-field">
                            <label>Họ tên: </label>
                            <input type="text" name="name" id="name" value="<%=users.getName()%>">
                        </div>

                        <div class="edit-field">
                            <label>Địa chỉ Email: </label>
                            <input type="text" name="email" id="email" value="<%=users.getEmail()%>">
                        </div>

                        <div class="edit-field">
                            <label>Lớp: </label>
                            <select name="lop" id="lop" required>
                                <option value="" disabled selected>Lớp</option>
                                <%  LopDAO lopDAO = new LopDAO(); 
                                    List<Lop> dsLop = lopDAO.GetAllLop(); 
                                    for (Lop lop: dsLop) { 
                                        if (lop.getMalop()==users.getLop()) { %>
                                            <option value="<%=lop.getMalop()%>" selected=""> <%=lop.getTenlop()%> </option>                                 
                                <%      } else { %>
                                            <option value="<%=lop.getMalop()%>"> <%=lop.getTenlop()%> </option>
                                <%      }
                                    }   
                                %>
                            </select>     
                        </div>
                        
                        <input type="password" name="currentpass" id="currentpass" value="<%=users.getPassword()%>" hidden="">
                        
                        <div class="edit-field">
                            <label>Mật khẩu mới (nếu đổi): </label>
                            <input type="password" name="password" id="password">
                        </div>

                        <div class="edit-field">
                            <label>Xác nhận mật khẩu mới: </label>
                            <input type="password" name="confirm_password" id="confirm_password">
                            <span id="confirm_password-result"></span>
                        </div>
                        <script type="text/javascript" src="../js/CheckPass.js"></script>
                        
                        <h4> ${sessionScope.SuccMessage} </h4> <br>
                        <%
                            session.setAttribute("SuccMessage", null);
                        %>
                        <input id="btnEdit" type="submit" value="Cập nhật">
                        <input type="hidden" value="update" name="command">      
                    </div>
                </form>
            </div>

            <script src="${pageContext.request.contextPath}/js/DisplayContent.js" type="text/javascript"></script>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
