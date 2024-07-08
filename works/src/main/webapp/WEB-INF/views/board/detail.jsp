<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/board/detail.css">
</head>
<body>

    <%@ include file="/WEB-INF/views/layout/board/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>

        <div id="list">
            <div id="title">
                <div>번호</div>
                <div>작성자</div>
                <div>제목</div>
                <div>작성일</div>
                <div>조회수</div>
                <div style="display:none;" id="empNo">${empNo}</div>
            </div>
            <div id="content"></div>
            <button id="btn" style="display:none;">수정하기</button>
            <button id="delete">삭제하기</button>
            <br>
            <div>댓글</div>
            <input type="text" id="comment" placeholder="댓글을 작성해주세요">
            <button onclick="comment();">작성하기</button>
            <div id="commentContent"></div>
        </div>
    </main>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="/js/board/detail.js"></script>
</body>
</html>
