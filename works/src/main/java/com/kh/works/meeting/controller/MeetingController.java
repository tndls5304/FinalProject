package com.kh.works.meeting.controller;


import com.kh.works.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService service;

    //예약하기 홈 화면 보여주기
    @GetMapping("home")
    public String home(){
        return "meeting/home";
    }
}
