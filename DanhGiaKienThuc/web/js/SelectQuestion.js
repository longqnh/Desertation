/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('click', '.numberCircle', function (e) {
    var theID = $(this).attr('id');
    $('html, body').animate({
        scrollTop: $('#Q' + theID).offset().top
    }, 1000);
    return false;
});
