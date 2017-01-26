<%-- 
    Document   : LamDeThi
    Created on : Jan 14, 2017, 8:38:02 PM
    Author     : NTL
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="connect.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Users"%>
<%@page import="tools.MD5"%>
<%@page import="model.Question"%>
<%@page import="java.util.*"%>
<%@page import="dao.QuestionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LÀM ĐỀ THI</title>
        <link rel="stylesheet" href="css/DoExamStyle.css" type="text/css">
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

<!--        <div id="sticky-anchor"></div>
        <div id="timer"></div>
        
        <script type="text/javascript" src="js/TimeCounter.js"></script>-->
        
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
            
//            if (users==null) {
//                response.sendRedirect("login.jsp");
//            }
        %>

        <div id="top">
            <div id="top-right">
                <% if (users!=null) { %>
                    <ul>
                        <li id="user-info"><a href="#" style="text-transform: none; text-align: center;"><%=users.getUsername()%></a>
                            <ul class="sub-top-right">
                                <li><a href="#">Quản lý tài khoản</a></li>
                                <form action="UserServlet"method="POST">
                                    <input id="btnlogout" type="submit" value="Thoát">
                                    <input type="hidden" value="logout" name="command">
                                </form>
                            </ul>
                        </li>
                    </ul>
                <% } %>
            </div>

            <div id="top-left">
                <a href="index.jsp">website đánh giá kiến thức toán thpt</a>
            </div>
        </div>
        
        <div id="main">
            <form id="doExam" name="doExam" action="CheckAnswer" method="POST">
                <h2>ĐỀ LUYỆN TẬP</h2>
                <%
                    String[] noidung = request.getParameterValues("kienthuc");
                    String level = request.getParameter("dokho");
                    QuestionDAO questionDAO = new QuestionDAO();
                    List exam = questionDAO.CreateExam(noidung,level); 

                    List IDlist = new ArrayList(); // save id of every question

                    for (int i = 0; i < exam.size(); i++) {
                        Question q = (Question) exam.get(i); %>

                        <div>
                            <p><b>Câu <%=i+1%>: </b> <%=q.getNoidung()%></p>
                            <%
                                if (q.getHasImage()==1) { %>
                                    <img src="images/<%=q.getId()%>.JPG">
                            <% } %>
                            <p><b>A. </b><input type="radio" name="question<%=q.getId()%>" value="A"> <%=q.getDapanA()%></p>
                            <p><b>B. </b><input type="radio" name="question<%=q.getId()%>" value="B"> <%=q.getDapanB()%></p>
                            <p><b>C. </b><input type="radio" name="question<%=q.getId()%>" value="C"> <%=q.getDapanC()%></p>
                            <p><b>D. </b><input type="radio" name="question<%=q.getId()%>" value="D"> <%=q.getDapanD()%></p>
                        </div>

                    <%
                        IDlist.add(q.getId()); 
                    }
                        session.setAttribute("ID_List", IDlist);
                    %>

                <!--<input type="hidden" value="" name="">-->   
                <input type="submit" value="Nộp bài">
            </form>
        </div>
    </body>
</html>
