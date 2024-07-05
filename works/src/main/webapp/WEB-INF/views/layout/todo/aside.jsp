<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <link rel="stylesheet" href="/css/layout/todo/aside.css">

  <div id="sidebar">
    <a href="http://127.0.0.1:8080/todo/write"><button>작성하기</button></a>
    <br>
    <button onclick="listAll();">전체 할일</button>
    <br>
    <button onclick="listPar();">담당 할일</button>
  </div>