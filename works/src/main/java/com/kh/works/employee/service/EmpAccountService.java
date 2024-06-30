package com.kh.works.employee.service;

import com.kh.works.employee.dao.EmpAccountDao;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpAccountService {

    private final EmpAccountDao dao;
    public EmployeeVo empLoginMatching(EmployeeVo vo) {
       return dao.empLoginMatching(vo);
    }
}
