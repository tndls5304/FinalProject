
$.ajax({
  url:"/emp/info",
     method:"GET",
     success:function(employeeVo){
      const empInfoDiv =document.querySelector("#empInfo")
      let str="";
      str+="<h3> 이름:"+employeeVo.name+"</h3>"
      str+="<h3>아이디:"+employeeVo.id+"</h3>"
      str+="<h3>이메일:"+employeeVo.email+"</h3>"


      empInfoDiv.innerHTML=str;


     }
     ,
     fail:()=>{
     console.log("통신실패")

     }
 });