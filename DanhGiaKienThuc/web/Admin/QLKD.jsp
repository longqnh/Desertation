<%-- 
    Document   : QLKD
    Created on : Feb 20, 2017, 11:15:13 PM
    Author     : NTL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="connect.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QUẢN TRỊ KHO ĐỀ THI</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

        <!-- Include one of jTable styles. -->
        <link href="${pageContext.request.contextPath}/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
        <!-- Include jTable script file. -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.jtable.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#TableContainer').jtable({
                    title: 'Danh sách câu hỏi',
                    paging: true, //Enable paging
                    //pageSize: 15, //Set page size (default: 10)
                    //sorting: true, //Enable sorting
                    //defaultSorting: 'id ASC',
                    selecting: true, //Enable selecting
                    multiselect: true, //Allow multiple selecting
                    selectingCheckboxes: true, //Show checkboxes on first column
                    selectOnRowClick: false, //Click row on check box
                    actions: {
                        listAction: '${pageContext.request.contextPath}/QuestionCRUD?action=list&kienthuc=hamso',
                        createAction: '${pageContext.request.contextPath}/QuestionCRUD?action=create',
                        updateAction: '${pageContext.request.contextPath}/QuestionCRUD?action=update',
                        deleteAction: '${pageContext.request.contextPath}/QuestionCRUD?action=delete'
                    },
                    fields: {
                        id: {
                            title: 'ID',
                            key: true,
                            list: true,
                            edit: true,
                            create: true
                        },
                        noidung: {
                            title: 'Nội dung',
                            type: 'textarea',
                            edit: true
                        },
                        dapanA: {
                            title: 'Đáp án A',
                            type: 'text',
                            edit: true
                        },
                        dapanB: {
                            title: 'Đáp án B',
                            type: 'text',
                            edit: true
                        },
                        dapanC: {
                            title: 'Đáp án C',
                            type: 'text',
                            edit: true
                        },
                        dapanD: {
                            title: 'Đáp án D',
                            type: 'text',
                            edit: true
                        },
                        answer: {
                            title: 'Đáp án',
                            type: 'select',
                            options: {'A': 'A', 'B': 'B', 'C': 'C', 'D': 'D'},
                            edit: true
                        },     
                        dangbt: {
                            title: 'Dạng bt',
                            type: 'text',
                            edit: true
                        },
                        level: {
                            title: 'Level',
                            type: 'select',
                            options: {'0': 'nhận biết', '1': 'thông hiểu', '2': 'vận dụng', '3': 'vận dụng cao'},
                            edit: true
                        },
                        hasImage: {
                            title: 'Hình',
                            type: 'text',
                            edit: true
                        }                      
                    }
                });
                
//                $('#TableContainer').jtable('option', 'pageSize', 10);
                $('#TableContainer').jtable('load');
            });
        </script>   
        
        <script type="text/javascript">
            function Ajax() {
                var val = document.getElementById("noidung").value;
                //alert(val);
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState==4 && xhttp.status==200) {
                        document.getElementById("TableContainer").innerHTML=xhttp.responseText;
                    }
                };
                xhttp.open("POST","${pageContext.request.contextPath}/QuestionCRUD?action=list&kienthuc="+val,true);
                $('#TableContainer').jtable('reload');
                xhttp.send();
            }
        </script>
    </head>
    <body>
        <div>
            <select id="noidung" onchange="Ajax()">
                <option value="hamso">Hàm số</option>
                <option value="loga">Lũy thừa - Mũ - Logarit</option>
                <option value="tp">Nguyên hàm - Tích phân</option>
                <option value="sp">Số phức</option>
                <option value="hkg">Hình học không gian</option>
                <option value="oxyz">Oxyz</option>
            </select>
            
            <div id="TableContainer"></div>
        </div>
    </body>
</html>
