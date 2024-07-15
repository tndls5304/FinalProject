function listAll() {
  console.log("전체할일");

  $.ajax({
    url: "http://127.0.0.1:8080/todo/listAll",
    method: "GET",
    data: {},
    success: function (listAll) {
      //리스트 데이터 받아옴
      const table = document.querySelector("#todoList");
      const detail = document.querySelector("#detail");

      let str = "";
      for (let i = 0; i < listAll.length; i++) {
        str += "<tr>";

        //completedYn이용하여 완료한 할일은 제목에 줄 그어주기...!
        if (listAll[i].completedYn === "Y") {
          str +=
            "<td class='todo-title' style='text-decoration: line-through;'>" +
            listAll[i].title +
            "</td>";
        } else {
          str += "<td class='todo-title'>" + listAll[i].title + "</td>";
        }
        // str += `<td><input class="checkbox-delete" type="checkbox" value="${listAll[i].todoNo}"></td>`;
        // str += "<td class='todo-title'>" + listAll[i].title + "</td>";
        str += "<td class='hidden-column' >" + listAll[i].todoNo + "</td>"; // todoNo 열을 숨김 처리
        str += "</tr>";
        str += "<tr>";
        str +=
          "<td class='empAndEnd' colspan='2'>요청자: " +
          listAll[i].todoEmpName +
          " | 기한: " +
          listAll[i].endDate +
          "</td>";
        // str += "<td>요청자 " + listAll[i].todoEmpName + "</td>";
        // str += "<td class='listEndDate'>기한 " + listAll[i].endDate + "</td>";
        str += "</tr>";
        str += "<tr><td colspan='2'>&nbsp;</td></tr>"; //공백추가
      }
      table.innerHTML = str;
      detail.innerHTML = "";
    },
    error: function (err) {
      console.error("리스트 아작스 실행중 에러", err);
      alert("리스트 아작스 실행중 에러");
    },
  });
}
