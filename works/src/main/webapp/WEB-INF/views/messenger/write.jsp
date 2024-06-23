<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <!DOCTYPE html>
   <html lang="en">

   <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Messenger</title>
     <link rel="stylesheet" href="/css/messenger/write.css">
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
           <div><button>안읽음</button></div>
           <div><button>중요</button></div>
           <div><button>휴지통</button></div>
         </div>
         <hr>
         <div id="all-messenger">
           <p>전체쪽지</p>
         </div>
         <div id="receive-messenger">
           <p>받은쪽지</p>
         </div>
         <div id="send-messenger">
           <p>보낸쪽지</p>
         </div>
         <div id="save-messenger">
            <p>임시저장</p>
        </div>
       </div>



       <div id="messenger-main">
         <div id="messenger-search">
           <div><input type="text" placeholder="검색어를 입력해주세요."></div>
           <div><input type="button" value="검색"></div>
         </div>
         <div id="messenger-check">
           <div id="check-all"><input type="checkbox">전체선택</div>
           <div id="check-delete"><input type="button" value="삭제"></div>
         </div>
         <div id="messenger-content">
           <div><input id="checkbox-delete" type="checkbox"></div>
           <div><input id="checkbox-important" type="checkbox"></div>
           <div id="list-person">송예린언니씨</div>
           <div id="list-content">[베이비개발자 WORKS] 예린언니 어딨죠</div>
           <div id="list-date">6. 20. 11:39</div>
         </div>
       </div>



       <div id="messenger-write-page">
        <form action="/messenger/write" method="post" enctype="multipart/form-data">
         <div id="messenger-write-btn">
           <input id="messenger-send-btn" type="submit" value="보내기">
           <input id="messenger-save-btn" type="submit" formaction="/messenger/saved" value="임시저장">
           <hr>
         </div>

         <div id="messenger-write-infor">
           <div id="messenger-infor-receiver">
             <div id="receiver-name">받는사람</div>
             <input id="receiver-text" type="text" name="receiverEmpNo">
           </div>

           <div id="messenger-infor-title">
             <div id="messenger-title">제목</div>
             <input id="title-text" type="text" name="title">
           </div>

           <div id="messenger-infor-file">
             <div id="messenger-file">파일첨부</div>
             <input id="file-upload" type="button" value="내 PC">
           </div>
         </div>

         <div id="messenger-write-content">
           <input id="content-text" type="text" name="content">
         </div>
        </form>
       </div>
     </main>


   </body>

   </html>