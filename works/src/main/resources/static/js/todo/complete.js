function complete(todoNo) {
  $.ajax({
    url: "http://127.0.0.1:8080/todo/complete",
    method: "POST",
    data: { todoNo: todoNo },
    success: function () {
      listAll();
      const detail = document.querySelector("#detail");
      detail.innerHTML = "";
    },
    error: function (err) {
      alert("할일완료 ajax 실행중 에러");
    },
  });
}
