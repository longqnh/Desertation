<%-- 
    Document   : QLTK
    Created on : Feb 20, 2017, 11:15:06 PM
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
        <title>QUẢN TRỊ CÁC TÀI KHOẢN</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OtherStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/HeaderStyle.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FooterStyle.css" type="text/css">
<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberStyle.css" type="text/css">-->
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
                    title: 'Danh sách tài khoản',
                    paging: true, //Enable paging
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    defaultSorting: 'id ASC',
                    selecting: true, //Enable selecting
                    multiselect: true, //Allow multiple selecting
                    selectingCheckboxes: true, //Show checkboxes on first column
                    selectOnRowClick: false, //Click row on check box
                    actions: {
                        listAction: '${pageContext.request.contextPath}/CRUDController?action=list',
                        createAction: '${pageContext.request.contextPath}/CRUDController?action=create',
                        updateAction: '${pageContext.request.contextPath}/CRUDController?action=update',
                        deleteAction: '${pageContext.request.contextPath}/CRUDController?action=delete'
                    },
                    fields: {
                        username: {
                            title: 'Username',
                            width: '30%',
                            key: true,
                            list: true,
                            edit: true,
                            create: true
                        },
                        password: {
                            title: 'Password',
                            width: '30%',
                            type: 'text',
                            edit: true
                        },
                        name: {
                            title: 'Họ tên',
                            width: '30%',
                            type: 'text',
                            edit: true
                        },
                        email: {
                            title: 'Email',
                            width: '30%',
                            type: 'text',
                            edit: true
                        }                     
                    }
                });
                $('#TableContainer').jtable('load');
            });
        </script>
    </head>
    <body>
        <div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
            <div id="TableContainer"></div>
        </div>
    </body>
</html>
