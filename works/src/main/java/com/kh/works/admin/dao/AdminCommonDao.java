package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminCommonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminCommonDao {

    private final AdminCommonMapper mapper;

    public String selectSidePageComponent() {
//       return mapper.selectSidePageComponent();
        return null;
    }
}
