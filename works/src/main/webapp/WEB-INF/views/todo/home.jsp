<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="UTF-8">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>할일</title>
      <script>
        window.onload = function () {
          listAll();
        };
      </script>
      <link rel="stylesheet" href="/css/todo/home.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>
      <!-- 네브 -->
      <nav>
        <div id="right-sidebar">
          <div><img src="/img/icon/todo.png" alt=""></div>
          <div id="title">할일</div>
        </div>
        <div id="left-sidebar">
          <div><a href=""><img src="/img/icon/chatting.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/email.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/organization-chart.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/customer.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/paper.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/reserved.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/board.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/calendar.png" alt=""></a></div>
          <div><a href=""><img src="/img/icon/todo.png" alt=""></a></div>
          <div><button><img src="/img/icon/user.png" alt=""></button></div>
        </div>
      </nav>



      <main>
        <!-- 사이드바 -->
        <div id="sidebar">
          <button id="writeModalBtn">할 일 쓰기</button>
          <br>
          <button class="sideBtn" onclick="listAll();">전체 할일</button>
          <button class="sideBtn" onclick="listPar();">담당 할일</button>
        </div>





        <!-- 검색창 상세조회!!!!!!!!!-->
        <div id="content">


          <div class="up-nav">
            <div id="search-div">
              <input type="search" id="search" placeholder="내용을 검색하세요.">
              <button type="submit" onclick="search();"><i class="fas fa-search"></i></button>
            </div>

            <div id="todo-check">
              <input type="checkbox" id="select-all">
              <label>전체선택 </label>
              <input type="button" value="삭제" onclick="checkDelete()">
              <label for="sortOptions">정렬</label>
              <select id="sortOptions" name="sortOptions">
                <option value="createDate">최신 작성 순</option>
                <option value="endDate">기한 마감순</option>
              </select>
            </div>
          </div>


          <!-- 리스트, 상세조회 -->
          <div class="todo">

            <!-- 리스트조회 -->
            <div id="todo-list-all">
              <table id="todoList">
              </table>
            </div>

            <!-- 상세조회 -->
            <div id="detail">
              <div class="title"></div>
              <div id="create-date"></div>
              <div id="end-date"></div>
              <div class="request"></div>
              <div class="manager"></div>
              <div class="action-btn"></div>
              <div id="comBtn"></div>
            </div>
          </div>


        </div>





        <!-- 참여자 모달 -->
        <div id="todoModal" class="modal">
          <div class="modal-content">
            <!-- 모달창 안에 닫기 버튼 만들기 -->
            <span class="close">&times;</span>
            <!-- 할일 작성 폼 -->
            <h3>할 일 쓰기</h3>
            <form id="todoForm" action="/todo/write" method="post">

              <input type="text" id="title" name="title" placeholder="제목을 입력해주세요." required>
              <br><br>


              <textarea id="form-content" name="content" placeholder="내용을 입력해주세요." required></textarea>
              <br><br>

              <label for="parNo" data-name="todoEmpNo">요청자:${loginEmpVo.name}</label>
              <br><br>

              <button type="button" onclick="clickBtn()">담당자 조회</button>
              <div id="managerList" style="display: none;">
                <div id="todoManager">
                  <c:forEach var="emp" items="${empList}">
                    <div class="managerItem">
                      <input type="checkbox" name="todoManagerList" value="${emp.no}">
                      ${emp.name}&nbsp;&nbsp;${emp.deptNo}
                    </div>
                  </c:forEach>
                </div>
              </div>
              <br><br>

              <label for="endDate">기한
                <input type="hidden" id="endDate" name="endDate">
                <div class="dateBtn">
                  <button class="endDateBtn" type="button" onclick="setEndDate('today',event)">오늘</button>
                  <button class="endDateBtn" type="button" onclick="setEndDate('tomorrow', event)">내일</button>
                  <button class="endDateBtn" type="button" onclick="setEndDate('nextWeek',event)">다음주</button>
                </div>
              </label>

              <br><br>

              <input type="submit" value="저장">
            </form>
          </div>
        </div>

        <!-- 사원조회 모달 -->
        <div id="empModal" class="modal">
          <div class="modal-content">
            <!-- 모달창 안에 닫기 버튼 만들기 -->
            <span class="close">&times;</span>
            <!-- 사원 정보 표시 -->
            <h3>사원 정보</h3>
            <div id="empDetail"></div>
          </div>
        </div>



      </main>

    </body>

    </html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script src="/js/todo/listAll.js"></script>
    <script src="/js/todo/listPar.js"></script>
    <script src="/js/todo/detail.js"></script>
    <script src="/js/todo/delTodo.js"></script>
    <script src="/js/todo/complete.js"></script>
    <script src="/js/todo/edit.js"></script>
    <script src="/js/todo/write.js"></script>


    <script>
      //모달 열기
      $(document).ready(function () {
        var modal = document.getElementById("todoModal"); //모달div가져오기
        var btn = document.getElementById("writeModalBtn"); //작성하기 버튼 가져오기
        var span = document.getElementsByClassName("close")[0];//getElementsByClassName은 모두 배열로 반환 그래서 0번째 배열을 가져옴

        btn.onclick = function () {
          modal.style.display = "block";
        }

        span.onclick = function () {
          modal.style.display = "none";
        }

        //evt타겟이 모달이 아닐때 모달창을 닫아줌.
        window.onclick = function (evt) {
          if (event.target == modal) {
            modal.style.display = "none";
          }
        }
      });
    </script>


    <!-- 동적 요소에 이벤트 처리하는 방법  -->
    <!--(document).on('click', ...) => 이벤트 위임 제이쿼리 메소드 동적으로 추가되는 요소에대해 이벤트 처리를 해준다...!-->
    <!--'#todoList tr' => 이벤트를 수신할 모든 요소 선택 todoList아래에 tr을 선택-->
    <!--var todoNo = $(this).find('.hidden-column').text(); => 클릭된 요소의 텍스트를 가져옴..todoNo를 가져온다-->
    <!--getTodoDetail(todoNo); => 앞에서 가져온 할일번호를 매개변수로 넣어준 뒤 함수실행..! -->
    <script>
      $(document).on('click', '#todoList tr', function () {
        const todoNo = $(this).find('.hidden-column').text();
        getTodoDetail(todoNo);
      });
    </script>

    <script>
      $(document).on('click', '.detTodoBtn', function () {
        const todoNo = $(this).find('.hidden-column').text();
        delTodo(todoNo);
      });
    </script>

    <script>
      $(document).on('click', '.comBtn', function () {
        const todoNo = $(this).find('.hidden-column').text();
        complete(todoNo);
      });
    </script>

    <script>
      $(document).on('click', '.editBtn', function () {
        const todoNo = $(this).find('.hidden-column').text();
        edit(todoNo);
      });
    </script>


    <!-- 검색하고 목록 조회 -->
    <script>
      function search(searchData) {
        const searchContent = document.querySelector("#search").value;
        $.ajax({
          url: "http://127.0.0.1:8080/todo/search",
          method: "GET",
          data: {
            content: searchContent,
          },
          success: function (searchData) {
            const table = document.querySelector("#todoList");

            let str = "";
            for (let i = 0; i < searchData.length; i++) {
              str += "<tr>";
              str += "<td>" + searchData[i].content + "</td>";
              str += "<td class='hidden-column'>" + searchData[i].todoNo + "</td>"; // todoNo 열을 숨김 처리
              str += "</tr>";
              str += "<tr>";
              str += "<td>요청자 " + searchData[i].todoEmpName + "</td>";
              str += "<td>기한 " + searchData[i].endDate + "</td>";
              str += "</tr>";
              str += "<tr><td colspan='2'>&nbsp;</td></tr>"; //공백추가
            }
            table.innerHTML = str;

            //검색내용 지우기
            document.querySelector("#search").value = "";


          },
          error: function (err) {
            alert("검색 ajax 실행중 에러");
          },

        });

      }
    </script>

    <!-- <script>
  //할일 폼 제출//아무짝에도 쓸모없다....
  $('#todoForm').on('submit', function (event) {
    event.preventDefault(); // 기본 폼 제출 방지

    var formData = $(this).serialize(); // 폼 데이터 직렬화

    $.ajax({
      url: '/todo/write', // 폼 action에서 URL 가져오기
      type: 'POST', // 폼 method에서 HTTP 메서드 가져오기
      data: formData,
      success: function (data) {
        // 성공 시 처리
        modal.style.display = "none"; // 모달 닫기
        listAll(); // listAll 함수 호출하여 할 일 목록 갱신
      },
      error: function (error) {
        // 에러 시 처리
        alert('할 일 작성에 실패하였습니다.');
      }
    });
  });
</script> -->

    <!-- 사원 상세조회 왜 안되는데==백틱을 안쓰니까 해결...지금까지 잘 썼는데 왜지-->
    <script>
      function getEmpDetail(empNo) {

        $.ajax({
          url: "/todo/empInfo",
          method: "GET",
          data: { no: empNo },
          success: function (data) {
            console.log("Received Data:", data); // 서버로부터 받은 데이터 로그 출력

            const empDetailDiv = document.querySelector("#empDetail");
            console.log("empDetailDiv:", empDetailDiv); // 선택된 요소 확인

            let str = "";
            str += "<p>이름: " + data.name + "</p>";
            str += "<p>이메일: " + data.email + "</p>";
            str += "<p>직급: " + data.positionName + "</p>";
            str += "<p>부서: " + data.deptName + "</p>";

            empDetailDiv.innerHTML = str; // 데이터 삽입

            // 데이터 삽입 후 로그 출력
            console.log("@@@@@empDetailDiv updated:", empDetailDiv.innerHTML);

            // 모달 열기
            const empModal = document.getElementById("empModal");
            empModal.style.display = "block";
          },
          error: function (err) {
            console.error("사원 정보 조회 중 오류 발생", err);
          }
        });
      }

      // 모달 닫기 기능
      $(document).ready(function () {
        const empModal = document.getElementById("empModal");
        const closeBtn = document.querySelector("#empModal .close");

        closeBtn.onclick = function () {
          empModal.style.display = "none";
        }

        window.onclick = function (event) {
          if (event.target == empModal) {
            empModal.style.display = "none";
          }
        }
      });

    </script>

    <!-- 정렬하기 -->
    <script>
      //DOM이 로드된 뒤 실행 셀렉트 옵션을 선택함
      $(document).ready(function () {
        $('#sortOptions').change(function () { //change: 이벤트 리스너임 사용자가 선택한 옵션이 바뀔때마다 실행
          const sortOption = $(this).val(); // 선택된 옵션의 값을 반환해서 변수에 넣어줌..

          $.ajax({
            url: '/todo/' + sortOption,
            method: 'GET',
            success: function (data) {
              // 결과를 화면에 표시하는 로직
              const table = document.querySelector("#todoList");
              const detail = document.querySelector("#detail");

              let str = "";
              for (let i = 0; i < data.length; i++) {
                str += "<tr>";
                str +=
                  "<td class='todo-checkbox-container'><input type='checkbox' class='todo-checkbox' todo-no='" +
                  data[i].todoNo +
                  "'></td>";
                //completedYn이용하여 완료한 할일은 제목에 줄 그어주기...!
                if (data[i].completedYn === "Y") {
                  str +=
                    "<td class='todo-title' style='text-decoration: line-through;'>" +
                    data[i].title +
                    "</td>";
                } else {
                  str += "<td class='todo-title'>" + data[i].title + "</td>";
                }
                str += "<td class='hidden-column' >" + data[i].todoNo + "</td>"; // todoNo 열을 숨김 처리
                str += "</tr>";
                str += "<tr>";
                str +=
                  "<td class='empAndEnd' colspan='2'>요청자: " +
                  data[i].todoEmpName +
                  " | 기한: " +
                  data[i].endDate +
                  "</td>";
                str += "</tr>";
                str += "<tr><td colspan='2'>&nbsp;</td></tr>"; //공백추가
              }
              table.innerHTML = str;
              detail.innerHTML = "";
            },
            error: function (xhr, status, error) {
              console.error("목록 정렬 조회중 에러");
            }
          });
        });
      });
    </script>

    <script>
      //전체삭제 , 개별삭제 
      //전체 선택에 체크하면 목록의 모든 체크박스가 선택되는 코드
      document.querySelector('#select-all').addEventListener('change', function (event) {
        const checked = event.target.checked;
        const todoCheckboxes = document.querySelectorAll('.todo-checkbox');
        //전체 선택 여부에 따라 체크박스 상태 변경
        todoCheckboxes.forEach(function (checkbox) {
          checkbox.checked = checked;
        });
      });
      function checkDelete() {

        // 체크박스 선택하면 todoNo를 배열로 넘겨주는 코드
        const checkboxes = document.querySelectorAll('.todo-checkbox:checked'); // 체크된 체크박스만 선택
        const todoNoList = []; // todoNo를 저장할 배열
        // 선택된 체크박스의 data-id 값을 todoNoList 배열에 추가한다
        checkboxes.forEach(checkbox => {
          todoNoList.push(checkbox.getAttribute('todo-no')); // 체크된 체크박스에서 todoNo를 가져옴
        });


        if (todoNoList.length > 0) {
          $.ajax({
            url: '/todo/checkDel',
            method: 'POST',
            data: { todoNoList: todoNoList },
            success: function (response) {
              alert("삭제 완료!"); // 삭제 완료 메시지
              listAll(); // 목록을 다시 불러오는 함수 호출
              // 전체 선택 체크박스를 체크 해제
              document.querySelector('#select-all').checked = false;
            },
            error: function (err) {
              console.error("삭제 중 오류 발생", err);
            }
          });
        } else {
          alert("삭제할 항목을 선택하세요."); // 체크된 항목이 없을 경우 경고
        }
      }

    </script>