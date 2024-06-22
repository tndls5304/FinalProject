package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminMapper;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminMapper mapper;

    //신규사원 등록하는 페이지: 옵션에 고를 수 있게 부서 조회해오기
    public List<DeptVo> selectDeptList(){
        return mapper.selectDeptList();
    }

    public void insertEmp(EmployeeVo employeeVo) {
        mapper.insertEmp(employeeVo);
    }


    //테스ㅌ,
    public String getTest(){
        return  mapper.selectTest();
    }

}