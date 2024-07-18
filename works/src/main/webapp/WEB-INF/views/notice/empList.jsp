<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/notice/empList.css">

</head>
<body>

        <%@ include file="/WEB-INF/views/layout/nav.jsp" %>

    <main>
        <%@ include file="/WEB-INF/views/layout/board/aside.jsp" %>

        <div id="main">
            <div id="searchTag">
                <label>검색</label>
                <select name="" id="searchTitle">
                    <option>제목</option>
                    <option>작성자</option>
                </select>
                <input type="search" id="search">
                <button id="searchBtn" onclick="search();">검색하기</button>
            </div>
            <div id="list">
                <div id="title">
                    <div>번호</div>
                    <div>작성자</div>
                    <div>제목</div>
                    <div>작성일</div>
                    <div>조회수</div>
                </div>
                <div id="content">
                  <!--ajax로 꺼내용 내용-->  
                </div>
            </div>            
        </div>

        

    </main>

    </body>
    </html>



</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="/js/notice/empList.js"></script>