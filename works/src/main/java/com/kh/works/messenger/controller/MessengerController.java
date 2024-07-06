package com.kh.works.messenger.controller;

//import에 EmpSessionVo 꼭 추가하기
import com.kh.works.employee.vo.EmployeeVo;
//import com.kh.works.security.EmpSessionVo;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import com.kh.works.messenger.service.MessengerService;
import com.kh.works.messenger.vo.MessengerVo;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("messenger")
@RequiredArgsConstructor
public class MessengerController {

    private final MessengerService service;

    //쪽지 화면
//    @GetMapping("write")
//    public String write(){
//        return "messenger/write";
//    }

    //쪽지 작성
    @PostMapping("write")
    //로그인 한 사원번호로 받기 위해 @AuthenticationPrincipal~ 사용 - 이거 삭제함
    //로그인 한 사원 - HttpSession session 사용해서 꺼내옴
    public String write(MessengerVo vo, HttpSession session) {

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        //내가 가지고 올 칼럼명, 즉 나는 사원번호이니까 empNo로 변수명 지어줌
        String empNo = loginEmpVo.getNo();
        System.out.println("empNo = " + empNo);
        //현재 MessengerVo에 senderEmpNo가 필요하니까 setSenderEmpNo(내가 지어준 변수명)
        vo.setSenderEmpNo(empNo);

        int result = service.write(vo);
        if (result == 1) {
            return "redirect:/messenger/all";
        } else {
            return "redirect:/messenger/write";
        }
    }

    //쪽지 작성 - 사원목록 처리하기 위한 메서드
    @GetMapping("write")
    public String showEmployee(Model model){
        List<EmployeeVo> employeeList = service.getEmployeeList();
        System.out.println("employeeList = " + employeeList);
        model.addAttribute("employeeList", employeeList);
        return "messenger/write";
    }


    //전체 쪽지 화면
    @GetMapping("all")
    public String getMessengerList(HttpSession session, Model model) {

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String senderEmpNo = loginEmpVo.getNo();
        String receiverEmpNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getMessengerList(senderEmpNo, receiverEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/all";  // all.jsp로 포워딩
    }

    //받은 쪽지 화면
    @GetMapping("/received")
    public String getReceivedList(HttpSession session, Model model) {

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getReceivedList(receiverEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/received";
    }

    //보낸 쪽지 화면
    @GetMapping("sent")
    public String getSentList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String senderEmpNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getSentList(senderEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/sent";
    }

    //상세 쪽지 화면
    @GetMapping("detail")
    public String getDetailPage(@RequestParam("messenNo") String messenNo, Model model){
        List<MessengerVo> voList = service.getDetailPage(messenNo);
        model.addAttribute("voList", voList);
        return "messenger/detail";
    }

    //안 읽음 쪽지 화면
    @GetMapping("unread")
    public String getUnreadList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getUnreadList(receiverEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/unread";
    }
    // 쪽지를 읽음으로 표시 - all.jsp에서 ajax 사용
    // 수신자가 쪽지를 읽었을 경우에만, 읽음(Y)으로 바뀌어야 된다. 그러기 위해, 아래와 같은 로직 사용.
    @PostMapping("read")
    public String read(@RequestParam(name = "messenNo") int messenNo, HttpSession session) {
        System.out.println("Received messenNo: " + messenNo);

        //로그인한 사원 정보를 가져온다.
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String receiverEmpNo = loginEmpVo.getNo();

        //쪽지 정보를 가져온다.
        MessengerVo messengerVo = service.getMessengerById(messenNo);

        //쪽지의 수신자가 현재 로그인한 사원인지 확인하는 로직
        if(messengerVo != null && receiverEmpNo.equals(messengerVo.getReceiverEmpNo())){
            service.read(messenNo);
        }

        return "redirect:/messenger/unread";
    }

    //중요 쪽지 화면
    @GetMapping("important")
    public String getImportantList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getImportantList(empNo);
        model.addAttribute("voList", voList);
        return "messenger/important";


//        ---------------------------기존 코드----------------------------------
//        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
//
//        // 로그인한 사원의 사원번호를 가져온다.
//        String receiverEmpNo = loginEmpVo.getNo();
//        String senderEmpNo = loginEmpVo.getNo();
//
//        List<MessengerVo> voList = service.getImportantList(receiverEmpNo, senderEmpNo);
//        model.addAttribute("voList", voList);
//        return "messenger/important";
    }
    //쪽지를 중요로 표시
    @PostMapping("importantStatus")
    public String importantStatus(@RequestParam(name = "messenNo") int messenNo, HttpSession session){

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();

        service.importantStatus(messenNo, empNo);
        return "redirect:/messenger/important";

//        ----------------기존 코드------------------
//       ------ HttpSession session도 없애야함.------
//        service.importantStatus(messenNo);
//        return "redirect:/messenger/importantStatus";
    }


    //검색 기능 - 이름으로 검색
    @GetMapping("search")
    public String searchByKeyword(@RequestParam("keyWord") String keyWord, Model model){
        List<MessengerVo> voList = service.searchByKeyword(keyWord);
        model.addAttribute("voList", voList);
        return "messenger/search";
    }



}
