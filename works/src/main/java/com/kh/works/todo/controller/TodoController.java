package com.kh.works.todo.controller;

//주석처리 했어용
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import com.kh.works.security.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.service.TodoService;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {


    private final TodoService service;


    //할일 작성
    @PostMapping("write")
    @ResponseBody
//    반환 타입이 int일때는 JSON으로 직접 변환할 수 없기 때문에,
//    @ResponseBody 애너테이션을 사용하거나 ResponseEntity<Integer>와 같은 객체를 사용하여
//    클라이언트에게 JSON 형식으로 데이터를 반환할 수 있다.
    public int todoWrite(TodoAllVo allVo ){

        
        int result = service.todoWrite(allVo);
        System.out.println("result = " + result);
                
        return result;
    }


    //할일 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public ResponseEntity getTodoByNo(String no) {
        System.out.println("@@@@@@@@@@@@@@@@@@@no = " + no);
        TodoVo vo = service.getTodoByNo(no);

        if (vo == null) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok("detail");
    }
}
