/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#confirm_password').on('keyup', function () {
    if ($(this).val() != "") {
        if ($(this).val() == $('#password').val()) {
            $('#confirm_password-result').html('Mật khẩu trùng khớp').css('color', 'green');
        } else 
            $('#confirm_password-result').html('Mật khẩu không trùng khớp').css('color', 'red');
    }
    else
        $('#confirm_password-result').html('');
});  
