<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="/css/notice/empDetail.css">
</head>
<body>

    <%@ include file="/WEB-INF/views/layout/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>

        <div id="list">
            <div id="title">
                <div>번호</div>
                <div>작성자</div>
                <div>제목</div>
                <i class="fa-regular fa-heart fa-lg" style="color:#f005dc" id="like"></i>
            </div>
            <div id="content"></div>
        </div>
    </main>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="/js/notice/empDetail.js"></script>
</body>
</html>
