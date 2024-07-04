function listPar() {
  console.log("담당할일");

  $.ajax({
    url: "http://127.0.0.1:8080/api/todo/listPar",
    method: "GET",
    data: {},
    success: function (listPar) {
      const table = document.querySelector("#todoList");

      let str = "";
      for (let i = 0; i < listPar.length; i++) {
        str += "<tr>";
        str += "<td>" + listPar[i].title + "</td>";
        str += "<td class='hidden-column'>" + listPar[i].todoNo + "</td>"; // todoNo 열을 숨김 처리
        str += "</tr>";
        str += "<tr>";
        str += "<td>요청자 " + listPar[i].todoEmpName + "</td>";
        str += "<td>기한 " + listPar[i].endDate + "</td>";
        str += "</tr>";
        str += "<tr><td colspan='2'>&nbsp;</td></tr>"; //공백추가
      }
      table.innerHTML = str;
    },
    error: function (err) {
      console.error("todoListPar-err", err);
    },
  });
}
