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

        <c:forEach var="attend" items="${attendList}">
            <div class="">
               <div id="">${attend.weekNum}주차</div>
               <div id="">출근시간: ${attend.startTime}</div>
               <div id="">퇴근시간: ${attend.endTime}</div>
            </div>
        </c:forEach>
      </main>

</body>
</html>