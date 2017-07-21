<%-- 
    Document   : QLDToan
    Created on : Jun 26, 2017, 8:14:09 PM
    Author     : NTL
--%>
<%@page import="model.Lop"%>
<%@page import="dao.LopDAO"%>
<%@page import="model.MonHoc"%>
<%@page import="java.util.List"%>
<%@page import="dao.MonHocDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN LÝ DẠNG TOÁN</title>
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
                    title: 'Danh sách dạng toán',
                    paging: true, //Enable paging
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    defaultSorting: 'dangtoan ASC',
                    selecting: true, //Enable selecting
                    multiselect: true, //Allow multiple selecting
                    selectingCheckboxes: true, //Show checkboxes on first column
                    selectOnRowClick: false, //Click row on check box
                    actions: {
                        listAction: '${pageContext.request.contextPath}/DangToanCRUD?action=list',
                        createAction: '${pageContext.request.contextPath}/DangToanCRUD?action=create',
                        updateAction: '${pageContext.request.contextPath}/DangToanCRUD?action=update',
                        deleteAction: '${pageContext.request.contextPath}/DangToanCRUD?action=delete'
                    },
                    fields: {
                        dangtoan: {
                            title: 'Mã dạng toán',
                            key: true,
                            list: true,
                            edit: true,
                            create: true
                        },
                        dangtoanTV: {
                            title: 'Tên dạng toán',
                            type: 'text',
                            edit: true
                        },
                        madangtoan: {
                            title: 'Viết tắt',
                            type: 'text',
                            edit: true
                        },
                        malop: {
                            title: 'Lớp',
                            type: 'select',
                            options: '${pageContext.request.contextPath}/DangToanCRUD?action=other',
                            edit: true
                        },
                        hocky: {
                            title: 'Học kỳ',
                            type: 'select',
                            options: {'1': 'Học kỳ 1', '2': 'Học kỳ 2'},
                            edit: true
                        }
                    }
                });
                
                //Re-load records when user click 'load records' button.
                $('#LoadRecordsButton').click(function (e) {
                    e.preventDefault();
                    $('#TableContainer').jtable('load', {
                        monhoc: $('#monhoc').val(),
                        lop: $('#lop').val()
                    });
                });

                //Load all records when page is first shown
                $('#LoadRecordsButton').click();
                        
                //Delete selected students                        
                $('#DeleteAllButton').button().click(function () {
                    var $selectedRows = $('#TableContainer').jtable('selectedRows');
                    $('#TableContainer').jtable('deleteRows', $selectedRows);
                });
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
                                    <form action="<%=request.getContextPath()%>/UserServlet" method="POST">
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
                        <%
                            if (users.getRole().equals("admin")) { %>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLMH.jsp"> Quản lý các môn học</a></li>                                                                
                                <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp"> Quản lý kho đề</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDT.jsp">Quản lý các bài thi</a></li>                                
                                <li><a href="<%=request.getContextPath()%>/Admin/QLLop.jsp">Quản lý lớp</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDToan.jsp">Quản lý dạng toán</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDBT.jsp">Quản lý dạng bài tập</a></li>                                 
                                <li><a href="<%=request.getContextPath()%>/Admin/QLCH.jsp">Xem số lượt làm câu hỏi</a></li>                                
                                <li><a href="<%=request.getContextPath()%>/Admin/TestLatex.jsp">Kiểm tra Latex</a></li>                                                                
                        <%  } %>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
                        
            <div id="main-right">
                <h2>QUẢN LÝ CÁC DẠNG TOÁN</h2>     
                
                <div class="filtering">
                    <form>
                        Môn:
                        <select name="monhoc" id="monhoc" required>
                            <%
                                MonHocDAO monHocDAO = new MonHocDAO();
                                List<MonHoc> dsMon = monHocDAO.GetAllMonHoc(); 
                                for (MonHoc mon : dsMon) { %>
                                    <option value="<%=mon.getMonhocID()%>"> <%=mon.getTenmonhoc()%> </option>                                  
                            <%  } %>
                        </select>                    
                        Lớp:
                        <select name="lop" id="lop" required>
                            <option value="" disabled selected>Lớp</option>
                            <%
                                LopDAO lopDAO = new LopDAO(); 
                                List<Lop> dsLop = lopDAO.GetAllLop(); 
                                for (Lop lop: dsLop) { %>
                                    <option value="<%=lop.getMalop()%>"> <%=lop.getTenlop()%> </option>                                  
                            <%  } %>
                        </select>                    

                        <button type="submit" id="LoadRecordsButton">Search</button>
                    </form>
                </div>                                
                
                <div id="TableContainer"></div>
                <button type="button" id="DeleteAllButton">Delete All Selected</button>
            </div>                
        </div>
            
        <script type="text/javascript" src="../js/autoscroll.js"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
