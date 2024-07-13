function getTodoDetail(todoNo) {
  $.ajax({
    url: "http://127.0.0.1:8080/todo/detail",
    method: "GET",
    data: { todoNo: todoNo },
    success: function (data) {
      console.log(data);
      const detail = document.querySelector("#detail");

      // const title = document.querySelector("#detail");

      let str = "";
      str += `<div id='todoNo'class='hidden-no'>${data[0].todoNo}</div>`;
      str += `<div><span name='changeTitle' id='title'>${data[0].title}</span></div>`;
      str += `<div><span name='changeContent' id='content'>${data[0].content}</span></div>`;
      str += `<div id='endDate'>작성일: ${data[0].createDate}</div>`;
      str += `<div id='endDate'>기한:  ${data[0].endDate}</div>`;
      str += `<div id='empName'>요청자: <button class="empBtn" onclick="getEmpDetail(${data[0].todoEmpNo});">${data[0].todoEmpName}</button></div>`;

      //완료되지 않은 할일에만 버튼 생기게
      if (data[0].completedYn !== "Y") {
        str += `<button class='comBtn' onclick='complete(${data[0].todoNo});'>완료하기</button>`;
      }

      //담당자만 여러명이라 for문
      str += `<div id='todoManagerNo'>담당자 :`;
      for (let i = 0; i < data.length; i++) {
        str += `<button class="managerBtn" onclick="getEmpDetail(${data[i].todoManagerNo});">${data[i].todoManagerName}</button>`;
      }
      str += "</div>";

      str += `<button class='editBtn' onclick='editBtn(${data[0].todoNo});'>수정하기</button>`;
      str += `<button class=delToBtn onclick='delTodo(${data[0].todoNo});'>삭제하기</button>`;

      detail.innerHTML = str;
    },
    error: function (err) {
      console.error("상세조회 ajax 실행중 에러", err);
    },
  });
}
