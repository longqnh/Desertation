<%-- 
    Document   : DanhGia
    Created on : Mar 28, 2017, 2:13:36 PM
    Author     : NTL
--%>

<%@page import="model.Thongke"%>
<%@page import="java.util.List"%>
<%@page import="dao.ThongkeDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ĐÁNH GIÁ KIẾN THỨC</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css"> 
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
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
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/Member/User.jsp"> Thông tin tài khoản </a></li>
                        <li><a href="<%=request.getContextPath()%>/Member/QuanLyHocTap.jsp"> Quản lý học tập</a></li>
                        <li><a href="<%=request.getContextPath()%>/thongke"> Đánh giá kiến thức</a></li>
                        <%
                            if (users.getUsername().equals("admin")) { %>                     
                                <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp"> Quản lý kho đề</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDT.jsp">Quản lý các bài thi</a></li>                                
                        <%  } %>
                    </ul>                    
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>Đánh giá kiến thức</h2>
                
<!--                <script type="text/javascript">
                    $(document).ready(function() {
                        $('.btnXemDA').click(function () {
                           var noidung = $(this).attr('value');
//                           alert(noidung);
                           $.ajax({
                                type: 'GET',
                                data: {noidung: noidung},
                                url: '{pageContext.request.contextPath}/thongke',
                                success: function (result) {
                                    $('#loinhanxet').html(result);
                                }
                           });
                        });
                    });
                </script>-->

                <script type="text/javascript">
                    // Load the Visualization API and the piechart package.
                    google.load('visualization', '1', {'packages': ['columnchart']});

                    // Set a callback to run when the Google Visualization API is loaded.
                    google.setOnLoadCallback(drawChart);

                    // Callback that creates and populates a data table,
                    // instantiates the pie chart, passes in the data and
                    // draws it.
                    function drawChart() {

                        // Create the data table.    
                        var data = google.visualization.arrayToDataTable([
                            ['', ''],
                            ['Số câu sai', ${requestScope.socausai}],
                            ['Số câu đúng', ${requestScope.socaudung}]
                        ]);
                        // Set chart options
                        var options = {
                            'title': '${requestScope.noidung}',
                            is3D: true,
                            pieSliceText: 'label',
                            tooltip: {showColorCode: true},
                            'width': 700,
                            'height': 400,
                            colors: ['red', 'blue']
                        };

                        // Instantiate and draw our chart, passing in some options.
                        var chart = new google.visualization.PieChart(document.getElementById('Chart'));
                        chart.draw(data, options);
                    }
                </script>
        
                <div id="Chart"></div>
                
                <span id="loinhanxet">${requestScope.Message}</span><br>
                
                <form class="_btn" action="${pageContext.request.contextPath}/thongke" method="GET">
                    <button type="submit" class="btnXemDA" name="kienthuc" value="hamso">Hàm số</button>
                    <button type="submit" class="btnXemDA" name="kienthuc" value="loga">Logarith</button>
                    <button type="submit" class="btnXemDA" name="kienthuc" value="tichphan">Tích phân</button>
                    <button type="submit" class="btnXemDA" name="kienthuc" value="sophuc">Số phức</button>
                    <button type="submit" class="btnXemDA" name="kienthuc" value="hhkg">HHKG</button>
                    <button type="submit" class="btnXemDA" name="kienthuc" value="oxyz">Oxyz</button>
                </form>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>