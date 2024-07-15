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
   <!-------달력 api----------->
         <script src='https://cdn.jsdelivr.net/npm/fullcalendar/index.global.min.js'></script>
   <!-- 지도api 정적파일-->
          <link rel="stylesheet" href="/css/admin/map_calendar.css">
          <script defer src="/js/admin/map_calendar.js"></script>
   <!-- 수작업 모달 정적파일-->
         <link rel="stylesheet" href="/css/admin/modal_calendar.css">
         <script defer src="/js/admin/modal_calendar.js"></script>
    <!---달력설정 기본설정 커스터마이징------------>
         <script defer src="/js/admin/setting_calendar.js"></script>
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
               <input id="latitude"     placeholder="위도" class="hidden">
               <input id="longitude"    placeholder="경도" class="hidden">
            <button onclick="showMap()" class="hidden">선택</button>
      </h3>
      <select id="openRangeNo" onchange="changeOpenRange(this.value)" required>  <!-- 공부하깅 🐬자신의 value를 바로 보내줌  -->
                      <option disabled selected value="">필수🍊공개범위설정</option>
                      <option value="1">전체공개</option>
                      <option value="2">참여자 지정하기 </option>
                      <option value="3">개인 일정</option>
     </select>
     <div id="partnerPlace" class="partners-place">
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
            <h6>캐린더번호:<span id=detailNo></span></h6>
            <h5>startDay <input type="date" id="startDetail"></h5>
            <h5>endDay<input type="date" id="endDetail"></h5>
            <button class="modal-close" id="closeMyDetailModal">닫기</button>
            <h2>일정 상세보기& 수정하기</h2>
            <input type="text" id="titleDetail" class="input-title">
            <textarea id="contentDetail"></textarea>
            <h3 id="addressDetail">일정 장소:
                    <input id="placeNameDetail"    placeholder="장소이름">
                    <input id="latitudeDetail"     placeholder="위도" class="hidden">
                    <input id="longitudeDetail"    placeholder="경도" class="hidden">
                <button onclick="showMapDetail()" class="hidden">조회</button>
            </h3>

            <select id="openRangeDetail"onchange="changeOpenRangeDetail(this.value)"  >
                <option disabled selected value="">필수🍋공개범위설정 </option>
                <option value="1">전체공개</option>
                <option value="2">참여자 지정하기 </option>
                <option value="3">개인 일정</option>
            </select>

            <div id="partnerDetail" class="partners-place">
                     <%--여기에 들어올 정보는 js에서 그려줄 예정  <div class="empDiv">
                                                                <span>번호</span>
                                                                <span>이름</span>
                                                                <span class="removeEmp" onclick="removeEmp()">x</span>
                                                           </div>
                       --%>
            </div>

            <h4>작성일시:<span id="insertDate"></span></h4>
            <h4>수정일시:<span id="updateDate"></span></h4>

            <button class="btn-insert-schedule" onclick="updateCalendar()"> 수정하기</button>
              <button class="btn-calendar-delete" onclick="deleteCalendar()"> 삭제</button>
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