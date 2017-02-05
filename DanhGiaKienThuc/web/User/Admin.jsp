<%-- 
    Document   : Admin
    Created on : Feb 2, 2017, 12:39:58 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="../css/FooterStyle.css" type="text/css">
    </head>
    <body>
        <jsp:include page="../WebInterface/header.jsp"></jsp:include>
        
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
                        <li><a> Thông tin tài khoản </a>
                            <ul class="submnu">
                                <li><a href="#"> Sửa thông tin </a></li>
                                <li><a href="#"> Đổi mật khẩu </a></li>
                            </ul>
                        </li>

                        <li><a> Quản lý học tập</a></li>
                    </ul>
                </div>
                
                <script src="../js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">

            </div>

            <script src="../js/DisplayContent.js" type="text/javascript"></script>
        </div>
        
        <script src="../js/autoscroll.js" type="text/javascript"></script>
        
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
