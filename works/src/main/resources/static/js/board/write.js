// let images = []; // 선택된 이미지들을 담을 배열
// //사진 추가하는 함수 호출
// function addImage() {
//     //사진을 담을 인풋태그 추가
//     const fileInput = document.createElement('input');
//     //타입은 파일명이고
//     fileInput.type = 'file';
//     //이미지만 올릴 수 있게 만듬
//     fileInput.accept = 'image/*';
//     //인풋태그에 이벤트리스너 추가 파일이 추가되면 체인지함 내용은 아래에
//     fileInput.addEventListener('change', function() {
//         //file 이라는 변수를 만들어주면서 배열로 만들어줌
//         const file = this.files[0];
//         //만약에 파일이 들어온다
//         if (file) {
//             //파일을 읽는다
//             const reader = new FileReader();
//             //읽는걸 다 마쳤으면 함수 다시한번 실행ㄴ
//             reader.onload = function(e) {
//                 //이미지라는 변수를 만들어서 그것을 이미지태그로 변화시킴
//                 const img = document.createElement('img');
//                 //이미지 주소는 input타켓으로 전달함
//                 img.src = e.target.result;
                
//                 // 컨텐트라는 아이디를 가져와서 컨텐트 안에 미리보기한 이미지를 추가시킴
//                 const contentDiv = document.querySelector('#content');
//                 contentDiv.appendChild(img);

//                 // 추가된 이미지와 파일을 배열에 저장합니다.
//                 images.push({ file: file, element: img });
//             };

//             // 파일을 읽어 base64 형식의 데이터 URL로 변환합니다
//             reader.readAsDataURL(file);
//         }
//     });

//     fileInput.click(); // 가상의 파일 입력 요소를 클릭하여 사용자가 이미지를 선택할 수 있도록 합니다.
// }


const addImg = document.querySelector("#addImg");

addImg.addEventListener('click', () => {
    const file = document.querySelector("#imageFile");
    file.click();
    

    //파일 객체가 담기면 변환하는중
    file.onchange = () => {
        //content라는 디브태그 가져와서 img라는 상수를 쥐어줌
        const img = document.querySelector("#content");

        //파일이 0보다 작다면 리턴
        if (file.files.length <= 0) {
            return;
        }
        
        //파일이 0보다 크다면 반복문 시작
        //파일 길이만큼 반복문 시작
        for (let i = 0; i < file.files.length; i++) {
            //통과하면 reader상수를 통해서 frileReader객체를 읽어드림
            const reader = new FileReader();
            //파일이 업로드가 될때마다 함수 실행 evt라는 데이터 담아서 넘겨줌
            reader.onload = function (evt) {
                // 이미지 태그 상수 만들어서 img라는 태그 만듬
                const imgTag = document.createElement("img");
                const fileName = file.files[i].name;
                imgTag.className= "imgTag";
                //이미지 태그의 src는 evt.target.result 즉 주소임
                imgTag.src = evt.target.result;
                imgTag.alt = fileName;
                
                console.log(imgTag);
                console.log(fileName);

                // 이미지를 content 요소에 추가
                img.appendChild(imgTag);
            }
            
            reader.readAsDataURL(file.files[i]);
   
        }
    }
});

function separate() {
    const imgElements = document.querySelectorAll(".imgTag");
    const images = [];
    for(let i = 0; i< imgElements.length ; i++){
        images.push(imgElements[i].src);
    }

    const textContent = document.querySelector("#content").innerText.trim();

    return{
        images:images,
        textContent:textContent
    }
    
    
}


function submitPost() {
    const {images , textContent} = separate();        
    //사진을 보내기 위한 폼 데이터 멀티파트 가져오기
    const form = document.getElementById('postForm');
    const title =document.querySelector("#title").value// 제목 가져오기
    
    // 폼데이터 뭉치기 위한 폼 객체 생성하기
    const formData = new FormData();
     // 제목과 내용을 FormData에 추가
    formData.append('title', title);
    formData.append('content', textContent);

    for(let i = 0; i< images.length ; i++){
        formData.append(`img` , images[i].files[0]);
    }


    

    // AJAX 요청
    $.ajax({
        url: '/board/write',
        type: 'POST',
        data: formData,
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
