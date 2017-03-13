<%-- 
    Document   : XemDapAn
    Created on : Mar 2, 2017, 8:31:35 AM
    Author     : NTL
--%>

<%@page import="model.Question"%>
<%@page import="java.util.List"%>
<%@page import="dao.QuestionDAO"%>
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
                                <%
                                    String page_redirect= "../Member/User.jsp?username=" + users.getUsername();
                                %>
                                <li><a href="<%=page_redirect%>">Quản lý tài khoản</a></li>
                                <form action="../UserServlet"method="POST">
                                    <input id="btnlogout" type="submit" value="Thoát">
                                    <input type="hidden" value="logout" name="command">
                                </form>
                            </ul>
                        </li>
                    </ul>
                <% } %>
            </div>

            <div id="top-left">
                <a href="../index.jsp">website đánh giá kiến thức toán thpt</a>
            </div>

            <%
                QuestionDAO questionDAO = new QuestionDAO();
                List exam = questionDAO.GetDeThi();                
                List UserAnswer = (List) session.getAttribute("UserAnswer");
            %>         
        </div>
        
        <script type="text/javascript">
            function ShowAnswer(id, choice, answer) {
                switch (choice) {
                    case 'A':
                        document.getElementById(id+'AA').checked = true;
                        break;
                    case 'B':
                        document.getElementById(id+'BB').checked = true;
                        break;
                    case 'C':
                        document.getElementById(id+'CC').checked = true;
                        break;
                    case 'D':
                        document.getElementById(id+'DD').checked = true;
                        break;
                }

                switch (answer) {
                    case 'A':
                        document.getElementById(id+'A').style.color = "green";
                        break;
                    case 'B':
                        document.getElementById(id+'B').style.color = "green";
                        break;
                    case 'C':
                        document.getElementById(id+'C').style.color = "green";
                        break;
                    case 'D':
                        document.getElementById(id+'D').style.color = "green";
                        break;
                }
                
                if ((choice!=answer)) {
                    document.getElementById(id+choice).style.color = "red";
                }
            }
        </script>
        
        <div id="main">
            <h2>ĐÁP ÁN</h2>

            <%    
                for (int i = 0; i < exam.size(); i++) {
                    Question q = (Question) exam.get(i); 
                    String user_select = (String) UserAnswer.get(i); %>
                    
                    <div>
                        <p><b>Câu <%=i+1%>: </b> <%=q.getNoidung()%></p>
                        <%
                            if (q.getHinh()==1) { %>
                                <img src="../images/<%=q.getDangtoan()%>/<%=q.getId()%>.JPG">
                        <%  } %>
                        <p id="<%=q.getId()%>A"><b>A. </b><input type="radio" disabled="" id="<%=q.getId()%>AA" name="<%=q.getId()%>" value="A"> <%=q.getDapanA()%></p>
                        <p id="<%=q.getId()%>B"><b>B. </b><input type="radio" disabled="" id="<%=q.getId()%>BB" name="<%=q.getId()%>" value="B"> <%=q.getDapanB()%></p>
                        <p id="<%=q.getId()%>C"><b>C. </b><input type="radio" disabled="" id="<%=q.getId()%>CC" name="<%=q.getId()%>" value="C"> <%=q.getDapanC()%></p>
                        <p id="<%=q.getId()%>D"><b>D. </b><input type="radio" disabled="" id="<%=q.getId()%>DD" name="<%=q.getId()%>" value="D"> <%=q.getDapanD()%></p>
                    </div>
                    
                    <script type="text/javascript">
                        ShowAnswer('<%=q.getId()%>', '<%=user_select%>', '<%=q.getDapan()%>');
                    </script>      
                <% } %>
        </div>
    </body>
</html>
