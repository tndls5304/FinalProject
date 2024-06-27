const urlParams = new URLSearchParams(window.location.search);
const boardNo = urlParams.get('boardNo');

$.ajax({
    url: "/board/api/detail",
    method: "get",
    data:{boardNo:boardNo},
    dataType: 'json',
    success: (data) => {
        console.log(data);



      const inputTag = document.createElement("input");
      inputTag.type = "text";
      inputTag.name = "title";
      inputTag.value = data.title;


      const inputTag2 = document.createElement("input");
      inputTag2.type = "text";
      inputTag2.name = "content"; 
      inputTag2.value = data.content;
      
      const inputTag3 = document.createElement("input");
      inputTag3.type = "submit";
      inputTag3.value = "수정하기"

      const inputTag4 = document.createElement("input");
        inputTag4.type = "hidden"; 
        inputTag4.name = "boardNo"; 
        inputTag4.value = boardNo; 

      const form = document.querySelector("form");
      form.appendChild(inputTag);
      form.appendChild(inputTag2);
      form.appendChild(inputTag3);
      form.appendChild(inputTag4);

        
    },
    error: (error) => {
       console.log("error :" , error);
    }
});
