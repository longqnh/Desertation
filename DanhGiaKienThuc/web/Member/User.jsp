<%-- 
    Document   : Admin
    Created on : Feb 2, 2017, 12:39:58 PM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN LÝ TÀI KHOẢN</title>
        <link rel="stylesheet" href="../css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/MemberStyle.css" type="text/css">
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
        
        <div id="clr"></div>
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
                    <ul>
                        <li><a href="User.jsp"> Thông tin tài khoản </a>
<!--                            <ul class="submnu">
                                <li><a href="#"> Sửa thông tin </a></li>
                                <li><a href="#"> Đổi mật khẩu </a></li>
                            </ul>-->
                        </li>

                        <li><a href="QuanLyHocTap.jsp"> Quản lý học tập</a></li>
                    </ul>
                </div>
                
                <script src="../js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>Thông tin tài khoản</h2>
                
                <form action="../UserUpdate" method="POST">
                    <div class="edit-info">
                        <div class="edit-field">
                            <label>Tên tài khoản: </label>
                            <input type="text" readonly value="<%=users.getUsername()%>">
                        </div>

                        <div class="edit-field">
                            <label>Họ tên: </label>
                            <input type="text" name="name" id="name" value="<%=users.getName()%>">
                        </div>

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
                        
                        <input id="btnEdit" type="submit" value="Cập nhật">
                    </div>
                </form>
            </div>

            <script src="../js/DisplayContent.js" type="text/javascript"></script>
        </div>
        
        <script src="../js/autoscroll.js" type="text/javascript"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>