<%-- 
    Document   : QLLT
    Created on : Apr 8, 2017, 12:48:23 AM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN LÝ THƯ VIỆN LÝ THUYẾT</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
        <script src="//cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>        
    </head>
    <body>
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
        %>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>
        
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
                <h2>QUẢN LÝ THƯ VIỆN LÝ THUYẾT</h2>

                <form id="capnhatLT" action="${pageContext.request.contextPath}/EditLyThuyet" method="POST">
                    <textarea name="content">
                        ${requestScope.content}
                    </textarea>
                    <input id="btnUpdateLT" type="button" value="Lưu">
                    <input type="text" hidden="" name="kienthuc" value="${requestScope.kienthuc}">
                    <input type="text" hidden="" id="contentEdited" name="contentEdited">
                </form>
                
                <script>
                    CKEDITOR.replace("content", {height: '500px'});
                    CKEDITOR.config.entities_latin = false;
                </script>
                
                <script type="text/javascript">
                    $("#btnUpdateLT").click(function () {
                        var data = CKEDITOR.instances.content.getData();
                        $("#contentEdited").val(data);
                        $("#capnhatLT").submit();
                    });
                </script>
            </div>         
        </div>
            
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/autoscroll.js"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>        
    </body>
</html>
