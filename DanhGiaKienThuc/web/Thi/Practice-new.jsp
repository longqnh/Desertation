<%-- 
    Document   : Practice-new
    Created on : Jul 10, 2017, 8:29:06 PM
    Author     : NTL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.MonHoc"%>
<%@page import="dao.MonHocDAO"%>
<%@page import="dao.DokhoDAO"%>
<%@page import="model.Dokho"%>
<%@page import="model.Lop"%>
<%@page import="java.util.List"%>
<%@page import="dao.LopDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LUYỆN TẬP</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>    
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/multi-select.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>   
    </head>
    
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>

        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
            
            if (users==null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
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
                        <li><a href="<%=request.getContextPath()%>/index.jsp"> Trang chủ</a></li>                       
                        <li><a> Làm đề thi </a>
                            <ul class="submnu">
                                <li><a href="<%=request.getContextPath()%>/Thi/MockTest.jsp"> Thi thử </a></li>
                                <li><a href="<%=request.getContextPath()%>/Thi/Practice.jsp"> Luyện tập </a></li>
                            </ul>
                        </li>
                        <li><a> Lý thuyết</a>
                            <ul class="submnu">
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop12.jsp"> Toán 12 </a></li>
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop11.jsp"> Toán 11 </a></li>
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop10.jsp"> Toán 10 </a></li>
                            </ul>                            
                        </li>
                        <li><a href="<%=request.getContextPath()%>/tutorial.jsp"> Hướng dẫn</a></li>
                        <li><a href="<%=request.getContextPath()%>/information.jsp"> Giới thiệu</a></li>
                        <li><a href="<%=request.getContextPath()%>/contact.jsp"> Liên hệ - Góp ý</a></li>
                    </ul>
                </div>
                
                <script src="<%=request.getContextPath()%>/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>

            <div id="main-right">
                <h2>Thông tin đề thi</h2>
                
                <h3>Nhập số câu tương ứng vào các ô trống</h3>
                <b><u>Lưu ý</u>:</b> Số câu ở từng nội dung phải bằng tổng số câu thì mới có thể tạo đề
                
                <form style="margin-top: 20px;" class="createExam" id="createExam-socau-noidung" name="createExam" action="${pageContext.request.contextPath}/LamDeThi" method="POST">
                    <div class="search-field">
                        <label>Tổng số câu: </label>
                        <input type="text" id="socau" name="socau" value="${requestScope.socau}" readonly="">
                    </div>
                    
                    <div>
                        <c:forEach var="noidung" items="${cacND}">
                            <div class="search-field">
                                <label>${noidung.dangtoanTV}:</label>
                                <input type="text" class="number" id="${noidung.dangtoan}" name="${noidung.dangtoan}" required="">
                            </div>
                        </c:forEach>
                    </div>
                    
                    <input type="text" name="lop" value="${requestScope.lop}" hidden="">
                    <input type="text" name="monhoc" value="${requestScope.monhoc}" hidden="">
                    <input type="text" name="dokho" value="${requestScope.dokho}" hidden="">
                    
                    <h4 id="thongbao"></h4><br>
                    <input id="btnTaoDe" type="button" value="Tạo đề" onclick="DoExam()"> <!-- css in MemberStyle -->
                </form>   
                                                               
                <script type="text/javascript">                          
                    $('.number').blur(function () {
                        var sum = 0;
                        $('.number').each(function() {
                            sum += Number($(this).val());
                        });
                        
                        if (sum == $("#socau").val()) {
                            $("#thongbao").html("Số câu trùng khớp").css("color", "green");
                            $("#btnTaoDe").prop("disabled",false);
                        } else {
                            $("#thongbao").html("Số câu không trùng khớp").css("color", "red");
                            $("#btnTaoDe").prop("disabled",true);
                        }
                    });                    
                    
                    $(document).ready(function() {
                        $('[type=text]').keydown(function (e) {
                            // Allow: backspace, delete, tab, escape, enter and .
                            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                                 // Allow: Ctrl/cmd+A
                                (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                                 // Allow: Ctrl/cmd+C
                                (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
                                 // Allow: Ctrl/cmd+X
                                (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
                                 // Allow: home, end, left, right
                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                     // let it happen, don't do anything
                                     return;
                            }
                            // Ensure that it is a number and stop the keypress
                            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                                e.preventDefault();
                            }
                        });
                    });
                    
                    function DoExam() {
                        if (confirm('Bạn đã sẵn sàng làm bài chưa?') == true) {
                            $("#createExam-socau-noidung").submit();
                        }
                    }
                </script>                                        
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>            
    </body>
</html>
