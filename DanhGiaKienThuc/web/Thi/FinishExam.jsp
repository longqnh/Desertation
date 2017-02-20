<%-- 
    Document   : FinishExam
    Created on : Feb 20, 2017, 10:30:48 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOÀN THÀNH BÀI THI</title>
        <link rel="stylesheet" href="../css/HeaderStyle.css">
        <link rel="stylesheet" href="../css/FooterStyle.css">
        <link rel="stylesheet" href="../css/OtherStyle.css">
    </head>
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>
        
        <div id="clr"></div>
        <div class="container">
            <% 
                int Diem = (int) session.getAttribute("DiemThi"); 
            %>
            <h2>Bạn được <%=Diem%> điểm</h2>
        </div>
        
        <script src="../js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>

    </body>
</html>
