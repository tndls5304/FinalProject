package com.kh.works.todo.controller;

//주석처리 했어용
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import com.kh.works.security.EmpSessionVo;
import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.service.TodoService;
//import com.kh.works.todo.vo.TodoAllVo;
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


    //!!!뭔가를 셀렉트 해올때 그냥 다 넘겨받아오는게 기능구현하기 편하다 받아와야할 항목만 받아오면 나중에 추가해야할게 많아지니까 시간이 많으면 ///

    //할일 홈화면
    // 할일 작성을 모달로 만들면 여기서 담당 사원 목록을 가져왔어야 한다.== 모달을 동적이라 새로운 서버로 요청을 보내지 않기 때문에 미리 받아오기!!todoHome에서 모달창을 여는것이기 때문에!
    @GetMapping("home")
    public String todoHome(Model model){

        List<EmployeeVo> empList = service.getMangerList();
        model.addAttribute("empList", empList);

        return "todo/home";
    }

    //할일 작성
    @PostMapping("write")
    public String todoWrite(TodoVo vo, HttpSession session){
//        getAttribute :세션에 저장된 객체 가져오는 메소드
        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@loginEmpVo = " + loginEmpVo);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@vo = " + vo);

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

//    @모달창을 열때는 별도의 URL요청을 보내지 않는다.(창이 열릴때 서버에 새로운 요청을 보내지 않기 때문에!==현재 상태를 유지하며 데이터 표시)
//    @따라서 todoHome에서 미리 담당자 데이터를 받아와야 한다.그래서 모달창에서 담당자 리스트가 안떴었던 것...!
        //할일작성 화면 - 참여자 추가하기
    //model : 컨트롤러와 화면(뷰) 사이에서 데이터를 전달해주는 역할 empList를 가져와model에 담아준다
//    @GetMapping("write")
//   public String writeView(Model model){
//        List<EmployeeVo> empList = service.getMangerList();
//        model.addAttribute("empList", empList);
//        return "todo/write";
//    }

    //모든 할일 목록조회(요청자 담당자 모두
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

    //담당자 할일 목록조회(내가 담당자인것만
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
    //세션추가 == 로그인 한 사람 글만 검색해야 하니까
    //@RequestParam을 이용해 요청을 매개변수로 받기 reqired = false =>해당 파라미터가 필수가 아니라는 뜻
    @GetMapping("search")
    @ResponseBody
    public List<TodoVo> todoSearch(TodoVo vo, HttpSession session){
        EmployeeVo loginEmpVo =(EmployeeVo)session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();
        vo.setTodoEmpNo(empNo);
        vo.setTodoManagerNo(empNo);

        System.out.println("@@@@@@@@@@@@@@@todoEmpNo = " + empNo);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@TodoVo = " + vo);

        List<TodoVo> voList = service.todoSearch(vo);


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

//    할일을 작성할때 알람 테이블에 같이 insert를 해준다
//    그 다음 알람 컨트롤러를 만들어 읽지않은 알람(받은알람)과 읽은알람 처리를 해준다
//    =>select, update

    //할일 담당자 알림
    @PostMapping("todoAlarm")
    @ResponseBody
    public ResponseEntity<List<AlarmVo>> getTodoAlarm(HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String todoManagerNo = loginEmpVo.getNo();

        //받은 알람 가져오기
        List<AlarmVo> unread = service.getTodoAlarm(todoManagerNo);

        //읽은 알람 처리
        service.read(todoManagerNo);

        //읽지 않은 알람 목록 띄워주기
        return ResponseEntity.ok(unread);
    }


    //사원 정보 조회
    @GetMapping("empInfo")
    @ResponseBody
    public EmployeeVo getEmpInfo(EmployeeVo empVo){
        EmployeeVo empInfo = service.getEmpInfo(empVo);





        return empInfo;
    }
}
