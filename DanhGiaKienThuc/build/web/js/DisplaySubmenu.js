/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(window).load(function() {
//    $('#main-left-bottom > ul > li:first a').next().slideToggle();

    $('#main-left-bottom > ul > li > a').click(function(){
        if ($(this).attr('class') != 'active'){
            $('#main-left-bottom ul li ul').slideUp();
            $(this).next().slideToggle();
            $('#main-left-bottom ul li a').removeClass('active');
            $(this).addClass('active');
        }
    });
});