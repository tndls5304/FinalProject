package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminCommonMapper;
import com.kh.works.admin.vo.AdminPageMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminCommonDao {

    private final AdminCommonMapper mapper;

    public List<AdminPageMenuVo> selectSidePageComponent(String adminAuthNo) {
     return mapper.selectSidePageComponent(adminAuthNo);
    }
}
