<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <!-- <script defer src="/js/home/emp_info.js"></script> -->

    <script src="/js/home/home.js"></script>
    <link rel="stylesheet" href="/css/home/home.css">
</head>
<body>

     <!-- *****************HomeController에는 근태관리를 위한 출퇴근 메소드가 있습니다***************** -->


    <%@ include file="/WEB-INF/views/layout/home/nav.jsp" %>

      <main>
        <div id="container">
                    <div id="profile-section">
                        <img src="https://sports.chosun.com/news/html/2021/02/19/2021022001001441300094961.jpg" alt="Profile Picture" id="profile-picture">
                        <div id="profile-info">
                            <h2>신세경 부장</h2>
                            <p>인사총무팀</p>
                            <div id="clock">00:00:00</div>
                            <div id="work-status">
                                <div class="work-item">
                                    <p>출근시간 </p>
                                </div>
                                <div class="work-item">
                                    <p>퇴근시간 </p>
                                </div>
                                <div id="button-group">
                                    <button id="start-button">출근하기</button>
                                    <button id="end-button">퇴근하기</button>
                                    <div style="display:none;" class="empNo">${loginEmpVo.no}</div>
                                </div>
                            </div>
                        </div>
                    </div>

            <div id="main-section">
                <div id="left-section">
                    <div id="board">
                        <h3>필요한 거</h3>
                        <p>작성</p>
                        <p></p>
                    </div>
                    <div id="documents">
                        <h3>필요한 거</h3>
                        <p>작성</p>
                        <p></p>
                    </div>
                </div>
                <div id="right-section">
                    <div id="notify">
                        <h3>최근 알림</h3>
                        <p>구현.. 해볼까요</p>
                        <p></p>
                    </div>
                </div>
            </div>
        </div>
      </main>


    <!-- //*** 수인언니가 작성한 친절한 예시 참고 *** -->
    <!-- <main>
    <%@ include file="/WEB-INF/views/layout/home/aside.jsp" %>
            <h1>여기는 베이비웍스 홈입니다 🤍 </h1>
            <hr>
            <h2>1번 . ajax로 회원 정보 가져오기 예시 </h2>
            <div id="empInfo"></div>
            <hr>
            <h2> 2번 .폼태그로 게시물 작성하기 예시 </h2>
                 <form action="/writing" method="post">
                   <input type="text" name="title"  placeholder="글제목쓰시오">
                   <input type="text" name="content" placeholder="글내용쓰시오">
                   <input type="submit" value="작성하기">
               </form>
    </main> -->

</body>
</html>


 <!-- jquery 넣기 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>

        //출근 처리 Ajax
        document.querySelectorAll('#start-button').forEach(item => {
          item.addEventListener('click', startAttend);
        });

        function startAttend(evt){
          console.log("클릭된 요소:", evt.target);

          const empNo = document.querySelector('.empNo').innerText.trim();
          console.log("empNo:", empNo);

          $.ajax({
            url: "/start",
            method: "post",
            data: {
              empNo: empNo,
            },
            success: (data) => {
              console.log("출근등록 완료!");
              console.log(data);
            },

            error: (xhr, status, error) => {
              console.log("출근등록 실패...");
            },
          });
        }

      </script>



