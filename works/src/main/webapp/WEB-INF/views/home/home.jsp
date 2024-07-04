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
    <!-- fontAwesome을 사용하기 위한 코드이다. -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

     <!-- *****************HomeController에는 근태관리를 위한 출퇴근 메소드가 있습니다***************** -->


    <%@ include file="/WEB-INF/views/layout/home/nav.jsp" %>

      <main>
        <div id="container">
            <div id="profile-section">

                    <img src="/img/profile/${loginEmpVo.profile}" alt="Profile Picture" id="profile-picture">
                    <!-- fontAwesome에서 가지고 온 이모티콘이다. 누르면 나의 근태 리스트로 넘어가게 해두었다!!!!!!!!! -->
                    <i class="fa-solid fa-list attend-list" onclick="window.location.href='http://localhost:8080/attend/list';"></i>
                    <div id="profile-info">
                        <h2>${loginEmpVo.name} ${loginEmpVo.positionName}</h2>
                        <h3>🖤 ${loginEmpVo.deptName} 🖤</h3>
                        <div id="clock">00:00:00</div>
                        <div id="work-status">
                            <div class="work-item">
                                <p><b>출근시간</b> ${attendVo.startTime}</p>
                            </div>
                            <div class="work-item">
                                <p><b>퇴근시간</b> ${attendVo.endTime}</p>
                            </div>
                            <div id="button-group">
                                <button id="start-button">출근하기</button>
                                <button id="end-button">퇴근하기</button>
                                <!-- <button id="load-button" onclick="location.reload();">새로고침</button> -->
                                <i class="fa-solid fa-rotate-right" id="load-button" onclick="location.reload();"></i>
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
            url: "home/start",
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



        //퇴근 처리 Ajax
        document.querySelectorAll('#end-button').forEach(item => {
          item.addEventListener('click', endAttend);
        });

        function endAttend(evt){
          console.log("클릭된 요소:", evt.target);

          const empNo = document.querySelector('.empNo').innerText.trim();
          console.log("empNo:", empNo);

          $.ajax({
            url: "home/end",
            method: "post",
            data: {
              empNo: empNo,
            },
            success: (data) => {
              console.log("퇴근등록 완료!");
              console.log(data);
            },

            error: (xhr, status, error) => {
              console.log("퇴근등록 실패...");
            },
          });
        }

    </script>



