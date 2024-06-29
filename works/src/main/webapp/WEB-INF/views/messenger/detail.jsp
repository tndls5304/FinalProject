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
         <div id="messenger-write"><button>쪽지쓰기</button></div>
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