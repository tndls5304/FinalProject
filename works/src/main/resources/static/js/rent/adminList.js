$.ajax({
    url:"/api/rent/car"
    ,method : "get"
    ,success:(data)=>{
        const content = document.querySelector("#content");
        let str = "";

        for(let i =0; i< data.length ; i++){
            str = `
            <div>
                <div>'${data[i].vhclRsvNo}'</div>
                <div>'${data[i].empName}'</div>
                <div>'${data[i].vhclNumber}'</div>
                <div>'${data[i].name}'</div>
                <div>'${data[i].reason}'</div>
                <div>'${data[i].loanDate}'</div>
                <div>'${data[i].returnDate}'</div>
                <div>'${data[i].approvalStatus}'</div>
            </div>
            `

            content.innerHTML= str;
        }
    }
    ,error:(ERROR)=>{
        alert("예약조회 실패하였습니다 다시 시도해주세요");
        console.log(ERROR);
    }
})