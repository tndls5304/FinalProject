<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/board/write.css">
</head>
<body>

        <%@ include file="/WEB-INF/views/layout/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>
       
        <div id="main">
            <div id="outLine">  
                <form id="postForm" action="/board/write" method="post" enctype="multipart/form-data">
                    <label for="title">제목:</label>
                    <input type="text" id="title"><br>
                    <label for="content">내용:</label>
                    <div id="content" contenteditable="true"></div><br>
                    <input type="file" id="imageFile" accept="image/*" style="display: none;"><br>
                    <button type="button" id="addImg">사진 추가</button>
                    <button type="button" onclick="submitPost()">포스트 작성</button>
                </form>
            </div>
        </div>
        

    </main>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/js/board/write.js"></script>