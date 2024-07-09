<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   <!-- 제이쿼리-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

   <!-- 공통css-->
        <link rel="stylesheet" href="/css/layout/admin/same.css">

   <!--지도 js api불러오기-->
          <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5da1687d10c0a4c9bb9e0e849d2b635a&libraries=services"></script>


   <!-- 모달 정적파일-->
         <link rel="stylesheet" href="/css/admin/modal_schedule.css">
         <script defer src="/js/admin/modal_schedule.js"></script>
   <!-- 지도api 정적파일-->
          <link rel="stylesheet" href="/css/admin/map_schedule.css">
          <script defer src="/js/admin/map_schedule.js"></script>

   <!--달력-->
         <script src='https://cdn.jsdelivr.net/npm/fullcalendar/index.global.min.js'></script>
           <script>
               document.addEventListener('DOMContentLoaded', function() {
                 var calendarEl = document.getElementById('calendar');
                 var calendar = new FullCalendar.Calendar(calendarEl, {
                           initialView: 'dayGridMonth',
                                    //드래그했을때 동작하는거 :모달창띄우기
                           selectable: true,
                           select:function( selectionInfo) {
                                 const insertModal = document.getElementById("insertModal");
                                 const startDate =document.querySelector("input[id=startDate]")
                                 const endDate =document.querySelector("input[id=endDate]")
  //TODO 3초뒤에 모달창 나오게 하는게 이게 맞는지 확인해보기 날짜가 이상해
                                 insertModal.style.display="block",3000
                                 startDate.value=selectionInfo.startStr;
                                 endDate.value=selectionInfo.endStr;
                             },
                           eventClick:  function(info) {
                                        console.log('eventClick');
                                            console.log(info);
                                            alert('Event: ' + info.event.title);
                                            alert('Content: ' + info.event.content);

                                            // change the border color just for fun 이벤트확인후에 바뀌는 색깔
                                            info.el.style.borderColor = 'red';
                                          },

                           timeZone: 'UTC',

                           customButtons:{
                                myCustomButton:{
                                    text:"일정 추가하기"
                                    }

                            },
                            //헤더 툴바
                            headerToolbar:{
                            left: 'prev,next today,myCustomButton,mySaveButton'
                            }
                         });                                                    //calendar기본설정

                //화면 보여주기
                calendar.render();
                //내가 원하는작업 캘린더리스트 쭉 다 조회해오자
                $.ajax({
                         url:"/admin/calendar/all",
                         success:function(list){
                           for(let i = 0; i < list.length;i++){
                           var calendarVo=list[i];
                           calendar.addEvent(calendarVo);
                          }
                         }
                     })




               //-----------------------------



               //=-------------------------
              })//DOMContentLoaded
           </script>

    <!-- 글씨체 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

</head>
<body>
        <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

<main>
            <div class="header">
                    <h1>스케줄</h1>
                    <div><h3>지정내역</h3></div>
            </div>

         <div id='calendar'></div>



<!-- -----일정등록모달 --------------->
<div class="modal-insert" id="insertModal">
      <button class="modal-close" id="closeInsertModal">닫기</button>
      <h2>일정 등록</h2>
      <input type="text" id="titleInsert" class="input-title" placeholder="제목을 입력하세요">
      <h5>startDate <input type="date" id="startDate"></h5>
      <h5>endDate<input type="date" id="endDate"></h5>
      <textarea id="content" placeholder="내용을 입력해 주세요."></textarea>
      <h3 id="address">일정 장소:
               <input id="placeName"    placeholder="장소">
               <input id="latitude"      class="nonShow"   placeholder="위도">
               <input id="longitude"     class="nonShow"    placeholder="경도">
            <button onclick="showMap()">선택</button>
      </h3>
     <select id="openRangeNo" onchange="changeOpenRange(this.value)">
                      <option disabled selected>공개범위설정 🍊</option>
                      <option value="1">전체공개</option>
                      <option value="2">참여자 지정하기 </option>
                      <option value="3">개인 일정</option>
     </select>

     <div id="partnerPlace" class="partner-place">
             <%--여기에 들어올 정보    <div class="empDiv">
                                         <span>번호</span>
                                         <span>이름</span>
                                         <span class="removeEmp" onclick="removeEmp()">x</span>
                                  </div>
    --%>
     </div>

      <button class="btn-insert-schedule" onclick="insertSchedule()" >등록하기</button>

</div>


  <!-- 참여자 선택 모달 -->
      <div class="modal-partner" id="partnerModal">
            <h2 >참여자선택</h2>
            <button class="modal-close" id="closePartnerModal">닫기</button>
            <h4> 부서선택:<select id=deptSelect onchange="changeDept(this.value)">
                    <option disabled selected id="deptNo">부서 목록입니다 🍊</option>
                    <option value="1">인사부</option>
                    <option value="2">총무부</option>
                    <option value="3">개발부</option>
                    <option value="4">영업부</option>
            </select></h4>
            <table width="300px" border="1">
                  <thead>
                    <tr> <td><input type="checkbox" id="topCheckbox" onclick="allChangeCheck();"> </td>
                         <td>사원번호</td>
                         <td>이름</td>
                     </tr>
                  </thead>
                  <tbody id="partnerModalTBody">
                  </tbody>
            </table>
            <button class="btnPlus" onclick="addList()">목록에 추가</button>
            <h3>선택한 참여자 목록</h3>
            <div class="partner-list"id="partnerList">

            </div>
            <button class="btn-insert" onclick="insertPartner()">참여자 반영</button>
      </div>


 <!-- 지도 모달 -->
 <div id="modalMap" class="mapModal">
    <button style="
     position: absolute;
     top: 0;
     right: 0;
     z-index: 9999;
     margin: 0;">X</button>
      <div class="map_wrap">
            <div id="map" style="width:600px;height:600px;posi overflow:hidden;"></div>
            <div id="menu_wrap" class="bg_white">
                <div class="option">
                        <div>
                            <form onsubmit="searchPlaces(); return false;">
                            키워드 : <input type="text" value="이태원 맛집" id="keyword" size="15">
                            <button type="submit">검색하기</button>
                            </form>
                        </div>
                </div>
                <hr>
                <ul id="placesList"></ul>
                <div id="pagination"></div>
            </div>
      </div>
 </div>
 <!-- 지도 모달 끝-->



</main>


</body>
</html>