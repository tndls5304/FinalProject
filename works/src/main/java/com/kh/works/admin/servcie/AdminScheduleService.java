package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminScheduleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminScheduleService {
    private final AdminScheduleDao dao;
}
