<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   <!-- 제이쿼리-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
   <!-- 공통css-->
        <link rel="stylesheet" href="/css/layout/admin/same.css">
   <!-- 개별 정적파일-->
   <!--달력-->
         <script src='https://cdn.jsdelivr.net/npm/fullcalendar/index.global.min.js'></script>
           <script>
               document.addEventListener('DOMContentLoaded', function() {
                 var calendarEl = document.getElementById('calendar');
                 var calendar = new FullCalendar.Calendar(calendarEl, {
                           initialView: 'dayGridMonth',

                           eventClick:  function(info){
                                         console.log(info)
                                           },
                           customButtons:{
                                myCustomButton:{
                                    text:"일정 추가하기"
                                    },
                                 mySaveButton:{
                                    text:"저장하기"
                                    }
                            }


                 });                                                    //calendar
                     //화면 보여주기
                calendar.render();
                     //내가 원하는작업
                calendar.addEvent({
                        title:"공부",
                         content: '마무리하기',
                        start:'2024-07-03',
                        end:'2024-07-06',
                       })//addEvent
              })//DOMContentLoaded
           </script>

    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

</head>
<body>
        <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

<main>
            <div class="header">
                    <h1>스케줄</h1>
                    <div><h3>지정내역</h3></div>
            </div>

         <div id='calendar'></div>


</main>


</body>
</html>