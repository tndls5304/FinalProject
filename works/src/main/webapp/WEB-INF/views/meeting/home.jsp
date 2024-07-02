<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
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
            </div>
            
            <!--달 나오기-->
            <section class="content-right">
                <div class="day-of-week">
                    <div class="dayHeader-sun">Sun</div>
                    <div class="dayHeader">Mon</div>
                    <div class="dayHeader">Tue</div>
                    <div class="dayHeader">Wed</div>
                    <div class="dayHeader">Thu</div>
                    <div class="dayHeader">Fri</div>
                    <div class="dayHeader">Sat</div>
                </div>
                <div class="calendar-body"></div>
            </section>
        </div>
            
    </main>

    </body>
    </html>

    <script src="/js/rent/home.js"></script>