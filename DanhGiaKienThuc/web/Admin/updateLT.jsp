<%-- 
    Document   : updateLT
    Created on : Mar 4, 2017, 1:06:12 AM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
        <script src="//cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>
        <%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>     
        <title>CẬP NHẬT LÝ THUYẾT</title>
    </head>
    <body>                
        <form method="POST">    
            <textarea id="editor" name="editor">DEMO CKEDITOR</textarea>
            <input type="submit" value="Lưu">

            <!--<div id="trackingDiv" ></div>-->

            <script type="text/javascript">
                config = {};
                config.entities_latin = false;
                config.height = 200;
                config.extraPlugins = 'save';
                CKEDITOR.replace('editor',config);

                timer = setInterval(updateDiv,100);
                function updateDiv(){
                    var editorText = CKEDITOR.instances.editor.getData();
                    $('#trackingDiv').html(editorText);
                }
            </script>
        </form>

        <ckeditor:replace replace="editor" basePath="/ckeditor/"/>
    </body>
</html>
