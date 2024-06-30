package com.kh.works.employee.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmpAccountMapper {

    @Select("""
            SELECT NO,EMAIL,NAME,ID,PROFILE,PHONE,HIRE_DATE,ENT_YN,ENT_DATE,LOGIN_FAIL_NUM,LOCK_YN,DEPT_NO,POSITION_NO
            FROM EMPLOYEE WHERE ID=#{id} AND PWD=#{pwd}
            """ )
    EmployeeVo empLoginMatching(EmployeeVo vo);


    @Select("SELECT COUNT(*) AS CNT FROM EMPLOYEE WHERE ID =#{id}")
    int empJoinDuplicateTest(String id);


    @Update("""
            UPDATE EMPLOYEE
            SET ID=#{id},PWD=#{pwd},NAME=#{name},PHONE=#{phone},PROFILE=#{changeProfileName}
            WHERE NO=#{no}
            """)
    int join(EmployeeVo vo);
}
