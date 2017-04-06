<%-- 
    Document   : FinishExam
    Created on : Feb 20, 2017, 10:30:48 PM
    Author     : NTL
--%>

<%@page import="dao.DanhgiaDAO"%>
<%@page import="java.util.ArrayList"%>
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>   
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
                    String made = request.getParameter("made");
                    QuanLyDeThiDAO qldeThiDAO = new QuanLyDeThiDAO();
                    float Diem = qldeThiDAO.GetDiem(made);
                                       
                    List<Thongke> noidungYeu = new ArrayList<>();
                    ThongkeDAO thongkeDAO = new ThongkeDAO();
                    List<Thongke> thongkeND = thongkeDAO.thongkenoidung(made);
                %>
                
                <h2>Kết quả thi - Mã đề: <%=made%></h2>
                <h3 id="result">Bạn đã hoàn thành bài thi với điểm số <%=Diem%>/10</h3>
                
                <ol type="1">
                <div id="thongke">
                    <li id="dokho">Theo mức độ</li>
                <%
                    for (int i=0; i < thongkeND.size(); i++) {
                        Thongke nD = (Thongke) thongkeND.get(i); 
                        List<Thongke> thongkeDK = thongkeDAO.thongkedokho(made, nD.getMadangtoan()); %>
                        <ul class="content_dokho">
                            <p>Đúng <%=nD.getSocaudung()%>/<%=nD.getSocau()%> câu <%=nD.getDangtoan()%> gồm:</p>
                    <%
                        for (int j = 0; j < thongkeDK.size(); j++) { 
                            Thongke DK = (Thongke) thongkeDK.get(j); %>
                            <li><%=DK.getSocaudung()%>/<%=DK.getSocau()%> câu mức độ <%=DK.getMucdo()%></li>
                <%
                        }
                %>
                        </ul>
                <%
                    }
                %>
                    <li id="noidung">Theo nội dung</li>
                <%
                    for (int i=0; i < thongkeND.size(); i++) {
                        Thongke nD = (Thongke) thongkeND.get(i); 
                        List<Thongke> thongkeBT = thongkeDAO.thongkedangbt(made, nD.getMadangtoan()); %>
                        <ul class="content_noidung">
                            <p>Đúng <%=nD.getSocaudung()%>/<%=nD.getSocau()%> câu <%=nD.getDangtoan()%> gồm:</p>
                    <%
                        for (int j = 0; j < thongkeBT.size(); j++) { 
                            Thongke nd = (Thongke) thongkeBT.get(j); 
                            if (nd.getSocaudung() <= nd.getSocau()/2) {
                                noidungYeu.add(nd);
                            }
                    %>
                            <li><%=nd.getSocaudung()%>/<%=nd.getSocau()%> dạng <%=nd.getDangtoan()%></li>
                <%
                        }
                %>
                        </ul>
                <%
                    }
                %>                    
                </div>
                
                <div id="nhanxet">
                    <%
                        if (noidungYeu.isEmpty()) { %>
                            <p>Kiến thức của bạn khá tốt, hãy tiếp tục phát huy</p>
                    <%  } else { %>
                            <p>Các kiến thức làm sai nhiều:</p>
                            <ul class="noidungyeu">
                    <%      for (int i = 0; i < noidungYeu.size(); i++) { 
                                Thongke ndY = noidungYeu.get(i); %>
                                <li><%=ndY.getDangtoan()%></li>
                    <%      }  %>
                            </ul>
                    <%  }  %>
                </div>
                </ol>
                
                <form class="_btn" target="_blank" action="XemDapAn.jsp" method="GET">
                    <input type="button" class="btnXemDA" id="showTK" value="Thống kê">
                    <input type="button" class="btnXemDA" id="showNX" value="Nhận xét">
                    <input type="text" name="made" value="<%=made%>" hidden="">
                    <input type="submit" class="btnXemDA" value="Xem đáp án">
                </form>
                    
                <script type="text/javascript">
                    $(function() {
                        $("#showTK").click(function() {
                            $("#nhanxet").hide();
                            $("#thongke").slideToggle();
                        });
                        
                        $("#showNX").click(function() {
                            $("#thongke").hide();
                            $("#nhanxet").slideToggle(); 
                        });
                        
                        $("#dokho").click(function() {
                            $(".content_noidung").hide();
                            $(".content_dokho").slideToggle();
                        });
                        
                        $("#noidung").click(function() {
                            $(".content_dokho").hide();
                            $(".content_noidung").slideToggle();
                        });
                    });
                </script>
            </div>
        </div>
        
        <script src="../js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>

    </body>
</html>
