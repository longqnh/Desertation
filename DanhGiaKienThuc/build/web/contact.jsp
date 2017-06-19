<%-- 
    Document   : contact
    Created on : Jan 4, 2017, 12:07:36 AM
    Author     : NTL
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LIÊN HỆ - GÓP Ý</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        
        <%
            Users users = null;
            String hoten="";
            String email="";
            if (session.getAttribute("user")!=null) {
                users = (Users) session.getAttribute("user");
                hoten = users.getName();
                email = users.getEmail();                
            }
        %>

        <div id="clr"></div>
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
                        <li><a href="index.jsp"> Trang chủ</a></li>                       
                        <li><a> Làm đề thi </a>
                            <ul class="submnu">
                                <li><a href="Thi/MockTest.jsp"> Thi thử </a></li>
                                <li><a href="Thi/Practice.jsp"> Luyện tập </a></li>
                            </ul>
                        </li>
                        <li><a> Lý thuyết</a>
                            <ul class="submnu">
                                <li><a href="OnLyThuyet/Lop12.jsp"> Toán 12 </a></li>                               
                                <li><a href="OnLyThuyet/Lop11.jsp"> Toán 11 </a></li>
                                <li><a href="OnLyThuyet/Lop10.jsp"> Toán 10 </a></li>
                                <li><a href="OnLyThuyet/LyThuyetTracNghiem.jsp"> Lý Thuyết Trắc Nghiệm </a></li>
                            </ul>                            
                        </li>
                        <li><a href="tutorial.jsp"> Hướng dẫn</a></li>
                        <li><a href="information.jsp"> Giới thiệu</a></li>
                        <li><a href="contact.jsp"> Liên hệ - Góp ý</a></li>
                    </ul>
                </div>
                
                <script src="js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>

            <div id="main-right">
                <h2>Liên hệ - Góp ý</h2>
                <p id="thankyou">${requestScope.Thankyou}</p>
                <form action="${pageContext.request.contextPath}/Contact" method="POST">
                    <div class="edit-field">
                        <label>Họ tên</label>   
                        <input type="text" name="hoten" value="<%=hoten%>" required>                   
                    </div>

                    <div class="edit-field">
                        <label>Email</label>  
                        <input type="email" name="email" value="<%=email%>" required>
                    </div>

                    <div class="edit-field">
                        <label>Số điện thoại</label>                        
                        <input type="text" name="sdt">
                    </div>

                    <div class="edit-field">
                        <label>Tiêu đề</label>
                        <input type="text" name="subject" required>
                    </div>

                    <div class="edit-field">
                        <label>Nội dung</label>  
                        <textarea rows="25" name="content" required></textarea>
                    </div>
                    
                    <input id="btnSend" type="submit" value="Gửi">
                </form>
            </div>
        </div>
        
        <script type="text/javascript" src="js/autoscroll.js"></script>
        
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
