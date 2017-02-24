<%-- 
    Document   : contact
    Created on : Jan 4, 2017, 12:07:36 AM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LIÊN HỆ - GÓP Ý</title>
        <link rel="stylesheet" href="css/ContactStyle.css" type="text/css">
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        <div class="container">
            <h2>LIÊN HỆ - GÓP Ý</h2>
            <form>
                <div class="group">      
                    <input type="text" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Họ tên</label>
                </div>
                <div class="group">      
                    <input type="email" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Email</label>
                </div>
                <div class="group">      
                    <input type="text">
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Số điện thoại</label>
                </div>                
                <div class="group">      
                    <input type="text" required>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label>Tiêu đề</label>
                </div>                
                <div class="group">
                    <textarea required></textarea>
                    <span class="highlight"></span>
                    <span class="bar"></span>                    
                    <label>Nội dung</label>
                </div>
                <input id="btnSend" type="submit" value="Gửi">            
            </form>
        </div>

        <script type="text/javascript" src="js/autoscroll.js"></script>
        
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
