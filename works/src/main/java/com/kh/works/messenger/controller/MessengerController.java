package com.kh.works.messenger.controller;

//import에 EmpSessionVo 꼭 추가하기
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.security.EmpSessionVo;
import com.kh.works.messenger.service.MessengerService;
import com.kh.works.messenger.vo.MessengerVo;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    //로그인 한 사원번호로 받기 위해 @AuthenticationPrincipal~ 사용
    public String write(MessengerVo vo, @AuthenticationPrincipal EmpSessionVo loginEmployeeVo) {

        //내가 가지고 올 칼럼명, 즉 나는 사원번호이니까 empNo로 변수명 지어줌
        String empNo = loginEmployeeVo.getNo();
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
    public String getMessengerList(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo, Model model) {
        // 로그인한 사원의 사원번호를 가져온다.
        String senderEmpNo = loginEmployeeVo.getNo();
        String receiverEmpNo = loginEmployeeVo.getNo();

        List<MessengerVo> voList = service.getMessengerList(senderEmpNo, receiverEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/all";  // all.jsp로 포워딩
    }

    //받은 쪽지 화면
    @GetMapping("/received")
    public String getReceivedList(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo, Model model) {
        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmployeeVo.getNo();

        List<MessengerVo> voList = service.getReceivedList(receiverEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/received";
    }

    //보낸 쪽지 화면
    @GetMapping("sent")
    public String getSentList(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo, Model model){
        // 로그인한 사원의 사원번호를 가져온다.
        String senderEmpNo = loginEmployeeVo.getNo();

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
    public String getUnreadList(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo, Model model){
        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmployeeVo.getNo();

        List<MessengerVo> voList = service.getUnreadList(receiverEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/unread";
    }
    // 쪽지를 읽음으로 표시 - all.jsp에서 ajax 사용
    @PostMapping("read")
    public String read(@RequestParam(name = "messenNo") int messenNo) {
        System.out.println("Received messenNo: " + messenNo);
        service.read(messenNo);
        return "redirect:/messenger/unread";
    }

    //중요 쪽지 화면
    @GetMapping("important")
    public String getImportantList(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo, Model model){
        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmployeeVo.getNo();
        String senderEmpNo = loginEmployeeVo.getNo();

        List<MessengerVo> voList = service.getImportantList(receiverEmpNo, senderEmpNo);
        model.addAttribute("voList", voList);
        return "messenger/important";
    }
    //쪽지를 중요로 표시
    @PostMapping("importantStatus")
    public String importantStatus(@RequestParam(name = "messenNo") int messenNo){
        service.importantStatus(messenNo);
        return "redirect:/messenger/importantStatus";
    }

    //검색 기능 - 이름으로 검색
    @GetMapping("search")
    public String searchByKeyword(@RequestParam("keyWord") String keyWord, Model model){
        List<MessengerVo> voList = service.searchByKeyword(keyWord);
        model.addAttribute("voList", voList);
        return "messenger/search";
    }



}
