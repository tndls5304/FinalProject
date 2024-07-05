//반복되는 ajax를 처리하기 위해서 생성하였는데,
//일단 사용하지 않겠습니다.



//쪽지 읽음 처리 Ajax
document.querySelectorAll('.click-title').forEach(item => {
  item.addEventListener('click', getMessenNo);
});

function getMessenNo(evt){
  console.log("함수 실행됨 ~~~");
  console.log("클릭된 요소:", evt.target);

  const messenNo = evt.target.parentNode.querySelector('.messenNo').innerText.trim();
  console.log("messenNo:", messenNo);

  $.ajax({
    url: "/messenger/read",
    method: "post",
    data: {
      messenNo: messenNo,
    },
    success: (data) => {
      console.log("쪽지 통신성공!");
      console.log(data);
    },

    error: (xhr, status, error) => {
      console.log("쪽지 통신실패...");
    },
  });
}


//전체 쪽지 목록에서 쪽지 상세페이지로 처리 Ajax
document.querySelectorAll('.click-title').forEach(item => {
  item.addEventListener('click', moveToDetail);
});

function moveToDetail(evt){
  console.log("함수 실행됨 ~~~");
  console.log("클릭된 요소:", evt.target);

  const messenNo = evt.target.parentNode.querySelector('.messenNo').innerText.trim();
  console.log("messenNo:", messenNo);

  $.ajax({
    url: "/messenger/detail",
    method: "get",
    data: {
      messenNo: messenNo,
    },
    success: (data) => {
      console.log("쪽지번호 통신성공!");
      console.log(data);

      //(제목)클릭시, detail 페이지로 바로 이동할 수 있도록 설정해줘야 한다.
      location.href = "/messenger/detail?messenNo=" + messenNo;
    },

    error: (xhr, status, error) => {
      console.log("쪽지번호 통신실패...");
    },
  });
}


//쪽지 중요 처리 Ajax
document.querySelectorAll('#checkbox-important').forEach(item => {
    item.addEventListener('click', sendImportant);
  });

  function sendImportant(evt){
    console.log("중요 체크박스 클릭됨 ~~~");
    console.log("클릭된 요소:", evt.target);

    const messenNo = evt.target.closest('.messenger-item').querySelector('.messenNo').innerText.trim();
    console.log("messenNo:", messenNo);

    $.ajax({
      url: "/messenger/importantStatus",
      method: "post",
      data: {
        messenNo: messenNo,
      },
      success: (data) => {
        console.log("중요 쪽지 통신성공!");
        console.log(data);
      },
      error: (xhr, status, error) => {
        console.log("중요 쪽지 통신실패...");
      },
    });
}


// /messenger/all에서 쪽지쓰기 눌렀을 때, 쪽지쓰기 페이지(/messenger/write)로 이동
function moveToWrite(){
    window.location.href = "http://localhost:8080/messenger/write";
}