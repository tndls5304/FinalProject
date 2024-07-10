<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>

      <link rel="stylesheet" href="/css/attend/list.css">
      <!-- fontAwesome을 사용하기 위한 코드이다. -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>

      <main>
        <div id="myAttend-list">
          <div id="employee-infor">
            <!-- <img src="/img/profile/${loginEmpVo.profile}" alt="Profile Picture" id="profile-picture"> -->
            <div id="employee-name">${loginEmpVo.name} ${loginEmpVo.positionName}</div><br>
            <!-- fontAwesome에서 가지고 온 이모티콘이다. 누르면 홈페이지로 넘어가게 해두었다!!!!!!!!! -->
            <!-- **********상황에 따라 링크 바꾸기  -->
            <!-- <i class="fa-solid fa-circle-xmark close-button" onclick="window.location.href='http://localhost:8080/home';"></i> -->
            <i class="fa-solid fa-circle-xmark close-button"
              onclick="window.location.href='http://127.0.0.1:8080/home';"></i>
            <br>
            <div id="date-search">
                 <div><input type="text" id="date-search-keyword" name="dateSearch" placeholder="날짜를 입력해주세요. 예) 07"></div>
                 <div><input type="button" value="검색" onclick="searchByDate()"></div>
            </div>
            <br>
          </div>
          <c:forEach var="attend" items="${attendList}" varStatus="status">
            <c:if test="${status.first || attend.weekNum != prevWeekNum || attend.monthNum != prevMonthNum}">
              <div class="week-group">
                <p>${attend.monthNum}월 ${attend.weekNum}주차</p>
                <table>
                  <thead>
                    <tr>
                      <th>출근시간</th>
                      <th>퇴근시간</th>
                      <th>총 시간</th>
                    </tr>
                  </thead>
                  <tbody>
            </c:if>
            <tr>
              <td>${attend.startTime}</td>
              <td>${attend.endTime}</td>
              <td><b>${attend.totalWork}</b></td>
            </tr>

            <!-- 현재 항목이 리스트의 마지막 항목이거나, 다음 항목의 주차 또는 월이 현재 항목과 다를 때 -> 테이블을 닫는다. -->
            <!-- status.last : 현재 항목이 리스트의 마지막 항목인지 확인한다. -->
            <!-- 마지막 항목 or 현재 항목 주차와 다음 항목 주차가 다른가 or 현재 항목 월과 다음 항목 월이 다른가 -->
            <c:if test="${status.last || attend.weekNum != attendList[status.index + 1].weekNum || attend.monthNum != attendList[status.index + 1].monthNum}">
              </tbody>
              </table>
              <!-- 해당 월.주 테이블 다 닫고 테이블간 띄워주기 -->
              <br>
        </div>
        </c:if>

        <c:set var="prevWeekNum" value="${attend.weekNum}" />
        <c:set var="prevMonthNum" value="${attend.monthNum}" />
        </c:forEach>
        </div>
      </main>


    </body>

    </html>

     <!-- jquery 넣기 -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

     <script>
          // 검색기능을 위한 자바스크립트 함수
          function searchByDate() {
          	    var keyword = document.getElementById("date-search-keyword").value;

          	    // 새로운 form 생성
          	    var searchForm = document.createElement("form");
          	    searchForm.method = "get"; //form 전송 방식 - GET
          	    searchForm.action = "/attend/search";

          	    // 검색을 하기 위해 input 요소 생성
          	    var input = document.createElement("input");
          	    input.type = "hidden"; //화면에 보이지 않도록 hidden 설정
          	    input.name = "dateSearch"; //Mapper에 설정한 값으로 name 지정
          	    input.value = keyword; //input 값에 keyword 설정

          	    searchForm.appendChild(input);

          	    document.body.appendChild(searchForm);
          	    searchForm.submit();
          }

     </script>
