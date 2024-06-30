package com.kh.works.employee.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpAccountMapper {


    @Select("SELECT \n" +
            "NO,EMAIL,NAME,ID,PROFILE,PHONE,HIRE_DATE,ENT_YN,ENT_DATE,LOGIN_FAIL_NUM,LOCK_YN,DEPT_NO,POSITION_NO\n" +
            "FROM EMPLOYEE WHERE ID=#{id} AND PWD=#{pwd}")
    EmployeeVo empLoginMatching(EmployeeVo vo);
}
