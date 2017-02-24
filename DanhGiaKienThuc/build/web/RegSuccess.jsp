<%-- 
    Document   : LoginSuccess
    Created on : Jan 8, 2017, 1:40:25 AM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/SuccessStyle.css" type="text/css">
        <title>ĐĂNG KÝ THÀNH CÔNG</title>
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        
        <div id="reg_success">
            <h2>Đăng ký thành công</h2><br>
            <h2>Hãy <a href="login.jsp">đăng nhập</a> để tiếp tục sử dụng</h2><br><br>
            <p>Hệ thống sẽ chuyển sang trang <a href="login.jsp">đăng nhập</a> sau <span id="countdown">5</span> giây nữa</p>
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
