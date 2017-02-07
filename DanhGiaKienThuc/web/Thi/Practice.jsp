<%-- 
    Document   : Practice
    Created on : Jan 20, 2017, 8:16:05 PM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LUYỆN TẬP</title>
        <link rel="stylesheet" href="../css/DoExamStyle.css" type="text/css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>    
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link type="text/css" rel="stylesheet" href="../css/materialize.min.css"  media="screen,projection"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/materialize.min.js"></script>
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
        </div>
            
        <form id="createExam" name="createExam" action="LamDeThi.jsp" method="POST">
            <h5>Thông tin đề thi</h5>
            <div class="input-field col s12">
                <p>Chọn độ khó: </p>
                <select name="dokho" required>
                    <option value="" disabled selected>Độ khó</option>
                    <option value="0">Dễ</option>
                    <option value="1">Trung bình</option>
                    <option value="2">Trung bình - khó</option>
                    <option value="3">Khó</option>
                </select>
            </div>

            <div class="input-field col s12">
                <p>Chọn thời gian làm bài: </p>
                <select name="time" required>
                    <option value="" disabled selected>Thời gian</option>
                    <option value="15">15'</option>
                    <option value="45">45'</option>
                    <option value="90">90'</option>
                </select>
            </div>

            <div class="input-field col s12">
                <p>Chọn các kiến thức có trong đề thi: </p>
                <select multiple="" name="kienthuc" required>
                    <option value="" disabled selected>Kiến thức</option>
                    <option value="hs">Hàm số</option>
                    <option value="loga">Lũy thừa - Mũ - Logarit</option>
                    <option value="tp">Nguyên hàm - Tích phân</option>
                    <option value="sp">Số phức</option>
                    <option value="hkg">Hình học không gian</option>
                    <option value="oxyz">Oxyz</option>
                </select>
            </div>

            <script type="text/javascript">
                $(document).ready(function() {
                    $('select').material_select();
                });
            </script>
            
            <input id="btnTaoDe" type="submit" value="Tạo đề"> <!-- css in DoExamStyle -->
        </form>   
    </body>
</html>
