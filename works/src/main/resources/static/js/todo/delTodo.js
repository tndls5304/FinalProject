function delTodo(todoNo){
  console.log("삭제 연결");
  $.ajax({
    url : "http://127.0.0.1:8080/todo/delete",
    method : "GET",
    data : {todoNo : todoNo},
    success : function(){
      alert("삭제완료");
      listAll();
      const title = document.querySelector("#detail");
      title.innerHTML= '';
    },
    error: function(err){
      console.error("삭제 실행중 에러", err);
    }
  });
}