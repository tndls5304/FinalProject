<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script defer src="/js/home/emp_info.js"></script>
<link rel="stylesheet" href="/css/home/home.css">

</head>
<body>

        <%@ include file="/WEB-INF/views/layout/home/nav.jsp" %>



    <main>
    <%@ include file="/WEB-INF/views/layout/home/aside.jsp" %>
            <h1>여기는 베이비웍스 홈입니다 🤍 </h1>
            <hr>

            <h2>1번 . ajax로 회원 정보 가져오기 예시 </h2>
            <div id="empInfo"></div>

<hr>

                <h2> 2번 .폼태그로 게시물 작성하기 예시 </h2>
                     <form action="/writing" method="post">
                       <input type="text" name="title"  placeholder="글제목쓰시오">
                       <input type="text" name="content" placeholder="글내용쓰시오">
                       <input type="submit" value="작성하기">
                   </form>
    </main>

    </body>
    </html>



</body>
</html>