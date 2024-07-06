function getTodoDetail(todoNo) {
  $.ajax({
    url: "/api/todo",
    method: "GET",
    data: { todoNo: todoNo },
    success: function (data) {
       const detail = document.querySelector("#detail");


      //   const title = document.createElement("div");
      //   title.classList.add("title");
      //   title.textContent = data[0].title;
  
      //   const content = document.createElement("div");
      //   content.classList.add("content");
      //   content.textContent = data[0].content;
  
      //   const endDate = document.createElement("div");
      //   endDate.classList.add("endDate");
      //   endDate.textContent = data[0].endDate;
        
        
      //   const todoManagerNo = document.createElement("div");
      //   todoManagerNo.classList.add("todoManagerNo");
      //   todoManagerNo.textContent = data[0].todoManagerNo;
  

      // detail.appendChild(title);
      // detail.appendChild(content);
      // detail.appendChild(endDate);
      // detail.appendChild(todoManagerNo);
      
      // detail.innerHTML= detail;
      const title = document.querySelector("#detail");

      let str = "";
      str +=`<div id='todoNo'>${data[0].todoNo}</div>`;
      str +=`<div id='title'>${data[0].title}</div>`;
      str +=`<div id='content'>${data[0].content}</div>`;
      str +=`<div id='endDate'>${data[0].endDate}</div>`;
      
      str +=`<div id='todoManagerNo'>담당자 :`;
      for(let i = 0; i < data.length; i++){
        str +=`<button>${data[i].todoManagerNo}</button>`
      } 
      str += '</div>';


      title.innerHTML= str;

    },
    error: function (err) {
      console.error("상세조회 아작스 실행중 에러", err);
      alert("상세조회 아작스 실행중 에러");
    },
  });
}
