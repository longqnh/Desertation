<%-- 
    Document   : LoginSuccess
    Created on : Jan 9, 2017, 8:35:46 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/SuccessStyle.css" type="text/css">
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        
        <div id="reg_success">
            <h2 style="color: red">Sai tên tài khoản hoặc mật khẩu. Hãy <a href="login.jsp">đăng nhập</a> lại</h2><br>
            <p>Hệ thống quay trở lại trang <a href="login.jsp">đăng nhập</a> sau <span id="countdown">5</span> giây nữa</p>
        </div>
        <script type="text/javascript">
            var count=5;
            setInterval (function() {
                count--;
                document.getElementById('countdown').innerHTML = count;
                if (count==0) {
                    window.location = "login.jsp";
                }
            }, 1000);
        </script>   
        
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
