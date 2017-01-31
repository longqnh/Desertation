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
        <link rel="stylesheet" href="../css/DoExamStyle.css" type="text/css">
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
                <a href="../index.jsp">website đánh giá kiến thức toán thpt</a>
            </div>

            <%
                String[] noidung = request.getParameterValues("kienthuc");
                String level = request.getParameter("dokho");
                int time = Integer.parseInt(request.getParameter("time")); 
                
                QuestionDAO questionDAO = new QuestionDAO();
                List exam = questionDAO.CreateExam(noidung,level); 
            %>
                    
                <div id="sticky-anchor"></div>
                <div id="user-answer">
                    <div id="timer"></div>
                    <div id="answer-table">
                        <%
                            for (int i = 0; i < exam.size(); i++) { %>
                                <div class="numberCircle" id="cau<%=i+1%>"><%=i+1%></div>
                            <% } %>
                    </div>
                    
                    <script type="text/javascript">
                        $("#cau1").click(function() {
                            $('html,body').animate({
                                scrollTop: $("#answer-table > #cau5").offset().top},'slow');    
                        });
                    </script>
                    
                    <script type="text/javascript">
                        function SubmitExam() {
                            document.getElementById("doExam").submit();
                        }
                    </script>
                    
                    <button id="btnSubmit" onclick="SubmitExam()">
                        Nộp bài
                    </button>
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
            <form id="doExam" name="doExam" action="../FinishExam" method="POST">
                <h2>ĐỀ LUYỆN TẬP</h2>
              
                <input type="hidden" id="examtime" value="<%=time%>">
                <script src="../js/TimeCounterJS.js" type="text/javascript"></script>

                <%    
                    List IDlist = new ArrayList(); // save id of every question

                    for (int i = 0; i < exam.size(); i++) {
                        Question q = (Question) exam.get(i); %>

                        <div onclick="Answered(<%=q.getId()%>)">
                            <p><b>Câu <%=i+1%>: </b> <%=q.getNoidung()%></p>
                            <%
                                if (q.getHasImage()==1) { %>
                                    <img src="../images/<%=q.getId()%>.JPG">
                            <% } %>
                            <p><b>A. </b><input type="radio" id="question<%=q.getId()%>" name="question<%=q.getId()%>" value="A"> <%=q.getDapanA()%></p>
                            <p><b>B. </b><input type="radio" id="question<%=q.getId()%>" name="question<%=q.getId()%>" value="B"> <%=q.getDapanB()%></p>
                            <p><b>C. </b><input type="radio" id="question<%=q.getId()%>" name="question<%=q.getId()%>" value="C"> <%=q.getDapanC()%></p>
                            <p><b>D. </b><input type="radio" id="question<%=q.getId()%>" name="question<%=q.getId()%>" value="D"> <%=q.getDapanD()%></p>
                        </div>
                    <%
                        IDlist.add(q.getId()); 
                    }
                        session.setAttribute("ID_List", IDlist);
                    %>
                    
                    <script type="text/javascript">
                        function Answered(i) {
                            var radios = document.getElementsByName("question" + i);

                            for (var j = 0, length = radios.length; j < length; j++) {
                                if (radios[j].checked) {
                                    document.getElementById("cau"+i).style.backgroundColor = "gray";
                                    break;
                                }
                            }
//                            var id = document.getElementById("question" + i);
//                            if(document.querySelectorAll('input[type="radio"][name="' + id + '"]:checked').length < 1) {
//                                document.getElementById("test"+i).style.backgroundColor = "gray";
//                            }
                        }
                    </script>
            </form>
        </div>
    </body>
</html>
