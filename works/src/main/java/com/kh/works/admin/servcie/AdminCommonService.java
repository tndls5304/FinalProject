package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminCommonDao;
import com.kh.works.admin.vo.AdminPageMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommonService {
    private final AdminCommonDao dao;

    public List<AdminPageMenuVo> selectSidePageComponent(String adminAuthNo) {
       return dao.selectSidePageComponent(adminAuthNo);
    }
}
