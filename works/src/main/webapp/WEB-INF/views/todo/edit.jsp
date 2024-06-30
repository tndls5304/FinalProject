<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="UTF-8">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>

    </head>

    <body>
        <main>
            <h1> 할일 수정</h1>
        <form action="/todo/edit" method="post">

               <label for="title">글 번호</label>
               <input type="text" id="todoNo" name="todoNo" required>
               <br><br>

              <label for="title">제목:</label>
              <input type="text" id="title" name="title" required>
              <br><br>

              <label for="content">내용:</label>
              <textarea id="content" name="content" required></textarea>
              <br><br>

              <input type="submit" value="작성">
            </form>



        </main>

    </body>

    </html>