<%-- 
    Document   : QuanLyHocTap
    Created on : Feb 9, 2017, 9:26:57 PM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN LÝ HỌC TẬP</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>   
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
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
                    title: 'Danh sách bài thi',
                    paging: true, //Enable paging
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    defaultSorting: 'made ASC',
                    actions: {
                        listAction: '${pageContext.request.contextPath}/DethiCRUD?action=list'
                    },
                    fields: {
                        made: {
                            title: 'Mã đề',
                            key: true,
                            list: true,
                            display: function(data) {
                                if (data.record.ngaythi != null) {
                                    return '<a target="_blank" href="<%=request.getContextPath()%>/Thi/FinishExam.jsp?made=' + data.record.made + '">' + data.record.made + '</a>';
                                } else {
                                    return data.record.made;
                                }
                            }
                        },
                        socau: {
                            title: 'Số câu',
                            type: 'text'
                        },
                        noidung: {
                            title: 'Kiến thức',
                            type: 'text'
                        },
                        thoigian: {
                            title: 'Thời gian',
                            type: 'text'
                        },
                        mucdo: {
                            title: 'Độ khó',
                            type: 'text',
                            display: function(data) {
                                if (data.record.mucdo == 0) {
                                    return 'Dễ';
                                } else {
                                    if (data.record.mucdo == 1) {
                                        return 'Trung bình';
                                    } else {
                                        return 'Khó';
                                    }
                                }
                            }                            
                        },
                        diem: {
                            title: 'Điểm',
                            type: 'text'
                        },
                        ngaythi: {
                            title: 'Thời gian thi',
                            type: 'text'
                        }
                    }
                });
                //Re-load records when user click 'load records' button.
                $('#LoadRecordsButton').click(function (e) {
                    e.preventDefault();
                    $('#TableContainer').jtable('load', {
                        name: $('#made').val()
                    });
                });

                //Load all records when page is first shown
                $('#LoadRecordsButton').click();
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
            <div id="main-left">
                <div id="main-left-top">
                        <h2 style="text-align: center; text-transform: uppercase; margin-top: 5px; font-family:'Roboto';">Tìm kiếm</h2>
                        <div id="search">
                            <form>
                                <input type="text" placeholder="Search this site..." id="textsearch"/>
                                <input type="submit" id="search-button" value=""/>
                            </form>
                        </div>                            
                </div>

                <div id="main-left-bottom">
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/Member/User.jsp"> Thông tin tài khoản </a></li>
                        <li><a href="<%=request.getContextPath()%>/Member/QuanLyHocTap.jsp"> Quản lý học tập</a></li>   
                        <li><a href="<%=request.getContextPath()%>/Member/DanhGia.jsp"> Đánh giá kiến thức</a></li>                        
                        <%
                            if (users.getRole().equals("admin")) { %>
                                <li><a href="<%=request.getContextPath()%>/Member/QuanLyHocTap.jsp"> Quản lý học tập</a></li>                        
                                <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp"> Quản lý kho đề</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDT.jsp">Quản lý các bài thi</a></li>                                
                        <%  } %>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>QUẢN LÝ HỌC TẬP</h2>

                <div class="filtering">
                    <form>
                        <input type="text" name="made" id="made" hidden=""/>
                        <button type="submit" id="LoadRecordsButton" hidden=""></button>
                    </form>
                </div>

                <div id="TableContainer"></div>
            </div>
        </div>
            
        <script type="text/javascript" src="../js/autoscroll.js"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>