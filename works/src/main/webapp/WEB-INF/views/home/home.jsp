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

    <script>
    $(document).ready(function () {

      // ----------------------------------------알림 정보를 가져오는 AJAX 요청

      $.ajax({
        url: "/messenger/alarmInfor",
        method: "post",
        success: function (data) {

          //전체적으로, html에 형식을 미리 만드는 것이 아닌, js를 통해서 요소를 설정해주고 생성해준다는 것만 기억하면 된다.

          //HTML에서 id가 notify인 요소를 가지고 와, 설정해준다.
          let notificationDiv = document.getElementById("notify");


          //기존 알림 지우고 추가
          notificationDiv.innerHTML = "";


          //notification, newNotification임의로 지어준 변수명이다.
          data.forEach(function (notification) {
            //p 태그를 만들어, 그곳에 알림을 띄운다는 설정이다.
            let newNotification = document.createElement("p");
            //message는 NotificationHandler에서 매개변수로 받아오는 친구다.
            newNotification.innerText = notification.message;

            //"notification-message"라는 class를 생성해준 것이다.
            //css에서 스타일 적용을 위해 작성한 코드이다.
            //css -> .notification-messag{} 작성해주면 된다.
            newNotification.classList.add("notification-message");

            //alarmNo 데이터를 설정한다.
            newNotification.dataset.alarmNo = notification.alarmNo;

            //띄운 알림을 클릭했을 때, 이동하는 경로를 작성해준다.
            newNotification.onclick = function () {
              window.location.href = "http://127.0.0.1:8080/messenger/all";

              //해당 알림을 읽음처리한다.
              //여기에서 markNotificationAsRead는 아래에서 설명할 ajax 함수이름이다.
              markNotificationAsRead(notification.alarmNo);
            };

            //위의 모든 요소들을 최종적으로 HTML 알림창에 띄우기 위해 넣어주는 작업이다.
            notificationDiv.appendChild(newNotification);
          });
        },
        error: function (xhr, status, error) {
          console.log("알림 띄우기 실패: ", error);
        }
      });

    // ----------------------------------------WebSocket 설정(일부분만 바꿔주면 된다.
    // 본인이 Ajax로 쓴 값을 넘어주면 된다. 주석으로 해놓은 부분만 바꿔주면 된다.)
    let socket = new WebSocket("ws://localhost:8080/notifications");

    socket.onopen = function (event) {
      console.log("WebSocket is open now.");
    };

    socket.onmessage = function (event) {
      console.log("WebSocket message received:", event.data);

      //본인이 Ajax에 이미 넣은 값으로 값을 바꿔줘야 한다.
      //추가
      let data = JSON.parse(event.data);

      let notificationDiv = document.getElementById("notify");
      let newNotification = document.createElement("p");

      //수정
      //newNotification.innerText = event.data;
      newNotification.innerText = data.message;

      //본인이 Ajax에 이미 넣은 값으로 값을 바꿔줘야 한다.
      newNotification.classList.add("notification-message");

      //추가
      newNotification.dataset.todoNo = data.todoNo;

      //본인이 Ajax에 이미 넣은 값과 링크로 값을 바꿔줘야 한다.
      newNotification.onclick = function () {
        //window.location.href = "http://127.0.0.1:8080/messenger/all";
        //추가
        markNotificationAsRead(data.todoNo);
        window.location.href = data.link;
      };
      notificationDiv.appendChild(newNotification);

      //실시간으로 알림을 띄운다. (수정X)
      //수정
      //alert(event.data);
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
  });


    // ----------------------------------------알림을 읽었는지 확인하는 AJAX 요청
    function markNotificationAsRead(alarmNo) {
        console.log("markNotificationAsRead 호출됨: " + alarmNo); // 호출 로그
        $.ajax({
            url: "/messenger/readAlarm",
            method: "post",
            data: { alarmNo: alarmNo },
            success: function (result) {
                console.log("알림 읽음 처리 성공: " + result); // 성공 메시지 출력
            },
            error: function (xhr, status, error) {
                console.log("알림 읽음 처리 실패: ", error);
                console.log("상태: ", status);
                console.log("응답 텍스트: ", xhr.responseText);
            }
        });
    }



    // 지수 근태관리----------------------------------------------------------------------------------------------------------

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

<!-- ---------------------------------------------------------- -->


    <!-- 예린 투두 알림~~~ -->
    <script>
      $(document).ready(function () {
        // --------------------알림 정보를 가져오는 AJAX 요청
        $.ajax({
          url: "/todo/todoAlarm",
          method: "post",
          success: function (data) {

            //전체적으로, html에 형식을 미리 만드는 것이 아닌, js를 통해서 요소를 설정해주고 생성해준다는 것만 기억하면 된다.

            //HTML에서 id가 notify인 요소를 가지고 와, 설정해준다.
            let notificationDiv = document.getElementById("notify");

            //@@@@기존 알림 지우고 추가
            notificationDiv.innerHTML = "";

            //notification, newNotification임의로 지어준 변수명이다.
            data.forEach(function (notification) {
              //p 태그를 만들어, 그곳에 알림을 띄운다는 설정이다.
              let newNotificationToDo = document.createElement("p");
              //message는 NotificationHandler에서 매개변수로 받아오는 친구다.
              newNotificationToDo.innerText = notification.message;

              //"notification-message"라는 class를 생성해준 것이다.
              //css에서 스타일 적용을 위해 작성한 코드이다.
              //css -> .notification-messag{} 작성해주면 된다.
              newNotificationToDo.classList.add("notification-message");

              //messenNo 데이터를 설정한다.
              newNotificationToDo.dataset.todoNo = notification.todoNo;

              //띄운 알림을 클릭했을 때, 이동하는 경로를 작성해준다.
              newNotificationToDo.onclick = function () {
                window.location.href = "http://127.0.0.1:8080/todo/home";

                //해당 알림을 읽음처리한다.
                //여기에서 markNotificationAsRead는 아래에서 설명할 ajax 함수이름이다.
                markNotificationAsRead(notification.todoNo);
              };

              //위의 모든 요소들을 최종적으로 HTML 알림창에 띄우기 위해 넣어주는 작업이다.
              notificationDiv.appendChild(newNotificationToDo);



            });
          },
          error: function (xhr, status, error) {
            console.log("알림 띄우기 실패: ", error);
          }
        });

        // --------------------WebSocket 설정(일부분만 바꿔주면 된다.
        // 본인이 Ajax로 쓴 값을 넘어주면 된다. 주석으로 해놓은 부분만 바꿔주면 된다.)
        let socket = new WebSocket("ws://localhost:8080/notifications");

        socket.onopen = function (event) {
          console.log("WebSocket is open now.");
        };

        socket.onmessage = function (event) {
          console.log("WebSocket message received:", event.data);

          //본인이 Ajax에 이미 넣은 값으로 값을 바꿔줘야 한다.
          //추가
          let data = JSON.parse(event.data);

          let notificationDiv = document.getElementById("notify");
          let newNotificationToDo = document.createElement("p");

          //수정
          //newNotification.innerText = event.data;
          newNotificationToDo.innerText = data.message;

          //본인이 Ajax에 이미 넣은 값으로 값을 바꿔줘야 한다.
          newNotificationToDo.classList.add("notification-message");

          //추가
          newNotificationToDo.dataset.todoNo = data.todoNo;

          //본인이 Ajax에 이미 넣은 값과 링크로 값을 바꿔줘야 한다.
          newNotificationToDo.onclick = function () {
            //window.location.href = "http://127.0.0.1:8080/messenger/all";
            //추가
            markNotificationAsRead(data.todoNo);
            window.location.href = data.link;
          };
          notificationDiv.appendChild(newNotificationToDo);

          //실시간으로 알림을 띄운다. (수정X)
          //수정
          //alert(event.data);
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
      });

      // --------------------알림을 읽었는지 확인하는 AJAX 요청
      function markNotificationAsRead(todoNo) {
        $.ajax({
          url: "/todo/todoAlarm",
          method: "post",
          data: { todoNo: todoNo },
          success: function () {
            console.log("알림 읽었는지 확인 성공");
          },
          error: function (xhr, status, error) {
            console.log("알림 읽었는지 확인 실패: ", error);
          }
        });
      }

    </script>