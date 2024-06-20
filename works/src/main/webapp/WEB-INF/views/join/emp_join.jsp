<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>직원 새계정등록 </title>
    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


        <link rel="stylesheet" href="/resources/css/join/emp_join.css">
        <script src="/resources/js/join/emp_join.js"></script>
</head>
<body>

    <main>

        <div class="mainjoin"><h1>🪪신규  직원  등록 </h1></div>

        <form id="signupForm" action="/app/?/?" method="post" enctype="multipart/form-data">
            <div class="form-group">
                 <label for="employee_no" class=""><h3>발급된 사원번호 :</h3></label>
                 <input type="text" id="employeeNumber" name="employee_no" readonly>
            </div>
            <div class="form-group">
                <label for="id"><h3>아이디</h3> </label>
                <input type="text" id="id"  name="id" class="id-input" placeholder="아이디 입력">
                <button class="id-button">중복검사</button>
                <div class="error-message" id="id-error"></div>
            </div>
            <div class="form-group">
                <label for="password"><h3>비밀번호</h3> </label>
                <input type="password" id="password" name="password" placeholder="비밀번호 입력">
                <div class="error-message" id="password-error"></div>
            </div>
            <div class="form-group">
                <label for="passwordCheck"><h3>비밀번호 확인</h3> </label>
                <input type="password" id="password-check" name="password-check" placeholder="비밀번호 확인">
                <div class="error-message" id="passwordCheck-error"></div>
                <div class="error-message" id="password-mismatch-error"></div>
            </div>
            <div class="form-group">
                <label for="name"><h3>이름</h3></label>
                <input type="text" id="name" name="name" placeholder="이름 입력">
                <div class="error-message" id="name-error"></div>
            </div>
            <div class="form-group">
                <label for="phone"><h3>연락처</h3></label>
                <input type="tel" id="phone" name="phone" placeholder="연락처입력 ('-'없이 숫자로만 입력해주세요)">
                <div class="error-message" id="phone-error"></div>
            </div>


            <div id="reviewPreviewImage" class="form-group"></div>
            <div class="btncenter">
                <button type="button" onclick="validateForm()">계정등록하기</button>
            </div>
        </form>
    </main>


</body>
</html>