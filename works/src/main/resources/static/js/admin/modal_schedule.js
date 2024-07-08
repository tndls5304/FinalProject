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
                span3.setAttribute('id','removeEmp');

                //
                empDiv.appendChild(span1);
                empDiv.appendChild(span2);
                empDiv.appendChild(span3);
                partnerList.appendChild(empDiv);
           }
        }
    }


//삭제버튼에 클릭이벤트
    var removeEmp= document.getElementById("removeEmp");
      removeEmp.addEventListener('click',function(){
       removeEmp.parentElement.remove();                   //DOM에서 완전히 제거하는거💦💦 즉, 해당 요소와 그 자식 요소들이 모두 제거함 .innerHTML = "";이렇게 하면 Dom에 그대로 남아있음
      })

}


