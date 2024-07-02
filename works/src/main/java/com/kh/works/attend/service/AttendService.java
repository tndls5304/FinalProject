package com.kh.works.attend.service;

import com.kh.works.attend.dao.AttendDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendDao dao;

}
