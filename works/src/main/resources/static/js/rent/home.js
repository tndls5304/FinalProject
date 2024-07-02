let today;

window.onload = ()=>{
    today = new Date();
    const calendarBody = document.querySelector(".calendar-body");
    const prevE1 = document.querySelector(".prev");
    const nextE1 = document.querySelector(".next");
    let currentDate;
    buildCalendar();
}

function buildCalendar(params) {
    let firstDate = new Date(today.getFullYear(),today.getMonth(),1);
    const monthList = ['January' ,'February' ,'March', 'April' , 'May' ,'June', 'July' , 'August' , 'September' , 'October' , 'November' , 'December'];
    const leapYear = [31 , 29 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 ,31];
    const notLeapYear = [31 , 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 ,31];
    const headerYear = document.querySelector(".current-year-month");
    
    if(firstDate.getFullYear() % 4 === 0){
        pageYear = leapYear;
    }else{
        pageYear = notLeapYear;
    }
    headerYear.innerHTML =`${monthList[firstDate.getMonth()]}&nbsp;&nbsp;&nbsp;&nbsp;${today.getFullYear()}`;
    makeElement(firstDate);
}
