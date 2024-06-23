package com.kh.works.employee.dao;

import com.kh.works.employee.mapper.EmpAccountMapper;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmpAccountDao {
    private final EmpAccountMapper mapper;

    public EmployeeVo empLoginIdMatching(String id) {
       return mapper.empLoginIdMatching(id);
    }
}
