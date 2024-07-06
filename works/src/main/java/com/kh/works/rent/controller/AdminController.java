package com.kh.works.rent.controller;

import com.kh.works.rent.service.RentService;
import com.kh.works.rent.vo.CarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/rent")
public class AdminController {

    private final RentService service;

    //어드민 예약 확인페이지 보여주기
    @GetMapping("list")
    public String adminRent(){
        return "rent/adminList";
    }

    @PutMapping("approve")
    @ResponseBody
    public int adminApprove(@RequestParam("no") String no){
        int result = service.adminApprove(no);
        return result;
    }

    @PutMapping("fail")
    @ResponseBody
    public int adminFail(@RequestParam("no") String no){
        int result = service.adminFail(no);
        return result;
    }

    @GetMapping("api/list")
    @ResponseBody
    public List<CarVo> adminList(){
        List<CarVo> voList = service.adminList();
        return voList;
    }

}
