<%-- 
    Document   : QLCH
    Created on : Jun 29, 2017, 10:12:24 PM
    Author     : NTL
--%>

<%@page import="model.Question"%>
<%@page import="dao.QuestionDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SỐ LƯỢT LÀM CÂU HỎI</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>   
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.rawgit.com/mathjax/MathJax/2.7.1/MathJax.js">
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
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>        
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>  
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
        </script>
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
                                    <%
                                        String page_redirect= request.getContextPath() + "/Member/User.jsp";
                                    %>
                                    <li><a href="<%=page_redirect%>">Quản lý tài khoản</a></li>
                                    <form action="<%=request.getContextPath()%>/UserServlet" method="POST">
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
                <a href="<%=request.getContextPath()%>/index.jsp">website đánh giá kiến thức toán thpt</a>
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
                <li><a href="<%=request.getContextPath()%>/index.jsp" <!--class="selected"-->Trang chủ</a></li>
                <li><a href="#">Làm đề thi</a>
                    <ul class="submenu">
                        <li><a href="<%=request.getContextPath()%>/Thi/MockTest.jsp">Thi thử</a></li>
                        <li><a href="<%=request.getContextPath()%>/Thi/Practice.jsp">Luyện tập</a></li>
                    </ul>
                </li>
                <li><a> Lý thuyết</a>
                    <ul class="submenu">
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop12.jsp"> Toán 12 </a></li>                        
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop11.jsp">Toán 11</a></li>
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop10.jsp">Toán 10</a></li>
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/LyThuyetTracNghiem.jsp">Lý Thuyết Trắc Nghiệm</a></li>
                    </ul>                            
                </li>
                <li><a href="<%=request.getContextPath()%>/tutorial.jsp">Hướng dẫn</a></li>
                <li><a href="<%=request.getContextPath()%>/information.jsp">Giới thiệu</a></li>
                <li><a href="<%=request.getContextPath()%>/contact.jsp">Liên hệ - góp ý</a></li>
            </ul>
            
            <script type="text/javascript">
                function Click() {
                    $('#select').addClass('selected');
                }
            </script>
        </div>   
            
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
                        <li><a href="<%=request.getContextPath()%>/Member/User.jsp"> Thông tin tài khoản </a></li>
                        <li><a href="<%=request.getContextPath()%>/Member/QuanLyHocTap.jsp"> Quản lý học tập</a></li>   
                        <%
                            if (users.getRole().equals("admin")) { %>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLMH.jsp"> Quản lý các môn học</a></li>                                                                
                                <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp"> Quản lý kho đề</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDT.jsp">Quản lý các bài thi</a></li>                                
                                <li><a href="<%=request.getContextPath()%>/Admin/QLLop.jsp">Quản lý lớp</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDToan.jsp">Quản lý dạng toán</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDBT.jsp">Quản lý dạng bài tập</a></li>  
                                <li><a href="<%=request.getContextPath()%>/Admin/QLCH.jsp">Xem số lượt làm câu hỏi</a></li>                                
                                <li><a href="<%=request.getContextPath()%>/Admin/TestLatex.jsp">Kiểm tra Latex</a></li>                                                                
                        <%  } %>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
                        
            <div id="main-right">
                <h2>XEM SỐ LƯỢT LÀM</h2>     
                                                                        
                <form action="${pageContext.request.contextPath}/XemSoLuot" method="GET">
                    <div class="edit-field">
                        <label>ID: </label>
                        <input type="text" name="id" id="id" required="">
                        <input type="submit" id="btnEdit" value="Thống kê">
                    </div>
                </form>
                
                <div style="width: 98%; margin-top: 20px;">
                    <%
                        String id = request.getParameter("id");
                        QuestionDAO qdao = new QuestionDAO();
                        if (id != null) {
                            Question q = qdao.getQuestionById(id); %>
                    
                            <p><b>(<%=q.getId()%>):</b> <%=q.getNoidung()%></p>
                            <%
                                if (q.getHinh()==1) { %>
                                    <img src="${pageContext.request.contextPath}/images/NHCH/<%=q.getId()%>.JPG">
                            <% } %>
                            <p><b>A. </b><%=q.getDapanA()%></p>
                            <p><b>B. </b><%=q.getDapanB()%></p>
                            <p><b>C. </b><%=q.getDapanC()%></p>
                            <p><b>D. </b><%=q.getDapanD()%></p>
                    <%  }  %>
                </div>

                <script type="text/javascript">
                    google.charts.setOnLoadCallback(drawChart);

                    function drawChart() {

                        var data = google.visualization.arrayToDataTable([
                            ['Tổng','Đúng'],
                            ['Tổng số thí sinh làm', ${requestScope.tong}],
                            ['Số thí sinh làm đúng', ${requestScope.dung}]
                        ]);

                        var options = {
                            title: 'Số lần làm'
                        };

                        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                        chart.draw(data, options);
                    }
                </script>
                
                <div id="piechart" style="width: 800px; height: 500px;"></div>
            </div>
        </div>
            
        <script type="text/javascript" src="../js/autoscroll.js"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>