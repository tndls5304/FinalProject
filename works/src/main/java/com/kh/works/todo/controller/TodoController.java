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




    //할일 홈화면
    @GetMapping("home")
    public String todoHome(){
        return "todo/home";
    }

    //할일 작성
    @PostMapping("write")
    public String todoWrite(TodoVo vo, HttpSession session){
//        getAttribute :세션에 저장된 객체 가져오는 메소드
        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");


        //로그인한 회원 번호를 세션에서 가져와 EmpNo에 담아준다
        String empNo = loginEmpVo.getNo();
        String empName = loginEmpVo.getName();
        vo.setTodoEmpNo(empNo);
        vo.setTodoEmpName(empName);

        int result = service.todoWrite(vo);

            if (result != 1){
                return "common/error";
            }
            return "redirect:/todo/home";
        }

        //할일작성 화면 - 참여자 추가하기
    //model : 컨트롤러와 화면(뷰) 사이에서 데이터를 전달해주는 역할 empList를 가져와model에 담아준다
    @GetMapping("write")
   public String writeView(Model model){
        List<EmployeeVo> empList = service.getMangerList();
        model.addAttribute("empList", empList);
        return "todo/write";
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

    //할일 상세 조회
    //처음에 todoVo로 반환타입을 지정해줬는데 담당자 여러명이니까 List로 해줘야함 아직도 모르겠니...??
    @GetMapping("detail")
    @ResponseBody
    public List<TodoVo> getTodoByNo(@RequestParam("todoNo") String todoNoStr) {
        int todoNo = Integer.parseInt(todoNoStr); //todoNo int라서 형변환 해줌.
        List<TodoVo> voList = service.getTodoByNo(todoNo);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~vo" + voList);
        return voList;
    }


    //할일 수정(글번호 이용
    @PostMapping("edit")
    @ResponseBody
    public ResponseEntity todoEdit(TodoVo vo){
        int result = service.todoEdit(vo);

        return ResponseEntity.ok(result);
    }

    //할일 검색
    //@RequestParam을 이용해 요청을 매개변수로 받기 reqired = false =>해당 파라미터가 필수가 아니라는 뜻
    @GetMapping("search")
    @ResponseBody
    public List<TodoVo> todoSearch(@RequestParam(value = "title", required = false) String title){
        List<TodoVo> voList = service.todoSearch(title);
        return voList;
    }



    //할일 삭제

    @ResponseBody
    @GetMapping("delete")
    public int todoDelete(@RequestParam("todoNo") String todoNo){
        int result = service.todoDelete(todoNo);
        return result;
    }

    //할일 완료
    @PostMapping("complete")
    @ResponseBody
    public int todoComplete(@RequestParam("todoNo") String todoNo){
        int result = service.todoComplete(todoNo);
        return result;
    }

}
