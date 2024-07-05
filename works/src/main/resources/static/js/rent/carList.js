$(document).ready(function() {
    // loginMember 값 가져오기
    const loginMember = document.querySelector("#loginMember").innerText.trim();
    console.log("loginMember:", loginMember);
    
    // 리스트 조회하기
    $.ajax({
        url: "/api/rent/car",
        method: "get",
        success: (data) => {
            const content = document.querySelector("#carContent");

            let str = "";

            for (let i = 0; i < data.length; i++) {
                str += "<div>" + `${data[i].vhclRsvNo}` + "</div>";
                str += "<div>" + data[i].empName + "</div>";
                str += "<div>" + data[i].vhclNumber + "</div>";
                str += "<div>" + data[i].name + "</div>";
                str += "<div>" + data[i].approvalStatus + "</div>";
                str += `<button class="openBtn" data-no="${data[i].vhclRsvNo}">상세조회</button>`;
            }

            content.innerHTML = str;

            const openBtns = document.querySelectorAll(".openBtn");
            openBtns.forEach((btn) => {
                btn.addEventListener("click", () => {
                    const vhclRsvNo = btn.getAttribute("data-no");
                    detail(vhclRsvNo, loginMember);
                });
            });
        },
        error: () => {}
    });
});

function detail(no, loginMember) {
    const detail = document.querySelector("#detail");

    $.ajax({
        url: "/api/rent/detail/car",
        method: "get",
        data: {
            no: no
        },
        success: (data) => {
            let str = "";
            str += "<div>" + `${data.vhclRsvNo}` + "</div>";
            str += "<div>" + `${data.loanDate}` + "</div>";
            str += "<div>" + `${data.returnDate}` + "</div>";
            str += "<div>" + `${data.reason}` + "</div>";
            str += "<div>" + `${data.vhclNumber}` + "</div>";
            str += "<div>" + `${data.name}` + "</div>";
            str += "<div>" + data.approvalStatus + "</div>";
            str += "<div>" + data.empName + "</div>";

            const writeNo = data.empNo;

            if (loginMember === writeNo) {
                str += `<button onclick="editDate('${data.vhclRsvNo}', '${data.loanDate}', '${data.returnDate}', '${data.reason}', '${data.vhclNumber}', '${data.name}')">수정하기</button>`;
            }

            detail.innerHTML = str;
            detail.style.display = "block"; 
        },
        error: () => {
            alert("잠시 후 다시 조회해주세요");
        }
    });
}

function editDate(vhclRsvNo, loanDate, returnDate, reason, vhclNumber, name) {
    const detail = document.querySelector("#detail");

    let str = `
        <input type="date" id="loanDate" name="loanDate" value="${loanDate}"><br><br>
        <input type="date" id="returnDate" name="returnDate" value="${returnDate}"><br><br>
        <label for="reason">사유:</label><br>
        <textarea id="reason" name="reason" placeholder="${reason}"></textarea><br><br>
        <select id="carOption">
        </select>
        <button onclick="carEdit('${vhclRsvNo}')">수정완료</button>
    `;

    detail.innerHTML = str;

    // 옵션 값 가져오기
    $.ajax({
        url: "/api/rent/option/car",
        method: "get",
        success: (data) => {
            const carOption = document.querySelector("#carOption");
            let option = "";

            for (let i = 0; i < data.length; i++) {
                if (data[i].vhclNumber === vhclNumber) {
                    option += `<option value="${data[i].vhclNo}" selected>${data[i].name} [ 차량번호 : ${data[i].vhclNumber} ]</option>`;
                } else {
                    option += `<option value="${data[i].vhclNo}">${data[i].name} [ 차량번호 : ${data[i].vhclNumber} ]</option>`;
                }
            }

            carOption.innerHTML = option;
        },
        error: () => {
            console.error("옵션 로딩 실패");
        }
    });
}

function carEdit(no) {

    const loanDate = document.querySelector("#loanDate").value;
    const returnDate = document.querySelector("#returnDate").value;
    const reason = document.querySelector("#reason").value;
    const carOption = document.querySelector("#carOption").value;

    
    $.ajax({
        url:"/api/rent/car"
        ,method:"put"
        ,data:{
            no:no
            ,vhclNo:carOption
            ,loanDate:loanDate
            ,returnDate:returnDate
            ,reason:reason
        }
        ,success:()=>{
            alert("수정 성공하였습니다");
        }
        ,error:()=>{
            alert("수정 실패하였습니다 담당부서로 연락주세요"+
                "010 - 5738 - 2844 [총무부 : 박근아]"
            )
        }
    })
}
