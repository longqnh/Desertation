<%-- 
    Document   : login
    Created on : Jan 3, 2017, 9:27:44 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ĐĂNG NHẬP</title>
        <link rel="stylesheet" href="css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="css/LoginStyle.css" type="text/css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        
        <p id="notice">${requestScope.notice}</p>
        <div class="container">
            <h2>ĐĂNG NHẬP</h2>
            <form action="UserServlet" method="POST">
                <div class="edit-field">   
                    <label>Tên tài khoản:</label>                    
                    <input type="text" required name="username" id="username">
                </div>

                <div class="edit-field">      
                    <label>Mật khẩu:</label>                    
                    <input type="password" required name="password" id="password">
                </div>
                
                <h4 style="color: red;"> ${requestScope.errorMessage} </h4> <br>    
                <input id="btnDangNhap" type="submit" value="Đăng nhập">
                <input type="hidden" value="login" name="command">                
                <div id="noti">
                    <h3>Bạn chưa có tài khoản? Hãy <a href="register.jsp">Đăng ký</a></h3>
                </div>
            </form>
        </div>
        
        <script type="text/javascript" src="js/autoscroll.js"></script>
        
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
