<%-- 
    Document   : DanhGia
    Created on : Mar 28, 2017, 2:13:36 PM
    Author     : NTL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Lop"%>
<%@page import="dao.LopDAO"%>
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
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            // Load the Visualization API and the piechart package.
//            google.load('visualization', '1', {'packages': ['columnchart']});
            google.charts.load('current', {packages: ['corechart', 'line']});
        </script>
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
                <h2>Đánh giá kiến thức</h2>
                
                <form id="frmDanhgia" action="${pageContext.request.contextPath}/thongke" method="GET">
                    <div class="edit-field">
                        <label>Lớp: </label>
                        <select name="lop" id="lop" required>
                            <option value="" disabled selected>Lớp</option>
                            <%
                                LopDAO lopDAO = new LopDAO(); 
                                List<Lop> dsLop = lopDAO.GetAllLop(); 
                                for (Lop lop: dsLop) { %>
                                    <option value="<%=lop.getMalop()%>"> <%=lop.getTenlop()%> </option>                                  
                            <%  } %>
                        </select>
                    </div>

                    <div class="edit-field">
                        <label>Nội dung: </label>
                        <select id="kienthuc" name="kienthuc"></select>                        
                    </div>

                    <input id="btnEdit" type="submit" value="Chọn">        
                </form>
     
                <script type="text/javascript">
                    $('#lop').change (
                        function() {
                            $.ajax({
                                type: "POST",
                                url: "${pageContext.request.contextPath}/DangtoanServlet",
                                data: {lop: $(this).val() },
                                success: function(data){
                                    $("#kienthuc").html(data);
                                }
                            });
                        }
                    );
                
//                    $('#btnEdit').click (
//                        function() {
//                            alert($('#kienthuc').find(":selected").val());
//                            $.ajax({
//                                type: "GET",
//                                url: "{pageContext.request.contextPath}/thongke",
//                                dataType: "JSON",
//                                data: { kienthuc: $('#kienthuc').find(":selected").val() },
//                                success: function(result){       
//                                    console.log(result);
//                                    console.log(result.noidung);
//                                    
////                                    google.setOnLoadCallback(drawChart);  
//                                    
//                                    $("#loinhanxet").html(result.Message);
//                                }
//                            });          
//                    });
                    
                    google.setOnLoadCallback(drawChart);  
                    function drawChart() {
                        // Create the data table.    
                        var data = new google.visualization.DataTable();
                        data.addColumn('string', 'Mã đề');
                        data.addColumn('number', 'Tỉ lệ');

                        data.addRows([
                            <c:forEach var="data" items="${thongkedata}">['${data.made}',${data.tyle}],</c:forEach>
                        ]);
                        // Set chart options
                        var options = {
                            'title': '${requestScope.noidung}',
                            hAxis: {
                              title: 'Mã đề'
                            },
                            vAxis: {
                              title: 'Tỉ lệ'
                            },
                            height: 500
                        };
                        // Instantiate and draw our chart, passing in some options.
                        var chart = new google.visualization.LineChart(document.getElementById('Chart'));
                        chart.draw(data, options);
                    }
                </script>
              
                <div id="Chart"></div>
                
                <span id="loinhanxet">${requestScope.Message}</span><br>                                    
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>