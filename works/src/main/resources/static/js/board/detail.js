const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');
console.log(boardNo);

$.ajax({
    url: "/board/api/detail",
    method: "get",
    data:{boardNo:boardNo},
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
    },
    error: (error) => {
       console.log("error :" , error);
    }
});
