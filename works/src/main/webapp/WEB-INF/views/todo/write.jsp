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

      <main>

        <h3>할 일 쓰기</h3>

        <form action="/todo/write" method="post">

          <label for="todo-title">제목:</label>
          <input type="text" id="title" name="title" placeholder="제목을 입력해주세요." required>
          <br><br>

          <label for="content">내용:</label>
          <textarea id="content" name="content" placeholder="내용을 입력해주세요." required></textarea>
          <br><br>


          <label for="parNo" data-name="todoEmpNo">요청자:${loginEmpVo.name}</label>
          <br><br>

          <!-- 수정된 부분: 체크박스를 사용하여 여러 명의 담당자를 선택 -->
          <!-- 이버튼의 타입을 button으로 바꿔줘야 함
            그냥 버튼으로하면 제출이 된다. -->
          <button type="button" onclick="clickBtn()">담당자 추가</button>
          <div id="managerList" style="display: none;">
            <label for="todoManager">담당자</label>
            <c:forEach var="emp" items="${empList}">
              <input type="checkbox" name="todoManagerList" value="${emp.no}"> ${emp.name}&nbsp;&nbsp;${emp.deptNo}<br>
            </c:forEach>
          </div>
          <br><br>


          <label for="endDate">기한</label>
          <input type="hidden" id="endDate" name="endDate">
          <button type="button" onclick="setEndDate('today')">오늘</button>
          <button type="button" onclick="setEndDate('tomorrow')">내일</button>
          <button type="button" onclick="setEndDate('nextWeek')">다음주</button>
          <br><br>

          <input type="submit" value="작성">
        </form>





      </main>

    </body>

    </html>

    <script src=" /js/todo/write.js">
    </script>