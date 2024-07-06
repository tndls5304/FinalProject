$.ajax({
    url: "/admin/rent/api/list",
    method: "get",
    success: (data) => {
        const content = document.querySelector("#content");
        let str = "";
        console.log(data);

        for (let i = 0; i < data.length; i++) {
            str += `
            <div ${data[i]}>
                <div>${data[i].vhclRsvNo}</div>
                <div>${data[i].empName}</div>
                <div>${data[i].vhclNumber}</div>
                <div>${data[i].name}</div>
                <div>${data[i].reason}</div>
                <div>${data[i].loanDate}</div>
                <div>${data[i].returnDate}</div>
            `;

            if (data[i].cancelReservationYn === 'N') {
                str += `
                <div>${data[i].approvalStatus}</div>
                <button onclick="approve(${data[i].vhclRsvNo});">승인</button>
                <button onclick="fail(${data[i].vhclRsvNo});">보류</button>
                `;
            } else {
                str += `<div>취소된 예약입니다.</div>`;
            }

            str += `</div>`;
        }

        content.innerHTML = str;
    }
    ,error:(ERROR)=>{
        alert("예약조회 실패하였습니다 다시 시도해주세요");
        console.log(ERROR);
    }
})

function approve(no) {
   $.ajax({
        url:"/admin/rent/approve"
        ,method:"put"
        ,data:{
            no:no
        }
        ,success:()=>{
            alert("승인완료 되었습니다");
            location.reload();  
 
        }
        ,error:(x)=>{
            console.log(x);
        }


   })
}

function fail(no) {
    $.ajax({
        url:"/admin/rent/fail"
        ,method:"put"
        ,data:{
            no:no
        }
        ,success:()=>{
            alert("보류되었습니다");
            location.reload();
        }
        ,error:(x)=>{
            console.log(x);
        }
    })
    
}