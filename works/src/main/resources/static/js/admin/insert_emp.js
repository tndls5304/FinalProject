//모든 부서명가져오기
$.ajax({
  url:"http://localhost:8080/admin/select_dept",
     method:"GET",
     success:function(deptVoList){
      const deptSelect=document.querySelector("#deptSelect");

      let str="<option>부서선택</option>";

      for(let i=0;i<deptVoList.length;i++){
        str+="<option value='"
        str+=deptVoList[i].no
        str+="'>"
        str+=deptVoList[i].name
        str+="</option>"
          }
     deptSelect.innerHTML=str;
     }
     ,
     error:function(){
     alert('통신실패');
     }
 });

 //모든 직위명 가져오기


$.ajax({
  url:"/admin/select_position",
  method:"GET",
  success:function(positionVoList){
        const positionSelectTag=document.querySelector("#positionSelect");
        let optionTag= document.createElement("option");
        optionTag.innerText="직위선택";
        positionSelectTag.appendChild(optionTag);

         for(let i=0;i<positionVoList.length;i++){
             const positionVo=positionVoList[i];

             optionTag=document.createElement("option");
             positionSelectTag.appendChild(optionTag);

             optionTag.setAttribute("value",positionVo.no);
             optionTag.innerText=positionVo.name;
              }
      },
      error:function(){
      console.log("직위명가져오기 통신실패")
      }
  })