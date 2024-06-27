<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

  <!-- 제이쿼리-->
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <!--공통 css-->
   <link rel="stylesheet" href="/css/layout/admin/same.css">

     <!-- 본문에 서브관리자가 가진 메뉴 권한 보여주기ajax 쓴 js -->
    <script  src="/js/admin/get_sub_admin_menu.js"></script>


    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<%@ include file="/WEB-INF/views/layout/util.jsp" %>


</head>
<body>
        <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
               <div class="header">
                   <h2>관리자의 권한관리입니다</h2>
                </div>
                         <table border="1px" id="menuList">
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
                                <button id="modifyBtn">수정하기</button>


    </main>



    </div>


</body>
</html>