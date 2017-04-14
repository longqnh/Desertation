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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js">
            MathJax.Hub.Config({
                extensions: ["tex2jax.js","TeX/AMSmath.js","TeX/AMSsymbols.js"],
                jax: ["input/TeX", "output/HTML-CSS"],
                tex2jax: {
                    inlineMath: [ ['$','$'], ["\\(","\\)"] ],
                    displayMath: [ ['$$','$$'], ["\\[","\\]"] ],
                    },
                    "HTML-CSS": { availableFonts: ["TeX"] }
            });
        </script>
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
            
            <form id="main-right" action="${pageContext.request.contextPath}/EditLyThuyet" method="GET" style="overflow: scroll;">
                <h2>Ôn tập lý thuyết toán 11</h2>
                <ol type="I">
                    <li>
                        <a href="" class="content"> Lượng giác </a>
                        <div>
                            <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            <% if (canEdit==true) { %>
                                <button id="btnUpdateLT" type="submit" name="kienthuc" value="luonggiac">Cập nhật</button>                                                
                            <% } %>  
                        </div>
                    </li>
                    <li>
                        <a href="" class="content"> Tổ hợp - Xác suất </a>
                        <div>
                            <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            <% if (canEdit==true) { %>
                                <button id="btnUpdateLT" type="submit" name="kienthuc" value="tohop">Cập nhật</button>                                                
                            <% } %>                              
                        </div>
                    </li>
                    <li>
                        <a href="" class="content"> Giới hạn - Đạo hàm </a>
                        <div>
                            <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            <% if (canEdit==true) { %>
                                <button id="btnUpdateLT" type="submit" name="kienthuc" value="gioihan">Cập nhật</button>                                                
                            <% } %>                              
                        </div>
                    </li>
                    <li>
                        <a href="" class="content"> Phép dời hình </a>
                        <div>
                            <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            <% if (canEdit==true) { %>
                                <button id="btnUpdateLT" type="submit" name="kienthuc" value="phepdoihinh">Cập nhật</button>                                                
                            <% } %>                              
                        </div>                        
                    </li>
                    <li>
                        <a href="" class="content"> Hình học không gian </a>
                        <div>
                            <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            <% if (canEdit==true) { %>
                                <button id="btnUpdateLT" type="submit" name="kienthuc" value="hhkg">Cập nhật</button>                                                
                            <% } %>                              
                        </div>                        
                    </li>
                </ol>
            </form>

            <script src="${pageContext.request.contextPath}/js/DisplayContent.js" type="text/javascript"></script>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
 
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
