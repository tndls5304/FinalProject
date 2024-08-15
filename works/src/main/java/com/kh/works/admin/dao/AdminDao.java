package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminEmpMapper;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminEmpMapper mapper;

    //신규사원 등록하는 페이지: 옵션에 고를 수 있게 부서 조회해오기
    public List<DeptVo> selectDeptList() {
        return mapper.selectDeptList();
    }

    public int insertEmp(EmployeeVo employeeVo) {
        return mapper.insertEmp(employeeVo);
    }

    public List<PositionVo> selectPosition() {
        return mapper.selectPosition();
    }


    public EmployeeVo getEmpByNo(String empNo) {
        return mapper.getEmpByNo(empNo);
    }

    public int editEmp(EmployeeVo vo) {
        return mapper.editEmp(vo);
    }

    public int resignEmp(String empNo) {
        return mapper.resignEmp(empNo);
    }

    public List<EmployeeVo> selectEmpByCondition(EmployeeVo vo) {
        return mapper.selectEmpByCondition(vo);
    }

    public String checkAuthYnForInsertEmp() {
        return mapper.checkAuthYnForInsertEmp();

    }

    public String checkAuthYnForUpdateEmpInfo() {
        return mapper.checkAuthYnForUpdateEmpInfo();
    }

    public String checkAuthYnForResignEmp() {
        return mapper.checkAuthYnForResignEmp();
    }

    public String getEmpSeqNo() {
        return mapper.getEmpSeqNo();
    }
}
