<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Messenger</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
      <link rel="stylesheet" href="/css/attend/invalidAccess.css">
    </head>

    <body>

      <nav>
           <div id="right-sidebar">
             <div><a href="http://127.0.0.1:8080/home"><button><img src="/img/icon/board.png" alt=""></button></a></div>
             <div><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></div>
             <div id="title">게시판</div>
           </div>
           <div id="left-sidebar">
           <div><a href=""><img src="/img/icon/chatting.png" alt=""></a></div>
                <!-- 근태관리(인사부) -->
                <div><a href="http://127.0.0.1:8080/attend/allList"><img src="/img/icon/attendMange.png" alt=""></a></div>
                <div><a href=""><img src="/img/icon/organization-chart.png" alt=""></a></div>
                <div><a href=""><img src="/img/icon/customer.png" alt=""></a></div>
                <!-- 쪽지 -->
                <div><a href="http://127.0.0.1:8080/messenger/all"><img src="/img/icon/paper.png" alt=""></a></div>
                <div><a href=""><img src="/img/icon/reserved.png" alt=""></a></div>
                <div><a href="http://127.0.0.1:8080/board/list"><img src="/img/icon/board.png" alt=""></a></div>
                <div><a href=""><img src="/img/icon/calendar.png" alt=""></a></div>
                <div><a href="http://127.0.0.1:8080/todo/home"><img src="/img/icon/todo.png" alt=""></a></div>
                <div><button><img src="/img/icon/user.png" alt=""></button></div>
          </div>
      </nav>

      <main>
        <div id="container">
          <i class="fa-solid fa-triangle-exclamation icon"></i>
          <h1>잘못된 접근입니다.</h1>
          <br>
          <p>인사부만 접근할 수 있는 페이지입니다.</p>
        </div>
      </main>
    </body>

    </html>