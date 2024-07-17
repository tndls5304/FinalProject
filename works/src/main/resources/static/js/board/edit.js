const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');

$.ajax({
    url: "/board/api/detail",
    method: "get",
    data: { boardNo: boardNo },
    dataType: 'json',
    success: (data) => {

        console.log(data);

        const edit = document.querySelector("#edit");

        //제목 가져오기
        const inputTag = document.createElement("input");
        inputTag.type = "text";
        inputTag.id="title"
        inputTag.value = data.title;

        //내용가져오기
        const divTag = document.createElement("div");
        divTag.contentEditable = "true";
        divTag.innerHTML = data.content;
        divTag.id = "content";

        //사진 추가하기 버튼 만들기
        const inputTag3 = document.createElement("input");
        inputTag3.type = "file";
        inputTag3.style = "display: none";
        inputTag3.id = "imageFile";

        //실제로는 이게 이루어짐
        const btn = document.createElement("button");
        btn.type = "button";
        btn.textContent = "이미지 추가";
        btn.addEventListener('click', addImage);

        function addImage() {
            inputTag3.click();
        }
        inputTag3.addEventListener('change' , ()=>{
            const file = inputTag3.files[0];

            if (file) {
                const reader = new FileReader();
                reader.onload = function(evt) {
                    const imgTag = document.createElement("img");
                    imgTag.className = "imgTag";
                    imgTag.src = evt.target.result;
                    imgTag.alt = file.name;
                    divTag.appendChild(imgTag);

                    uploadImge(file ,imgTag);
                };
                reader.readAsDataURL(file);
            }
        });

        function  uploadImge(file,imgTag) {
            const formDate = new FormData();
            
            formDate.append("imgList", file);
        
            $.ajax({
                url:"/board/upload"
                ,type:"post"
                ,data:formDate
                ,processData: false
                ,contentType: false
                ,success:(response)=>{
                    console.log("이미지 업로드 성공");
                    console.log(response);
                    imgTag.src = response;
                    
        
                }
            });
            
        }


        //수정하기 완료 누르면 클릭이벤트 발생하면서 submitPost로 이동
        const btn2 = document.createElement("button");
        btn2.type = "button";
        btn2.textContent = "수정하기";
        btn2.addEventListener('click', submitPost);


        edit.appendChild(inputTag);
        edit.appendChild(divTag);
        edit.appendChild(inputTag3);
        edit.appendChild(btn);
        edit.appendChild(btn2);       
        
    },
    error: (error) => {
        console.log("error :", error);
    }
});

function submitPost(params) {
    const formData = new FormData();

    // 제목 추가
    const title = document.querySelector("#title").value;
    formData.append('title', title);

    // 내용 추가
    const content = document.querySelector("#content").innerHTML;
    formData.append('content', content);

    formData.append('boardNo', boardNo);


    $.ajax({
        url: '/board/edit',
        type: 'POST',
        data: formData ,
        processData: false,
        contentType: false,
        success: function() {
            alert('포스트가 수정되었습니다.');
            window.location.href = '/board/detail?boardNo=' + boardNo;
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}
