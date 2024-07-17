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

      <%@ include file="/WEB-INF/views/layout/attend/nav.jsp" %>

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