package com.kh.works.todo.controller;

//주석처리 했어용
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import com.kh.works.security.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.service.TodoService;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoVo;
import jakarta.servlet.http.HttpSession;
import java.util.List;
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
//    반환 타입이 int일때는 JSON으로 직접 변환할 수 없기 때문에,
//    @ResponseBody 애너테이션을 사용하거나 ResponseEntity<Integer>와 같은 객체를 사용하여
//    클라이언트에게 JSON 형식으로 데이터를 반환할 수 있다.
    public ResponseEntity todoWrite(TodoAllVo allVo ){

        
        int result = service.todoWrite(allVo);
        System.out.println("result = " + result);
                
        return ResponseEntity.ok(allVo);
    }


    //할일 상세 조회
    @GetMapping("detail")
    public ResponseEntity getTodoByNo(String no) {
        System.out.println("@@@@@@@@@@@@@@@@@@@no = " + no);
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



    //할일 수정 화면

    @GetMapping("edit")
    public String todoEditView(){
        return "todo/edit";

    }

//    //할일 수정(글번호 이용
//    @PostMapping("edit")
//    public ResponseEntity todoEdit(TodoVo vo,@RequestParam String todoNo ){
//        int result = service.todoEdit(vo, todoNo);
//
//        return ResponseEntity.ok(result);
//    }

    //할일 검색

    //할일 삭제...
}
