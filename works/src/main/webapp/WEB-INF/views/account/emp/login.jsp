<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>직원로그인</title>
     <!-- 제이쿼리-->
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- jstl라이브러리쓰기-->
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- 글씨체 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
    <!-- 연결된 정적파일 -->
        <link rel="stylesheet" href="/css/login/emp_login.css">
        <script defer src="/js/login/emp_login.js"></script>
    <!-- 간단한 에러메세지-->
        <script>
            <c:if test="${not empty errorMsg}">
                     alert('${errorMsg}');
            </c:if>
            <c:if test="${not empty joinSuccessMsg}">
                      alert('${joinSuccessMsg}');
            </c:if>
        </script>
</head>
<body>
   <main>
        <div class="mainjoin"><h1>직원 로그인</h1><img class="icon-login"width="50px"  height="50px" src="/img/icon/login.png"> </div>

        <form id="signupForm" action="/emp/login" method="post">

            <div class="form-group">
                <label for="id"><h3>아이디</h3> </label>
                <input type="text" id="id"  name="id" class="id-input" placeholder="아이디 입력">
            </div>

            <div class="form-group">
                <label for="password"><h3>비밀번호</h3> </label>
                <input type="password" id="password" name="pwd" placeholder="비밀번호 입력">
            </div>


            <div class="btncenter">
                <button type="submit" >로그인하기</button>
            </div>

        </form>

            <div class="btnunder">
                  <button onclick="btnFindId()"> 아이디찾기</button>
                  <button onclick="btnFindPwd()">비밀번호 찾기</button>
             </div>
    </main>


<!-- -------------------아이디찾기 모달창------------------------------------------->
  <div id="modalFindId" class="modal-find-id" >
      <div class="modalFindAccount-content" id=modalIdContent>
              <h2>아이디 찾기</h2>
               <h4>이름:</h4>
               <input type="text" id="nameModalFindId" class="modal-inputBox"   required>
               <h4>휴대폰번호:</h4>
                <input type="number" id="phoneModalFindId" class="modal-inputBox" required>
                <button onclick="requestFindId()">아이디찾기 요청</button>
                <button class="close-modalFindId" onclick="closeModalFindId()">닫기</button>
      </div>
  </div>
<!---------비밀번호 찾기 모달창------------------------------------------->
  <div id="modalFindPwd" class="modal-find-pwd">
       <div class="modalFindAccount-content" id=modalPwdContent>
                <h2>비밀번호 찾기</h2>

                 <h4>이름:</h4>
                 <input type="text" id="nameModalFindPwd" name="nameModalFindPwd" class="modal-inputBox" required>
                 <h4>아이디:</h4>
                 <input type="text" id="idModalFindPwd" name="idModalFindPwd" class="modal-inputBox" required>

                    <button  onclick="requestFindPwd()">비밀번호찾기 요청</button>
                    <button class="close-modalFindPwd" onclick="closeModalFindPwd()" >닫기</button>

     </div>
  </div>




</body>
</html>