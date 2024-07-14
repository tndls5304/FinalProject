<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                <i class="fa-regular fa-heart fa-lg" style="color:#f005dc" id="like"></i>
                <div style="display:none;" id="empNo">${empNo}</div>
            </div>
            <div id="postTitle"></div>
            <div id="postContent"></div>
            <button id="btn" style="display:none;">수정하기</button>
            <button id="delete">삭제하기</button>
            <br>
            <div>댓글</div>
            <input type="text" id="comment" placeholder="댓글을 작성해주세요">
            <button onclick="comment();" type="button">작성하기</button>
            <div id="commentContent">

            </div>
        </div>
    </main>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="/js/board/detail.js"></script>
</body>
</html>
