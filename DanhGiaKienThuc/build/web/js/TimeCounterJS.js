/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function countdown(elementName, minutes, seconds)
{
    var element, endTime, hours, mins, msLeft, time;

    function twoDigits( n )
    {
        return (n <= 9 ? "0" + n : n);
    }

    function updateTimer()
    {
        msLeft = endTime - (+new Date);
        if ( msLeft < 1000 ) {
//            element.innerHTML = "Đã hết thời gian làm bài";
            alert("Đã hết thời gian làm bài");
            document.getElementById("doExam").submit();
        } else {
            if (msLeft <= 300000) {
                document.getElementById("timer").style.color = "red";
            }
            time = new Date( msLeft );
            hours = time.getUTCHours();
            mins = time.getUTCMinutes();
            element.innerHTML = (hours ? hours + ':' + twoDigits( mins ) : mins) + ':' + twoDigits( time.getUTCSeconds());
            setTimeout( updateTimer, time.getUTCMilliseconds() + 500 );
        }
    }
        
    element = document.getElementById(elementName);
    endTime = (+new Date) + 1000 * (60*minutes + seconds) + 500;
    updateTimer();
}

$(function() {
    var time = document.getElementById("examtime").value;
    countdown("timer", time, 0);
});
