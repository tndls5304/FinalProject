package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//-----------------------------------------------수인
@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminDao dao;

    public String getTest() {
       return dao.getTest();
    }
}
