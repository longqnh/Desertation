<%-- 
    Document   : MockTest
    Created on : Jan 25, 2017, 1:06:00 AM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>THI THỬ</title>
    </head>
    <body>
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
            
            if (users==null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else { %>        
                <jsp:forward page="LamDeThi.jsp">
                    <jsp:param name="kienthuc" value="hamso"></jsp:param>
                    <jsp:param name="kienthuc" value="loga"></jsp:param>
                    <jsp:param name="kienthuc" value="tichphan"></jsp:param>
                    <jsp:param name="kienthuc" value="sophuc"></jsp:param>
                    <jsp:param name="kienthuc" value="hhkg"></jsp:param>
                    <jsp:param name="kienthuc" value="oxyz"></jsp:param>
                    <jsp:param name="dokho" value="1"></jsp:param>
                    <jsp:param name="time" value="90"></jsp:param>
                </jsp:forward>
            <%  }  %>
    </body>
</html>
