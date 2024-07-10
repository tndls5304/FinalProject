<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>

      <link rel="stylesheet" href="/css/attend/allList.css">
    </head>

    <body>

        <nav>
           <div id="right-sidebar">
             <div><button><img src="../resources/img/free-icon-menu-2791777.png" alt=""></button></div>
             <div><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></div>
             <div id="title">게시판</div>
           </div>
           <div id="left-sidebar">
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
             <div><button><img src="../resources/img/free-icon-person-11081570.png" alt=""></button></div>
           </div>
      </nav>

      <main>

          <div id="all-list">
              <div id="employee-infor">
                <form action="searchFromAll" method="get">
                  <div id="name-search">
                      <div>
                          <select id="department-select" name="deptSearch">
                              <option value="">부서 선택</option>
                              <option value="1">인사부</option>
                              <option value="2">총무부</option>
                              <option value="3">개발부</option>
                              <option value="4">영업부</option>
                          </select>
                      </div>
                      <div>
                          <input type="text" id="name-search-keyword" name="nameSearch" placeholder="사원명을 입력해주세요.">
                      </div>
                      <div>
                        <input type="submit" value="검색">
                      </div>
                  </div>
                </form>
                <br>
              </div>

              <table class="attend-table">
                  <thead>
                      <tr>
                          <th>이름</th>
                          <th>직급</th>
                          <th>부서</th>
                          <th>출근 시간</th>
                          <th>퇴근 시간</th>
                      </tr>
                  </thead>
                  <tbody>
                      <c:forEach var="attend" items="${attendList}">
                          <tr>
                            <td>${attend.empName}</td>
                            <td>${attend.positionName}</td>
                            <td>${attend.deptName}</td>
                            <td>${attend.startTime}</td>
                            <td>${attend.endTime}</td>
                          </tr>
                      </c:forEach>
                  </tbody>
              </table>
          </div>
      </main>


    </body>

  </html>