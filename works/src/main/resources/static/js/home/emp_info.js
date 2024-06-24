
$.ajax({
  url:"/emp/info",
     method:"GET",
     success:function(employeeVo){
      const empInfoDiv =document.querySelector("#empInfo")
      let str="";
      str+="<h1> 이름:"+employeeVo.name+"</h1>"
       str+="<h1>아이디:"+employeeVo.id+"</h1>"

      empInfoDiv.innerHtml=str;


     }
     ,
     fail:()=>{
     console.log("통신실패")

     }
 });