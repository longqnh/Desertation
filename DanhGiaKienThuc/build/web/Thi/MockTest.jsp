<%-- 
    Document   : MockTest
    Created on : Jan 25, 2017, 1:06:00 AM
    Author     : NTL
--%>

<%@page import="model.Lop"%>
<%@page import="java.util.List"%>
<%@page import="dao.LopDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>THI THỬ</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
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
                        <label>Lớp đang học: </label>
                        <select name="lop" id="lop" required>
                            <%
                                LopDAO lopDAO = new LopDAO(); 
                                List<Lop> dsLop = lopDAO.GetAllLop(); 
                                for (Lop lop: dsLop) { 
                                    if (lop.getMalop()==users.getLop()) { %>
                                        <option value="<%=lop.getMalop()%>" selected=""> <%=lop.getTenlop()%> </option>                                  
                            <%      } else { %>
                                        <option disabled=""> <%=lop.getTenlop()%> </option>
                            <%      }
                                }   
                            %>
                        </select>
                    </div>
                    
                    <div class="search-field">
                        <label>Danh sách đề thi: </label>
                        <select name="dethi" id="lop" required>
                            <option value="" disabled selected>Chọn đề thi</option>
                            <option value="hk1"> Học kỳ 1 </option> 
                            <option value="hk2"> Học kỳ 2 </option> 
                            <option value="canam"> Cả năm </option>
                            <%
                            if (users.getLop()==12) { %>
                                <option value="thptqg"> THPT Quốc Gia </option> 
                            <% } %>
                        </select>
                        <input type="text" hidden="" name="dokho" value="1">
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
