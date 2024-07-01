const urlParams = new URLSearchParams(window.location.search);
//URL 주소에서 받아오겠다
const noticeNo = urlParams.get('noticeNo');
const btn = document.querySelector("#delete")
btn.addEventListener("click" , deletePage);

function deletePage(evt) {
    const det = '/notice/delete?noticeNo=' + noticeNo;
    window.location.href=det;
    
}



$.ajax({
    url:"/notice/api/detail",
    method:"get",
    data:{noticeNo : noticeNo },
    dataType: 'json',
    success:(data)=>{
        console.log(data);

        const content = document.querySelector("#content");

        let str ="";
        str = "<div>" 
        + data.title
        +"</div>"
        + "<br>"
        +"<div>" 
        + data.content 
        + "</div>";

        content.innerHTML= str;


    },
    error:(x)=>{
        console.log(x);

    }

})

