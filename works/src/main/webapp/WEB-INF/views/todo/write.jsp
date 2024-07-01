<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="UTF-8">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>
      <link rel="stylesheet" href="/css/todo/todoWrite.css">
    </head>

    <body>

      <%@ include file="/WEB-INF/views/layout/todo/nav.jsp" %>


        <main>
          <%@ include file="/WEB-INF/views/layout/todo/aside.jsp" %>


            <h1> 할일 작성</h1>

            <form action="/todo/write" method="post">

              <label for="title">제목:</label>
              <input type="text" id="title" name="title" required>
              <br><br>

              <label for="content">내용:</label>
              <textarea id="content" name="content" required></textarea>
              <br><br>

              <label for="endDate">기한:</label>
              <button type="button" onclick="setEndDate(0)">오늘</button>
              <button type="button" onclick="setEndDate(1)">내일</button>
              <button type="button" onclick="setEndDate(7)">다음주</button>
              <br><br>

              <label for="requesterName">요청자 이름:</label>
              <input type="text" id="requesterName" name="requesterName" value="송예린" readonly>
              <br><br>

              <label for="manager">담당자:</label>
              <input type="text" id="manager" name="manager" required>
              <button type="button" onclick="openAddressBook()">주소록</button>
              <br><br>

              <input type="submit" value="작성">
            </form>





        </main>

    </body>

    </html>