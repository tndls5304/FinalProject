package com.kh.works.todo.controller;


import com.kh.works.todo.service.TodoService;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class TodoApiController {
    private final TodoService service;


    //할일 작성
    @PostMapping
//    반환 타입이 int일때는 JSON으로 직접 변환할 수 없기 때문에,
//    @ResponseBody 애너테이션을 사용하거나 ResponseEntity<Integer>와 같은 객체를 사용하여
//    클라이언트에게 JSON 형식으로 데이터를 반환할 수 있다.
    public ResponseEntity todoWrite(TodoAllVo allVo ){


        int result = service.todoWrite(allVo);
        System.out.println("result = " + result);

        return ResponseEntity.ok(allVo);
    }


    //할일 상세 조회
    @GetMapping
    public ResponseEntity getTodoByNo(String no) {
        TodoVo vo = service.getTodoByNo(no);

        if (vo == null) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok(vo);
    }


    //모든 할일 목록조회(담당자 참여자 모두
    //리스트로 반환받기
    @GetMapping("listAll")
    @ResponseBody
    public List<TodoVo> getTodoListAll(String empNo){
        //반환값을 List로 변환
        List<TodoVo> voList = service.getTodoListAll(empNo);
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
    //테이블이 나눠져 있기때문에 두개의 테이블을 업데이트 해야 한다.//sql에서 트리거 사용...!
    @ResponseBody
    @GetMapping("delete")
    public int todoDelete(@RequestParam("todoNo") String todoNo){
        int result = service.todoDelete(todoNo);
        return result;
    }
}
