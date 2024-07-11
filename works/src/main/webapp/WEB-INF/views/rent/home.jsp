<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rent</title>
        <link rel="stylesheet" href="/css/rent/home.css">
    </head>
    <body>

        <%@ include file="/WEB-INF/views/layout/rent/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/rent/aside.jsp" %>

        <div id="main">
            <!--달 옆으로 넘기기-->
            <div class="calendar_title">
                <button class="prev">&#60;</button>
                <span class="current-year-month"></span>
                <button class="next">&#62;</button>
                <button class="back-to-today">Today</button>
            </div>
            
            <!--달 나오기-->
            <section class="content-right">
                <div class="day-of-week">
                    <div class="dayHeader , sun">Sun</div>
                    <div class="dayHeader">Mon</div>
                    <div class="dayHeader">Tue</div>
                    <div class="dayHeader">Wed</div>
                    <div class="dayHeader">Thu</div>
                    <div class="dayHeader">Fri</div>
                    <div class="dayHeader , sat">Sat</div>
                </div>
                <div class="calendar-body"></div>
                <div style="display: none;" id="write">
                    <div>예약하기</div>
                    <div>
                    <div>차량예약</div>
                    <select name="" id="carOption">
                       <!--여기도 차량예약 옵션 나올거임-->
                    </select>
                    <input type="date" id="startDate">
                    <input type="date" id="endDate">
                    <textarea name="" id="reason" placeholder="사유"></textarea>
                    <button id="sendButton" onclick="carRent()">예약하기</button>
                </div>
                <div>
                    <div>회의실 예약</div>
                    <select name="" id="meetingOption">
                       <!--여기 옵션 나올거임-->
                    </select>
                    <input type="date" name="" id="rentDate">
                    <input type="time" id="startTime">
                    <input type="time" id="endTime">
                    <button id="sendButton2" onclick="meetingRent()">예약하기</button>
                    <button id="closeButton">닫기</button>
                    </div>
                </div>
            </section>
        </div> 
    </main>

    </body>
    </html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script src="/js/rent/home.js"></script>
    <script src="/js/rent/main.js"></script>