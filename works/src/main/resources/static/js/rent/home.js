let today;

window.onload = () => {
    today = new Date();
    const prevE1 = document.querySelector(".prev");
    const nextE1 = document.querySelector(".next");
    const calendarBody = document.querySelector(".calendar-body");
    let currentDate;
    buildCalendar();

    prevE1.addEventListener('click', function () {
        today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
        removeCalendar();
        buildCalendar();
    });

    nextE1.addEventListener('click', function () {
        today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
        removeCalendar();
        buildCalendar();
    });
};

function buildCalendar() {
    let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
    const monthList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    const leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    const notLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    const headerYear = document.querySelector(".current-year-month");

    let pageYear;
    if (firstDate.getFullYear() % 4 === 0) {
        pageYear = leapYear;
    } else {
        pageYear = notLeapYear;
    }

    headerYear.innerHTML = `${monthList[firstDate.getMonth()]}&nbsp;&nbsp;&nbsp;&nbsp;${today.getFullYear()}`;
    makeElement(firstDate, pageYear);
}

function removeCalendar() {
    const calendarBody = document.querySelector(".calendar-body");
    while (calendarBody.firstChild) {
        calendarBody.removeChild(calendarBody.firstChild);
    }
}

function makeElement(firstDate, pageYear) {
    
    const calendarBody = document.querySelector(".calendar-body");

    // 첫 번째 날짜의 요일 계산하라고!!!!!
    const firstDay = firstDate.getDay();

    // 현재 월의 일수
    const daysInMonth = pageYear[firstDate.getMonth()];

    //월
    const monthList = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];


    // 달력에 날짜를 추가
    let date = 1;
    for (let i = 0; i < 6; i++) { // 최대 6주
        const row = document.createElement("div");
        row.className ="line";

        for (let j = 0; j < 7; j++) { // 1주 = 7일
            const cell = document.createElement("div");
            cell.className="day";

    

            // 첫 번째 주에서 시작 날짜를 설정
            if (i === 0 && j < firstDay) {
                const cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            
            } else if (date > daysInMonth) {
                // 날짜가 월의 마지막 날짜를 넘어가면 빈 셀 추가
                const cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            } else {
                const cellText = document.createTextNode(date);
                cell.appendChild(cellText);
                cell.setAttribute("id", `day${today.getFullYear()}-${monthList[firstDate.getMonth()]}-${date}`);
                row.appendChild(cell);
                date++;
            }
        }

        calendarBody.appendChild(row);

        // 모든 날짜가 추가되면 루프 종료
        if (date > daysInMonth) {
            break;
        }
    }
}
