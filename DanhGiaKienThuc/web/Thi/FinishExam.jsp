<%-- 
    Document   : FinishExam
    Created on : Feb 20, 2017, 10:30:48 PM
    Author     : NTL
--%>

<%@page import="dao.ThongkeDAO"%>
<%@page import="dao.QuanLyDeThiDAO"%>
<%@page import="model.Users"%>
<%@page import="model.Thongke"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KẾT QUẢ THI</title>
        <link rel="stylesheet" href="../css/HeaderStyle.css">
        <link rel="stylesheet" href="../css/FooterStyle.css">
        <link rel="stylesheet" href="../css/OtherStyle.css">
    </head>
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>
        
        <%
            Users users = null;
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
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
                <% 
//                    float Diem = (float) session.getAttribute("DiemThi");
                    String made = request.getParameter("made");
                    QuanLyDeThiDAO qldeThiDAO = new QuanLyDeThiDAO();
                    float Diem = qldeThiDAO.GetDiem(made);
                    
                    ThongkeDAO thongkeDAO = new ThongkeDAO();
                    List<Thongke> list = thongkeDAO.thongketheodokho(made);
                %>
                
                <h2>Kết quả thi - Mã đề: <%=made%></h2>
                <h3>Bạn được <%=Diem%>/10 điểm</h3>
                
                <%
                    for (int i=0; i<list.size(); i++) { 
                        Thongke tk = (Thongke) list.get(i); %>
                        <p>Đúng <%=tk.getSocaudung()%>/<%=tk.getSocau()%> câu mức độ <%=tk.getMucdo()%> </p>
                <%
                    }
                %>
                <form target="_blank" action="XemDapAn.jsp" method="GET">
                    <input type="text" name="madethi" value="<%=made%>" hidden="">
                    <input type="submit" id="btnXemDA" value="Xem đáp án">
                </form>
            </div>
        </div>
        
        <script src="../js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>

    </body>
</html>
