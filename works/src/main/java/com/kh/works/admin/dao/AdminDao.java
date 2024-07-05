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
    public List<DeptVo> selectDeptList(){
        return mapper.selectDeptList();
    }

    public void insertEmp(EmployeeVo employeeVo) {

        mapper.insertEmp(employeeVo);
    }

    public List<PositionVo> selectPosition() {
        return  mapper.selectPosition();
    }

    public List<EmployeeVo> getAllEmpList() {
        return mapper.getAllEmpList();
    }

    public EmployeeVo getEmpByNo(String no) {
        return mapper.getEmpByNo(no);
    }

    public int editEmp(EmployeeVo vo) {
        return mapper.editEmp(vo);
    }

    public int resignEmp(String no) {
        return mapper.resignEmp(no);
    }

    public List<EmployeeVo> selectEmpByCondition(EmployeeVo vo) {
        return mapper.selectEmpByCondition(vo);
    }

    public String checkAuthYn() {
        return mapper.checkAuthYn();

    }

    public String checkAuthYnForUpdateEmpInfo() {
        return mapper.checkAuthYnForUpdateEmpInfo();
    }
}
