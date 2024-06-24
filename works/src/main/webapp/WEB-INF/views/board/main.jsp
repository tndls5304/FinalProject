<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/board/main.css">
</head>
<body>

        <%@ include file="/WEB-INF/views/layout/board/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>
        <div>작성하기</div>
        <form action="" method="post">
            <input type="text" name="title" id="">
            <input type="text" name="content">
            <input type="submit" value="작성하기">
        </form>

    </main>

    </body>
    </html>
    


</body>
</html>