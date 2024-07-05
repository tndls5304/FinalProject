

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

//아이디 찾기
function requestFindId(){
     var name=document.querySelector("#nameModalFindId").value
     var phone=document.querySelector("#phoneModalFindId").value

    $.ajax({
        url:"/emp/find-id",
        method:"POST",
        data:{name:name,
              phone:phone},
        success:function(response){
            alert(response);
        },
        error:function(errorMsg){
            alert(errorMsg.responseText)
        }
        })
}


//비밀번호 찾기
function requestFindPwd(){
     var name=document.querySelector("#nameModalFindPwd").value
     var id=document.querySelector("#idModalFindPwd").value

    $.ajax({
            url:"/emp/find-pwd",
            method:"POST",
            data:{name:name,
                  id:id},
            success:function(response){
            //response 은 그냥 json 문자열임!
            var empVo=JSON.parse(response);//js객체로변환
            var no=empVo.empNo;
               var modalPwdContent=document.querySelector("#modalPwdContent");
               modalPwdContent.innerHTML="";

               var h3Tag=document.createElement("h3");
               h3Tag.innerText="이메일로 임시 비밀번호 전송해드릴게요";
               modalPwdContent.appendChild(h3Tag);

               var h5Tag=document.createElement("h5");
               h5Tag.innerText=empVo.hintEmail;
               modalPwdContent.appendChild(h5Tag);

               var yesButton=document.createElement("button");
                 yesButton.innerText="네.전송하기";
                 yesButton.addEventListener("click",function(){
                  requestEmail(no);
                  });

                modalPwdContent.appendChild(yesButton);

                var closeButton=document.createElement("button");
                  closeButton.innerText="닫기";
                  modalPwdContent.appendChild(closeButton);

            },
            error:function(errorMsg){
                alert(errorMsg.responseText)
            }
        })
}

//비밀번호를 이메일로 전송하기 요청 버튼
function requestEmail(no) {
console.log(no);
    $.ajax({
    url:"/emp/send-email-to-find-pwd",
    method:"POST",
    data:{no:no},
    success:function(msg){
    alert(msg)},
    error:function(errorMsg){
     alert(errorMsg.responseText)
    }
    })
}