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
   <!-- 공통css-->
   <link rel="stylesheet" href="/css/layout/admin/same.css">
   <!-- 개별 정적파일-->
   <link rel="stylesheet" href="/css/admin/emp_list.css">
   <script defer src="/js/admin/emp_list.js"></script>

    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

</head>
<body>
        <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
            <div class="header">
                    <h1>직원리스트</h1>
                    <table id="table1"></table>
            </div>

            <div>
              <input placeholder="부서"><input placeholder="직위"><input placeholder="이   름">
              <button>사원검색</button>
            </div>
            <h4> <직위 높은순, 부서순> </h4>

          <!-- 테이블 -->
          <table border="1">
               <thead>
                          <tr>
                            <th>사원번호</th>
                            <th>부서 명</th>
                            <th>직위 명</th>
                            <th>사원 이름</th>
                            <th>아이디</th>
                            <th>잠금여부</th>
                          </tr>
                  </thead>
              <tbody>
              </tbody>
          </table>


         <!-- 모달 -->
<div class="modal" id="modal">
    <button class="modal-close" id="modalClose">닫기</button>
    <h2 id="modalTitle">직원 상세 정보</h2>
    <div class="modal-content" id="modalContent">
          <img id="empImage" src="" alt="프로필 사진">
          <div>
                  <p id="empNo">사원번호: </p>
                  <p id="empName">사원이름: <input type="text"name="name"></p>
                   <p id="empJoinDate">입사일: </p>
                   <p id="empContact">연락처:<input type="text" name="phone"> </p>
                   <p id="empPassword">비밀번호: <input type="text" name="pwd"> </p>
                   <p id="empLoginFailCount">로그인실패횟수: <input type="text" name="loginFailNum"> </p>
                   <p id="empLockStatus">잠금여부: <input type="text" name="lockYn"> </p>
                   <p id="empEmail">이메일:<input type="text" name="email"> </p>
                        <button onclick="editEmp()" >수정하기</button>
                       <button class="" id="">퇴사처리하기</button>
         </div>
    </div>
</div>

 </main>




</body>
</html>