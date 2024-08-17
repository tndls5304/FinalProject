<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>직원 새계정등록 </title>
    <!-- 연결된 정적파일-->
        <link rel="stylesheet" href="/css/join/emp_join.css">
        <script defer src="/js/join/emp_join.js"></script>
    <!-- jstl 라이브러리-->
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- 제이쿼리-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- 글씨체 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <!-- 간단한 에러메세지-->
    <script>
        <c:if test="${not empty errorMsg}">
            alert('${errorMsg}');
            window.close();
        </c:if>
    </script>
</head>

<body>
    <main>
        <div class="mainjoin"><h1>baby works 신규 직원 등록</h1></div>
        <form id="signupForm" action="/emp/join" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="no"><h3>발급된 키 :</h3></label>
                <input type="text" value= ${joinKey}  name="joinKey" <%--readonly--%>
            </div>
            <div class="form-group">
                <label for="id"><h3>아이디</h3> </label>
                <input type="text" id="id"  name="id" class="id-input" placeholder="아이디 입력" maxlength="30">
                <button type="button" id="idDupTestBtn" class="id-button">id중복검사</button>
                <div class="error-message" id="id-error"></div>
            </div>
            <div class="form-group">
                <label for="pwd"><h3>비밀번호</h3> </label>
                <input type="password" id="pwd" name="pwd" placeholder="비밀번호 입력" maxlength="20">
                <div class="error-message" id="pwd-error"></div>
            </div>
            <div class="form-group">
                <label for="pwdCheck"><h3>비밀번호 확인</h3> </label>
                <input type="password" id="pwdCheck" name="pwdCheck" placeholder="비밀번호 확인" maxlength="20">
                <div class="error-message" id="pwdCheck-error"></div>
                <div class="error-message" id="pwd-mismatch-error"></div>
            </div>
            <div class="form-group">
                <label for="name"><h3>이름</h3></label>
                <input type="text" id="name" name="name" placeholder="이름 입력" maxlength="30">
                <div class="error-message" id="name-error"></div>
            </div>
            <div class="form-group">
                <label for="phone"><h3>연락처</h3></label>
                <input type="tel" id="phone" name="phone" placeholder="연락처입력 ('-'없이 숫자로만 입력해주세요)">
                <div class="error-message" id="phone-error"></div>
            </div>
          <div class="form-group">
                 <label for="profile"><h3>프로필사진</h3></label>
                 <input type="file" id="profile" name="profileInfo">
                 <div class="error-message" id="profile-error"></div>
          </div>
          <div class="btncenter">
                <button type="button" onclick="validateForm()">계정등록하기</button>
          </div>
        </form>
    </main>
</body>
