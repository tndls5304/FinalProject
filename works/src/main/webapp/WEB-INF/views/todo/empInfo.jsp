<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 제이쿼리-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

  </head>

  <body>


    <main>

      <div class="modal" id="modal">
        <button class="modal-close" id="modalClose">닫기</button>
        <h2 id="modalTitle">직원 상세 정보</h2>
        <div class="modal-content" id="modalContent">
          <img id="empImage" src="" alt="프로필 사진">
          <div>
            <p id="empNo"></p>
            <p id="empName">사원이름: <input type="text" name="name"></p>
            <p id="empHireDate"></p>
            <p id="empContact">연락처:<input type="text" name="phone"> </p>
            <p id="empPassword">비밀번호: <input type="text" name="pwd"> </p>
            <p id="empLoginFailCount">로그인실패횟수: <input type="text" name="loginFailNum"> </p>
            <p id="empLockStatus">잠금여부: <input type="text" name="lockYn"> </p>
            <p id="empEmail">이메일:<input type="text" name="email"> </p>
            <button onclick="editEmp();">수정하기</button>
            <button onclick="resignEmp();">퇴사처리하기</button>
          </div>
        </div>
      </div>
    </main>




  </body>

  </html>