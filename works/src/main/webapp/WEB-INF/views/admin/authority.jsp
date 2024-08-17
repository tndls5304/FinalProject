<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 제이쿼리 -->
        <script defer src="/js/jquery-api-script/jquery.min.js"></script>
    <!--공통 css-->
        <link rel="stylesheet" href="/css/layout/admin/same.css">
    <!-- 여기서만 쓰는 정적파일들 -->
        <script defer src="/js/admin/authority.js"></script>
        <link rel="stylesheet" href="/css/admin/auth_manage.css">
    <!-- 글씨체 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
</head>

<body>
    <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>
    <main>
        <div class="header">
            <h2>🔐부 관리자에게 권한 부여</h2>
            </div>
            <h4 class="allCheck"><input type="checkbox" id="selectAllCheckBox"></input>모두 체크📝</h4>
            <table border="1px" id="menuList" class="table-auth">
                <thead>
                    <tr>
                        <th>메뉴번호</th>
                        <th>메뉴이름</th>
                        <th>조회권한 </th>
                        <th>등록권한</th>
                        <th>수정권한</th>
                        <th>삭제권한</th>
                    </tr>
                </thead>
           </table>
           <button id="modifyBtn" class="modify-btn">수정하기</button>
    </main>
</body>
