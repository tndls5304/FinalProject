package com.kh.works.meeting.service;


import com.kh.works.meeting.dao.MeetingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingDao dao;
}
