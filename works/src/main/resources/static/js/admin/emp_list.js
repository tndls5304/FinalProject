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
      error(error){
      console.log(" 직원 전체 목록보기 통신안됨");
      }
    })



 // 페이지 모두 로드 된 후 직원 상세보기
window.onload = function() {
   const tbody=  document.querySelector("tbody");
   tbody.addEventListener("click",getEmpByNo);

   function getEmpByNo(evt){
        console.log("상세보기클릭함")
        //클릭이벤트 부모 요소
        const trNode = evt.target.parentNode;


        /*상세보기 클릭하면 1초동안 색깔 바뀌게 하기
         1. 기존 배경색 저장                                 */
        const originalColor = trNode.style.backgroundColor;
         //2. 배경색을 소라색으로 변경
        trNode.style.backgroundColor = "lightBlue";
         //3. 초 후에 원래 배경색으로 복원
        setTimeout(function() {
        trNode.style.backgroundColor = originalColor;}, 0500);


        const empNo= trNode.children[0].innerText;
        $.ajax({
             url:'/admin/emp_by_no',
             method:'GET',
             data:{no: empNo},

             success:function(empVo){
             console.log("empVo를 가져왔니?",empVo);
              console.log("empVo의 사진을보까?",empVo.profile);

                modal.style.display = 'block';
                document.querySelector("#empNo").textContent =empVo.no;
                document.querySelector("#empImage").src ="/img/profile/"+empVo.profile;
                document.querySelector("input[name=name]").value = empVo.name;
                document.querySelector("input[name=phone]").value = empVo.phone;
                document.querySelector("input[name=pwd]").value = empVo.pwd;
                document.querySelector("input[name=email]").value =empVo.email;
                document.querySelector("input[name=loginFailNum]").value = empVo.loginFailNum;
                document.querySelector("#empHireDate").textContent = "입사일:" +empVo.hireDate;
                document.querySelector("input[name=lockYn]").value =  empVo.lockYn;
             },
             error:function(){}
            })
          }
 }




//------------------------------------------------모달창
// 모달 닫기 버튼 요소 참조
const modalClose = document.getElementById('modalClose');
const modal = document.getElementById('modal');

// 모달 닫기 버튼 클릭 시 이벤트 핸들러

    modalClose.addEventListener('click', function() {
        modal.style.display = 'none';
    });



    // 모달 창 밖을 클릭하여도 모달 닫기
    window.addEventListener('click', function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
//--------------------------------------------모달창에서 회원정보 수정

 function editEmp(){
      const no=$('#empNo').text();
      const name=$('input[name=name]').val();
      const phone=$('input[name=phone]').val();
      const pwd=$('input[name=pwd]').val();
      const loginFailNum=$('input[name=loginFailNum]').val();
      const lockYn=$('input[name=lockYn]').val();
      const email=$('input[name=email]').val();
      const dataValue={
                  no:no,
                  name:name,
                  phone:phone,
                  pwd:pwd,
                  loginFailNum:loginFailNum,
                  lockYn:lockYn,
                  email:email
      }
      $.ajax({
       url:'/admin/edit_emp',
       method:'POST',
       data: JSON.stringify(dataValue),
       contentType : 'application/json',
       success:function(){
        alert("수정 성공!!");
        location.reload();
       },
       error:function(){
        alert("수정실패");
       }
      })
    }

//-------------------------------------------------퇴사처리하기
  function resignEmp(){
    const no=$("#empNo").text();
    $.ajax({
      url:"/admin/resign_emp",
      method:"POST",
      data:{no:no},
      success:function(){
        alert("퇴사처리 완료!");
        location.reload();
      },
      error:function(){
        alert("퇴사처리 실패!")
      }
    })
  }