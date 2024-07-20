<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 제이쿼리 -->
      <script defer src="/js/jquery-api-script/jquery.min.js"></script>
    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

     <link rel="stylesheet" href="/css/admin/insert_emp.css">
     <link rel="stylesheet" href="/css/layout/admin/same.css">

       <!-- 부서, 직위 조회해오는 제이쿼리-->
     <script defer src="/js/admin/insert_emp.js"></script>

</head>


<body>
    <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
        <div class="header">
            <h2>신규 직원 계정등록 & 회원가입 메일 전송🪪</h2>
        </div>
          <div class="empInfo-group">
               <label for="name"><h3> 사원 이름 :</h3>
               <input type="text"  id="name" placeholder="사원 이름 입력" required>
          </div>
          <div class="empInfo-group">
             <h3>이메일</h3>
              <input type="text"  id="email"  placeholder="이메일 입력" value="@gmail.com" required>
          </div>
          <div class="empInfo-group">
             <h3>부서</h3>
                    <select class="dept_select" id="deptSelect"></select>
          </div>

          <div class="empInfo-group">
           <h3>직위</h3>
                  <select  class="position_select" id="positionSelect"></select>
        </div>

          <div class="btncenter">
              <button onclick="insertEmp()" >신규 직원 등록 </button>
          </div>




    </main>



    </div>


</body>
</html>