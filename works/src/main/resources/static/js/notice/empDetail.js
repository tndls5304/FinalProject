const urlParams = new URLSearchParams(window.location.search);
const noticeNo = urlParams.get('noticeNo');

$.ajax({
    url:"/board/notice/api/detail"
    ,method:"get"
    ,data:{
        noticeNo : noticeNo
    }
    ,dataType:'json'
    ,success:(data)=>{
        const content = document.querySelector("#content");
        
        let str ="";

        str = `
        <div>
            <div>${data.noticeNo}</div>
            <div>${data.title}</div>
            <div>${data.content}</div>
            <div>${data.crtnDate}</div>
        </div>   
        `
        content.innerHTML=str;
        
    }
    ,error:(x)=>{
        console.log(x);
        alert("상세조회 실패하였습니다 다시 시도해주세요")
    }
})