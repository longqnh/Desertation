<%-- 
    Document   : tutorial
    Created on : Jan 4, 2017, 2:06:42 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/OtherStyle.css" type="text/css">
        <title>HƯỚNG DẪN</title>
    </head>
    <body>
        <jsp:include page="WebInterface/header.jsp"></jsp:include>
        
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
                <h2>Hướng dẫn sử dụng</h2>
                
                <iframe src="https://www.youtube.com/embed/60ItHLz5WEA" frameborder="0" allowfullscreen></iframe>
            </div>
        </div>
        
        <script src="js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
