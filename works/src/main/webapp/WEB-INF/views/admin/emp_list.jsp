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

   <link rel="stylesheet" href="/css/layout/admin/same.css">
   <script src="/js/admin/emp_list.js"></script>

    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

</head>
<body>
        <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
            <div class="header">
                    <h2>직원리스트</h2>
                    <table id="table1"></table>
            </div>

            <div>
              <input placeholder="부서"><input placeholder="직위"><input placeholder="이   름">
              <button>사원검색</button>
            </div>
            <h2> <직위 높은순, 부서순> </h2>


          <table border="1">
              <thead>
                      <tr>
                        <th>번호</th>
                        <th>부서</th>
                        <th>직위</th>
                        <th>사원 이름</th>
                        <th>아이디</th>
                        <th>잠금여부</th>
                      </tr>
              </thead>
              <tbody>
              </tbody>
          </table>
    </main>



    </div>


</body>
</html>