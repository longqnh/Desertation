<%-- 
    Document   : header
    Created on : Jan 3, 2017, 7:55:08 PM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/HeaderStyle.css" type="text/css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>        
        <!-- jQuery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\x3C/script>')</script>        
    </head>
    <body>
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
        %>
        
        <div id="sticky-anchor"></div>
        <div id="top">
            <div id="top-right">
                    <% if (users!=null) { %>
                        <ul>
                            <li id="user-info"><a href="#" style="text-transform: none; text-align: center;"><%=users.getUsername()%></a>
                                <ul class="sub-top-right">
                                    <li><a href="User/Admin.jsp">Quản lý tài khoản</a></li>
                                    <form action="UserServlet"method="POST">
                                        <input id="btnlogout" type="submit" value="Thoát">
                                        <input type="hidden" value="logout" name="command">
                                    </form>
                                </ul>
                            </li>
                        </ul>
                    <% } else { %>
                        <ul>
                            <li><a href="login.jsp">Đăng nhập</a></li>
                            <li><a href="register.jsp">Đăng ký</a></li>
                        </ul>
                    <% } %>
            </div>

            <div id="top-left">
                <a href="index.jsp">website đánh giá kiến thức toán thpt</a>
            </div>
        </div>

        <script type="text/javascript">
            function sticky_relocate() {
                var window_top = $(window).scrollTop();
                var div_top = $('#sticky-anchor').offset().top;
                if (window_top > div_top) {
                    $('#top').addClass('stick');
                    $('#sticky-anchor').height($('#top').outerHeight());
                } else {
                    $('#top').removeClass('stick');
                    $('#sticky-anchor').height(0);
                }
            }

            $(function() {
                $(window).scroll(sticky_relocate);
                sticky_relocate();
            });
        </script>
        
        <!-- Header -->
        <div id="banner">
            <!-- add image -->
        </div>

        <!-- Menu -->
        <div id="menu">
            <ul>
                <li><a href="index.jsp" <!--class="selected"-->Trang chủ</a></li>
                <li><a href="#">Làm đề thi</a>
                    <ul class="submenu">
                        <li><a href="Thi/MockTest.jsp">Thi thử</a></li>
                        <li><a href="Thi/Practice.jsp">Luyện tập</a></li>
                    </ul>
                </li>
                <li><a href="LyThuyet.jsp">Lý thuyết</a>
                </li>
                <li><a href="tutorial.jsp">Hướng dẫn</a></li>
                <li><a href="information.jsp">Giới thiệu</a></li>
                <li><a href="contact.jsp">Liên hệ - góp ý</a></li>
            </ul>
            
            <script type="text/javascript">
                function Click() {
                    $('#select').addClass('selected');
                }
            </script>
        </div>
    </body>
</html>
