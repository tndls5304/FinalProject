<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

     <link rel="stylesheet" href="/css/admin/insert_emp.css">
     <link rel="stylesheet" href="/css/layout/admin/same.css">
       <!-- 제이쿼리-->
     <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
       <!-- 부서, 직위 조회해오는 제이쿼리-->
     <script defer src="/js/admin/insert_emp.js"></script>

</head>

<%@ include file="/WEB-INF/views/layout/util.jsp" %>
<body>
    <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
        <div class="header">
            <h2>신규 직원 계정등록</h2>
        </div>
        <form id="signupForm" action="/admin/insert_emp" method="post" >
          <div class="form-group">
               <label for="name"><h3> 사원 이름 :</h3></label>
               <input type="text"  name="name" placeholder="사원 이름 입력">
          </div>
          <div class="form-group">
              <label for="email"><h3>이메일</h3> </label>
              <input type="text"  name="email"  placeholder="이메일 입력" value="@gmail.com">

          </div>
          <div class="form-group">
              <label for="deptNo"> <h3>부서</h3> </label>
                    <select name="deptNo" class="dept_select" id="deptSelect"></select>
          </div>

          <div class="form-group">
            <label for="positionNo"> <h3>직위</h3> </label>
                  <select name="positionNo" class="position_select" id="positionSelect"></select>
        </div>

          <div class="btncenter">
              <button type="submit" >신규 직원 등록 </button>
          </div>
      </form>



    </main>



    </div>


</body>
</html>