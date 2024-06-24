package com.kh.works.home.dao;

import com.kh.works.employee.vo.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.home.mapper.HomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HomeDao {

    private final HomeMapper mapper;


    public EmployeeVo selectEmpInfo(String no) {
        return mapper.selectEmpInfo(no);
    }
}
