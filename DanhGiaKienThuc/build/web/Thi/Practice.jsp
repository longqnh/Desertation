<%-- 
    Document   : Practice
    Created on : Jan 20, 2017, 8:16:05 PM
    Author     : NTL
--%>

<%@page import="model.MonHoc"%>
<%@page import="dao.MonHocDAO"%>
<%@page import="dao.DokhoDAO"%>
<%@page import="model.Dokho"%>
<%@page import="model.Lop"%>
<%@page import="java.util.List"%>
<%@page import="dao.LopDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LUYỆN TẬP</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>    
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/multi-select.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>   
    </head>
    
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>

        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
            }
            
            if (users==null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        %>
        
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
                        <li><a href="<%=request.getContextPath()%>/index.jsp"> Trang chủ</a></li>                       
                        <li><a> Làm đề thi </a>
                            <ul class="submnu">
                                <li><a href="<%=request.getContextPath()%>/Thi/MockTest.jsp"> Thi thử </a></li>
                                <li><a href="<%=request.getContextPath()%>/Thi/Practice.jsp"> Luyện tập </a></li>
                            </ul>
                        </li>
                        <li><a> Lý thuyết</a>
                            <ul class="submnu">
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop12.jsp"> Toán 12 </a></li>
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop11.jsp"> Toán 11 </a></li>
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/Lop10.jsp"> Toán 10 </a></li>
                                <li><a href="<%=request.getContextPath()%>/OnLyThuyet/LyThuyetTracNghiem.jsp"> Lý Thuyết Trắc Nghiệm </a></li>
                            </ul>                            
                        </li>
                        <li><a href="<%=request.getContextPath()%>/tutorial.jsp"> Hướng dẫn</a></li>
                        <li><a href="<%=request.getContextPath()%>/information.jsp"> Giới thiệu</a></li>
                        <li><a href="<%=request.getContextPath()%>/contact.jsp"> Liên hệ - Góp ý</a></li>
                    </ul>
                </div>
                
                <script src="<%=request.getContextPath()%>/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>

            <div id="main-right">
                <h2>Thông tin đề thi</h2>
                
                <form id="createExam" name="createExam" action="${pageContext.request.contextPath}/LamDeThi" method="POST">
                    <div class="search-field">
                        <label>Chọn môn: </label>
                        <select name="monhoc" id="monhoc" required>
                            <%
                                MonHocDAO monHocDAO = new MonHocDAO();
                                List<MonHoc> dsMon = monHocDAO.GetAllMonHoc(); 
                                for (MonHoc mon : dsMon) { %>
                                    <option value="<%=mon.getMonhocID()%>"> <%=mon.getTenmonhoc()%> </option>                                  
                            <%  } %>
                        </select>
                    </div>
                        
                    <div class="search-field">
                        <label>Chọn lớp: </label>
                        <select name="lop" id="lop" required>
                            <option value="" disabled selected>Lớp</option>
                            <%
                                LopDAO lopDAO = new LopDAO(); 
                                List<Lop> dsLop = lopDAO.GetAllLop(); 
                                for (Lop lop: dsLop) { %>
                                    <option value="<%=lop.getMalop()%>"> <%=lop.getTenlop()%> </option>                                  
                            <%  } %>
                        </select>
                    </div>
                    
                    <div class="search-field">
                        <label>Chọn độ khó: </label>
                        <select name="dokho" required>
                            <option value="" disabled selected>Độ khó</option>
                            <%
                                DokhoDAO dokhoDAO = new DokhoDAO();
                                List<Dokho> dsLevel = dokhoDAO.GetAllDokho();
                                for (Dokho dokho:dsLevel) { %>
                                    <option value="<%=dokho.getDokho()%>"> <%=dokho.getMucdo()%> </option>
                            <%  } %>
                        </select>
                    </div>

                    <div class="search-field">
                        <label>Chọn thời gian làm bài: </label>
                        <select name="time" required>
                            <option value="" disabled selected>Thời gian</option>
                            <option value="15">15'</option>
                            <option value="60">60'</option>
                            <option value="90">90'</option>
                        </select>
                    </div>
                        
                    <div style="margin: 10px 0px 0px 115px">
                        <select id="kienthuc" multiple="multiple" name="kienthuc" required></select>
                        
                        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
                        <script type="text/javascript">
                            $('#kienthuc').multiSelect({
                                selectableHeader: "<div class='custom-header' style='font-weight: bold;'>Kiến thức:</div>",
                                selectionHeader: "<div class='custom-header' style='font-weight: bold;'>Kiến thức đã chọn:</div>"
                            });
                            
                            $('#lop').change (
                                function() {
                                    $.ajax({
                                        type: "GET",
                                        url: "${pageContext.request.contextPath}/DangtoanServlet",
                                        data: {
                                            lop: $(this).val(), 
                                            monhoc: $("#monhoc").val()
                                        },
                                        success: function(data){
                                            $('#kienthuc').empty().multiSelect('refresh');
                                            for(var i = 0; i < data.length; i++) {
                                                $('#kienthuc').multiSelect('addOption', { value: data[i].dangtoan, text: data[i].dangtoanTV}); 
                                            }
                                        }
                                    });                                     
                                }
                            );                    
                        </script>                        
                    </div>

                    <script type="text/javascript">
                        function DoExam() {
                            if (confirm('Bạn đã sẵn sàng làm bài chưa?') == true) {
                                document.getElementById("createExam").submit();
                            }
                        }
                    </script>
                    <input id="btnTaoDe" type="button" value="Tạo đề" onclick="DoExam()"> <!-- css in MemberStyle -->
                </form>   
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>        
    </body>
</html>
