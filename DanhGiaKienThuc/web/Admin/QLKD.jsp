<%-- 
    Document   : QLKD
    Created on : Feb 20, 2017, 11:15:13 PM
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
        <title>QUẢN TRỊ KHO ĐỀ THI</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
                <h2>QUẢN TRỊ KHO ĐỀ THI</h2>
                
                <form class="search-box" method="GET">
                    <div class="search-field">
                        <label>Dạng câu hỏi:</label>
                        <select name="noidung" onchange="this.form.submit()">
                            <option value="" disabled="" selected=""></option>
                            <option value="hamso">Hàm số</option>
                            <option value="loga">Lũy Thừa - Mũ - Logarith</option>
                            <option value="">Nguyên hàm - Tích phân</option>
                            <option value="">Số phức</option>
                            <option value="">HHKG</option>
                            <option value="">OXYZ</option>
                        </select>
                    </div>
                    <div class="search-field">
                        <label>Tìm kiếm theo:</label>
                        <select>
                            <option value="">Mã CH</option>
                            <option value="">Nội dung</option>
                            <option value="">Độ khó</option>
                        </select>
                    </div>

                    <div class="search-field">
                        <label>Nội dung tìm kiếm:</label>
                        <input type="text" id="search-content" name="search-content" value="">
                    </div>
                </form>

                <div>
                <%
                    try {
                            Connection connect=DBConnect.getConnecttion();
                            String nd = request.getParameter("noidung");
                            String sql = "";
                            if (nd != null) {
                                sql = "select * from table_" + nd;
                            }
                            PreparedStatement ps = connect.prepareCall(sql);
                            ResultSet rs = ps.executeQuery(sql); %>
                    <div class="quanlyde">
                        <table>
                            <tr>
                                    <th>ID</th>
                                    <th>Nội dung</th>
                                    <th>Đáp án A</th>
                                    <th>Đáp án B</th>
                                    <th>Đáp án C</th>
                                    <th>Đáp án D</th>
                                    <th>Đáp án</th>
                                    <th>Dạng bt</th>
                                    <th>Level</th>
                                    <th>Hình</th>
                            </tr>
                            <%
                                while (rs != null && rs.next()) {
                            %>
                                    <tr>
                                        <td><%=rs.getString("id")%></td>
                                        <td><%=rs.getString("noidung")%></td>
                                        <td><%=rs.getString("dapanA")%></td>
                                        <td><%=rs.getString("dapanB")%></td>
                                        <td><%=rs.getString("dapanC")%></td>
                                        <td><%=rs.getString("dapanD")%></td>
                                        <td><%=rs.getString("answer")%></td>
                                        <td><%=rs.getString("dangbt")%></td>
                                        <td><%=rs.getString("level")%></td>
                                        <td>
                                            <%
                                                if (rs.getInt("hasImage")==1) { %>
                                                    <a href="../images/<%=rs.getString("dangtoan")%>/<%=rs.getString("id")%>.JPG">Link</a>
                                            <%  } %>
                                        </td>
                                    </tr>
                            <%
                                }
                            %>
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
