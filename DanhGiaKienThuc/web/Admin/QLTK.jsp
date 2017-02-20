<%-- 
    Document   : QLTK
    Created on : Feb 20, 2017, 11:15:06 PM
    Author     : NTL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="connect.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN TRỊ CÁC TÀI KHOẢN</title>
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
                        <li><a href="../Member/User.jsp"> Thông tin tài khoản </a></li>
                        <%
                            if (users.getUsername().equals("admin")) { %>
                                <li><a href="QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="QLKD.jsp"> Quản lý kho đề</a></li>
                        <%  }
                            else {
                        %>
                            <li><a href="QuanLyHocTap.jsp"> Quản lý học tập</a></li>
                        <% } %>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>QUẢN TRỊ CÁC TÀI KHOẢN</h2>
                
                <div class="row">
                <%
                    try {
                            Connection connect=DBConnect.getConnecttion();
                            String sql = "select * from table_user";
                            PreparedStatement ps = connect.prepareCall(sql);
                            ResultSet rs = ps.executeQuery(sql); %>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                        <th>Username</th>
                                        <th>Họ và tên</th>
                                        <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    while (rs != null && rs.next()) {
                                %>
                                        <tr>
                                            <td><%=rs.getString("username")%></td>
                                            <td><%=rs.getString("name")%></td>
                                            <td><%=rs.getString("email")%></td>
                                        </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                <%
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
                </div>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>

    </body>
</html>
