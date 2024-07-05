function getTodoDetail(todoNo) {
  $.ajax({
    url: "/api/todo",
    method: "GET",
    data: { todoNo: todoNo },
    success: function (data) {
      const detail = document.querySelector("#detail");

      console.log("!!!!!!detail", detail);

      const title = document.createElement("div");
      title.classList.add("title");
      title.textContent = data.title;

      const content = document.createElement("div");
      content.classList.add("content");
      content.textContent = data.content;

      const endDate = document.createElement("div");
      endDate.classList.add("endDate");
      endDate.textContent = data.endDate;

      console.log("detail", detail);
      console.log("title", title);
      console.log("content", content);
      console.log("endDate", endDate);

      detail.appendChild(title);
      detail.appendChild(content);
      detail.appendChild(endDate);

      detail.innerHTML = str;
    },
    error: function (err) {
      console.error("상세조회 아작스 실행중 에러", err);
      alert("상세조회 아작스 실행중 에러");
    },
  });
}
