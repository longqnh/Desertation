<%-- 
    Document   : UpdateQuestion
    Created on : Jun 11, 2017, 12:43:56 PM
    Author     : NTL
--%>

<%@page import="model.DangBaiTap"%>
<%@page import="dao.DangBaiTapDAO"%>
<%@page import="dao.QuestionDAO"%>
<%@page import="model.Question"%>
<%@page import="model.Dokho"%>
<%@page import="dao.DokhoDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Dangtoan"%>
<%@page import="dao.DangtoanDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHỈNH SỬA CÂU HỎI TRẮC NGHIỆM</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>   
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <style>
            textarea {
                font-size: 15px;
                width: 600px;
            }
        </style>
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
                        <%
                            if (users.getRole().equals("admin")) { %>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLTK.jsp"> Quản lý các tài khoản</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLMH.jsp"> Quản lý các môn học</a></li>                                                                
                                <li><a href="<%=request.getContextPath()%>/Admin/QLKD.jsp"> Quản lý kho đề</a></li>
                                <li><a href="<%=request.getContextPath()%>/Admin/QLDT.jsp">Quản lý các bài thi</a></li>                                
                        <%  } %>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>Chỉnh sửa câu hỏi</h2>
                
                <%
                    String id = request.getParameter("id");
                    Question q = new QuestionDAO().getQuestionById(id);
                %>
                
                <form action="${pageContext.request.contextPath}/QuestionCRUD?action=update" method="POST">
                    <div class="edit-field">
                        <label>Nội dung: </label>
                        <textarea name="noidung" required=""><%=q.getNoidung()%></textarea>     
                    </div>

                    <div class="edit-field">
                        <label>Đáp án A: </label>
                        <textarea name="dapanA" required=""><%=q.getDapanA()%></textarea>                        
                    </div>

                    <div class="edit-field">
                        <label>Đáp án B: </label>
                        <textarea name="dapanB" required=""><%=q.getDapanB()%></textarea>                          
                    </div>

                    <div class="edit-field">
                        <label>Đáp án C: </label>
                        <textarea name="dapanC" required=""><%=q.getDapanC()%></textarea>  
                    </div>

                    <div class="edit-field">
                        <label>Đáp án D: </label>
                        <textarea name="dapanD" required=""><%=q.getDapanD()%></textarea>                          
                    </div>
                    
                    <div class="edit-field">
                        <label>Đáp án: </label>
                        <select name="dapan" id="dapan" required="">
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                            <option>D</option>
                        </select>
                        
                        <script type="text/javascript">
                            $("#dapan").val("<%=q.getDapan()%>");
                        </script>
                    </div>

                    <div class="edit-field">
                        <label>Dạng toán</label>
                        <select name="kienthuc" id="dangtoan" required="">
                            <option value="" disabled selected>Chọn dạng toán</option>
                        <%
                            DangtoanDAO dangtoanDAO = new DangtoanDAO();
                            List<Dangtoan> allDangtoan = dangtoanDAO.getAll();
                            for (Dangtoan dt : allDangtoan) { 
                                if (dt.getDangtoan().equals(q.getDangtoan())) { %>                                
                                    <option value="<%=dt.getDangtoan()%>" selected=""> <%=dt.getDangtoanTV()%> </option>                                
                            <%  } else { %>
                                    <option value="<%=dt.getDangtoan()%>"> <%=dt.getDangtoanTV()%> </option>
                            <%  } 
                            }   %>                            
                        </select>                        
                    </div>
                    
                    <div class="edit-field">
                        <label>Dạng bài tập</label>
                        <select name="dangbt" id="dangbt" required="">
                        <%
                            DangBaiTapDAO baiTapDAO = new DangBaiTapDAO();
                            List<DangBaiTap> allBaiTap = baiTapDAO.GetAllDangBaiTap(q.getDangtoan());
                            for (DangBaiTap dt : allBaiTap) {
                                if (dt.getDangbt().equals(q.getDangbt())) { %>                                
                                    <option value="<%=dt.getDangbt()%>" selected=""> <%=dt.getDangbtTV()%> </option>                                
                            <%  } else { %>
                                    <option value="<%=dt.getDangbt()%>"> <%=dt.getDangbtTV()%> </option>
                            <%  } 
                            }   %>
                        </select>
                    </div>
                        
                    <script type="text/javascript">
                        $('#dangtoan').change (
                            function() {
                                $.ajax({
                                    type: "POST",
                                    url: "${pageContext.request.contextPath}/getDangBT",
                                    data: {dangtoan: $(this).val() },
                                    success: function(data){
                                        $("#dangbt").html(data);
                                    }
                                });
                            }
                        );
                    </script>
                    
                    <div class="edit-field">
                        <label>Độ khó: </label>
                        <select name="dokho" required="">
                        <%
                            DokhoDAO dokhoDAO = new DokhoDAO();
                            List<Dokho> allDokho = dokhoDAO.GetAllDokhoCH();
                            for (Dokho dk : allDokho) { 
                                if (dk.getDokho()==q.getDokho()) { %>
                                    <option value="<%=dk.getDokho()%>" selected=""> <%=dk.getMucdo()%> </option>                                
                            <%  } else { %>                            
                                    <option value="<%=dk.getDokho()%>"> <%=dk.getMucdo()%> </option>
                            <%  }  
                            }   %>
                        </select>    
                    </div>

                    <div class="edit-field">
                        <label>Hình: </label>
                        <select name="hinh" required="">
                            <%
                                if (q.getHinh()==0) { %>
                                    <option value="0" selected="">Không có hình</option>
                                    <option value="1">Có hình</option>
                            <%  } else { %>
                                    <option value="0">Không có hình</option>
                                    <option value="1" selected="">Có hình</option>
                            <%  } %>
                        </select>
                    </div>
                    
                    <input type="text" name="id" value="<%=id%>" hidden="">
                    <input type="submit" id="btnThemCH" value="Cập nhật">
                </form>
            </div>  
        </div>
            
        <script type="text/javascript" src="../js/autoscroll.js"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>        
    </body>
</html>
