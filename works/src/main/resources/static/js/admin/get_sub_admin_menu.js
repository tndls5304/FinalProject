
$.ajax({
    url:"/admin/get_sub_admin_menu",
    method:"GET",
    success:function(voList){
console.log("voList란?",voList)
 const tbodyTag= document.createElement("tbody");

for(let i=0; i<voList.length;i++){
     const vo= voList[i];
console.log("vo는?",vo)
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
        inputTag01.setAttribute("name","menuNo:"+vo.menuNo+","+"authSelectYn:"+vo.authSelectYn);
        if(vo.authSelectYn ==='Y'){
          inputTag01.setAttribute("checked", true);
        }
     trTag.appendChild(tdTag03);


    const tdTag04=document.createElement("td");
    const inputTag02=document.createElement("input");

    tdTag04.appendChild(inputTag02);
        inputTag02.setAttribute("type","checkbox");
        inputTag02.setAttribute("name","menuNo:"+ vo.menuNo+","+"authInsertYn:"+vo.authInsertYn);
        if(vo.authInsertYn ==='Y'){
        inputTag02.setAttribute("checked", true);
         }
    trTag.appendChild(tdTag04);


    const tdTag05=document.createElement("td");
    const inputTag03=document.createElement("input");
    tdTag05.appendChild(inputTag03);
        inputTag03.setAttribute("type","checkbox");
        inputTag03.setAttribute("name", "menuNo:"+ vo.menuNo+","+"authModifyYn:" + vo.authModifyYn);
        if(vo.authModifyYn ==='Y'){
          inputTag03.setAttribute("checked", true);
        }
    trTag.appendChild(tdTag05);

    const tdTag06=document.createElement("td");
    const inputTag04=document.createElement("input");
    tdTag06.appendChild(inputTag04);
        inputTag04.setAttribute("type","checkbox");
        inputTag04.setAttribute("name", "menuNo:"+ vo.menuNo+","+"authRemoveYn"+ vo.authRemoveYn );
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

console.log("연결돼라")