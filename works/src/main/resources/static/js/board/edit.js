const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');

$.ajax({
    url: "/board/api/detail",
    method: "get",
    data: { boardNo: boardNo },
    dataType: 'json',
    success: (data) => {

        let images = []; // 선택된 이미지들을 담을 배열
        // 사진 추가하는 함수 호출
        function addImage() {
            // 사진을 담을 인풋태그 추가
            const fileInput = document.createElement('input');
            // 타입은 파일명이고
            fileInput.type = 'file';
            // 이미지만 올릴 수 있게 만듬
            fileInput.accept = 'image/*';
            // 인풋태그에 이벤트리스너 추가 파일이 추가되면 체인지함 내용은 아래에
            fileInput.addEventListener('change', function() {
                // file 이라는 변수를 만들어주면서 배열로 만들어줌
                const file = this.files[0];
                // 만약에 파일이 들어온다
                if (file) {
                    // 파일을 읽는다
                    const reader = new FileReader();
                    // 읽는걸 다 마쳤으면 함수 다시한번 실행
                    reader.onload = function(e) {
                        // 이미지라는 변수를 만들어서 그것을 이미지태그로 변화시킴
                        const img = document.createElement('img');
                        // 이미지 주소는 input타켓으로 전달함
                        img.src = e.target.result;
                        img.classList.add('post-image');

                        // 컨텐트라는 아이디를 가져와서 컨텐트 안에 미리보기한 이미지를 추가시킴
                        const contentDiv = document.querySelector('.content');
                        contentDiv.appendChild(img);

                        // 추가된 이미지와 파일을 배열에 저장합니다.
                        images.push({ file: file, element: img });
                    };

                    // 파일을 읽어 base64 형식의 데이터 URL로 변환합니다
                    reader.readAsDataURL(file);
                }
            });

            fileInput.click(); // 가상의 파일 입력 요소를 클릭하여 사용자가 이미지를 선택할 수 있도록 합니다.
        }

        console.log(data);

        const content = document.querySelector("#content");

        const inputTag = document.createElement("input");
        inputTag.type = "text";
        inputTag.name = "title";
        inputTag.value = data.title;
        inputTag.className = "title";

        const divTag = document.createElement("div");
        divTag.contentEditable = "true";
        divTag.innerHTML = data.content;
        divTag.className = "content";

        const inputTag3 = document.createElement("input");
        inputTag3.type = "file";
        inputTag3.style = "display: none";
        inputTag3.id = "imageFile";

        const btn = document.createElement("button");
        btn.type = "button";
        btn.textContent = "이미지 추가";
        btn.addEventListener('click', addImage);

        const btn2 = document.createElement("button");
        btn2.type = "button";
        btn2.textContent = "수정하기";
        btn2.addEventListener('click', submitPost);

        const inputTag5 = document.createElement("input");
        inputTag5.type = "hidden";
        inputTag5.name = "boardNo";
        inputTag5.value = boardNo;

        const imageContainer = document.createElement("div");
        imageContainer.id = "imageContainer";

        // 기존 이미지를 불러와서 화면에 표시
        if (data.images) {
            data.images.forEach(imageUrl => {
                const img = document.createElement('img');
                img.src = imageUrl;
                img.classList.add('post-image');
                imageContainer.appendChild(img);
                images.push({ file: imageUrl, element: img });
            });
        }

        const form = document.querySelector("#postForm");
        form.appendChild(inputTag);
        form.appendChild(divTag);
        form.appendChild(imageContainer);
        form.appendChild(inputTag3);
        form.appendChild(btn);
        form.appendChild(btn2);
        form.appendChild(inputTag5);

        // 사진을 보내기 위한 폼 데이터 멀티파트 가져오기
        function submitPost() {
            const title = document.querySelector(".title").value; // 제목 가져오기
            const content = document.querySelector(".content").innerHTML; // 내용 가져오기 (HTML 형식)
            const formData = new FormData();

            // 제목과 내용을 FormData에 추가
            formData.append('title', title);
            formData.append('content', content);

            // 이미지 파일들을 FormData에 추가
            images.forEach(image => {
                if (image.file instanceof File) {
                    formData.append('images', image.file);
                } else {
                    formData.append('existingImages', image.file);
                }
            });

            formData.append('boardNo', boardNo);

            // AJAX 요청
            $.ajax({
                url: '/board/edit',
                type: 'POST',
                data: formData,
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
    },
    error: (error) => {
        console.log("error :", error);
    }
});
