<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>meetingList</title>
    <link rel="stylesheet" href="/css/rent/meetingList.css">
</head>
<body>
    
    <%@ include file="/WEB-INF/views/layout/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/rent/aside.jsp" %>
        
        <div id="main">
            <div id="meetingList">
                <div>실시간 회의실 현황</div>
                <div id="meetingContent">

                </div>
                <div style="display: none;" id="detail">

                </div>
            </div>
            <div style="display: none;" id="loginMember">${loginEmpVo.no}</div>
            <div id="meetingImg">이미지 들어올거임</div>
        </div>
    </main>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="/js/rent/meetingList.js"></script>

