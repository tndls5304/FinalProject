package com.kh.works.notice.controller;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.notice.service.NoticeService;
import com.kh.works.notice.vo.NoticeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("board/notice")
@RequiredArgsConstructor
public class NoticeMemberController {

    private final NoticeService service;

    @GetMapping("list")
    public String empNotice(){
        return "notice/empList";
    }

    //리스트 emp홈페이지에도 보여주기
    @GetMapping
    @ResponseBody
    public List<NoticeVo> empView(){
        List<NoticeVo> voList = service.empView();
        return voList;
    }

    @GetMapping("api/detail")
    @ResponseBody
    public NoticeVo Noticedetail(@RequestParam("noticeNo")String noticeNo){
        NoticeVo voList = service.noticeDetail(noticeNo);
        return voList;
    }

    @GetMapping("/detail")
    public String NoticeDetailView(HttpSession session){
        System.out.println("넘어옴");
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        return "notice/empDetail";
    }
}
