<%-- 
    Document   : LamDeThi
    Created on : Jan 14, 2017, 8:38:02 PM
    Author     : NTL
--%>

<%@page import="dao.DethiDAO"%>
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

            <%
                List exam = (List) request.getAttribute("exam");
            %>
                    
                <div id="sticky-anchor"></div>
                <div id="user-answer">
                    <div id="timer"></div>
                    <div id="answer-table">
                        <%
                            for (int i = 0; i < exam.size(); i++) { 
                                Question q = (Question) exam.get(i); %>
                                <div class="numberCircle" id="<%=q.getId()%>"><%=i+1%></div>
                            <% } %>
                    </div>
                    
                    <script type="text/javascript">
                        function SubmitExam() {
                            if (confirm('Bạn có chắc chắn muốn nộp bài không?') == true) {
                                document.getElementById("doExam").submit();
                            }
                        }
                    </script>
                    
                    <script type="text/javascript" src="${pageContext.request.contextPath}/js/SelectQuestion.js"></script>
                    
                    <button id="btnSubmit" onclick="SubmitExam()"> Nộp bài </button>
                </div>
            
                <script type="text/javascript">
                    function sticky_relocate() {
                        var window_top = $(window).scrollTop();
                        var div_top = $('#sticky-anchor').offset().top;
                        if (window_top > div_top) {
                            $('#user-answer').addClass('stick');
                            $('#sticky-anchor').height($('#user-answer').outerHeight());
                        } else {
                            $('#user-answer').removeClass('stick');
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
            <form id="doExam" name="doExam" action="${pageContext.request.contextPath}/FinishExam" method="POST">
                <h2>ĐỀ LUYỆN TẬP - Mã đề: ${sessionScope.made} </h2>
                
                <input type="hidden" id="examtime" value="${requestScope.time}">
                <script src="${pageContext.request.contextPath}/js/TimeCounterJS.js" type="text/javascript"></script>

                <%    
                    List IDlist = new ArrayList(); // save id of every question

                    for (int i = 0; i < exam.size(); i++) {
                        Question q = (Question) exam.get(i); %>

                        <div id="Q<%=q.getId()%>" onclick="Answered('<%=q.getId()%>')">
                            <p><b>Câu <%=i+1%> (<%=q.getId()%>):</b> <%=q.getNoidung()%></p>
                            <%
                                if (q.getHinh()==1) { %>
                                    <img src="${pageContext.request.contextPath}/images/NHCH/<%=q.getId()%>.JPG">
                            <% } %>
                            <p><b>A. </b><input type="radio" name="<%=q.getId()%>" value="A"> <%=q.getDapanA()%></p>
                            <p><b>B. </b><input type="radio" name="<%=q.getId()%>" value="B"> <%=q.getDapanB()%></p>
                            <p><b>C. </b><input type="radio" name="<%=q.getId()%>" value="C"> <%=q.getDapanC()%></p>
                            <p><b>D. </b><input type="radio" name="<%=q.getId()%>" value="D"> <%=q.getDapanD()%></p>
                        </div>
                    <%
                        IDlist.add(q.getId()); 
                    }
                        session.setAttribute("ID_List", IDlist);
                    %>
                    
                    <script type="text/javascript">
                        function Answered(i) {
                            var radios = document.getElementsByName(i);

                            for (var j = 0, length = radios.length; j < length; j++) {
                                if (radios[j].checked) {
                                    document.getElementById(i).style.backgroundColor = "gray";
                                    break;
                                }
                            }
                        }
                    </script>
            </form>
        </div>
    </body>
</html>
