package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminCommonDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCommonService {
    private final AdminCommonDao dao;


    public String selectSidePageComponent() {
       return dao.selectSidePageComponent();

    }
}
