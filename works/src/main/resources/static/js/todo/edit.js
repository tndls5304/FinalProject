//수정하기 버튼 누르면 수정 가능하게
function editBtn(todoNo){
  const titleSpan = document.querySelector("span[name=changeTitle]");
  const contentSpan = document.querySelector("span[name=changeContent]");


  //outerHtml : 요소 안에 전채 HTML 문자열 반환 , 요소를 완전히 대체할 수 있다
  //span요소를 input과 textarea로 변경
  titleSpan.outerHTML = `<input name='changeTitle' id='title' value='${titleSpan.textContent}'>`;
  contentSpan.outerHTML = `<textarea name='changeContent' id='content'>${contentSpan.textContent}</textarea>`;

  //수정하기 버튼이 완료로 바뀜
  const editBtnChange = document.querySelector(".editBtn");
  editBtnChange.textContent = "완료";
  //익명 함수를 만들어 준 뒤 수정하기 함수 실행
  editBtnChange.onclick = function(){edit(todoNo)};
}



function edit(todoNo){
  const changeTitle = document.querySelector("input[name=changeTitle]").value;
  const changeContent = document.querySelector("textarea[name=changeContent]").value;
  $.ajax({
    url : "http://127.0.0.1:8080/todo/edit",
    method:"POST",
    data:{
      todoNo : todoNo,
      title : changeTitle,
      content : changeContent,
    },
    success:function(data){
      //수정하기 완료 후 다시 상세 조회
      getTodoDetail(todoNo);
      listAll();
    },
    error: function (err) {
      console.error("수정하기 ajax 실행중 에러", err);
      console.error("Error 수정하기:", xhr.responseText); 
    }

  });
}