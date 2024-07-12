const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');
const btn = document.querySelector("#btn");
const btn2 = document.querySelector("#delete");
const login = document.getElementById("empNo").textContent;
const loginNo = login;




$.ajax({
    url: "/board/api/detail",
    method: "get",
    data: { boardNo: boardNo },
    dataType: 'json',
    success: (data) => {
        console.log("통신성공~");
        console.log(data);

        const x = document.querySelector("#content");
        console.log(x);

        // 게시물 제목과 내용을 HTML로 생성
        let str = "<div>" 
                + "<h2>" + data.title + "</h2>" 
                + "<p>" + data.content + "</p>";

        // img_names 필드를 쉼표로 분리하여 배열로 변환
        if (data.imgNames) {
            const imgArray = data.imgNames.split(",");
            for (let i = 0; i < imgArray.length; i++) {  // imgArray.length로 수정
                console.log(imgArray[i]);
                // 실제 이미지 파일 경로 설정
                str += "<img src='/img/boardImg/" + imgArray[i] + "' alt='ImageName " + (i + 1) + "'>";
            }
        }

        str += "</div>";

        // content 요소에 생성된 HTML을 삽입
        x.innerHTML = str;

        const userNo = data.empNo;
        const authorNo = loginNo;
        console.log(userNo);
        console.log(authorNo);

        // 버튼 표시 제어
        if (userNo == authorNo) {
            btn.style.display = 'block'; 
            btn2.style.display = 'block'; 
        } else {
            btn.style.display = 'none'; 
            btn2.style.display = 'none'; 
        }

        btn.addEventListener("click", editPage);
        btn2.addEventListener("click", deletePage);

        // 찜 목록 상태를 확인하는 AJAX 요청
        $.ajax({
            url: "/board/checkWishList",
            method: "get",
            data: { boardNo: boardNo },
            success: (response) => {
                if (response.wishList) {
                    like.classList.remove("fa-regular");
                    like.classList.add("fa-solid");
                    like.style.color = "#f005dc";
                } else {
                    like.classList.remove("fa-solid");
                    like.classList.add("fa-regular");
                    like.style.color = "#f005dc";
                }
            }
        });

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

function comment() {
    
    const comment = document.querySelector("#comment").value;

    console.log(comment);

    $.ajax({
        url:"/board/comment"
        ,method:"post"
        ,data:{
            boardNo : boardNo
            ,boardComment : comment
        }
        ,success:()=>{
            location.reload();
            alert("댓글 작성 완료");
        }
        ,error:()=>{
            alert("댓글 작성 실패하였습니다"
                + "개발팀부서로 연락주시길 바랍니다"
            +"개발팀 박근아 [010-5738-2844]")
        }
    })
    
}
const like = document.querySelector("#like");

like.addEventListener("click", () => {
    if (like.classList.contains("fa-regular")) {
        // 현재 비워진 하트일 경우, 채워진 하트로 변경
        like.classList.remove("fa-regular");
        like.classList.add("fa-solid");
        like.style.color = "#f005dc";

        console.log(boardNo);
        //채워진 하트의 ajax
        $.ajax({
            url:"/board/wishList"
            ,method:"post"
            ,data:{
                boardWishNo:boardNo
            }
            ,success:()=>{
                alert("My게시판에 추가되었습니다")
            }

        })
    } else {
        // 현재 채워진 하트일 경우, 비워진 하트로 변경
        like.classList.remove("fa-solid");
        like.classList.add("fa-regular");
        like.style.color = "#f005dc";

        //채워졌다가 비워진하트의 ajax
        $.ajax({
            url:"/board/wishList/cancle"
            ,method:"post"
            ,data:{
                boardWishNo:boardNo

            }
            ,success:()=>{
                alert("My게시판에서 취소되었습니다")

            }
            ,error:()=>{
                alert("취소에 실패했습니다. 잠시후 다시 시도해주세요.");
            }

        })
    }
});

const commentContent = document.querySelector("#commentContent")

$.ajax({
    url:"/board/api/comment"
    ,method:"get"
    ,data:{
        boardNo :boardNo
    }
    ,success:(data)=>{

        let str = "";

        for(let i = 0; i < data.length ; i++){
            str+= `
                <div class="${data[i].comtNo}">
                    <div>${data[i].depName} ${data[i].name}</div>
                    <div>${data[i].boardComment}</div>
                    <div>${data[i].comtDate}</div>
                </div>
            `
            
            const userNo = data[i].empNo;
            if(userNo === login){
                str+=`
                    <button onclick="commentDel(${data[i].comtNo});">삭제</button>
                `
            }
            
                commentContent.innerHTML = str;
        }


    }
    ,error:()=>{

    }
})

function commentDel(no) {

    $.ajax({
        url:"/board/comment/del"
        ,method:"post"
        ,data:{
            comtNo:no
        }
        ,success:()=>{
            location.reload();
        }
        ,error:()=>{
            alert("댓글삭제 실패하였습니다");
        }
    })


    
}




