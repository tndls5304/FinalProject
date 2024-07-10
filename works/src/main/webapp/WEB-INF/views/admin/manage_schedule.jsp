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

   <!-------달력------------>
         <script src='https://cdn.jsdelivr.net/npm/fullcalendar/index.global.min.js'></script>
           <script>
               document.addEventListener('DOMContentLoaded', function() {
                 var calendarEl = document.getElementById('calendar');
                 var calendar = new FullCalendar.Calendar(calendarEl, {
                           locale : 'ko',                                            //한국어 설정
                           initialView: 'dayGridMonth',
                           selectable: true,                                        //달력 일자 드래그 설정가능
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
                                            var myDetailModal=document.getElementById("myDetailModal");

                                            var titleBox=document.querySelector('#titleDetail');
                                            titleBox.value=info.event.title;

                                            var contentBox=document.querySelector('#contentDetail');
                                            contentBox.value=info.event.content;

                                            var placeNameBox=document.querySelector('#placeNameDetail');
                                            placeNameBox.value=info.event.extendedProps.placeName;

                                            var latitudeBox=document.querySelector('#latitudeDetail');
                                            latitudeBox.value=info.event.extendedProps.latitude;

                                            var longitudeBox=document.querySelector('#longitudeDetail');
                                            longitudeBox.value=info.event.extendedProps.longitude;


                                            var openRangeBox=document.querySelector('#openRangeDetail');
                                            openRangeBox.value=info.event.extendedProps.openRangeNo;
                                            //파트너가져와야됨
                                            var insertDateBox=document.querySelector('#insertDate');
                                            insertDateBox.value=info.event.extendedProps.insertDate;

                                            var updateDateBox=document.querySelector('#updateDate');
                                            updateDateBox.value=info.event.extendedProps.updateDate;

                                            myDetailModal.style.display="block";

                                            console.log('제목: ' + info.event.title);
                                            console.log('내용: ' +  info.event.extendedProps.content);
                                            console.log('캘린더no: ' +  info.event.extendedProps.no);
                                            console.log('오픈범위openRangeNo: ' +  info.event.extendedProps.openRangeNo);
                                            console.log('장소이름placeName: ' +  info.event.extendedProps.placeName);
                                            console.log('수정날짜updateDate: ' +  info.event.extendedProps.updateDate);
                                            console.log('위도 latitude: ' +  info.event.extendedProps. latitude);
                                             console.log('경도longitude: ' +  info.event.extendedProps.longitude);


                                            // change the border color just for fun 이벤트확인후에 바뀌는 색깔
                                            info.el.style.borderColor = 'red';
                                          },
                           editable: true,                                          //수정가능
                           eventChange: function(obj) {                             // 이벤트가 수정되면 발생하는 이벤트
                                                             console.log(obj);
                             },
                           eventRemove: function(obj){                              // 이벤트가 삭제되면 발생하는 이벤트
                                                           console.log(obj);
                             },
                           timeZone: 'UTC',

                           customButtons:{
                                    myCustomButton:{
                                        text:"목록보기",
                                        click:function(){
                                            alert('후순위니까 클릭하지마로');
                                         }
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
                           calendar.addEvent({
                                title:calendarVo.title,
                                start:calendarVo.startDate,
                                end:calendarVo.endDate,

                                //api에서 제공안되는 변수명 내가 지음
                                insertDate:calendarVo.insertDate,
                                updateDate:calendarVo.updateDate,
                                no:calendarVo.no,
                                content:calendarVo.content,
                                openRangeNo:calendarVo.openRangeNo,
                                placeName:calendarVo.placeName,            //장소
                                updateDate:calendarVo.updateDate,
                                //후순위
                                latitude:calendarVo.latitude,            //위도
                                longitude:calendarVo.longitude            //경도
                                });
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



<!---------달력에 일정등록모달 --------------->
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
                      <option disabled selected>필수🍊공개범위설정 </option>
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


<!------- 참여자 선택 모달 -------->
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


 <!-- -----내가쓴 일정 상세보기모달 --------------->
    <div class="modal-my-detail" id="myDetailModal">
            <button class="modal-close" id="closeMyDetailModal">닫기</button>
            <h2>일정 상세보기& 수정하기</h2>
            <input type="text" id="titleDetail" class="input-title">
            <textarea id="contentDetail"></textarea>
            <h3 id="addressDetail">일정 장소:
                    <input id="placeNameDetail"    placeholder="장소이름">
                    <input id="latitudeDetail"     placeholder="위도">
                    <input id="longitudeDetail"    placeholder="경도">
                <button onclick="showMapDetail()">조회</button>
            </h3>

            <select id="openRangeDetail" onchange="changeOpenRange(this.value)">
                <option disabled selected >필수🍋공개범위설정 </option>
                <option value="1">전체공개</option>
                <option value="2">참여자 지정하기 </option>
                <option value="3">개인 일정</option>
            </select>

            <div id="partnerPlace" class="partner-place">
            </div>

            <h4>작성날짜:<span id="insertDate"></span></h4>
            <h4>수정날짜:<span id="updateDate"></span></h4>

            <button class="btn-insert-schedule" onclick="()" > 내용 수정하기</button>
    </div>

 <!--------- 지도 모달 ----------------->
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