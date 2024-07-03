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
    public ResponseEntity getTodoByNo(TodoVo vo) {
      TodoVo todoVo  = service.getTodoByNo(vo);

        if (vo == null) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok(todoVo);
    }


    //모든 할일 목록조회(담당자 참여자 모두@@@@@@@@@@@@@@@@@@@@@@@여기서부터 다시 시작
    //리스트로 반환받기
    @GetMapping("listAll")
    @ResponseBody
    public List<TodoVo> getTodoListAll(TodoVo vo){

//        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
//        String empNo = loginEmpVo.getNo();
//        vo.setTodoEmpNo(empNo);
//
//
        String todoEmpNo = vo.getTodoEmpNo();


        vo.setTodoEmpNo(todoEmpNo);
        System.out.println("@@@@@@@@@@todoEmpNo = " + todoEmpNo);
        System.out.println("@@@@@@@@@@@@@@empNo = " + vo);

        //반환값을 List로 변환
        List<TodoVo> voList = service.getTodoListAll(vo);
        return voList;
    }



    //참여자 할일 목록조회(내가 참여자인것만
    @GetMapping("listPar")
    @ResponseBody
    public List<TodoVo> getTodoListPar(String empNo){
        List<TodoVo> pvoList = service.getTodoListPar(empNo);
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
