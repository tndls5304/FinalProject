package com.kh.works.employee.dao;

import com.kh.works.employee.mapper.EmpAccountMapper;
import com.kh.works.employee.vo.EmpSessionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmpAccountDao {
    private final EmpAccountMapper mapper;

    public EmpSessionVo empLoginIdMatching(String id) {
       return mapper.empLoginIdMatching(id);
    }

}
