package com.kh.works.rent.controller;

import com.kh.works.rent.service.RentService;
import com.kh.works.rent.vo.CarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @GetMapping("/api/list")
//    public List<CarVo> carList(){
//        List<CarVo> voList = service.adminCarList();
//    }

}
