<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>직원로그인</title>
    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


   <link rel="stylesheet" href="/css/login/emp_login.css">

</head>

<body>

    <main>

        <div class="mainjoin"><h1>직원 로그인 </h1></div>

        <form id="signupForm" action="/emp/login_proc" method="post">

            <div class="form-group">
                <label for="id"><h3>아이디</h3> </label>
                <input type="text" id="id"  name="username" class="id-input" placeholder="아이디 입력">

            </div>
            <div class="form-group">
                <label for="password"><h3>비밀번호</h3> </label>
                <input type="password" id="password" name="password" placeholder="비밀번호 입력">

            </div>

            <div class="btncenter">
                <button type="submit" >로그인하기</button>
            </div>
        </form>
    </main>


</body>
</html>