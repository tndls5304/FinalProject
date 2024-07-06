package com.kh.works.rent.controller;

import com.kh.works.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("rent")
public class RentController {

    private final RentService service;

    //예약홈페이지
    @GetMapping("home")
    public String homeView(){
        return "rent/home";
    }

    //회의실 예약 목록
    @GetMapping("meeting")
    public String meetingView(){
        return "rent/meetingList";
    }

    //차량 예약 목록
    @GetMapping("car")
    public String carView(){
        return "rent/carList";
    }


}
