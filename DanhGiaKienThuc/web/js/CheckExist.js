/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// CHECK USERNAME
$(document).ready(function () {
    var x_timer;
    $("#username").keyup(function (e) {
        clearTimeout(x_timer);
        var user_name = $(this).val();
        x_timer = setTimeout(function () {
            check_username_ajax(user_name);
        }, 1000);
    });

    function check_username_ajax(username) {
        $("#user-result").html('<img src="images/ajax-loader.gif" />');
        $.post('CheckUsernameServlet', {'username': username}, function (data) {
            $("#user-result").html(data);
        });
    }
});

// CHECK EMAIL
$(document).ready(function () {
    var x_timer;
    $("#email").keyup(function (e) {
        clearTimeout(x_timer);
        var email = $(this).val();
        x_timer = setTimeout(function () {
            check_email_ajax(email);
        }, 1000);
    });

    function check_email_ajax(email) {
        $("#email-result").html('<img src="images/ajax-loader.gif" />');
        $.post('CheckEmailServlet', {'email': email}, function (data) {
            $("#email-result").html(data);
        });
    }
});