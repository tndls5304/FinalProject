// 현재 시각 가지고 오는 법입니다. - HTML과 JS만 사용합니다.
// html에서 시간 넣는 부분(div) id를 clock으로 하였음. 그외에는 전부 JS에서 처리.

// 해당 html 문서 내용이 로드된 후 아래 코드가 실행될 수 있도록 작성
document.addEventListener("DOMContentLoaded", () => {
    const clock = document.getElementById("clock");

    function getClock() {
        const date = new Date();
        // hour가 2자리 문자열로 변환될 수 있도록 작성(두자리, 한자리라면 0붙이기 위함) - minutes와 seconds도 동일
        const hour = String(date.getHours()).padStart(2, "0");
        const minutes = String(date.getMinutes()).padStart(2, "0");
        const seconds = String(date.getSeconds()).padStart(2, "0");
        // 시(2자리):분(2자리):초(2자리)로 나올 수 있도록 작성
        clock.innerText = `${hour}:${minutes}:${seconds}`;
    }

    // 페이지가 로드될 때 현재 시각 가져올 수 있도록 작성
    getClock();
    // 1초마다 위에 getClock 함수를 호출하여 시각을 업데이트하도록 작성
    setInterval(getClock, 1000);
});