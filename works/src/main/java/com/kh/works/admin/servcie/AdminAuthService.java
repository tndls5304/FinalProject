package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminAuthDao;
import com.kh.works.admin.vo.SubAdminMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final AdminAuthDao dao;

    public List<SubAdminMenuVo> getMenuVoList() {
        return  dao.getMenuVoList();
    }
}
