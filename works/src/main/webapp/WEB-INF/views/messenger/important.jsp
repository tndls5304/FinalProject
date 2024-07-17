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
     <!-- fontawesome 이 부분 추가 -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
     <!-- <script src="/js/messenger/all.js"></script> -->
   </head>

   <body>

     <%@ include file="/WEB-INF/views/layout/messenger/nav.jsp" %>

     <main>
       <div id="sidebar">
         <div id="messenger-write">
            <button onclick="moveToWrite()">쪽지쓰기</button>
         </div>
         <div id="messenger-status">
            <a href="/messenger/unread"><div><button>안읽음 [<c:out value="${unreadCount}"/>]</button></div></a>
            <a href="/messenger/important"><div><button>중요</button></div></a>
            <a href="/messenger/trash"><div><button>휴지통</button></div></a>
         </div>
         <hr>
         <div id="all-messenger">
            <a href="/messenger/all"><p><i class="fa-brands fa-facebook-messenger"></i> 전체쪽지</p></a>
         </div>
         <div id="receive-messenger">
            <a href="/messenger/received"><p><i class="fa-solid fa-reply"></i> 받은쪽지</p></a>
         </div>
         <div id="send-messenger">
            <a href="/messenger/sent"><p><i class="fa-solid fa-share"></i> 보낸쪽지</p></a>
         </div>
       </div>



       <div id="messenger-main">
        <form action="/messenger/all" method="get" enctype="multipart/form-data">
         <div id="messenger-search">
           <div><input type="text" id="search-keyword" name="keyWord" placeholder="검색어를 입력해주세요."></div>
           <div><input type="button" value="검색" onclick="searchByKeyword()"></div>
         </div>
         <div id="messenger-check">
           <div id="check-all"><input type="checkbox" id="select-all">전체선택</div>
           <div id="check-delete"><input type="button" value="삭제" onclick="trashMessen()"></div>
         </div>
         <div id="messenger-content">
           <c:forEach var="message" items="${voList}">
            <div class="messenger-item">
               <div><input class="checkbox-delete" type="checkbox" value="${message.messenNo}"></div>
               <!-- <div><input id="checkbox-important" type="checkbox"></div> -->
               <div id="list-person">${message.name}</div>
               <div id="list-title" class="click-title">${message.title}</div>
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



        //쪽지 휴지통으로 이동 Ajax
       // 전체 선택 체크박스 기능
       document.querySelector('#select-all').addEventListener('change', function() {
           const checkboxes = document.querySelectorAll('.checkbox-delete');
           checkboxes.forEach(checkbox => {
               checkbox.checked = this.checked;
           });
       });

       // 삭제 버튼 클릭 시 선택된 쪽지 삭제
       function trashMessen() {
           const selectedMessages = [];
           document.querySelectorAll('.checkbox-delete:checked').forEach(item => {
               selectedMessages.push(item.value);
           });

           if (selectedMessages.length > 0) {
               $.ajax({
                   url: "/messenger/trashStatus",
                   method: "post",
                   data: {
                       messenNoList: selectedMessages
                   },
                   success: (data) => {
                       console.log("쪽지 삭제 성공");
                       location.reload();
                   },
                   error: (xhr, status, error) => {
                       console.log("쪽지 삭제 실패");
                   }
               });
           } else {
               alert("삭제할 쪽지를 선택하세요.");
           }
       }





       // /messenger/all에서 쪽지쓰기 눌렀을 때, 쪽지쓰기 페이지(/messenger/write)로 이동
       function moveToWrite(){
           window.location.href = "/messenger/write";
       }


       // 검색기능을 위한 자바스크립트 함수
       function searchByKeyword() {
           var keyword = document.getElementById("search-keyword").value;

           // 새로운 form 생성
           var searchForm = document.createElement("form");
           searchForm.method = "get"; //form 전송 방식 - GET
           searchForm.action = "/messenger/search";

           // 검색을 하기 위해 input 요소 생성
           var input = document.createElement("input");
           input.type = "hidden"; //화면에 보이지 않도록 hidden 설정
           input.name = "keyWord"; //Mapper에 설정한 값으로 name 지정
           input.value = keyword; //input 값에 keyword 설정

           searchForm.appendChild(input);

           document.body.appendChild(searchForm);
           searchForm.submit();
       }


     </script>
