const content = document.querySelector("#content");

$.ajax({
    url:"/board/api/wishList/mylist"
    ,method:"get"
    ,success:(data)=>{
        let str = "";
        
        for(let i = 0 ; i < data.length ; i++){
            str +=  `
            <div>
                <div>${data[i].boardNo}<div>
                <div>${data[i].title}<div>
                <div>${data[i].viewCount}<div>
                <div>${data[i].crtnDate}<div>
                <div>${data[i].name}<div>
            </div>
            `
            content.innerHTML = str;
        }

    }
    ,error:()=>{
        alert("찜 목록 조회중 오류가 발생하였습니다" +
            "해당부서로 연락주시면 빠른조치 하겠습니다" +
            "개발부 박근아 [010 -5738 - 2844]")    
    }
})