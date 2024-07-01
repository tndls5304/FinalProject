package com.kh.works.notice.controller;

import com.kh.works.admin.vo.AdminVo;
import com.kh.works.notice.service.NoticeService;
import com.kh.works.notice.vo.NoticeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    private final NoticeService service;

    @GetMapping("write")
    public String writeView(){
        return "notice/write";
    }

    //관리자 번호 받아오기
    //게시물 작성하기
    @PostMapping("write")
    public String write(NoticeVo vo ,HttpSession session){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@vo +" + vo);
        AdminVo loginAdminVo = (AdminVo)session.getAttribute("loginAdminVo");

        String no = loginAdminVo.getNo();
        System.out.println("#########################no =" + no);

        vo.setNo(no);
        int result = service.write(vo);
        if(result != 1){
            return "common/error";
        }
        return "redirect:/notice/list";
    }

    //게시물 보여주기
    @GetMapping("list")
    public String listView(){
        return "notice/list";
    }

    //게시물 글씨 내보내기
    @GetMapping("api/list")
    @ResponseBody
    public List<NoticeVo> list(){
        return service.list();
    }

    //관리자 번호 받아와서 관리자만 수정삭제하기 버튼 만들기
    //게시물 상세조회
    @GetMapping("detail")
    public String detailView(){
        //관리자 번호 가져오기
        return "notice/detail";
    }

    //게시물 상세조회 글씨 내보내기
    @GetMapping("api/detail")
    public NoticeVo detail(@RequestParam("noticeNo") String noticeNo , HttpSession session){

        AdminVo loginAdminNo = (AdminVo)session.getAttribute("loginAdminVo");

        String no = loginAdminNo.getNo();
        session.setAttribute("no" , no);
        NoticeVo vo = service.detail(noticeNo);
        return vo;
    }

    //삭제하기
    @GetMapping("delete")
    public String delete(@RequestParam("noticeNo")String noticeNo){
        int result = service.delete(noticeNo);

        if(result != 1){
            return "common/error";
        }
        return "redirect:/notice/list";
    }


    
    


}
