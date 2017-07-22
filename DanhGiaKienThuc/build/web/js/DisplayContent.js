/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('.lythuyet').next('ol').slideToggle();
    $('.lythuyet').click(function(){   
//        $('.lythuyet').next('ol').slideUp();
        $(this).next('ol').slideToggle(); 
        return false;
    });
    
    $('.content').nextAll('div').hide();
//    $('.content').next('div').slideToggle();
    $('.content').click(function(){ 
//        $('.content').next('div').slideUp();
        if ($(this).is(":visible") === true ) {
            $(this).next('div').slideToggle(); 
        } else {
            $(this).next('div').hide();
        }
        return false;
    });    
});
