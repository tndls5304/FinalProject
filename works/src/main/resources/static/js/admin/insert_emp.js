//모든 부서명가져오기
$.ajax({
    url:"/admin/emp/dept",
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
    },
    error:function(){
    alert('통신실패');
    }
});

//모든 직위명 가져오기
$.ajax({
    url:"/admin/emp/position",
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

function insertEmp(){
    var nameValue=document.querySelector("#name").value;
    var emailValue=document.querySelector("#email").value;
    var deptNoValue=document.querySelector("#deptSelect").value;
    var positionNoValue=document.querySelector("#positionSelect").value;
    $.ajax({
        url:"/admin/insert_emp",
        method:"POST",
        data:{
            name:nameValue,
            email:emailValue,
            deptNo:deptNoValue,
            positionNo:positionNoValue
        },
        success:function(result){
            alert(result);
        },
        error:function(errorMsg){
            alert(errorMsg.responseText);
        }
    })
}
