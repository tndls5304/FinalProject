//담당자 불러오기
function clickBtn() {
  const managerList = document.querySelector("#managerList");
  managerList.style.display = "block";
}

//기한 데이터 받아와서 백으로 넘겨주는 함수
function setEndDate(endDateValue) {
  document.getElementById("endDate").value = endDateValue;
}
