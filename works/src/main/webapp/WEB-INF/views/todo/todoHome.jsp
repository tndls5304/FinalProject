<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/css/todo/home.css">
  </head>

  <body>

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
      <aside>
        <input type="button" id="write" value="작성하기">
        <br>
        <input type="button" id="todo-all" value="전체 할일">
        <br>
        <input type="button" id="todo-manager" value="담당 할일">
      </aside>




      <div id="content">
        <div><input type="search" id="search" placeholder="할 일을 검색하세요.">
          <button type="submit">검색</button>
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

        <div class="todo">


          <div id="todo-list">
            <div class="todo-item">
              <input type="checkbox" id="todo1">
              <input type="checkbox" id="del1">
              <label for="todo1" class="todo-title">첫 번째 투두 제목</label>
              <br>
              <span class="todo-details">담당자: 이지수</span>
              <span class="todo-details">기한: 2024-06-30</span>
            </div>

            <div class="todo-item">
              <input type="checkbox" id="todo2">
              <input type="checkbox" id="del2">
              <label for="todo1" class="todo-title">두번째 투두 제목</label>
              <br>
              <span class="todo-details">담당자: 이수인</span>
              <span class="todo-details">기한: 2024-06-30</span>
            </div>

            <div class="todo-item">
              <input type="checkbox" id="todo1">
              <input type="checkbox" id="del1">
              <label for="todo1" class="todo-title">첫 번째 투두 제목</label>
              <br>
              <span class="todo-details">담당자: 박근아</span>
              <span class="todo-details">기한: 2024-06-30</span>
            </div>

            <div class="todo-item">
              <input type="checkbox" id="todo1">
              <input type="checkbox" id="del1">
              <label for="todo1" class="todo-title">첫 번째 투두 제목</label>
              <br>
              <span class="todo-details">담당자: 송예린</span>
              <span class="todo-details">기한: 2024-06-30</span>
            </div>
          </div>


          <div id="todo-detail">
            <div id="todo-edit">
              <button>수정</button>
              <button>삭제</button>

            </div>
            <div id="todo-detail-content">
              <h3>투두제목</h3>
              <p>내 할일
                절라 많음
              </p>
            </div>


            <div id="todo-text">
              <p>기한</p>
              요청자 : <button>송예린</button>
              <br>
              담당자 : <button>송예린</button>
              <br>
              <button type="submit">완료하기</button>

            </div>

            <div id="todo-status">
              <div>작업내역</div>
              <div class="todo-emp1">송예린님이 할일을 완료로 변경했습니다.</div>

              <div class="todo-emp1">이수인님이 할일을 완료로 변경했습니다.</div>

              <div class="todo-emp1">이지수님이 할일을 완료로 변경했습니다.</div>

              <div class="todo-emp1">박근아님이 할일을 완료로 변경했습니다.</div>
            </div>





          </div>


        </div>





      </div>
    </main>

  </body>

  </html>



  </body>

  </html>