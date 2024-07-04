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

    @GetMapping("home")
    public String homeView(){
        return "rent/home";
    }

    @GetMapping("meeting")
    public String meetingView(){
        return "rent/meeting";
    }

    @GetMapping("car")
    public String carView(){
        return "rent/car";
    }

}
