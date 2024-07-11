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
//내가 쓴 일정 상세보기 모달창 닫기 버튼 클릭시 이벤트 핸들러
const closeMyDetailModal = document.getElementById('closeMyDetailModal');
const myDetailModal = document.getElementById('myDetailModal');

    closeMyDetailModal.addEventListener('click', function() {
        myDetailModal.style.display = 'none';
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

//참여자 선택 모달창에서 체크박스 모두 선택 모두 해제 💦💦공부
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

//참여자 모달창에서 표에 있는 사람들 하위에 있는 목록에 넣기
function addList(){
//tbody에 있는 체크된 사람들의 사원번호와 이름 하위 디브에 넣기
var checkTags= document.getElementsByName("partnerCheck");
var partnerList= document.getElementById("partnerList");
for(let i=0;i<checkTags.length; i++){
     var checkTag=checkTags[i];
        if(checkTag.checked){
            //checkTag:체크된 박스
            var trTag=checkTag.parentElement.parentElement; // 체크된 박스의 부모(td)의 부모(tr)는 tr태그  공부💦💦
            var empNo=trTag.children[1].innerText;          //사원번호
            var empName= trTag.children[2].innerText; // 이름

            //이미 추가된 사원인지 확인
             var alreadyAdded = false;
             var empDivs = document.querySelectorAll('.empDiv')
             for (let j = 0; j < empDivs.length; j++) {
                  var span1 = empDivs[j].querySelector('span:first-child');
                  var span2 = empDivs[j].querySelector('span:nth-child(2)');
                  if (span1.innerText === empNo && span2.innerText === empName) {
                     alreadyAdded = true;
                     break;
                     }
             }
             /*<div class="empDiv">
                    <span>번호</span>
                    <span>이름</span>
                    <span class="removeEmp" onclick="removeEmp()">x</span>
               </div>

                */
            if (!alreadyAdded) {
                //목록에 추가할 새로운 디브 생성
                var empDiv=document.createElement('div');
                //속성
                empDiv.setAttribute('class','empDiv');
                //사원번호가 담기는 span태그 생성
                var span1=document.createElement('span');
                span1.innerText=empNo;

                var span2=document.createElement('span');
                span2.innerText=empName;

                var span3=document.createElement('span');
                span3.innerText='x';
                span3.setAttribute('class','removeEmp');
                span3.setAttribute('onclick','removeEmp(this)');
                //
                empDiv.appendChild(span1);
                empDiv.appendChild(span2);
                empDiv.appendChild(span3);
                partnerList.appendChild(empDiv);
           }
        }
    }


}


//참여자 모달창에서 삭제스팬 클릭이벤트 걸기
function removeEmp(myselfTag){
myselfTag.parentElement.remove();    //DOM에서 완전히 제거하는거💦💦 즉, 해당 요소와 그 자식 요소들이 모두 제거함 .innerHTML = "";이렇게 하면 Dom에 그대로 남아있음
}


//참여자 모달창에서 [참여자반영버튼] 누르면 동작
function insertPartner(){
    var partnerList=document.getElementById("partnerList");
    var partnerPlace=document.getElementById("partnerPlace");
    partnerPlace.innerHTML=partnerList.innerHTML;
}

//일정 등록하기 버튼을 눌렀을때 동작
function insertSchedule(){
        var title=document.getElementById("titleInsert").value;
        var startDate=document.getElementById("startDate").value;
        var endDate=document.getElementById("endDate").value;
        var content=document.getElementById("content").value;
        var placeName=document.getElementById("placeName").value;
        var latitude=document.getElementById("latitude").value;
        var longitude=document.getElementById("longitude").value;
        var openRangeNo=document.getElementById("openRangeNo").value;

        //배열로 만들어서 가져갈꼬!
        var arr = [];
        var empDivs=document.getElementById("partnerPlace").children;
            for(let i=0; i<empDivs.length; i++){
                var empDiv=empDivs[i];
                var empNo= empDiv.children[0].innerText; //번호

                var partnerVo={empNo:empNo}
                                      //객체 만들어서
                arr.push(partnerVo);                        //배열에 넣어주기
            }

            console.log("--------------서버로 보내질 데이터 확인작업");
            console.log("title",title);
            console.log("startDate",startDate);
            console.log("endDate",endDate);
            console.log("content",content);
            console.log("placeName",placeName);
            console.log("latitude",latitude);
            console.log("longitude",longitude);
            console.log("openRangeNo",openRangeNo);
            console.log("참여자 배열은??");
            console.log("arr",arr);


          $.ajax({
            url:'/admin/calendar',
            method:'POST',
            contentType : 'application/json',
            data:JSON.stringify({                                  //js객체를 제이슨문자열로 바꾸기
                                title:title,
                                startDate:startDate,
                                endDate:endDate,
                                content:content,
                                placeName:placeName,
                                latitude:latitude,
                                longitude:longitude,
                                openRangeNo:openRangeNo,
                                partnerList:arr                        //배열담기
                                }),
                success:function(result){
                alert(result);
                location.reload();  // 페이지 리로드
                },
                error:function(errorMsg){
                alert(errorMsg.responseText);
                }
           })//ajax

}//일정등록하기




//-------------------일정 수정하기 버튼 클릭시 -------------------------
function update(){
      var no=document.getElementById("detailNo").innerText;
      var title=document.getElementById("titleDetail").value;
      var startDate=document.getElementById("startDetail").value;
      var endDate=document.getElementById("endDetail").value;
      var content=document.getElementById("contentDetail").value;
      var placeName=document.getElementById("placeNameDetail").value;
      var latitude=document.getElementById("latitudeDetail").value;
      var longitude=document.getElementById("longitudeDetail").value;
      var openRangeNo=document.getElementById("openRangeDetail").value;

      //배열로 만들어서 가져갈꼬!
      var arr = [];
      var empDivs=document.getElementById("partnerDetail").children;
           for(let i=0; i<empDivs.length; i++){
                var empDiv=empDivs[i];
                var empNo= empDiv.children[0].innerText; //번호

                var partnerVo={empNo:empNo,
                               calendarNo:no}
                                          //객체 만들어서
                    arr.push(partnerVo);                        //배열에 넣어주기
            }


      $.ajax({
            url:'/admin/calendar/update',
            method:'POST',
            contentType : 'application/json',
            data:JSON.stringify({                                  //js객체를 제이슨문자열로 바꾸기
                                no:no,
                                title:title,
                                startDate:startDate,
                                endDate:endDate,
                                content:content,
                                placeName:placeName,
                                latitude:latitude,
                                longitude:longitude,
                                openRangeNo:openRangeNo,
                                partnerList:arr                        //배열담기
                                }),
                success:function(result){
                alert(result);
                location.reload();  // 페이지 리로드
                },
                error:function(errorMsg){
                alert(errorMsg.responseText);
                }
      })//ajax

}//일정 수정하기 끝!



//등록된 캘린더를 클릭했을때 상세모달창이 나오고
//다시 공개범위를 바꾸고 싶을때!!!! 공개범위 select option을 클릭했을떄?
//일정등록모달창에서 공개범위 선택 select 요소에 변경이벤트가 발생했을때 호출됨
function changeOpenRangeDetail(value){
  document.getElementById("partnerDetail").innerHTML="";
            //옵션조건에 따라 모달창 ✔️✔️✔️✔️공부하기

    if(value==="2"){
    partnerModal.style.display = 'block';
    }else{
    partnerModal.style.display = 'none';

    }
}

