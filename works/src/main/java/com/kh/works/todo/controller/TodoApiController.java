package com.kh.works.todo.controller;


import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.service.TodoService;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class TodoApiController {
    private final TodoService service;



    //할일 상세 조회
    @GetMapping
    @ResponseBody
    public TodoVo getTodoByNo(@RequestParam("todoNo") String todoNoStr) {
        int todoNo = Integer.parseInt(todoNoStr); //todoNo int라서 형변환 해줌.
      TodoVo todoVo  = service.getTodoByNo(todoNo);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~vo" + todoVo);
        return todoVo;
    }


    //모든 할일 목록조회(담당자 참여자 모두//여기 todoVo넘겨주지 않고 그냥 세션에 있는거 넘겨주면 안되나?
    //리스트로 반환받기
    @GetMapping("listAll")
    @ResponseBody
    public List<TodoVo> getTodoListAll(TodoVo vo, HttpSession session){

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
        String todoEmpNo = loginEmpVo.getNo();

        vo.setTodoEmpNo(todoEmpNo);
        vo.setTodoManagerNo(todoEmpNo);

        //반환값을 List로 변환
        List<TodoVo> voList = service.getTodoListAll(vo);
        return voList;
    }



    //참여자 할일 목록조회(내가 참여자인것만 //이것도 세션으로 받으면 되려나...
    @GetMapping("listPar")
    @ResponseBody
    public List<TodoVo> getTodoListPar(TodoVo vo, HttpSession session){

        EmployeeVo loginEmpVo =(EmployeeVo)session.getAttribute("loginEmpVo");
        String todoEmpNo = loginEmpVo.getNo();
        vo.setTodoManagerNo(todoEmpNo);


        List<TodoVo> pvoList = service.getTodoListPar(vo);
        return pvoList;
    }



    //할일 수정(글번호 이용
    @PutMapping
    @ResponseBody
    public ResponseEntity todoEdit(TodoVo vo){
        int result = service.todoEdit(vo);

        return ResponseEntity.ok(result);
    }



    //할일 검색
    //@RequestParam을 이용해 요청을 매개변수로 받기 reqired = false =>해당 파라미터가 필수가 아니라는 뜻
    @GetMapping("search")
    @ResponseBody
    public List<TodoVo> todoSearch(@RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "content", required = false) String content){
        List<TodoVo> voList = service.todoSearch(title, content);
        return voList;
    }



    //할일 삭제

    @ResponseBody
    @GetMapping("delete")
    public int todoDelete(@RequestParam("todoNo") String todoNo){
        int result = service.todoDelete(todoNo);
        return result;
    }
}
