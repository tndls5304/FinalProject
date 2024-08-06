package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminAccountMapper;
import com.kh.works.admin.mapper.AdminEmpMapper;
import com.kh.works.admin.vo.AdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminAccountDao {

    private final AdminAccountMapper mapper;

    public AdminVo login(AdminVo vo) {
        return mapper.login(vo);
    }
}
