<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <!DOCTYPE html>
   <html lang="en">

   <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Messenger</title>
     <link rel="stylesheet" href="/css/messenger/detail.css">
     <!-- fontawesome 이 부분 추가 -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
         <div id="messenger-write"><button onclick="moveToWrite()">쪽지쓰기</button></div>
            <div id="messenger-status">
               <a href="http://127.0.0.1:8080/messenger/unread"><div><button>안읽음 [<c:out value="${unreadCount}"/>]</button></div></a>
               <a href="http://127.0.0.1:8080/messenger/important"><div><button>중요</button></div></a>
               <a href="http://127.0.0.1:8080/messenger/trash"><div><button>휴지통</button></div></a>
            </div>
            <hr>
            <div id="all-messenger">
               <a href="http://127.0.0.1:8080/messenger/all"><p><i class="fa-brands fa-facebook-messenger"></i> 전체쪽지</p></a>
            </div>
            <div id="receive-messenger">
               <a href="http://127.0.0.1:8080/messenger/received"><p><i class="fa-solid fa-reply"></i> 받은쪽지</p></a>
            </div>
            <div id="send-messenger">
               <a href="http://127.0.0.1:8080/messenger/sent"><p><i class="fa-solid fa-share"></i> 보낸쪽지</p></a>
            </div>
       </div>



       <div id="messenger-main">
        <form action="/messenger/detail" method="get" enctype="multipart/form-data">
         <div id="messenger-content">
           <c:forEach var="message" items="${voList}">
            <div class="">
                <div style="display:none;" class="messenNo">${message.messenNo}</div>
                <div id="messenger-title">${message.title}</div>
                <div id="messenger-sender">보낸사람&nbsp;&nbsp;${message.senderName}</div>
                <div id="messenger-receiver">받는사람&nbsp;&nbsp;${message.receiverName}</div>
                <div id="messenger-date">${message.sendDate}</div>
                <div id="messenger-content-write">${message.content}</div>
            </div>
           </c:forEach>
         </div>
        </form>
       </div>
     </main>


   </body>

   </html>

   <!-- detail(상세페이지)에서는 ajax 할 거 없음. -->

   <script>
          // 쪽지쓰기 눌렀을 때, 쪽지쓰기 페이지(/messenger/write)로 이동
           function moveToWrite(){
               window.location.href = "http://127.0.0.1:8080/messenger/write";
           }
   </script>