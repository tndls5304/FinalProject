<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="UTF-8">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>
      <link rel="stylesheet" href="/css/todo/home.css">
    </head>

    <body>
      <!-- 네브 -->
      <%@ include file="/WEB-INF/views/layout/todo/nav.jsp" %>



        <main>
          <!-- 사이드바 -->
          <div id="sidebar">
            <button id="writeModalBtn">작성하기</button>
            <br>
            <button onclick="listAll();">전체 할일</button>
            <br>
            <button onclick="listPar();">담당 할일</button>
          </div>





          <!-- 검색창 -->
          <div id="content">
            <div><input type="search" id="search" placeholder="내용을 검색하세요.">
              <button type="submit" onclick="search();">검색</button>
            </div>


            <div>
              <input type="checkbox" id="del-all">
              <input type="button" id="del" value="삭제">
              <label for="sortOptions">정렬 기준:</label>
              <select id="sortOptions" name="sortOptions">
                <option value="latest">최신 작성 순</option>
                <option value="deadline">기한 마감순</option>
              </select>
            </div>


            <!-- 리스트, 상세조회 -->
            <div class="todo">

              <!-- 리스트조회 -->
              <div id="todo-list-all">
                <table id="todoList">
                </table>
              </div>

              <!-- 상세조회 -->
              <div id="detail"></div>
            </div>
          </div>


          <!-- 모달 -->
          <div id="todoModal" class="modal">
            <div class="modal-content">
              <!-- 모달창 안에 닫기 버튼 만들기 -->
              <span class="close">&times;</span>
              <!-- 할일 작성 폼 포함 -->
              <jsp:include page="/WEB-INF/views/todo/write.jsp" />
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
        var todoNo = $(this).find('.hidden-column').text();
        getTodoDetail(todoNo);
      });
    </script>

    <script>
      $(document).on('click', '.detTodoBtn', function () {
        var todoNo = $(this).find('.hidden-column').text();
        delTodo(todoNo);
      });
    </script>

    <script>
      $(document).on('click', '.comBtn', function () {
        var todoNo = $(this).find('.hidden-column').text();
        complete(todoNo);
      });
    </script>

    <script>
      $(document).on('click', '.editBtn', function () {
        var todoNo = $(this).find('.hidden-column').text();
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