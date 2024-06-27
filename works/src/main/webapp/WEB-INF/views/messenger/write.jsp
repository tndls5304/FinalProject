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
        <form id="messenger-write-form" action="/messenger/write" method="post" enctype="multipart/form-data">
         <div id="messenger-write-btn">
           <input id="messenger-send-btn" type="submit" value="보내기">
           <hr>
         </div>

         <div id="messenger-write-infor">
           <div id="messenger-infor-receiver">
             <div id="receiver-name">받는사람</div>
             <select id="receiver-select">
                <option value="">사원 선택</option>
                <c:forEach var="employee" items="${employeeList}">
                    <!-- &nbsp; 이건 공백을 넣을 때 사용한다. -->
                    <option value="${employee.no}" name="receiverEmpNo" class="click-option">${employee.name}&nbsp;&nbsp;&nbsp;${employee.positionNo}&nbsp;&nbsp;&nbsp;${employee.deptNo}</option>
                </c:forEach>
             </select>
             <input type="hidden" id="receiverEmpNo" name="receiverEmpNo">
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


   <!-- jquery 넣기 -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

   <script>
   $(document).ready(function(){
       $('#receiver-select').on('change', function() {
           const employeeNo = $(this).val();
           $('#receiverEmpNo').val(employeeNo);
       });

       // 폼 제출 시 Ajax로 데이터를 전송한다.
       $('#messenger-write-form').on('submit', function(event) {
           // 폼의 기본 제출을 막는다. 왜 막냐? 폼 제출과 Ajax가 중복으로 이루어지다보니, 값이 두 번 전달된다.
           // 값이 두 번 전달된다 == oracle 데이터베이스에 값이 두 번 입력된다는 뜻.
           event.preventDefault();

           // 아래 변수에 담아주는 건 뭐냐?
           // 사용자가 form에 입력한 데이터를 수집하여 이를 Ajax 요청으로 서버에 전송하기 위해 작성되었다.
           const employeeNo = $('#receiverEmpNo').val();
           const title = $('#title-text').val();
           const content = $('#content-text').val();

           $.ajax({
                // 위에 form에서 이미 post로 /messenger/write에 보내주고 있다.
                // 그렇기에, form의 action 속성 값을 사용한다.
               url: $(this).attr('action'),
               method: "post",
               data: {
                   // 변수에 담아준 걸 data로 보내준다.
                   receiverEmpNo: employeeNo,
                   title: title,
                   content: content
               },
               success: (data) => {
                   console.log("쪽지 보내기 성공!");
                   console.log(data);
               },
               error: (xhr, status, error) => {
                   console.log("쪽지 보내기 실패...");
               }
           });
       });
   });
   </script>
