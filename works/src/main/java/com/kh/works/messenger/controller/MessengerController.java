package com.kh.works.messenger.controller;

//import에 EmpSessionVo 꼭 추가하기
import com.kh.works.security.EmpSessionVo;
import com.kh.works.messenger.service.MessengerService;
import com.kh.works.messenger.vo.MessengerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
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
    @GetMapping("write")
    public String write(){
        return "messenger/write";
    }

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

    //전체 쪽지 화면
    @GetMapping("all")
    public String getMessengerList(Model model) {
        List<MessengerVo> voList = service.getMessengerList();
        model.addAttribute("voList", voList);
        return "messenger/all";  // all.jsp로 포워딩
    }

    //받은 쪽지 화면
    @GetMapping("received")
    public String getReceivedList(Model model){
        List<MessengerVo> voList = service.getReceivedList();
        model.addAttribute("voList", voList);
        return "messenger/received";
    }

    //보낸 쪽지 화면
    @GetMapping("sent")
    public String getSentList(Model model){
        List<MessengerVo> voList = service.getSentList();
        model.addAttribute("voList", voList);
        return "messenger/sent";
    }

    //안 읽음 쪽지 화면 (내가 받은 쪽지함 중)
    @GetMapping("unread")
    public String getUnreadList(Model model){
        List<MessengerVo> voList = service.getUnreadList();
        model.addAttribute("voList", voList);
        return "messenger/unread";
    }
    // 쪽지를 읽음으로 표시
    @PostMapping("markAsRead")
    public String markAsRead(@RequestParam("messenNo") int messenNo) {
        service.markAsRead(messenNo);
        return "redirect:/messenger/unread";
    }




    //임시저장 쪽지 화면
    @GetMapping("saved")
    public String saved(){
        return "messenger/saved";
    }


}
