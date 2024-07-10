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

        <%@ include file="/WEB-INF/views/layout/board/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>
       
        <div id="main">
            <form action="/board/write" method="post">
                <div id="top">
                    <h2>게시판</h2>
                    <div id="line"></div>
                </div>
                <div id="titleTag">
                    <span>제목</span>
                    <input type="text" name="title" placeholder="제목을 입력하세요">
                </div>
                <div id="fileTag">
                    <span>파일첨부</span>
                    <input type="file" name="img" placeholder="사진첨부">
                </div>
                <div id="contentTag">
                    <textarea name="content" id="" placeholder="내용을 입력하세요"></textarea>
                </div>
                <input type="submit" value="작성">
            </form>
        </div>

    </main>

    </body>
    </html>
    


</body>
</html>