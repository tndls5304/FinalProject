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
