var boardNo = "";

$.ajax({
    url: "/board/detail",
    method: "get",
    data:{boardNo:boardNo},
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
    error: () => {
        console.log("통신실패 ㅠㅠ");
    }
});
