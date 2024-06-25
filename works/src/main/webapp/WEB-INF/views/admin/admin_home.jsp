<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>




    <link rel="stylesheet" type="text/css" href="${ctx }/resource/js/jqgrid/css/ui.jqgrid.css"/>
    <script type="text/javascript" src="<c:url value='/resource/js/jqgrid/js/jquery.jqGrid.min.js'/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<%@ include file="/WEB-INF/views/layout/util.jsp" %>


   <style>


    </style>
</head>
<body>
        <%@ include file="/WEB-INF/views/layout/admin/aside.jsp" %>

    <main>
               <div class="header">
                        <h2>어드민 홈입니다</h2>
                        <table id="table1"></table>
               </div>
    </main>



    </div>


</body>
</html>