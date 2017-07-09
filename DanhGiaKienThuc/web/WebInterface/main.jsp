<%-- 
    Document   : main
    Created on : Jan 3, 2017, 8:18:54 PM
    Author     : NTL
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/MainStyle.css" type="text/css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto'>        
    </head>
    <body>
        <div id="clr"></div>
        
        <div id="main">
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
                <h2>Tin Tức Giáo Dục</h2>
                
		<c:import var="xmlContent" url="http://vnexpress.net/rss/giao-duc.rss"/>
		<x:parse var="doc" xml="${xmlContent}"/>
    		<table class="content-table" id="feed">    
		<tr class="profile_odd">
	    	<td align="center" colspan="2">  
                    <span class="title"> </span>
                <x:forEach var="story" begin="0"     
                    select="$doc/rss/channel/item" varStatus="status">
                    <tr class="profile_even"> 
                        <td><a href="<x:out select="link"/>" target="_blank"> &#x261B; <x:out select="title"/></td>
                </x:forEach>
		</table>
                <c:import var="xmlContent" url="http://tuoitre.vn/rss/tt-giao-duc.rss"/>
		<x:parse var="doc" xml="${xmlContent}"/>
    		<table class="content-table" id="feed">    
		<tr class="profile_odd">
	    	<td align="center" colspan="2">  
                    <span class="title"> </span>
                <x:forEach var="story" begin="0" end ="4"     
                    select="$doc/rss/channel/item" varStatus="status">
                    <tr class="profile_even"> 
                        <td><a href="<x:out select="link"/> " target="_blank"> &#x261B; <x:out select="title"/></td>
                </x:forEach>
		</table>
                
            </div>
        </div>
    </body>
</html>
