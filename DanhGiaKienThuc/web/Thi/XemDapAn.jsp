<%-- 
    Document   : XemDapAn
    Created on : Mar 2, 2017, 8:31:35 AM
    Author     : NTL
--%>

<%@page import="java.util.List"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XEM ĐÁP ÁN</title>
        <link rel="stylesheet" href="../css/DoExamStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/FooterStyle.css" type="text/css">             
        <link rel="stylesheet" href="../css/OtherStyle.css" type="text/css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js">
            MathJax.Hub.Config({
                extensions: ["tex2jax.js","TeX/AMSmath.js","TeX/AMSsymbols.js"],
                jax: ["input/TeX", "output/HTML-CSS"],
                tex2jax: {
                    inlineMath: [ ['$','$'], ["\\(","\\)"] ],
                    displayMath: [ ['$$','$$'], ["\\[","\\]"] ],
                    },
                    "HTML-CSS": { availableFonts: ["TeX"] }
            });
        </script>    
    </head>
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>
        
        <div class="container">
            <%
                List IDlist = null;
                if (session.getAttribute("ID_List")!=null) {
                    IDlist = (List) session.getAttribute("ID_List");
                }
            %>
        </div>            
        
        <script src="../js/autoscroll.js" type="text/javascript"></script>
            
        <%--<jsp:include page="../WebInterface/footer.jsp"></jsp:include>--%>           
    </body>
</html>
