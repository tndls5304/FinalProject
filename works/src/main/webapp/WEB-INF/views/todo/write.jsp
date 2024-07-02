<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="UTF-8">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>
      <link rel="stylesheet" href="/css/todo/todoWrite.css">
      <script defer src="/js/todo/write.js"></script>
    </head>

    <body>

      <main>

        <h3> 할일 작성</h3>

        <form action="/todo/write" method="post">

          <label for="title">제목:</label>
          <input type="text" id="title" name="title" required>
          <br><br>

          <label for="content">내용:</label>
          <textarea id="content" name="content" required></textarea>
          <br><br>



          <br><br>
          <label for="requesterName" data-name="todoEmpNo">담당자:${loginEmpVo.name}</label>

          <br><br>


          <label for="receiver-select">참여자 선택:</label>
          <select id="receiver-select" onchange="addReceiver()">
            <option value="">사원 선택</option>
            <c:forEach var="employee" items="${empList}">
              <option value="${employee.no}" data-name="${employee.name}">${employee.name} ${employee.positionNo}
                ${employee.deptNo}</option>
            </c:forEach>
          </select>
          <input type="hidden" id="todoManagerNo" name="todoManagerNo">
          <div id="selected-todoManager">
            <!-- 선택된 참여자가 여기에 표시됩니다 -->
          </div>
          <br><br>

          <!-- 위의 기한버튼을 누르면 날짜가 여기로 들어와서
           폼으로 날짜 데이터를 제출! -->
          <input type="hidden" id="endDate" name="endDate">

          <input type="submit" value="작성">
        </form>





      </main>

    </body>

    </html>