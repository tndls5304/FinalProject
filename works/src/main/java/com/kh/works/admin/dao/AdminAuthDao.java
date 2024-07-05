package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminAuthMapper;
import com.kh.works.admin.vo.SubAdminMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminAuthDao {
    private final AdminAuthMapper mapper;

    public List<SubAdminMenuVo> getMenuVoList() {
        return mapper.getMenuVoList();
    }

    public int updateAuth(SubAdminMenuVo vo) {
        return mapper.updateAuth(vo);
    }

    public String checkAuthYn() {
        return  mapper.checkAuthYn();
    }
}
