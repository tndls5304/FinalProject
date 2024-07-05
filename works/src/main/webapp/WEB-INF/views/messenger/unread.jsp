<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <!DOCTYPE html>
   <html lang="en">

   <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Messenger</title>
     <link rel="stylesheet" href="/css/messenger/write.css">
     <!-- <script src="/js/messenger/all.js"></script> -->
   </head>

   <body>

     <nav>
       <div id="right-sidebar">
         <div><button><img src="../resources/img/free-icon-menu-2791777.png" alt=""></button></div>
         <div><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></div>
         <div id="title">게시판</div>
       </div>
       <div id="left-sidebar">
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><a href=""><img src="../resources/img/스크린샷 2024-06-18 195610.png" alt=""></a></div>
         <div><button><img src="../resources/img/free-icon-person-11081570.png" alt=""></button></div>
       </div>
     </nav>

     <main>
       <div id="sidebar">
         <div id="messenger-write">
            <button onclick="moveToWrite()">쪽지쓰기</button>
         </div>
         <div id="messenger-status">
            <a href="http://localhost:8080/messenger/unread"><div><button>안읽음</button></div></a>
            <a href="http://localhost:8080/messenger/important"><div><button>중요</button></div></a>
            <a href="http://localhost:8080/messenger/delete"><div><button>휴지통</button></div></a>
         </div>
         <hr>
         <div id="all-messenger">
            <a href="http://localhost:8080/messenger/all"><p>전체쪽지</p></a>
         </div>
         <div id="receive-messenger">
            <a href="http://localhost:8080/messenger/received"><p>받은쪽지</p></a>
         </div>
         <div id="send-messenger">
            <a href="http://localhost:8080/messenger/sent"><p>보낸쪽지</p></a>
         </div>
       </div>



       <div id="messenger-main">
        <form action="/messenger/unread" method="get" enctype="multipart/form-data">
         <div id="messenger-search">
           <div><input type="text" placeholder="검색어를 입력해주세요."></div>
           <div><input type="button" value="검색"></div>
         </div>
         <div id="messenger-check">
           <div id="check-all"><input type="checkbox">전체선택</div>
           <div id="check-delete"><input type="button" value="삭제"></div>
         </div>
         <div id="messenger-content">
           <c:forEach var="message" items="${voList}">
             <div class="messenger-item">
               <div><input id="checkbox-delete" type="checkbox"></div>
               <div><input id="checkbox-important" type="checkbox"></div>
               <div id="list-person">${message.name}</div>
               <div id="list-title" class="click-title">${message.title}</a></div>
               <div id="list-date">${message.sendDate}</div>
               <div style="display:none;" class="messenNo">${message.messenNo}</div>
             </div>
           </c:forEach>
         </div>
        </form>
       </div>
     </main>


   </body>

   </html>

   <!-- all.jsp에 있는 ajax와 겹치기 때문에 반복하지 않기 위해서 messenger > all.js를 만들었다. -->

    <!-- jquery 넣기 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>

      //쪽지 읽음 처리 Ajax
      document.querySelectorAll('.click-title').forEach(item => {
        item.addEventListener('click', getMessenNo);
      });

      function getMessenNo(evt){
        console.log("함수 실행됨 ~~~");
        console.log("클릭된 요소:", evt.target);

        const messenNo = evt.target.parentNode.querySelector('.messenNo').innerText.trim();
        console.log("messenNo:", messenNo);

        $.ajax({
          url: "/messenger/read",
          method: "post",
          data: {
            messenNo: messenNo,
          },
          success: (data) => {
            console.log("쪽지 통신성공!");
            console.log(data);
          },

          error: (xhr, status, error) => {
            console.log("쪽지 통신실패...");
          },
        });
      }


      //전체 쪽지 목록에서 쪽지 상세페이지로 처리 Ajax
      document.querySelectorAll('.click-title').forEach(item => {
        item.addEventListener('click', moveToDetail);
      });

      function moveToDetail(evt){
        console.log("함수 실행됨 ~~~");
        console.log("클릭된 요소:", evt.target);

        const messenNo = evt.target.parentNode.querySelector('.messenNo').innerText.trim();
        console.log("messenNo:", messenNo);

        $.ajax({
          url: "/messenger/detail",
          method: "get",
          data: {
            messenNo: messenNo,
          },
          success: (data) => {
            console.log("쪽지번호 통신성공!");
            console.log(data);

            //(제목)클릭시, detail 페이지로 바로 이동할 수 있도록 설정해줘야 한다.
            location.href = "/messenger/detail?messenNo=" + messenNo;
          },

          error: (xhr, status, error) => {
            console.log("쪽지번호 통신실패...");
          },
        });
      }


      //쪽지 중요 처리 Ajax
      document.querySelectorAll('#checkbox-important').forEach(item => {
          item.addEventListener('click', sendImportant);
        });

        function sendImportant(evt){
          console.log("중요 체크박스 클릭됨 ~~~");
          console.log("클릭된 요소:", evt.target);

          const messenNo = evt.target.closest('.messenger-item').querySelector('.messenNo').innerText.trim();
          console.log("messenNo:", messenNo);

          $.ajax({
            url: "/messenger/importantStatus",
            method: "post",
            data: {
              messenNo: messenNo,
            },
            success: (data) => {
              console.log("중요 쪽지 통신성공!");
              console.log(data);
            },
            error: (xhr, status, error) => {
              console.log("중요 쪽지 통신실패...");
            },
          });
      }



      // /messenger/all에서 쪽지쓰기 눌렀을 때, 쪽지쓰기 페이지(/messenger/write)로 이동
      function moveToWrite(){
          window.location.href = "http://localhost:8080/messenger/write";
      }
    </script>
