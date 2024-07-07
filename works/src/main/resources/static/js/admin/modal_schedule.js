// 일정등록 모달창 닫기 버튼 클릭 시 이벤트 핸들러
const closeInsertModal = document.getElementById('closeInsertModal');
const insertModal = document.getElementById('insertModal');

    closeInsertModal.addEventListener('click', function() {
        insertModal.style.display = 'none';
    });

// 참여자선택  모달창 닫기 버튼 클릭 시 이벤트 핸들러
const closePartnerModal = document.getElementById('closePartnerModal');
const partnerModal = document.getElementById('partnerModal');

    closePartnerModal.addEventListener('click', function() {
        partnerModal.style.display = 'none';
    });


//일정등록모달창에서 공개범위 선택 select 요소에 변경이벤트가 발생했을때 호출됨
function changeOpenRange(value){
    var partnerModal = document.getElementById("partnerModal");
            //옵션조건에 따라 모달창 ✔️✔️✔️✔️공부하기
    if(value==="2"){
    partnerModal.style.display = 'block';
    }else{
    partnerModal.style.display = 'none';

    }
}


//참여자 모달창에서 검색기능

function changeDept(deptNo) {
     var tBodyTag=document.querySelector('#partnerModalTBody');
     tBodyTag.innerText="";
$.ajax({
        url:'/admin/give/emp-in-dept',
        method:'get',
        data:{deptNo:deptNo},
        success:function(voList){

       for(let i=0;i<voList.length;i++){
           var empVo=voList[i];
          const trTag=document.createElement('tr');
          const tdTag1=document.createElement('td');
          const inputTag=document.createElement('input');
          inputTag.setAttribute('type','checkbox');
          inputTag.setAttribute('name','partnerCheck');
          const tdTag2=document.createElement('td');
          const tdTag3=document.createElement('td');
          trTag.appendChild(tdTag1);
          tdTag1.appendChild(inputTag);
          trTag.appendChild(tdTag2);
          trTag.appendChild(tdTag3);

          tdTag2.innerHTML= empVo.no;
          tdTag3.innerHTML=empVo.name;
          tBodyTag.appendChild(trTag);
       }

        },
        error:function(errorMsg){
        console.log(errorMsg)
        }
    })
}

//체크박스 모두 선택 모두 해제
function allChangeCheck(){
    var checkTags= document.getElementsByName("partnerCheck")
    if(document.getElementById("topCheckbox").checked==true){
            for(var i=0;i<checkTags.length;i++){
              checkTags[i].checked=true;
            }
        }
        if(document.getElementById("topCheckbox").checked==false){
            for(var i=0;i<checkTags.length;i++){
              checkTags[i].checked=false;
            }
        }
}

//----------------------------------------------------------------------------------------------------지도
//----------------------------------------------장소검색 버튼 클릭시에 지도 나오는것
function showMap(){
  var mapTag=document.querySelector('#map');
    mapTag.style.display='block';
     var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
              var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                level: 3 //지도의 레벨(확대, 축소 정도)
              };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    //----------------------------------------------장소 주소랑 장소 이름을 알때 보여주기
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

        // 정상적으로 검색이 완료됐으면
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });
}


