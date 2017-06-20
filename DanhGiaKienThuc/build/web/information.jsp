<%-- 
    Document   : infomation
    Created on : Jan 4, 2017, 2:06:53 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/OtherStyle.css" type="text/css">
        <title>GIỚI THIỆU</title>
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
                <h2>Thông tin giới thiệu</h2>
                
                <ol type="I">
                    <li>
                        <a href="" class="content"> Thông tin sản phẩm: </a>
                        <div>
                            <ul style="list-style-type:disc">
                                <li> Sản phẩm là khóa luận tốt nghiệp cử nhân ngành học máy tính với đề tài 
                                "Xây dựng hệ hỗ trợ đánh giá kiến thức Toán THPT bằng hình thức trắc nghiệm"
                                </li>
                                <li> Website là công cụ hỗ trợ các bạn học sinh cấp THPT ôn luyện kiến thức môn Toán. 
                                Đặc biệt là các học sinh lớp 12 chuẩn bị cho kì thi THPT Quốc Gia
                                </li>
                                <li> Với ngân hàng câu hỏi trắc nghiệm phong phú và đa dạng, chúng tôi mong muốn đem đến 
                                một giải pháp tối ưu trong việc ôn tập và hy vọng sản phẩm sẽ là công cụ hỗ trợ đắc lực cho 
                                các bạn học sinh THPT.
                                </li>
                                <li> Chúng tôi luôn mong muốn được lắng nghe những ý kiến đóng góp từ bạn. Hãy gửi ý kiến của 
                                    bạn cho chúng tôi <a target="_blank" href="contact.jsp">tại đây</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="" class="content"> Thông tin tác giả: </a>
                        <div>                            
                            <ul style="list-style-type:disc">                                
                                <p>Nhóm tác giả: Gồm các thành viên đến từ trường Đại Học Công Nghệ Thông Tin - Đại Học Quốc Gia TPHCM</p>
                                <li>Nguyễn Thành Long</li>
                                <li>Nguyễn Lê Vĩnh Đức</li>
                                <p>Giảng viên hướng dẫn: ThS Phạm Thi Vương</p>
                            </ul>
                        </div>
                    </li>
                </ol>
                <script src="${pageContext.request.contextPath}/js/DisplayContent.js" type="text/javascript"></script>                
            </div>
        </div>
        
        <script src="js/autoscroll.js" type="text/javascript"></script>
            
        <jsp:include page="WebInterface/footer.jsp"></jsp:include>
    </body>
</html>
