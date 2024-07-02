package com.kh.works.attend.controller;

import com.kh.works.attend.service.AttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("attend")
public class AttendController {

    private final AttendService service;

}
