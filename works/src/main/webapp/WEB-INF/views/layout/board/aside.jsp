<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/layout/board/aside.css">

<aside>
 
    <div id="top">
       <h2>게시판</h2>
       <button type="button" onclick="location.href='/board/write'">작성</button>
    </div>
    <div id="bottom">
        <div><a href="/notice/list">공지사항</a></div>
        <div><a href="#">최신게시글</a></div>
        <div><a href="/board/list">자유게시판</a></div>
        <div><a href="/board/myList">내가 작성한 게시글</a></div>
        <div><a href="#">중요 게시물</a></div>
    </div>

</aside>