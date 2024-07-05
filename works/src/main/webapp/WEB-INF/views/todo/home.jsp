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
            <a href="http://127.0.0.1:8080/todo/write"><button>작성하기</button></a>
            <br>
            <button onclick="listAll();">전체 할일</button>
            <br>
            <button onclick="listPar();">담당 할일</button>
          </div>





          <!-- 검색창 -->
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


            <!-- 리스트, 상세조회 -->
            <div class="todo">

              <!-- 리스트조회 -->
              <div id="todo-list-all">
                <table id="todoList">
                </table>
              </div>

              <!-- 상세조회 -->
              <div id="detail">
                상세조회
              </div>
            </div>

            <!-- 리스트, 상세조회 -->

          </div>
        </main>

    </body>

    </html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script src="/js/todo/listAll.js"></script>
    <script src="/js/todo/listPar.js"></script>
    <script src="/js/todo/detail.js"></script>


    <!-- 동적 요소에 이벤트 처리하는 방법  -->
    <!--(document).on('click', ...) => 이벤트 위임 제이쿼리 메소드 동적으로 추가되는 요소에대해 이벤트 처리를 해준다...!-->
    <!--'#todoList tr' => 이벤트를 수신할 모든 요소 선택 todoList아래에 tr을 선택-->
    <!--var todoNo = $(this).find('.hidden-column').text(); => 클릭된 요소의 텍스트를 가져옴..todoNo를 가져온다-->
    <!--getTodoDetail(todoNo); => 앞에서 가져온 할이번호를 매개변수로 넣어준 뒤 함수실행..! -->
    <script>
      $(document).on('click', '#todoList tr', function () {
        var todoNo = $(this).find('.hidden-column').text();
        getTodoDetail(todoNo);
      });
    </script>