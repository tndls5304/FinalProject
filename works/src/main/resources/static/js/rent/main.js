$.ajax({
    url:"/api/rent/option/meeting",
    method:"get",
    success:(data)=>{
        
        const meetingOption = document.querySelector("#meetingOption");
        let str = "";

        for(let i =0 ; i < data.length; i++) {
            str += "<option value="+ data[i].metRoomNo +">" +data[i].floor + "층 " + data[i].name  + " 회의실" + "</option>"
        }
        meetingOption.innerHTML = str;    

    },
    error: (error)=>{
        alert(error);
    },
 });

 $.ajax({
    url:"/api/rent/option/car",
    method : "get",
    success:(data)=>{

        const carOption = document.querySelector("#carOption");
        let str ="";

        for(let i = 0 ; i< data.length ; i++){
            str+="<option value=" +data[i].vhclNo+ ">" + data[i].name + "   [차량번호 : " +data[i].vhclNumber+"]"+"</option>"
        }

        carOption.innerHTML = str;

    },
    error:()=>{

    },
 })
 

 function meetingRent() {
    const rentDate = document.querySelector("#rentDate").value;
    const startTime = document.querySelector("#startTime").value;
    const endTime = document.querySelector("#endTime").value;
    const meetingRoomNo = document.querySelector("#meetingOption").value;

    const startDate = rentDate + " " + startTime ;
    const endDate = rentDate + " " + endTime ;

    console.log(rentDate);
    console.log(startTime);
    console.log(endTime);
    console.log(meetingRoomNo);

    $.ajax({
        url:"/api/rent/meeting",
        method:"post",
        data:{
            metRoomNo: meetingRoomNo,
            rsvDate: rentDate,
            startDate: startDate,
            endDate: endDate
        },
        success: () => {
            alert("예약 성공하였습니다");
        },
        error: () => {
            alert("예약실패, 다시 확인 후 예약해주세요");
        }
    });
}


 
 function carRent(){

     const startDate =document.querySelector("#startDate").value;
     const endDate = document.querySelector("#endDate").value;
     const reason = document.querySelector("#reason").value;
     const carNo = document.querySelector("#carOption").value;

     console.log(startDate);
     console.log(endDate);
     console.log(reason);
     console.log(carNo);

     
     $.ajax({         
         url:"/api/rent/car"
         ,method:"post"
         ,data:{
            vhclNo:carNo
            ,loanDate:startDate
            ,returnDate:endDate
            ,reason:reason
        }
        ,success:()=>{
            alert("예약되었습니다 자세한 내용은 상세페이지에서 확인해주세요")
            window.location.href = "/rent/car";
        }
        ,error:()=>{
            alert("예약실패 , 다시 확인 후 예약해주세요")
    
        }
     })
 }