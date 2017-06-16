<%-- 
    Document   : FinishExam
    Created on : Feb 20, 2017, 10:30:48 PM
    Author     : NTL
--%>

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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css">
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
                
                <script src="<%=request.getContextPath()%>/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>

            <div id="main-right">
                <% 
                    String made = request.getParameter("made");
                    QuanLyDeThiDAO qldeThiDAO = new QuanLyDeThiDAO();
                    float Diem = qldeThiDAO.GetDiem(made);
                                       
                    List<Thongke> noidungYeu = new ArrayList<>();
                    int []socaudung = new int [5];
                    int []socau = new int [5];
                    
                    ThongkeDAO thongkeDAO = new ThongkeDAO();
                    List<Thongke> thongkeND = thongkeDAO.thongkenoidung(made);
                %>
                
                <h2>Kết quả thi - Mã đề: <%=made%></h2>
                <h3 id="result">Bạn đã hoàn thành bài thi với điểm số <%=Diem%>/10</h3>
                
                <ol type="1">
                <div id="thongke">
                    <li id="dokho">Theo dạng toán</li>
                    <div id="thongke-dangtoan">
                        <table style="width:96%" class="table-thongke">
                            <tr>
                                <th rowspan="2">Dạng toán</th>
                                <th colspan="4">Số câu đúng</th>
                                <th rowspan="2">Tổng số câu</th>    
                            </tr>
                            <tr>
                                <td>Nhận biết</td>
                                <td>Thông hiểu</td>
                                <td>Vận dụng</td>
                                <td>Vận dụng cao</td>
                            </tr>
                            <%
                                for (Thongke nD : thongkeND) { 
                                    List<Thongke> thongkeDK = thongkeDAO.thongkedokho(made, nD.getMadangtoan()); 
                                    int i=0;    
                            %>
                                    <tr>
                                        <td><%=nD.getDangtoan()%></td>
                                        <% 
                                            for (Thongke DK : thongkeDK) { 
                                                socau[i]+=DK.getSocau();
                                                socaudung[i++]+=DK.getSocaudung();
                                        %>
                                                <td><%=DK.getSocaudung()%>/<%=DK.getSocau()%></td>
                                        <%  }         
                                            socau[i]+=nD.getSocau();
                                            socaudung[i++]+=nD.getSocaudung(); 
                                        %>
                                        <td><%=nD.getSocaudung()%>/<%=nD.getSocau()%></td>
                                    </tr>
                            <% } %>
                            <tr>
                                <td>Tổng</td>
                                <%
                                    for (int i=0; i<socau.length; i++) {
                                %>
                                    <td><%=socaudung[i]%>/<%=socau[i]%></td> 
                                <%  } %>
                            </tr>
                        </table>
                    </div>
                    
                    <li id="noidung">Theo dạng bài tập</li>   
                    <div id="thongke-dangbt">
                        <table class="table-thongke">
                            <tr>
                                <th>Dạng bài tập</th>
                                <th>Số câu đúng</th>
                            </tr>
                            <%
                                for (Thongke nD : thongkeND) { 
                                    List<Thongke> thongkeBT = thongkeDAO.thongkedangbt(made, nD.getMadangtoan()); 
                                    for (Thongke BT : thongkeBT) { 
                                        if (BT.getSocaudung() <= BT.getSocau()/2) {
                                            noidungYeu.add(BT);
                                        }   %>
                                    <tr>
                                        <td><%=BT.getDangtoan()%></td>
                                        <td><%=BT.getSocaudung()%>/<%=BT.getSocau()%></td>
                                    </tr>
                            <% } } %>
                        </table>                    
                    </div>
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
                
                <form class="_btn" target="_blank" action="${pageContext.request.contextPath}/Thi/XemDapAn.jsp" method="GET">
                    <input type="button" class="btnXemDA" id="showTK" value="Thống kê">
                    <input type="button" class="btnXemDA" id="showNX" value="Nhận xét">
                    <input type="text" name="made" value="<%=made%>" hidden="">
                    <input type="submit" class="btnXemDA" value="Xem đáp án">
                </form>
                    
                <script type="text/javascript">
                    $(function() {
                        $("#thongke-dangbt").hide();
                        $("#thongke-dangtoan").hide();
                        
                        $("#showTK").click(function() {
                            $("#nhanxet").hide();
                            $("#thongke").slideToggle();
                        });
                        
                        $("#showNX").click(function() {
                            $("#thongke").hide();
                            $("#nhanxet").slideToggle(); 
                        });
                        
                        $("#dokho").click(function() {
                            $("#thongke-dangbt").hide();
                            $("#thongke-dangtoan").slideToggle();
                        });
                        
                        $("#noidung").click(function() {
                            $("#thongke-dangtoan").hide();
                            $("#thongke-dangbt").slideToggle();
                        });
                    });
                </script>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>

    </body>
</html>
