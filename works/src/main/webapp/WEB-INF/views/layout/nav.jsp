<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="/css/layout/nav.css">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

<nav>
    <div id="right-sidebar">
        <img src="/img/icon/menu.png" id="menu" onclick="none();">
        <div id="logo"><a href="/home">Baby Developer</a></div>
    </div>
    <div id="left-sidebar">
        <div><button><img src="/img/icon/user.png" alt="" id="modalBtn"></button></div>
    </div>
    <div id="modal" style="display: none;">
        <div id="grid">
            <div>
                <a href="/attend/allList"><i class="fa-regular fa-address-card fa-xl" style="color: #7ec7ff;"></i></a>
                <span>근태관리</span>   
            </div>
            <!--알람-->
            <div>
                <a href="/messenger/all"><i class="fa-regular fa-paper-plane fa-xl" style="color: #7ec7ff;;"></i></a>
                <span>쪽지</span>
            </div>
            <!--쪽지-->
            <div>
                <a href="/rent/home"><i class="fa-regular fa-calendar-check fa-xl" style="color: #7ec7ff;;"></i></a>
                <span>예약</span>
            </div>
            <!--예약-->
            <div>
                <a href="/board/list"><i class="fa-solid fa-chalkboard-user fa-xl " style="color: #7ec7ff;;"></i></a>
                <span>게시판</span>
            </div>
            <!--게시판-->
            <div>
                <a href="/todo/home"><i class="fa-solid fa-list-check fa-xl" style= "color: #7ec7ff;;"></i></a>
                <span>할일</span>
            </div>
            <!--할일-->
            <div>
                <a href=""><i class="fa-solid fa-arrow-right-from-bracket fa-xl"  style= "color: #7ec7ff;;"></i></a>
                <span>로그아웃</span>
            </div>
            <!--로그아웃-->
        </div>
    </div>
</nav>

<script src="/js/layout/nav.js"></script>
