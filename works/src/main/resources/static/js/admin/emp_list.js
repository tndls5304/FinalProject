    $.ajax({
      url:'/admin/list_all_emp',
      method:'GET',
      success(empList){
        console.log("통신성공");
        const tbodyTag=document.querySelector("tbody");

        for(let i=0; i<empList.length; i++){

          const trTag = document.createElement("tr");
          tbodyTag.appendChild(trTag);

          const empVo=empList[i];

          const tdTag1=document.createElement("td");
          tdTag1.innerText=empVo.no;
          trTag.appendChild(tdTag1);

          const tdTag2=document.createElement("td");
          tdTag2.innerText=empVo.deptName;
          trTag.appendChild(tdTag2);

          const tdTag3=document.createElement("td");
          tdTag3.innerText=empVo.positionName;
          trTag.appendChild(tdTag3);

          const tdTag4=document.createElement("td");
          tdTag4.innerText=empVo.name;
          trTag.appendChild(tdTag4);

          const tdTag5=document.createElement("td");
          tdTag5.innerText=empVo.id;
          trTag.appendChild(tdTag5);


          const tdTag6=document.createElement("td");
          tdTag6.innerText=empVo.lockYn;
          trTag.appendChild(tdTag6);

        }
      },
      error(){}

    })