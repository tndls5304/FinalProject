// // 담당자 리스트 열고 닫기
// function clickBtn() {
//   console.log("담당자");
//   const managerList = document.querySelector("#managerList");
//   const toggle = managerList.style.display === "block";

//   // 버튼 누르면 닫힘
//   managerList.style.display = toggle ? "none" : "block";
// }

// //기한 데이터 받아와서 백으로 넘겨주는 함수
// function setEndDate(endDateValue) {
//   document.getElementById("endDate").value = endDateValue;
// }

// // 모든 버튼의 active 클래스 제거
// const buttons = document.querySelectorAll(".endDateBtn");
// buttons.forEach((button) => button.classList.remove("active"));

// // 클릭된 버튼에 active 클래스 추가
// event.target.classList.add("active");
function clickBtn() {
  console.log("담당자");
  const managerList = document.querySelector("#managerList");
  const toggle = managerList.style.display === "block";

  // 버튼 누르면 닫힘
  managerList.style.display = toggle ? "none" : "block";
}

// 기한 데이터 받아와서 백으로 넘겨주는 함수
function setEndDate(endDateValue) {
  document.getElementById("endDate").value = endDateValue;

  // 모든 버튼의 active 클래스 제거
  const buttons = document.querySelectorAll(".endDateBtn");
  buttons.forEach((button) => button.classList.remove("active"));

  // 클릭된 버튼에 active 클래스 추가
  event.target.classList.add("active");
}


