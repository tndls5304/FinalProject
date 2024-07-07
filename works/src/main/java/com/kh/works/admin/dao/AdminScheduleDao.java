package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminScheduleMapper;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminScheduleDao {
    private final AdminScheduleMapper mapper;

    public List<EmployeeVo> empList(String deptNo) {
        return mapper.empList(deptNo);
    }
}