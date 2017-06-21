<%-- 
    Document   : register
    Created on : Jan 3, 2017, 11:19:49 PM
    Author     : NTL
--%>

<%@page import="model.Lop"%>
<%@page import="java.util.List"%>
<%@page import="dao.LopDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ĐĂNG KÝ TÀI KHOẢN</title>
        <link rel="stylesheet" href="css/LoginStyle.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/CheckExist.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        
        <div class="container">
            <h2>ĐĂNG KÝ THÀNH VIÊN</h2>
            <form action="UserServlet" method="POST">
                <div class="edit-field">   
                    <label>Tên tài khoản</label>                    
                    <input type="text" required name="username" id="username">
                    <span id="user-result"></span>
                </div>
                
                <div class="edit-field">      
                    <label>Mật khẩu</label>                    
                    <input type="password" required name="password" id="password">
                    <span></span>
                </div>
          
                <div class="edit-field">     
                    <label>Xác nhận mật khẩu</label>
                    <input type="password" required name="confirm_password" id="confirm_password">
                    <span id="confirm_password-result"></span>
                </div>
                <script type="text/javascript" src="js/CheckPass.js"></script>
                
                <div class="edit-field">
                    <label>Đang là học sinh lớp </label>
                    <select name="lop" id="lop" required>
                        <option value="" disabled selected>Lớp</option>
                        <%  LopDAO lopDAO = new LopDAO(); 
                            List<Lop> dsLop = lopDAO.GetAllLop(); 
                            for (Lop lop: dsLop) { %>
                                <option value="<%=lop.getMalop()%>"> <%=lop.getTenlop()%> </option>                                  
                        <%  } %>
                    </select>                  
                </div>
                
                <div class="edit-field">      
                    <label>Họ và tên</label>                    
                    <input type="text" required name="name" id="name">
                </div>
                    
                <div class="edit-field">   
                    <label>Địa chỉ email</label>                    
                    <input type="email" required name="email" id="email">
                    <span id="email-result"></span>
                </div>                
                    
                <input id="btnDangKy" type="submit" value="Đăng ký">
                <input type="hidden" value="insert" name="command">
                <div  id="noti">
                    <h3>Bạn đã có tài khoản? Hãy <a href="login.jsp" style="">Đăng nhập</a></h3>
                </div>
            </form>
        </div>
        
        <script type="text/javascript" src="js/autoscroll.js"></script>
        
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>        
    </body>
</html>
