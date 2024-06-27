
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


            const tdTag02=document.createElement("td");
            const menuNameTextNode=document.createTextNode(vo.menuName);
            tdTag02.appendChild(menuNameTextNode);
            trTag.appendChild(tdTag02);


            const tdTag03=document.createElement("td");
            const inputTag01=document.createElement("input");
            tdTag03.appendChild(inputTag01);
                inputTag01.setAttribute("type","checkbox");

                inputTag01.setAttribute("name",vo.menuNo)
                inputTag01.setAttribute("data-type","authSelectYn")
                 inputTag01.setAttribute("data",vo.authSelectYn)

                if(vo.authSelectYn ==='Y'){
                  inputTag01.setAttribute("checked", true);
                }
             trTag.appendChild(tdTag03);


            const tdTag04=document.createElement("td");
            const inputTag02=document.createElement("input");

            tdTag04.appendChild(inputTag02);
                inputTag02.setAttribute("type","checkbox");
                inputTag02.setAttribute("name",vo.menuNo);
                inputTag02.setAttribute("data-type","authInsertYn")
                inputTag02.setAttribute("data",vo.authInsertYn)
                if(vo.authInsertYn ==='Y'){
                inputTag02.setAttribute("checked", true);
                 }
            trTag.appendChild(tdTag04);


            const tdTag05=document.createElement("td");
            const inputTag03=document.createElement("input");
            tdTag05.appendChild(inputTag03);
                inputTag03.setAttribute("type","checkbox");
                inputTag03.setAttribute("name", vo.menuNo);
                inputTag03.setAttribute("data-type","authModifyYn")
                 inputTag03.setAttribute("data",vo.authModifyYn)
                if(vo.authModifyYn ==='Y'){
                  inputTag03.setAttribute("checked", true);
                }
            trTag.appendChild(tdTag05);

            const tdTag06=document.createElement("td");
            const inputTag04=document.createElement("input");
            tdTag06.appendChild(inputTag04);
                inputTag04.setAttribute("type","checkbox");
                inputTag04.setAttribute("name", vo.menuNo);
                inputTag04.setAttribute("data-type","authRemoveYn")
                inputTag04.setAttribute("data",vo.authRemoveYn)
                if(vo.authRemoveYn ==='Y'){
                  inputTag04.setAttribute("checked", true);
                }
            trTag.appendChild(tdTag06);
     }

    const menuListTable=document.querySelector("#menuList");
    menuListTable.append(tbodyTag);

  },
  error:function(){
  console.log("통신실패")}

})



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

        $('input[type="checkbox"]').each(function(){
        var menuNo=$(this).attr('name');      //1
        var authType=$(this).attr('data-type')    //authSelectYn
        var authValue=$(this).attr('data') //Y

        var checkboxVo={
          menuNo:menuNo,
          authType:authType,
          authValue:authValue
        }
         //배열에 차곡차곡 쌓아서
          checkboxVoArr.push(checkboxVo);
          })
console.log("checkboxVoArr 보내는 배열에는 뭐가 담겼을까?",checkboxVoArr)

        $.ajax({
          url:'/admin/update_auth',
          type:'post',
          data:JSON.stringify(checkboxVoArr),
          success:function(){
            console.log("통신성공");
          },error:function () {
            console.log("통신실패");
          }

        });
}