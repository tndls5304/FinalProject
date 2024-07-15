<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>

      <!-- <script defer src="/js/home/emp_info.js"></script> -->

      <!-- jquery 넣기 -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


      <script defer src="/js/home/home.js"></script>
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
              <!-- **********상황에 따라 링크 바꾸기 -->
              <!-- <i class="fa-solid fa-list attend-list" onclick="window.location.href='http://localhost:8080/attend/list';"></i> -->
              <i class="fa-solid fa-list attend-list"
                onclick="window.location.href='http://127.0.0.1:8080/attend/list';"></i>
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
                  <section id="memo-container">
                      <div id="memo-box">
                        <textarea id="memo-content" cols="30" rows="13" placeholder="메모를 적어주세요."></textarea>
                        <div id="button-container">
                          <button id="save-button">저 장</button>
                          <button id="clear-button">초기화</button>
                        </div>
                      </div>
                  </section>
                </div>
              </div>
              <div id="right-section">
                <div id="notify">
                  <h3>최근 알림</h3>
                  <!-- 여기에 알림이 표시됩니다 -->
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

    // 지수 근태관리----------------------------------------------------------------------------------------------------------

    <script>
      //출근 처리 Ajax
      document.querySelectorAll('#start-button').forEach(item => {
        item.addEventListener('click', startAttend);
      });

      function startAttend(evt) {
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

      function endAttend(evt) {
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
    <!-- --------------------------------------------------------------------------------------------------- -->



    <script>
        $(document).ready(function () {
          // ------------------------------------------알림 정보를 가져오는 AJAX 요청
          function fetchNotifications() {
            $.ajax({
              url: "/messenger/alarmInfor",
              method: "post",
              success: function (data) {
                console.log("알림 데이터:", data);
                let notificationDiv = document.getElementById("notify");
                notificationDiv.innerHTML = "<h3>최근 알림</h3>";
                data.forEach(function (notification) {
                  console.log("개별 알림:", notification); // 개별 알림 데이터 출력
                  let newNotification = document.createElement("p");
                  newNotification.innerText = notification.message;
                  console.log("알림 메시지:", notification.message);
                  newNotification.classList.add("notification-message");
                  console.log("notification classList:", newNotification.classList);
                  newNotification.dataset.alarmNo = notification.alarmNo;
                  console.log("Alarm in dataset:", newNotification.dataset.alarmNo);

                 // 알림 유형에 따른 링크 설정
                  newNotification.onclick = function () {
                  console.log("알림 클릭:", notification.alarmNo);
                    if (notification.message.includes("쪽지")) {
                      markMessageNotificationAsRead(notification.alarmNo);
                      window.location.href = "http://127.0.0.1:8080/messenger/all";
                    } else if (notification.message.includes("할일")) {
                      markTodoNotificationAsRead(notification.todoNo);
                      window.location.href = "http://127.0.0.1:8080/todo/home";
                    }
                  };
                  notificationDiv.appendChild(newNotification);
                });
              },
              error: function (xhr, status, error) {
                console.log("알림 띄우기 실패: ", error);
              }
            });

            $.ajax({
              url: "/todo/todoAlarm",
              method: "post",
              success: function (data) {
                data.forEach(function (notification) {
                  let newNotification = document.createElement("p");
                  newNotification.innerText = notification.message;
                  newNotification.classList.add("notification-message");
                  newNotification.dataset.todoNo = notification.todoNo;

                  // 알림 유형에 따른 링크 설정
                  newNotification.onclick = function () {
                    if (notification.message.includes("쪽지")) {
                      markMessageNotificationAsRead(notification.alarmNo);
                      window.location.href = "http://127.0.0.1:8080/messenger/all";
                    } else if (notification.message.includes("할일")) {
                      markTodoNotificationAsRead(notification.todoNo);
                      window.location.href = "http://127.0.0.1:8080/todo/home";
                    }
                  };
                  notificationDiv.appendChild(newNotification);
                });
              },
              error: function (xhr, status, error) {
                console.log("알림 띄우기 실패: ", error);
              }
            });
          }

          // ---------------------------------------------------WebSocket 설정
          let socket = new WebSocket("ws://localhost:8080/notifications");

          socket.onopen = function (event) {
            console.log("WebSocket is open now.");
          };

          socket.onmessage = function (event) {
            console.log("WebSocket message received:", event.data);
            let data = JSON.parse(event.data);
            let notificationDiv = document.getElementById("notify");
            let newNotification = document.createElement("p");
            newNotification.innerText = data.message;
            newNotification.classList.add("notification-message");
            newNotification.dataset.alarmNo = data.alarmNo;
            newNotification.dataset.todoNo = data.todoNo;

            // 알림 유형에 따른 링크 설정
            newNotification.onclick = function () {
              if (notification.message.includes("쪽지")) {
                markMessageNotificationAsRead(notification.alarmNo);
                window.location.href = "http://127.0.0.1:8080/messenger/all";
              } else if (notification.message.includes("할일")) {
                markTodoNotificationAsRead(notification.todoNo);
                window.location.href = "http://127.0.0.1:8080/todo/home";
              }
            };

            notificationDiv.appendChild(newNotification);
            alert(data.message);
          };

          socket.onclose = function (event) {
            if (event.wasClean) {
              console.log(`Connection closed cleanly, code=${event.code} reason=${event.reason}`);
            } else {
              console.log('Connection died');
            }
          };

          socket.onerror = function (error) {
            console.log(`[error] ${error.message}`);
          };

          // ----------------------------------------------------------메시지 알림 읽음 처리 AJAX 요청
          function markMessageNotificationAsRead(alarmNo) {
            $.ajax({
              url: "/messenger/readAlarm",
              method: "post",
              data: { alarmNo: alarmNo },
              success: function (result) {
                console.log("메시지 알림 읽음 처리 성공: " + result);
              },
              error: function (xhr, status, error) {
                console.log("메시지 알림 읽음 처리 실패: ", error);
                console.log("상태: ", status);
                console.log("응답 텍스트: ", xhr.responseText);
              }
            });
          }

          // 투두 알림 읽음 처리 AJAX 요청
          function markTodoNotificationAsRead(todoNo) {
            $.ajax({
              url: "/todo/todoAlarm",
              method: "post",
              data: { todoNo: todoNo },
              success: function (result) {
                console.log("투두 알림 읽음 처리 성공: " + result);
              },
              error: function (xhr, status, error) {
                console.log("투두 알림 읽음 처리 실패: ", error);
                console.log("상태: ", status);
                console.log("응답 텍스트: ", xhr.responseText);
              }
            });
          }

          fetchNotifications();
        });
    </script>
