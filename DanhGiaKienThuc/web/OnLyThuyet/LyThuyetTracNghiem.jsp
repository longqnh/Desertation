<%-- 
    Document   : LyThuyetTracNghiem
    Created on : Jan 27, 2017, 2:30:17 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js">
            MathJax.Hub.Config({
                extensions: ["tex2jax.js","TeX/AMSmath.js","TeX/AMSsymbols.js"],
                jax: ["input/TeX", "output/HTML-CSS"],
                tex2jax: {
                    inlineMath: [ ['$','$'], ["\\(","\\)"] ],
                    displayMath: [ ['$$','$$'], ["\\[","\\]"] ],
                    },
                    "HTML-CSS": { availableFonts: ["TeX"] }
            });
        </script>        
        <title>ÔN TẬP LÝ THUYẾT</title>
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
                        <li><a> Toán </a>
                            <ul class="submnu">
                                <li><a href="Lop12.jsp"> Lớp 12 </a></li>
                                <li><a href="Lop11.jsp"> Lớp 11 </a></li>
                                <li><a href="Lop10.jsp"> Lớp 10 </a></li>
                            </ul>
                        </li>

                        <li><a> Lý thuyết trắc nghiệm</a></li>
                    </ul>
                </div>
                
                <script src="${pageContext.request.contextPath}/js/DisplaySubmenu.js" type="text/javascript"></script>
            </div>
            
            <div id="main-right">
                <h2>Lý thuyết trắc nghiệm</h2>
                <ol type="I">
                    <li>
                        <a href="" class="lythuyet"> Các dạng bài tập trắc nghiệm </a>
                        <ol>
                            <li>
                                <a href="" class="content"> Trắc nghiệm đúng - sai: </a>
                                <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            </li>
                            <li>
                                <a href="" class="content"> Trắc nghiệm đa đáp án: </a>
                                <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            </li> 
                            <li>
                                <a href="" class="content"> Trắc nghiệm điền khuyết: </a>
                                <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            </li>
                            <li>
                                <a href="" class="content"> Trắc nghiệm đối chiếu: </a>
                                <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                            </li>                          
                        </ol>
                    </li>
                    <li>
                        <a href="" class="content"> Tính tinh cậy </a>
                        <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                    </li>
                    <li>
                        <a href="" class="content"> Tính giá trị </a>
                        <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                    </li>
                    <li>
                        <a href="" class="content"> Độ khó </a>
                        <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                    </li>
                    <li>
                        <a href="" class="content"> Độ phân cách </a>
                        <p>wlkeglmaglkweajtglkmaelknmlkafm</p>
                    </li>                    
                </ol>
            </div>

            <script src="${pageContext.request.contextPath}/js/DisplayContent.js" type="text/javascript"></script>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/autoscroll.js" type="text/javascript"></script>
 
        <jsp:include page="../WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
