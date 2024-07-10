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
                  <div id="name-search">
                      <div><input type="text" id="name-search-keyword" name="nameSearch" placeholder="사원명을 입력해주세요."></div>
                      <div><input type="button" value="검색" onclick="searchByName()"></div>
                  </div>
                  <br>
              </div>

              <table class="attend-table">
                  <thead>
                      <tr>
                          <th>사원명</th>
                          <th>출근 시간</th>
                          <th>퇴근 시간</th>
                          <th>퇴근 시간</th>
                      </tr>
                  </thead>
                  <tbody>
                      <c:forEach var="attend" items="${attendList}">
                          <tr>
                            <td></td>
                            <td>${attend.startTime}</td>
                            <td>${attend.endTime}</td>
                            <td></td>
                          </tr>
                      </c:forEach>
                  </tbody>
              </table>
          </div>
      </main>


    </body>

  </html>