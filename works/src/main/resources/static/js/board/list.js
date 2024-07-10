$.ajax( {
    url : "/board/api/list",
    method: "get" ,
    success: (data)=> {
        console.log("통신성공~");

       const x = document.querySelector("#content");

       let str = "";
       for (let i = 0; i < data.length; i++) {
           str += "<div class='detail' data-boardNo='" + data[i].boardNo + "'>" 
               + data[i].boardNo + " " 
               + data[i].name + " " 
               + data[i].title + " " 
               + data[i].crtnDate + " " 
               + data[i].viewCount 
       }
       x.innerHTML = str;

       const details = document.querySelectorAll(".detail");
       for (let i = 0; i < details.length; i++) {
           details[i].addEventListener("click", detail);
       }

       function detail(event) {
           const boardNo = event.target.getAttribute('data-boardNo');
           console.log("메롱메롱", boardNo);
           window.location.href = "/board/detail?boardNo=" + boardNo;
       }


    },
    error: () => {
        console.log("통신실패 ㅠㅠ");
    }

} );

function search() {
    const searchOption = document.querySelector("#searchTitle").value;
    const search = document.querySelector("#search").value;
    const content = document.querySelector("#content");

    content.innerHTML= "";

    let data = {};
    if(searchOption === "제목"){
        data = {title : search}
    }else if(searchOption === "작성자"){
        data = {empName: search}
    }
    
    $.ajax({
        url:"/board/search"
        ,method:"post"
        ,data:data
        ,success:(data)=>{
            let str = "";
            for(let i= 0 ;  i < data.length ; i++){
                str+= `<div class='detail' data-boardNo='${data[i].boardNo}' >
                            <div>${data[i].boardNo}</div>
                            <div>${data[i].name}</div>
                            <div>${data[i].title}</div>
                            <div>${data[i].crtnDate}</div>
                            <div>${data[i].viewCount}</div>
                       </div>`
            }

            content.innerHTML = str;

            const details = document.querySelectorAll(".detail");
            for (let i = 0; i < details.length; i++) {
                details[i].addEventListener("click", detail);
            }
     
            function detail(event) {
                const detailDiv = event.target.closest('.detail');
                const boardNo = detailDiv.getAttribute('data-boardNo');
                window.location.href = "/board/detail?boardNo=" + boardNo;
            }
            
    
        }
        ,error:()=>{
    
        }
    
    })
    
}

