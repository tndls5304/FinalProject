let today;

window.addEventListener('keyup', (event) => {
    if (event.key === 'Escape') {
        const write = document.querySelector("#write");
        write.style.display = 'none'; 
    }
});

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

    window.addEventListener('keyup', (event) => {
        if (event.key === 'Escape') {
            const write = document.querySelector("#write");
            write.style.display = 'none'; 
        }
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

    


            if (i === 0 && j < firstDay) {
                const cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            
            } else if (date > daysInMonth) {

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

 
        if (date > daysInMonth) {
            break;
        }
        
    }


    document.querySelectorAll('.day').forEach(day => {
        day.addEventListener('click', () => {
            const write = document.querySelector("#write");
            write.style.display = 'block'; 

            const date =document.querySelector("#startDate");
            const date2 =document.querySelector("#endDate");
            const time = document.querySelector("#startTime");
            const time2 = document.querySelector("#endTime");
            const rentDate = document.querySelector("#rentDate");

            rentDate.value= "";
            time.value="";
            time2.value="";
            date.value="";
            date2.value = "";
        });
    });

    const closeButton = document.querySelector("#closeButton");
    closeButton.addEventListener('click', () => {
        const write = document.querySelector("#write");
        write.style.display = 'none'; 
    });
    
}

