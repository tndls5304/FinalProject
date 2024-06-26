
$.ajax({
  url:"/admin/common/sidebar",
     method:"GET",
     success:function(voList){


     let str="";

    str+= "<li><h1>"+ voList[0].adminType +"</h1></li>"
        for(let i=0;i<voList.length;i++){
        str+= '<li><a href="'+voList[i].url+'">'+voList[i].name+'</a></li>';
        }

         const sidebarMenu=document.querySelector("#sidebarMenu")

          sidebarMenu.innerHTML=str;
     }
     ,
     error: () => {
        console.log("통신실패")
     }
 });