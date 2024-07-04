package com.kh.works.employee.dao;

import com.kh.works.employee.mapper.EmpAccountMapper;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmpAccountDao {
    private final EmpAccountMapper mapper;


    public EmployeeVo empLoginIdMatching(EmployeeVo vo) {

        return mapper.empLoginIdMatching(vo);
    }

    public int empJoinDuplicateTest(String id) {
        return mapper.empJoinDuplicateTest(id);
    }

    public int join(EmployeeVo vo) {
       return mapper.join(vo);
    }

    public int plusLoginFailNum(String loginFailEmpNo) {
        return mapper.plusLoginFailNum(loginFailEmpNo);
    }

    public int lockAccount(String loginFailEmpNo) {
        return mapper.lockAccount(loginFailEmpNo);
    }

    public String findId(EmployeeVo vo) {
        return mapper.findId(vo);
    }
}
