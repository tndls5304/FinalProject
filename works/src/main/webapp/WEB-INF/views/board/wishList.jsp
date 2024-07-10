<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/board/wishList.css">
</head>
<body>
    
    <%@ include file="/WEB-INF/views/layout/board/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>

        <div id="main">
            <div id="title">
                <div>번호</div>
                <div>제목</div>
                <div>날짜</div>
                <div>조회수</div>
            </div>
            <div id="content">
                <!--내용 들어올거임-->
            </div>
        </div>

    </main>

</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/js/board/wishList.js"></script>