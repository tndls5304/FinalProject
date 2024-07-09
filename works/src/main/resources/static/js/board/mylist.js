$.ajax( {
    url : "/board/api/mylist",
    method: "get" ,
    success: (data)=> {
        console.log("통신성공~");

       const x = document.querySelector("#content")
       console.log(x);

      let str = "";
      for (let i = 0; i < data.length; i++) {
        str += "<div class='detail' data-boardNo="+ data[i].boardNo + ">" + data[i].boardNo +  data[i].name +  data[i].title + data[i].crtnDate + data[i].viewCount + "</div>"
      }

       x.innerHTML = str;

       const details = document.querySelectorAll(".detail");
       for(let i = 0; i < data.length ; i++){
        details[i].addEventListener("click" , detail);
       }

       function detail(event) {
        const boardNo = event.target.getAttribute('data-boardNo');
        window.location.href = "/board/myList/detail?boardNo=" + boardNo;
       }

    },
    fail: () => {
        console.log("통신실패 ㅠㅠ");
    }
} );