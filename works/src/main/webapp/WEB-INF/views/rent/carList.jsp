<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarList</title>
    <link rel="stylesheet" href="/css/rent/carList.css">
</head>
<body>
    
        <%@ include file="/WEB-INF/views/layout/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/rent/aside.jsp" %>
        
        <div id="main">
            <div id="carList">
                <div>실시간 차량예약 현황</div>
                <div id="carContent">

                </div>
                <div style="display: none;" id="detail">

                </div>
            </div>
            <div style="display: none;" id="loginMember">${loginEmpVo.no}</div>
            <div id="carImg">이미지 들어올거임</div>
        </div>
    </main>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/js/rent/carList.js"></script>

