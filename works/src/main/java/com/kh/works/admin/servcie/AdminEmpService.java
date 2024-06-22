package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminDao;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//-----------------------------------------------수인
@Service
@RequiredArgsConstructor
public class AdminEmpService {


    private final AdminDao dao;


    //신규사원 등록하는 페이지: 옵션에 고를 수 있게 부서 조회해오기
    public List<DeptVo> selectDeptList(){
        return dao.selectDeptList();
    }


    public void insertEmp(EmployeeVo employeeVo) {
        dao.insertEmp(employeeVo);
    }


    //테스트
    public String getTest() {

        return dao.getTest();
    }


}
