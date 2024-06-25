
$.ajax({
  url:"/admin/common/sidebar",
     method:"GET",
     success:function(AdminPageMenuVoList){
     console.log("안ㄴ영ㅇ!!")
//      const sidebarMenu=document.querySelector("#sidebarMenu")
//      let str='<li><a href="'+;
//      str+=""
//      str+=""
//      str+=""
//
//
//      sidebarMenu.innerHTML=str;


     }
     ,
     fail:()=>{
     console.log("통신실패")

     }
 });