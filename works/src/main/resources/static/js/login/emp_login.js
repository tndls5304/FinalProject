

//열기
window.onload = function() {

function btnFindId() {
    var modalFindId = document.getElementById("modalFindId");
    modalFindId.style.display = "flex";

       modalFindId.querySelector('.modalFindAccount-content').style.backgroundColor = "rgb(241, 241, 241)";
}
//닫기
function closeModalFindId() {
    var modalFindPwd = document.getElementById("modalFindPwd");
    modalFindPwd.style.display = "none";
}

//열기
function btnFindPwd() {
    var modalFindPwd = document.getElementById("modalFindPwd");
    modalFindPwd.style.display = "flex";
   modalFindId.querySelector('.modalFindAccount-content').style.backgroundColor = "rgb(241, 241, 241)";
}


//닫기

function closeModalFindId() {
    var modalFindPwd = document.getElementById("modalFindPwd");
    modalFindPwd.style.display = "none";
}

}