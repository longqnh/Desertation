<%-- 
    Document   : QLKD
    Created on : Feb 20, 2017, 11:15:13 PM
    Author     : NTL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="connect.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN TRỊ KHO ĐỀ THI</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>   
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

        <!-- Include one of jTable styles. -->
        <link href="${pageContext.request.contextPath}/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
        <!-- Include jTable script file. -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.jtable.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#TableContainer').jtable({
                    title: 'Danh sách câu hỏi',
                    paging: true, //Enable paging
                    //pageSize: 15, //Set page size (default: 10)
                    //sorting: true, //Enable sorting
                    //defaultSorting: 'id ASC',
                    selecting: true, //Enable selecting
                    multiselect: true, //Allow multiple selecting
                    selectingCheckboxes: true, //Show checkboxes on first column
                    selectOnRowClick: false, //Click row on check box
                    actions: {
                        listAction: '${pageContext.request.contextPath}/QuestionCRUD?action=list&kienthuc=hamso',
                        createAction: '${pageContext.request.contextPath}/QuestionCRUD?action=create',
                        updateAction: '${pageContext.request.contextPath}/QuestionCRUD?action=update',
                        deleteAction: '${pageContext.request.contextPath}/QuestionCRUD?action=delete'
                    },
                    fields: {
                        id: {
                            title: 'ID',
                            key: true,
                            list: true,
                            edit: true,
                            create: true
                        },
                        noidung: {
                            title: 'Nội dung',
                            type: 'textarea',
                            edit: true
                        },
                        dapanA: {
                            title: 'Đáp án A',
                            type: 'text',
                            edit: true
                        },
                        dapanB: {
                            title: 'Đáp án B',
                            type: 'text',
                            edit: true
                        },
                        dapanC: {
                            title: 'Đáp án C',
                            type: 'text',
                            edit: true
                        },
                        dapanD: {
                            title: 'Đáp án D',
                            type: 'text',
                            edit: true
                        },
                        answer: {
                            title: 'Đáp án',
                            type: 'select',
                            options: {'A': 'A', 'B': 'B', 'C': 'C', 'D': 'D'},
                            edit: true
                        },     
                        dangbt: {
                            title: 'Dạng bt',
                            type: 'text',
                            edit: true
                        },
                        level: {
                            title: 'Level',
                            type: 'select',
                            options: {'0': 'nhận biết', '1': 'thông hiểu', '2': 'vận dụng', '3': 'vận dụng cao'},
                            edit: true
                        },
                        hasImage: {
                            title: 'Hình',
                            type: 'text',
                            edit: true
                        }                      
                    }
                });
                
//                $('#TableContainer').jtable('option', 'pageSize', 10);
                $('#TableContainer').jtable('load');
            });
        </script>   
        
        <script type="text/javascript">
            function Ajax() {
                var val = document.getElementById("noidung").value;
                //alert(val);
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState==4 && xhttp.status==200) {
                        document.getElementById("TableContainer").innerHTML=xhttp.responseText;
                    }
                };
                xhttp.open("POST","${pageContext.request.contextPath}/QuestionCRUD?action=list&kienthuc="+val,true);
                $('#TableContainer').jtable('reload');
                xhttp.send();
            }
        </script>
    </head>
    <body>       
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
        %>
        
        <div id="sticky-anchor"></div>
        <div id="top">
            <div id="top-right">
                    <% if (users!=null) { %>
                        <ul>
                            <li id="user-info"><a href="#" style="text-transform: none; text-align: center;"><%=users.getUsername()%></a>
                                <ul class="sub-top-right">
                                    <%
                                        String page_redirect= request.getContextPath() + "/Member/User.jsp";
                                    %>
                                    <li><a href="<%=page_redirect%>">Quản lý tài khoản</a></li>
                                    <form action="<%=request.getContextPath()%>/UserServlet"method="POST">
                                        <input id="btnlogout" type="submit" value="Thoát">
                                        <input type="hidden" value="logout" name="command">
                                    </form>
                                </ul>
                            </li>
                        </ul>
                    <% } else { %>
                        <ul>
                            <li><a href="login.jsp">Đăng nhập</a></li>
                            <li><a href="register.jsp">Đăng ký</a></li>
                        </ul>
                    <% } %>
            </div>

            <div id="top-left">
                <a href="<%=request.getContextPath()%>/index.jsp">website đánh giá kiến thức toán thpt</a>
            </div>
        </div>

        <script type="text/javascript">
            function sticky_relocate() {
                var window_top = $(window).scrollTop();
                var div_top = $('#sticky-anchor').offset().top;
                if (window_top > div_top) {
                    $('#top').addClass('stick');
                    $('#sticky-anchor').height($('#top').outerHeight());
                } else {
                    $('#top').removeClass('stick');
                    $('#sticky-anchor').height(0);
                }
            }

            $(function() {
                $(window).scroll(sticky_relocate);
                sticky_relocate();
            });
        </script>
        
        <!-- Header -->
        <div id="banner">
            <!-- add image -->
        </div>

        <!-- Menu -->
        <div id="menu">
            <ul>
                <li><a href="<%=request.getContextPath()%>/index.jsp" <!--class="selected"-->Trang chủ</a></li>
                <li><a href="#">Làm đề thi</a>
                    <ul class="submenu">
                        <li><a href="<%=request.getContextPath()%>/Thi/MockTest.jsp">Thi thử</a></li>
                        <li><a href="<%=request.getContextPath()%>/Thi/Practice.jsp">Luyện tập</a></li>
                    </ul>
                </li>
                <li><a> Lý thuyết</a>
                    <ul class="submenu">
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop12.jsp"> Toán 12 </a></li>                        
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop11.jsp">Toán 11</a></li>
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop10.jsp">Toán 10</a></li>
                        <li><a href="<%=request.getContextPath()%>/OnLyThuyet/LyThuyetTracNghiem.jsp">Lý Thuyết Trắc Nghiệm</a></li>
                    </ul>                            
                </li>
                <li><a href="<%=request.getContextPath()%>/tutorial.jsp">Hướng dẫn</a></li>
                <li><a href="<%=request.getContextPath()%>/information.jsp">Giới thiệu</a></li>
                <li><a href="<%=request.getContextPath()%>/contact.jsp">Liên hệ - góp ý</a></li>
            </ul>
            
            <script type="text/javascript">
                function Click() {
                    $('#select').addClass('selected');
                }
            </script>
        </div>   
            
        <div class="container">
            <h2 style="text-align: center; font-weight: bold; color: rgb(6,114,28); font-family: Arial, sans-serif;">QUẢN TRỊ KHO ĐỀ THI</h2>
        
            <form id="demo" action="${pageContext.request.contextPath}/QuestionCRUD?action=list" method="POST">
                <!--<input type="text" name="action" id="action" value="list" hidden="">-->
                <select id="kienthuc" name="kienthuc" onchange="this.form.submit()">
                    <option value="hamso">Hàm số</option>
                    <option value="loga">Lũy thừa - Mũ - Logarit</option>
                    <option value="tichphan">Nguyên hàm - Tích phân</option>
                    <option value="sophuc">Số phức</option>
                    <option value="hhkg">Hình học không gian</option>
                    <option value="oxyz">Oxyz</option>
                </select>
            </form>
            <div id="TableContainer"></div>
        </div>
                
        <script type="text/javascript" src="../js/autoscroll.js"></script>
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
