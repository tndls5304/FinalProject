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

</head>

<%@ include file="/WEB-INF/views/layout/util.jsp" %>
<body>
    <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
        <div class="header">
            <h2>신규 직원 계정등록</h2>
        </div>
        <form id="signupForm" action="/app/?/?" method="post" enctype="multipart/form-data">
          <div class="form-group">
               <label for="employee_name" class=""><h3> 사원 이름 :</h3></label>
               <input type="text"  name="employee_name" placeholder="사원 이름 입력">
          </div>
          <div class="form-group">
              <label for="id"><h3>이메일</h3> </label>
              <input type="text"  name="email"  placeholder="이메일 입력">

          </div>
          <div class="form-group">
              <label for="dept"> <h3>부서</h3> </label>
                    <select name="dept" class="dept_select"> <option value="부서번호">부서이름</option></select>
          </div>


          <div class="form-group">
            <label for="dept"> <h3>직위</h3> </label>
                  <select name="dept" class="position_select"> <option value="부서번호">직위명</option></select>
        </div>

          <div class="btncenter">
              <button type="button" >신규 직원 등록 </button>
          </div>
      </form>



    </main>



    </div>


</body>
</html>