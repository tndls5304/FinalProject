const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');
const btn = document.querySelector("#btn");
const btn2 = document.querySelector("#delete");
const login = document.getElementById("empNo").textContent;
const loginNo = login;





$.ajax({
    url: "/board/api/detail",
    method: "get",
    data:{boardNo:boardNo} ,
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
        


        const userNo = data.empNo;
        const authorNo =loginNo; 
        console.log(userNo);
        console.log(authorNo);

        if (userNo == authorNo) {
            btn.style.display = 'block'; 
            btn2.style.display = 'block'; 
        } else {
            btn.style.display = 'none'; 
            btn2.style.display = 'none'; 
        }

        btn.addEventListener("click" , editPage);
        btn2.addEventListener("click" , deletePage);

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




