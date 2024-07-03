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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {


    private final TodoService service;



//    //할일 작성 화면
//    @GetMapping("write")
//    public String write(){
//        return "todo/write";
//    }

    //할일 작성
    @PostMapping("write")
//    반환 타입이 int일때는 JSON으로 직접 변환할 수 없기 때문에,
//    @ResponseBody 애너테이션을 사용하거나 ResponseEntity<Integer>와 같은 객체를 사용하여
//    클라이언트에게 JSON 형식으로 데이터를 반환할 수 있다.
    public String todoWrite(TodoVo vo){
        //getAttribute :세션에 저장된 객체 가져오는 메소드
//        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
//
//
//        //로그인한 회원 번호를 세션에서 가져와 EmpNo에 담아준다
//        String empNo = loginEmpVo.getNo();
//        String empName = loginEmpVo.getName();
//        vo.setTodoEmpNo(empNo);
//        vo.setTodoEmpName(empName);

        int result = service.todoWrite(vo);

            if (result != 1){
                return "common/error";
            }
            return "redirect:/todo/listAll";
        }

        //할일작성 화면 - 참여자 추가하기
    //model : 컨트롤러와 화면(뷰) 사이에서 데이터를 전달해주는 역할 empList를 가져와model에 담아준다
    @GetMapping("write")
   public String writeView(Model model){
        List<EmployeeVo> empList = service.getMangerList();
        model.addAttribute("empList", empList);
        return "todo/write";
    }

    //할일 상세 조회 화면
    @GetMapping("detail")
    public String detail(){
        return "todo/detail";
    }


    //모든 할일 목록조회 화면(담당자 참여자 모두
    @GetMapping("listAll")
    public String listAll(){
        return "todo/listAll";
    }


    //참여자 할일 목록조회 화면(내가 참여자인것만
    @GetMapping("listPar")
   public String listPar() {
        return "todo/listPar";
    }


    //할일 수정 화면
    @GetMapping("edit")
    public String edit(){
        return "todo/edit";

    }


    //할일 검색

    //할일 삭제...

    //할일 완료
    @ResponseBody
    @GetMapping("completed")
    public int todoCompleted(TodoVo vo){
        int result = service.todoCompleted(vo);
        return result;
    }

}
