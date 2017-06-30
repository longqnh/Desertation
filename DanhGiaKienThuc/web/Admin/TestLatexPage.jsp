<%-- 
    Document   : LatexTest
    Created on : Jul 1, 2017, 1:28:28 AM
    Author     : NTL
--%>

<%@page import="model.Question"%>
<%@page import="java.util.List"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KIỂM TRA LATEX</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/DoExamStyle.css" type="text/css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.rawgit.com/mathjax/MathJax/2.7.1/MathJax.js">
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
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
        %>
        
        <div id="top">
            <div id="top-right">
                <% if (users!=null) { %>
                    <ul>
                        <li id="user-info"><a href="#" style="text-transform: none; text-align: center;"><%=users.getUsername()%></a>
                            <ul class="sub-top-right">
                                <%
                                    String page_redirect= request.getContextPath() + "/Member/User.jsp?username=" + users.getUsername();
                                %>
                                <li><a href="<%=page_redirect%>">Quản lý tài khoản</a></li>
                                <form action="${pageContext.request.contextPath}/UserServlet"method="POST">
                                    <input id="btnlogout" type="submit" value="Thoát">
                                    <input type="hidden" value="logout" name="command">
                                </form>
                            </ul>
                        </li>
                    </ul>
                <% } %>
            </div>

            <div id="top-left">
                <a href="${pageContext.request.contextPath}/index.jsp">website đánh giá kiến thức toán thpt</a>
            </div>       

            <div id="sticky-anchor"></div>
            <div id="user-answer-fake">
                <button id="btnSubmit" onclick="window.location.href='${pageContext.request.contextPath}/Admin/TestLatex.jsp'"> Back </button>
            </div>

            <script type="text/javascript">
                function sticky_relocate() {
                    var window_top = $(window).scrollTop();
                    var div_top = $('#sticky-anchor').offset().top;
                    if (window_top > div_top) {
                        $('#user-answer-fake').addClass('stick');
                        $('#sticky-anchor').height($('#user-answer-fake').outerHeight());
                    } else {
                        $('#user-answer-fake').removeClass('stick');
                        $('#sticky-anchor').height(0);
                    }
                }

                $(function() {
                    $(window).scroll(sticky_relocate);
                    sticky_relocate();
                });
            </script>               
        </div>
        
        <div id="main">
            <%    
                List exam = (List) request.getAttribute("exam");
                
                for (int i = 0; i < exam.size(); i++) {
                    Question q = (Question) exam.get(i); %>

                    <div>
                        <p><b><%=q.getId()%>:</b> <%=q.getNoidung()%></p>
                        <%
                            if (q.getHinh()==1) { %>
                                <img src="${pageContext.request.contextPath}/images/NHCH/<%=q.getId()%>.JPG">
                        <% } %>
                        <p><b>A. </b> <%=q.getDapanA()%></p>
                        <p><b>B. </b> <%=q.getDapanB()%></p>
                        <p><b>C. </b> <%=q.getDapanC()%></p>
                        <p><b>D. </b> <%=q.getDapanD()%></p>
                    </div>
            <%  } %>
        </div>
    </body>
</html>
