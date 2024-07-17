package com.kh.works.messenger.controller;

//import에 EmpSessionVo 꼭 추가하기
import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
//import com.kh.works.security.EmpSessionVo;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import com.kh.works.messenger.service.MessengerService;
import com.kh.works.messenger.vo.MessengerVo;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.logging.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

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
        //알림(Socket) 띄워줄 때, 보낸사람 이름이 나오게 하려고 설정함
        String empName = loginEmpVo.getName();

        //System.out.println("empNo = " + empNo);

        //현재 MessengerVo에 senderEmpNo가 필요하니까 setSenderEmpNo(내가 지어준 변수명)
        vo.setSenderEmpNo(empNo);
        //알림(Socket) 띄워줄 때, 보낸사람 이름이 나오게 하려고 설정함
        vo.setSenderName(empName);

        int result = service.write(vo);
        if (result == 1) {
            return "redirect:/messenger/all";
        } else {
            return "redirect:/messenger/write";
        }
    }

    //쪽지 작성 - 작성할 때, 사원목록 띄우기 위한 메서드
    @GetMapping("write")
    public String showEmployee(Model model, HttpSession session){
        List<EmployeeVo> employeeList = service.getEmployeeList();
        System.out.println("employeeList = " + employeeList);

        //HttpSession이 필요없는데 사용한 이유 : unreadCount 변수명을 사용해야 하는데, 매개변수로 EmpNo가 필요해서 사용하였다.
        //unreadCount 변수명이 필요한 이유 : write.jsp에서 안읽은 쪽지 갯수를 표시해줘야 되기 위해서.
        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
        String receiverEmpNo = loginEmpVo.getNo();
        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(receiverEmpNo);

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("unreadCount", unreadCount);
        return "messenger/write";
    }


    //전체 쪽지 화면(목록조회)
    @GetMapping("all")
    public String getMessengerList(HttpSession session, Model model) {

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String senderEmpNo = loginEmpVo.getNo();
        String receiverEmpNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getMessengerList(senderEmpNo, receiverEmpNo);

        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(receiverEmpNo);

        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
        return "messenger/all";  // all.jsp로 포워딩
    }

    //받은 쪽지 화면(목록조회)
    @GetMapping("/received")
    public String getReceivedList(HttpSession session, Model model) {

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmpVo.getNo();

        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(receiverEmpNo);

        List<MessengerVo> voList = service.getReceivedList(receiverEmpNo);
        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
        return "messenger/received";
    }

    //보낸 쪽지 화면(목록조회)
    @GetMapping("sent")
    public String getSentList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String senderEmpNo = loginEmpVo.getNo();

        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(senderEmpNo);

        List<MessengerVo> voList = service.getSentList(senderEmpNo);
        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
        return "messenger/sent";
    }

    //상세 쪽지 화면
    @GetMapping("detail")
    public String getDetailPage(@RequestParam("messenNo") String messenNo, Model model, HttpSession session){

        List<MessengerVo> voList = service.getDetailPage(messenNo);

        //HttpSession이 필요없는데 사용한 이유 : unreadCount 변수명을 사용해야 하는데, 매개변수로 EmpNo가 필요해서 사용하였다.
        //unreadCount 변수명이 필요한 이유 : detail.jsp에서 안읽은 쪽지 갯수를 표시해줘야 되기 위해서.
        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
        String receiverEmpNo = loginEmpVo.getNo();
        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(receiverEmpNo);

        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
        return "messenger/detail";
    }

    //안 읽음 쪽지 화면(목록조회)
    @GetMapping("unread")
    public String getUnreadList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");

        // 로그인한 사원의 사원번호를 가져온다.
        String receiverEmpNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.getUnreadList(receiverEmpNo);
        //안 읽음 쪽지 갯수 세기 위한 코드
        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(receiverEmpNo);

        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
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

    //중요 쪽지 화면(목록조회)
    @GetMapping("important")
    public String getImportantList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();

        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(empNo);

        List<MessengerVo> voList = service.getImportantList(empNo);
        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
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

        int result = service.importantStatus(messenNo, empNo);

        if(result > 0){
            return "redirect:/messenger/important";
        }else{
            return "redirect:/messenger/error";
        }


//        ----------------기존 코드------------------
//       ------ HttpSession session도 없애야함.------
//        service.importantStatus(messenNo);
//        return "redirect:/messenger/importantStatus";
    }


    //검색 기능 - 목록조회에서 사원이름으로 검색
    @GetMapping("search")
    public String searchByKeyword(@RequestParam("keyWord") String keyWord, HttpSession session, Model model) {
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String receiverNo = loginEmpVo.getNo();
        String senderNo = loginEmpVo.getNo();

        List<MessengerVo> voList = service.searchByKeyword(keyWord, receiverNo, senderNo);
        model.addAttribute("voList", voList);

        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(receiverNo);
        model.addAttribute("unreadCount", unreadCount);

        return "messenger/all";
    }



    //휴지통 화면(목록조회)
    @GetMapping("trash")
    public String trash(HttpSession session, Model model) {

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();

        //안 읽음 쪽지 갯수 나타내기 위해 -> 뷰에 보이게 하기 위해, model에 추가한다.
        int unreadCount = service.getUnreadCount(empNo);

        List<MessengerVo> voList = service.trash(empNo);
        model.addAttribute("voList", voList);
        model.addAttribute("unreadCount", unreadCount);
        return "messenger/trash";
    }
    //휴지통함으로 쪽지 보내기
    @PostMapping("trashStatus")
    @ResponseBody
    //여기에는 ResponseEntity 사용해 볼게요.
    //List로 매개변수를 넘기는 이유 : 체크박스 (개별과) 전체 선택이 가능하도록 하기 위함.
    public ResponseEntity<String> trashMessen(@RequestParam("messenNoList") List<Integer> messenNoList, HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();

        service.trashMessen(messenNoList, empNo);
        return ResponseEntity.ok("[Success] Trash");
    }
    //휴지통함에서 쪽지 완전 삭제하기
    @PostMapping("delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam("messenNoList") List<Integer> messenNoList, HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();

        service.delete(messenNoList, empNo);
        return ResponseEntity.ok("[Success] Delete");
    }



    //알림 기능 - Socket을 사용하기 위한 Controller
    @PostMapping("alarmInfor")
    @ResponseBody
    public ResponseEntity<List<AlarmVo>> getAlarmInfor(HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        //사원번호가 아닌, 이름으로 띄워주면 좋을 것 같다.
        String receiverEmpNo = loginEmpVo.getNo();

        List<AlarmVo> unreadAlarm = service.getAlarmInfor(receiverEmpNo);

        return ResponseEntity.ok(unreadAlarm);
    }
    @PostMapping("readAlarm")
    @ResponseBody
    public ResponseEntity<String> readAlarm(@RequestParam("alarmNo") int alarmNo) {
        System.out.println("readAlarm 호출: alarmNo = " + alarmNo);

        int result = service.readAlarm(alarmNo);

        if (result > 0) {
            System.out.println("Controller: 알림 읽음 처리 성공");
            return ResponseEntity.ok("알림 읽음 처리 성공");
        } else {
            System.out.println("Controller: 알림 읽음 처리 실패");
            return ResponseEntity.status(500).body("알림 읽음 처리 실패");
        }
    }


}
