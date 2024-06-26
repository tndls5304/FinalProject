
$.ajax({
    url:"/admin/get_sub_admin_menu",
    method:"GET",
    success:function(voList){
    console.log(ㅎㅎㅎㅎㅎ헿)
        let str="";

        for(let i=0; i<voList.length;i++){
        str+="<tr>"
        str+="<td>"+voList[i].menuNo+"</td>"
        str+="<td>"+voList[i].menuName+"</td>"
        str+="<td>"+voList[i].authSelectYn+"</td>"
        str+="<td>"+voList[i].authInsertYn+"</td>"
        str+="<td>"+voList[i].authModifyYn+"</td>"
        str+="<td>"+voList[i].authRemoveYn+"</td>"
        str+="</tr>"

        }
        const tbody=document.querySelector("tbody")
        tbody.innerHTML=str;

       console.log(voList)
    },
    error:function(){
    console.log("통신실패")}

})

console.log("연결돼라")