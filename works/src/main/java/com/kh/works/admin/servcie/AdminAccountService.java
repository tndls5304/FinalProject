package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminAccountDao;
import com.kh.works.admin.vo.AdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

    private final AdminAccountDao dao;

    public AdminVo login(AdminVo vo) {
       return dao.login(vo);
    }
}
