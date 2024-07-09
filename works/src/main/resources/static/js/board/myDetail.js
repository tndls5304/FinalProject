const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');
const btn = document.querySelector("#btn");
const btn2 = document.querySelector("#delete");
// const login = document.getElementById("empNo").textContent;
// const loginNo = login;





$.ajax({
    url: "/board/api/myList/detail",
    method: "get",
    data:{boardNo:boardNo} ,
    dataType: 'json',
    success: (data) => {
        console.log("통신성공~");
        console.log(data);
        
        const content = document.querySelector("#content");
        
        let str = "";
        str = "<div>" 
        + data.title + "<br>" 
        + data.content 
        + "</div>";
        content.innerHTML = str;

        
        btn.addEventListener("click" , editPage);
        btn2.addEventListener("click" , deletePage);
        

    },

    error: (error) => {
        console.log("error :" , error);
    }
});





function editPage(evt) {
    const edit = '/board/edit?boardNo=' + boardNo;
    window.location.href= edit;
}

function deletePage(evt) {
    const det = '/board/delete?boardNo=' + boardNo;
    window.location.href= det;
}