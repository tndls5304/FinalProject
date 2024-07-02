package com.kh.works.meeting.controller;


import com.kh.works.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiMeetingController {

    private final MeetingService service;

}
