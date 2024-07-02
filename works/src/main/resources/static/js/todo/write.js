//버튼으로 날짜 받아오기
//plusDay를 매개변수로 받아오기
function setEndDate(plusDay) {
  console.log("setEndDate호출~~");
  //현재 시간을 나타내는 Date()객체 생성
  const today = new Date();
  //현재날짜 + 숫자 받아오기
  today.setDate(today.getDate() + plusDay);
  var year = today.getFullYear();
  var month = ("0" + (today.getMonth() + 1)).slice(-2);
  var day = ("0" + today.getDate()).slice(-2);
  var formattedDate = year + "/" + month + "/" + day;

  document.getElementById("endDateDisplay").value = formattedDate;
  document.getElementById("endDate").value = formattedDate;
}

// 초기 기한 설정 (오늘 기본 설정)
setEndDate(0);
