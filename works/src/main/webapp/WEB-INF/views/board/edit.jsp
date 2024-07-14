<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/board/edit.css">
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
            </div>
            <form action="/board/edit" method="post" id="postForm">
                <div id="content">
                        
                    
                </div>
            </form>
            
        </div>

        
    </main>

    </body>
    </html>



</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<script src="/js/board/edit.js"></script>