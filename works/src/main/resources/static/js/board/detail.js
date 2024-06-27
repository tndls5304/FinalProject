const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');
console.log(boardNo);
const btn = document.querySelector("#btn");
const login = document.getElementById("empNo").textContent;
const loginNo = login





$.ajax({
    url: "/board/api/detail",
    method: "get",
    data:{boardNo:boardNo} ,
    dataType: 'json',
    success: (data) => {
        console.log("통신성공~");
        console.log(data);
        
        const x = document.querySelector("#content");
        console.log(x);
        
        let str = "";
        str = "<div>" 
        + data.title + "<br>" 
        + data.content 
        + "</div>";
        x.innerHTML = str;
        


        const userNo = data.empNo;
        const authorNo =loginNo; 
        console.log(userNo);
        console.log(authorNo);
        if (userNo == authorNo) {
            btn.style.display = 'block'; 
        } else {
            btn.style.display = 'none'; 
        }
    },
    error: (error) => {
        console.log("error :" , error);
    }
});

function editPage(evt) {
    const edit = '/board/edit?boardNo=' + boardNo;
    window.location.href= edit;
}
    btn.addEventListener("click" , editPage);