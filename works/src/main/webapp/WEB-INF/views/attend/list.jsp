<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <!-- <link rel="stylesheet" href="/css/home/home.css"> --!>
</head>
<body>

   <main>
       <h1>${loginEmpVo.name} ${loginEmpVo.positionName}</h1>
       <h3>${loginEmpVo.deptName}</h3>

       <c:forEach var="attend" items="${attendList}" varStatus="status">
           <c:if test="${status.first || attend.weekNum != prevWeekNum || attend.monthNum != prevMonthNum}">
               <div class="week-group">
                   <h2>${attend.monthNum}월 ${attend.weekNum}주차</h2>
               </div>
           </c:if>
           <div class="attend-record">
               <div>출근시간: ${attend.startTime}  퇴근시간: ${attend.endTime}</div>
           </div>
           <c:set var="prevWeekNum" value="${attend.weekNum}" />
           <c:set var="prevMonthNum" value="${attend.monthNum}" />
       </c:forEach>
   </main>

</body>
</html>