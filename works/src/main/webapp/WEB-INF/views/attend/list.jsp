<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
           <i class="fa-solid fa-circle-xmark close-button" onclick="window.location.href='http://127.0.0.1:8080/home';"></i>
         </div>
         <c:forEach var="attend" items="${attendList}" varStatus="status">
           <c:if test="${status.first || attend.weekNum != prevWeekNum || attend.monthNum != prevMonthNum}">
             <div class="week-group">
               <p>✔${attend.monthNum}월 ${attend.weekNum}주차</p>
             </div>
           </c:if>
           <table>
             <thead>
               <tr>
                 <th>출근시간</th>
                 <th>퇴근시간</th>
                 <th>총 시간</th>
               </tr>
             </thead>
             <tbody>
               <tr>
                 <td>${attend.startTime}</td>
                 <td>${attend.endTime}</td>
                 <td><b>${attend.totalWork}</b></td>
               </tr>
             </tbody>
           </table>
           <c:set var="prevWeekNum" value="${attend.weekNum}" />
           <c:set var="prevMonthNum" value="${attend.monthNum}" />
         </c:forEach>
       </div>
   </main>


</body>
</html>