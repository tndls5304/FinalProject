$(document).ready(function() {
    const loginMember = document.querySelector("#loginMember").innerText.trim();
    console.log("loginMember:", loginMember);

    const content = document.querySelector("#meetingContent");

    $.ajax({
        url:"/api/rent/meeting",
        method:"get",
        success:(data)=>{
            console.log(data);
    
            let str = "";
    
            for(let i = 0; i < data.length ; i++){
                str += `
                    <div>
                        <div>${data[i].metRsvNo}</div>
                        <div>${data[i].floor} 층</div>
                        <div>${data[i].name} 회의실</div>
                        <div>${data[i].rsvDate}</div>
                        <div>시작시간 : ${data[i].startDate}</div>
                        <div>종료시간 : ${data[i].endDate}</div>
                        <div>성함 : ${data[i].empName}</div>
                    </div>
                `;
                if(loginMember === data[i].empNo){
                    str += `<button onclick="edit('${data[i].metRsvNo}', '${data[i].floor}', '${data[i].name}', '${data[i].rsvDate}', '${data[i].startDate}', '${data[i].endDate}');">변경하기</button>`;
                }
            }
            
            content.innerHTML = str;
        },
        error:()=>{
            alert("조회중 에러 발생하였습니다");
        }
    });
});

function edit(metRsvNo, floor, name, rsvDate, startDate, endDate) {
    const detail = document.querySelector("#detail");
    let str = `
        <div>
            <div>회의실 선택</div>
            <select id="meetingOption" name="meetingOption">
                <option value="selected">${floor}층 ${name} 회의실</option>
            </select>
        </div>
        <div>
            <label for="rsvDate">예약 날짜:</label>
            <input type="date" id="rsvDate" name="rsvDate" value="${rsvDate}">
        </div>
        <div>
            <label for="startDate">시작 시간:</label>
            <input type="time" id="startDate" name="startDate" value="${startDate}">
        </div>
        <div>
            <label for="endDate">종료 시간:</label>
            <input type="time" id="endDate" name="endDate" value="${endDate}">
        </div>
        <button onclick="updateMeeting('${metRsvNo}')">변경하기</button>
        <button type="button" onclick="closeBtn()">취소하기</button>
    `;
    
    detail.innerHTML = str;
    detail.style.display = "block";

    $.ajax({
        url:"/api/rent/option/meeting",
        method:"get",
        success:(data)=>{
            const meetingOption = document.querySelector("#meetingOption");
            let str = "<option value='selected'>" + floor + "층 " + name + " 회의실</option>";
    
            for(let i = 0; i < data.length; i++) {
                if (data[i].name !== name || data[i].floor !== floor) {
                    str += "<option value="+ data[i].metRoomNo +">" + data[i].floor + "층 " + data[i].name  + " 회의실" + "</option>";
                }
            }
            meetingOption.innerHTML = str;    
    
        },
        error: (error)=>{
            alert(error);
        },
    });
}

function updateMeeting(metRsvNo) {
    let meetingOption = document.querySelector("#meetingOption").value;
    const rsvDate = document.querySelector("#rsvDate").value;
    const startDate = document.querySelector("#startDate").value;
    const endDate = document.querySelector("#endDate").value;

    if (meetingOption === 'selected') {
        meetingOption = null;
    }

    console.log(meetingOption);

    $.ajax({
        url: "/api/rent/meeting",
        method: "put",
        data: {
            no: metRsvNo,
            metRoomNo: meetingOption,
            rsvDate: rsvDate,
            startDate: startDate,
            endDate: endDate
        },
        success: () => {
            alert("예약이 성공적으로 변경되었습니다.");
            location.reload();
        },
        error: (error) => {
            alert("수정 중 오류가 발생하였습니다.");
        }
    });
}

function closeBtn() {
    const detail = document.querySelector("#detail");
    detail.style.display = "none";
}
