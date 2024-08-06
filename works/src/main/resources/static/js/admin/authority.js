//모든 체크 박스 헤재 혹은 체크하기
$(document).ready(function() {
    $("#selectAllCheckBox").change(function() {
        const checkboxes = document.querySelectorAll("#menuList input[type=checkbox]");
        const isChecked = $(this).is(":checked");
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = isChecked;
            checkboxes[i].value = isChecked? "Y" : "N";
        }
    });
});

$.ajax({
    url:"/admin/get_sub_admin_menu",
    method:"GET",
    success:function(voList){
    console.log("서버로부터 받은데이터 확인하기",voList)

    const tbodyTag= document.createElement("tbody");

    for(let i=0; i<voList.length;i++){
             const vo= voList[i];

             const trTag= document.createElement("tr");
             tbodyTag.appendChild(trTag);


            const tdTag01= document.createElement("td");
            const menuNoTextNode= document.createTextNode(vo.menuNo);
            tdTag01.appendChild(menuNoTextNode);
            trTag.appendChild(tdTag01);
            const inputTagMenuNo=document.createElement("input");
            inputTagMenuNo.setAttribute("type","hidden");
            inputTagMenuNo.setAttribute("name",'menuNo')
            inputTagMenuNo.setAttribute("value",vo.menuNo)
            tdTag01.appendChild(inputTagMenuNo);


            const tdTag02=document.createElement("td");
            const menuNameTextNode=document.createTextNode(vo.menuName);
            tdTag02.appendChild(menuNameTextNode);
            trTag.appendChild(tdTag02);


            const tdTag03=document.createElement("td");
            const inputTag01=document.createElement("input");
            tdTag03.appendChild(inputTag01);
                inputTag01.setAttribute("type","checkbox");
                inputTag01.setAttribute("name","authSelectYn")
                 inputTag01.setAttribute("value",vo.authSelectYn)

                if(vo.authSelectYn ==='Y'){
                 inputTag01.setAttribute("checked", true);
                }
             trTag.appendChild(tdTag03);


            const tdTag04=document.createElement("td");
            const inputTag02=document.createElement("input");

            tdTag04.appendChild(inputTag02);
                inputTag02.setAttribute("type","checkbox");
                inputTag02.setAttribute("name","authInsertYn")
                inputTag02.setAttribute("value",vo.authInsertYn)
                if(vo.authInsertYn ==='Y'){
                inputTag02.setAttribute("checked", true);
                 }
            trTag.appendChild(tdTag04);


            const tdTag05=document.createElement("td");
            const inputTag03=document.createElement("input");
            tdTag05.appendChild(inputTag03);
                inputTag03.setAttribute("type","checkbox");
                inputTag03.setAttribute("name","authModifyYn")
                 inputTag03.setAttribute("value",vo.authModifyYn)
                if(vo.authModifyYn ==='Y'){
                  inputTag03.setAttribute("checked", true);
                }
            trTag.appendChild(tdTag05);

            const tdTag06=document.createElement("td");
            const inputTag04=document.createElement("input");
            tdTag06.appendChild(inputTag04);
                inputTag04.setAttribute("type","checkbox");
                inputTag04.setAttribute("name","authRemoveYn")
                inputTag04.setAttribute("value",vo.authRemoveYn)
                if(vo.authRemoveYn ==='Y'){
                  inputTag04.setAttribute("checked", true);
                }
            trTag.appendChild(tdTag06);
     }

    const menuListTable=document.querySelector("#menuList");
    menuListTable.appendChild(tbodyTag);

//체크박스의 상태가 바뀌면 함수호출하는 이벤트리스너 등록하기
 const checkboxTagList = document.querySelectorAll("input[type=checkbox]");
     for(let i=0; i<checkboxTagList.length ;i++){
        const checkboxTag= checkboxTagList[i];
        checkboxTag.addEventListener('change',function(){
        checkBoxStatusAccept(checkboxTag)
        });
     }
  },
  error:function(){
  console.log("통신실패")}
})


//체크박스의 상태 바뀌면 호출되는 함수
function checkBoxStatusAccept(checkboxTag){
    if(checkboxTag.checked){
            checkboxTag.value='Y';
      }
    else{
            checkboxTag.value='N';
      }
}





/* HTML 문서의 모든 요소가 로드된 후에 실행
수정하기 버튼눌렀을때 ajax로 데이터 보내기  menuNo/authType/authValue
*/
document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#modifyBtn").addEventListener("click", modify);
});

function modify(evt){
console.log("수정하기 버튼 활성화되었슴다")
      var checkboxVoArr=[];
        //체크박스의 요소를 반복해서 꺼낸뒤에 하나의 객체를 만들고

        var trList = $('tr')
        var resultList = [];
        for (let idx = 1; idx <trList.length; idx++) {
            var tr = trList[idx];
                 //find: 자식요소중에 input태그 골라서 찾는거
            let inputList = $(tr).find('input');

            let obj = {};
            for (let sIdx = 0; sIdx < inputList.length; sIdx++){
                    let inputTag = inputList[sIdx];
                    obj[inputTag.name]= inputTag.value;
            }
            resultList.push(obj);
        }
 console.log("서버로 보낼 리스트는 ??",resultList)
console.log("서버에 보낼 객체를 json으로바꿈",JSON.stringify(resultList))

        $.ajax({
          url:'/admin/update_auth',
          type:'post',
          contentType : "application/json; charset=utf-8",
          data:JSON.stringify(resultList),
          success:function(data){
            alert(data);
            location.reload();
          },error:function () {
            console.log("통신실패");
          }
        });
}
