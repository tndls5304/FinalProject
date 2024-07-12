<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>사이드바 구성</title>

         <!-- 제이쿼리-->
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

         <!-- 사이드바 불러오는 js -->
      <script defer src="/js/admin/common_getSidebar.js"></script>

        <!-- 글씨체 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
</head>
           <aside>
                <nav>
                    <ul>
                         <div id="sidebarMenu"></div>

                           <li><a href="/admin/logout"><button>로그아웃</button></a></li>
                    </ul>
                </nav>
            </aside>
    </html>