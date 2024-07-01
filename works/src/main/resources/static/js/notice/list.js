
$.ajax({
    url:"/notice/api/list" ,
    method:"get" ,
    success:(data)=>{
        
        const content = document.querySelector("#content");

        let str = "";
        for(let i = 0 ; i < data.length ; i++){
            str += "<div class='detail' data-noticeNo='" + data[i].noticeNo + "'>" 
            + data[i].noticeNo + " " 
            + data[i].title + " " 
            + data[i].crtnDate + " " 
            + data[i].viewCount 
            + "</div>";
        }
        content.innerHTML = str;

        const details = document.querySelectorAll(".detail");
        for(let i = 0; i < details.length ; i++){
            details[i].addEventListener("click" , detail);
        }

        function detail(event) {
            const noticeNo =  event.target.getAttribute(' data-noticeNo');
            window.location.href="/notice/detail?noticeNo=" + noticeNo;
        }


    },
    error:(x)=>{
        console.log(x);
    }
})