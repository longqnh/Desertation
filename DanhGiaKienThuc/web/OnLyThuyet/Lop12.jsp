<%-- 
    Document   : Lop12
    Created on : Jan 25, 2017, 11:16:44 PM
    Author     : NTL
--%>

<%@page import="dao.LythuyetDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">     
        <title>ÔN TẬP LÝ THUYẾT</title>
    </head>
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>

        <%
            Users users = null;
            boolean canEdit = false;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
                if (users.getRole().equals("admin")) {
                    canEdit = true;
                }
            }
            LythuyetDAO lythuyetDAO = new LythuyetDAO();
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
                        <li><a> Toán </a>
                            <ul class="submnu">
                                <li><a href="Lop12.jsp"> Lớp 12 </a></li>
                                <li><a href="Lop11.jsp"> Lớp 11 </a></li>
                                <li><a href="Lop10.jsp"> Lớp 10 </a></li>
                            </ul>
                        </li>

                        <li><a href="LyThuyetTracNghiem.jsp"> Lý thuyết trắc nghiệm</a></li>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <form id="main-right" action="${pageContext.request.contextPath}/EditLyThuyet" method="GET">
                <h2>Ôn tập lý thuyết toán 12</h2>
                <ol type="I">
                    <li>
                        <a href="" class="content"> Hàm số </a>
                        <%=lythuyetDAO.getContent("hamso")%>
                        <% if (canEdit==true) { %>
                            <button id="btnUpdateLT" type="submit" name="kienthuc" value="hamso">Cập nhật</button>                        
                        <% } %>
                    </li>
                    <li>
                        <a href="" class="content"> Lũy thừa - mũ - logarith </a>
                        <p>LT Lũy thừa - mũ - logarith</p>
                        <% if (canEdit==true) { %>
                            <button id="btnUpdateLT" type="submit" name="kienthuc" value="loga">Cập nhật</button>                                                
                        <% } %>                        
                    </li>
                    <li>
                        <a href="" class="content"> Tích phân </a>
                        <p>Tích phân</p>
                        <% if (canEdit==true) { %>
                            <button id="btnUpdateLT" type="submit" name="kienthuc" value="tichphan">Cập nhật</button>                                                
                        <% } %>                        
                    </li>
                    <li>
                        <a href="" class="content"> Số phức </a>
                        <p>Số phức</p>
                        <% if (canEdit==true) { %>
                            <button id="btnUpdateLT" type="submit" name="kienthuc" value="sophuc">Cập nhật</button>                                                
                        <% } %>                        
                    </li>
                    <li>
                        <a href="" class="content"> Hình học không gian </a>
                        <p>Hình học không gian</p>
                        <% if (canEdit==true) { %>
                            <button id="btnUpdateLT" type="submit" name="kienthuc" value="hhkg">Cập nhật</button>                                                
                        <% } %>                        
                    </li>
                    <li>
                        <a href="" class="content"> Giải tích không gian Oxyz </a>
                        <p>Giải tích không gian Oxyz</p>
                        <% if (canEdit==true) { %>
                            <button id="btnUpdateLT" type="submit" name="kienthuc" value="oxyz">Cập nhật</button>                                                
                        <% } %>                        
                    </li>
                </ol>             
            </form>
 
            <script src="${pageContext.request.contextPath}/js/DisplayContent.js" type="text/javascript"></script>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
 
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
