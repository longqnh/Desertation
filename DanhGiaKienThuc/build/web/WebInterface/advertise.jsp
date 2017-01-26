<%-- 
    Document   : advertise
    Created on : Jan 3, 2017, 10:49:23 PM
    Author     : NTL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/AdsStyle.css" type="text/css">
        <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
        <!-- jQuery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
        <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\x3C/script>')</script>                
    </head>
    <body>
        <!-- Ads -->
        <div id="ads">
            <section class="slider">
                <div class="flexslider">
                    <ul class="slides">
                        <li>
                            <img src="images/slider1.jpg" />
                        </li>
                        <li>
                            <img src="images/slider2.jpg" />
                        </li>
                        <li>
                            <img src="images/slider3.jpg" />
                        </li>
                        <li>
                            <img src="images/slider4.png" />
                        </li>
                    </ul>
                </div>
            </section>
        </div>

        <!-- FlexSlider -->
        <script defer src="js/jquery.flexslider.js"></script>

        <script type="text/javascript">
            $(function(){
                SyntaxHighlighter.all();
            });
            $(window).load(function(){
                $('.flexslider').flexslider({
                    animation: "slide",
                    start: function(slider){
                      $('body').removeClass('loading');
                    }
                });
            });
        </script>
    </body>
</html>
