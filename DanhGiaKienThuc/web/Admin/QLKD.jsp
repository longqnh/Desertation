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
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    defaultSorting: 'id ASC',
                    selecting: true, //Enable selecting
                    multiselect: true, //Allow multiple selecting
                    selectingCheckboxes: true, //Show checkboxes on first column
                    selectOnRowClick: false, //Click row on check box
                    actions: {
                        listAction: '${pageContext.request.contextPath}/QuestionCRUD?action=list',
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
                        dapan: {
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
                        dokho: {
                            title: 'Level',
                            type: 'select',
                            options: {'0': 'nhận biết', '1': 'thông hiểu', '2': 'vận dụng', '3': 'vận dụng cao'},
                            edit: true
                        },
                        malop: {
                            title: 'Lớp',
                            type: 'select',
                            options: {'12': '12', '11': '11', '10': '10'},
                            edit: true
                        },
                        hinh: {
                            title: 'Hình',
                            type: 'select',
                            options: {'0': 'không có hình', '1': 'có hình'},
                            display: function(data) {
                                if (data.record.hinh==1) {
                                    return '<a href="../images/' + data.record.dangtoan + '/' + data.record.id +'.JPG">Link</a>';
                                }
                            },
                            edit: true
                        }                      
                    }
                });
                
                //Re-load records when user click 'load records' button.
                $('#LoadRecordsButton').click(function (e) {
                    e.preventDefault();
                    $('#TableContainer').jtable('load', {
                        name: $('#name').val(),
                        kienthuc: $('#kienthuc').val()
                    });
                });

                //Load all records when page is first shown
                $('#LoadRecordsButton').click();
//                $('#TableContainer').jtable('load');
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
                <li><a href="#"><%=users.getUsername()%></a>
                    <ul class="submenu">
                        <li><a href="<%=request.getContextPath()%>/Member/User.jsp"> Thông tin tài khoản </a></li>                        
                        <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp">Quản lý các tài khoản</a></li>
                        <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp">Quản lý kho đề</a></li>
                    </ul>
                </li>
            </ul>
            
            <script type="text/javascript">
                function Click() {
                    $('#select').addClass('selected');
                }
            </script>
        </div>   
            
        <div class="container">
            <h2 style="text-align: center; font-weight: bold; color: rgb(6,114,28); font-family: Arial, sans-serif;">QUẢN TRỊ KHO ĐỀ THI</h2>

            <div class="filtering">
                <form>
                    ID: <input type="text" name="name" id="name" />
                    Kiến thức: 
                    <select id="kienthuc" name="kienthuc">
                        <option value="hamso" selected="selected">Hàm số</option>
                        <option value="loga">Lũy thừa - Mũ - Logarit</option>
                        <option value="tichphan">Nguyên hàm - Tích phân</option>
                        <option value="sophuc">Số phức</option>
                        <option value="hhkg">Hình học không gian</option>
                        <option value="oxyz">Oxyz</option>
                    </select>
                    <button type="submit" id="LoadRecordsButton">Load records</button>
                </form>
            </div>                    
            
            <div id="TableContainer"></div>
        </div>
                
        <script type="text/javascript" src="../js/autoscroll.js"></script>
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
