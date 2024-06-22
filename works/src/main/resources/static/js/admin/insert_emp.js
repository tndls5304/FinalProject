
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
     fail:function(){
     alert('통신실패');
     }
 });