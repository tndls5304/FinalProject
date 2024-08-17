//ajax를 써서 디비에서 동적으로 사이드바 메뉴를 가져오기
$.ajax({
     url:"/admin/common/sidebar",
     method:"GET",
     success:function(voList){
         if (Array.isArray(voList) && voList.length === 0) {
            alert("저장된 메뉴가 없습니다.");
         }
         let str="";
         str+= "<li><h1>"+ voList[0].adminType +"</h1></li>"
         for(let i=0;i<voList.length;i++){
            str+= '<li><a href="'+voList[i].url+'">'+voList[i].name+'</a></li>';
         }
         const sidebarMenu=document.querySelector("#sidebarMenu")
         sidebarMenu.innerHTML=str;
     },
     error: () => {
        console.log("통신실패")
     }
});