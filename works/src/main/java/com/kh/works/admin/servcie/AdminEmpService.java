package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminDao;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//-----------------------------------------------수인
@Service
@RequiredArgsConstructor
public class AdminEmpService {


    private final AdminDao dao;


    //신규사원 등록하는 페이지: 옵션에 고를 수 있게 부서 조회해오기
    public List<DeptVo> selectDeptList() {
        return dao.selectDeptList();
    }


    public void insertEmp(EmployeeVo employeeVo) {
        dao.insertEmp(employeeVo);
    }


    public List<PositionVo> selectPosition() {
        return dao.selectPosition();
    }



    public EmployeeVo getEmpByNo(String empNo) {
        return  dao.getEmpByNo(empNo);
    }

    public int editEmp(EmployeeVo vo) {
        return  dao.editEmp(vo);
    }

    public int resignEmp(String empNo) {
        return dao.resignEmp(empNo);
    }

    //조건부사원검색
    public List<EmployeeVo> selectEmpByCondition(EmployeeVo vo) {
        return dao.selectEmpByCondition(vo);
    }

    public String checkAuthYnForInsertEmp() {
        return  dao.checkAuthYnForInsertEmp();
    }

    public String checkAuthYnForUpdateEmpInfo() {
        return dao.checkAuthYnForUpdateEmpInfo();
    }

    public String checkAuthYnForResignEmp() {
        return  dao.checkAuthYnForResignEmp();
    }

    public String getEmpSeqNo() {
        return dao.getEmpSeqNo();
    }
}
