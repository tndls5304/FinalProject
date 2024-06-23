package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminMapper;
import com.kh.works.admin.vo.AdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminAccountDao {

    private final AdminMapper mapper;

    public AdminVo adminLoginIdMatching(String id) {
    return mapper.adminLoginIdMatching(id);
    }
}
