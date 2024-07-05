

//아이디모달열기

function btnFindId() {
    var modalFindId = document.getElementById("modalFindId");
    modalFindId.style.display = "flex";
}

//아이디모달닫기

function closeModalFindId() {
    var modalFindId = document.getElementById("modalFindId");
    modalFindId.style.display = "none";
}


//비번모달열기
function btnFindPwd() {
    var modalFindPwd = document.getElementById("modalFindPwd");
    modalFindPwd.style.display = "flex";

}




//비번모달닫기
function closeModalFindPwd() {
    var modalFindPwd = document.getElementById("modalFindPwd");
    modalFindPwd.style.display = "none";
}

function requestFindId(){
     var name=document.querySelector("#nameModalFindId").value
     var phone=document.querySelector("#phoneModalFindId").value

    $.ajax({
        url:"/emp/find_id",
        method:"POST",
        data:{name:name,
              phone:phone},
        success:function(email){
            alert(email);
        },
        error:function(errorMsg){
            alert(errorMsg.responseText)
        }
        })
}


