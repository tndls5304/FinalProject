const addImg = document.querySelector("#addImg");
const fileInput = document.querySelector("#imageFile");

addImg.addEventListener('click', () => {
    fileInput.click();
    //사진추가 버튼을 누르면 파일첨부가 실행되도록 만듬
});  

    //파일 객체가 담기면 변환하는중
fileInput.addEventListener('change' , ()=>{
    const imgContainer = document.querySelector("#content");

    const file = fileInput.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function(evt) {
            const imgTag = document.createElement("img");
            imgTag.className = "imgTag";
            imgTag.src = evt.target.result;
            imgTag.alt = file.name;
            imgContainer.appendChild(imgTag);

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
        ,success:(urlText)=>{
            console.log("이미지 업로드 성공");
            imgTag.src = urlText;
            console.log(imgTag.src + "이미지 주소시랍라라라");
            

        }
    });
    
}

function submitPost() {
    // const form = document.getElementById('postForm');
    // const {images , textContent} = separate();        
    //사진을 보내기 위한 폼 데이터 멀티파트 가져오기
    const title =document.querySelector("#title").value// 제목 가져오기
    const content = document.querySelector("#content").innerHTML;

    const formDate= new FormData();
     // 제목과 내용을 FormData에 추가
     formDate.append('title', title);
     formDate.append('content', content);
     

    // AJAX 요청
    $.ajax({
        url: '/board/write',
        type: 'POST',
        data: formDate,
        processData: false,
        contentType: false,
        success: function() {
            alert('포스트가 작성되었습니다.');
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
};
//     file.onchange = () => {
//         //content라는 디브태그 가져와서 img라는 상수를 쥐어줌
//         const img = document.querySelector("#content");

//         //파일이 0보다 작다면 리턴
//         if (file.files.length <= 0) {
//             return;
//         }
        
//         //파일이 0보다 크다면 반복문 시작
//         //파일 길이만큼 반복문 시작
//         for (let i = 0; i < file.files.length; i++) {
//             //통과하면 reader상수를 통해서 frileReader객체를 읽어드림
//             const reader = new FileReader();
//             //파일이 업로드가 될때마다 함수 실행 evt라는 데이터 담아서 넘겨줌
//             reader.onload = function (evt) {
//                 // 이미지 태그 상수 만들어서 img라는 태그 만듬
//                 const imgTag = document.createElement("img"); 
//                 const fileName = file.files[i].name;
//                 imgTag.className= "imgTag";
//                 //이미지 태그의 src는 이미지 데이터 url임
//                 imgTag.src = evt.target.result;
//                 imgTag.alt = fileName;
                
//                 console.log(imgTag);
//                 console.log(fileName);

//                 // 이미지를 content 요소에 추가
//                 img.appendChild(imgTag);
//             }
            
//             reader.readAsDataURL(file.files[i]);
   
//         }
//     }


