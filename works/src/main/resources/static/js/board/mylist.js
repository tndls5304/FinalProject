$.ajax( {
    url : "/board/api/mylist",
    method: "get" ,
    success: (data)=> {
        console.log("통신성공~");

       const x = document.querySelector("#content")
       console.log(x);

      let str = "";
      for (let i = 0; i < data.length; i++) {
        str += "<div>" + data[i].boardNo +  data[i].name +  data[i].title + data[i].crtnDate + data[i].viewCount + "</div>"
      }

       x.innerHTML = str;

    },
    fail: () => {
        console.log("통신실패 ㅠㅠ");
    }
} );