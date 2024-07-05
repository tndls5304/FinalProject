package com.kh.works.employee.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpAccountMapper {

    @Select("""
            SELECT E.NO,E.EMAIL,E.NAME,E.PWD,E.ID,E.PROFILE,E.PHONE,E.HIRE_DATE,E.RETIRE_YN,E.RETIRE_DATE,E.LOGIN_FAIL_NUM,E.LOCK_YN,E.DEPT_NO,E.POSITION_NO,D.NAME AS DEPT_NAME,P.NAME AS POSITION_NAME
                        FROM EMPLOYEE E
                        JOIN DEPARTMENT D ON E.DEPT_NO =D.NO
                        JOIN POSITION P ON E.POSITION_NO=P.NO
                        WHERE ID=#{id}
            """ )
    EmployeeVo empLoginIdMatching(EmployeeVo vo);


    @Select("SELECT COUNT(*) AS CNT FROM EMPLOYEE WHERE ID =#{id}")
    int empJoinDuplicateTest(String id);


    @Update("""
            UPDATE EMPLOYEE
            SET ID=#{id},PWD=#{pwd},NAME=#{name},PHONE=#{phone},PROFILE=#{profile}
            WHERE NO=#{no}
            """)
    int join(EmployeeVo vo);

    @Update("""
                 UPDATE EMPLOYEE
                        SET LOGIN_FAIL_NUM=LOGIN_FAIL_NUM+1
                        WHERE NO=#{loginFailEmpNo}
            """)

    int plusLoginFailNum(String loginFailEmpNo);


    //계정잠금처리하기
    @Insert("""
            UPDATE EMPLOYEE
            SET LOCK_YN='N'
            WHERE NO=#{loginFailEmpNo}
            """)
    int lockAccount(String loginFailEmpNo);


    //아이디 찾기

    @Select("""
            SELECT ID
            FROM EMPLOYEE
            WHERE NAME=#{name} AND PHONE=#{phone}
            """)
    String findId(EmployeeVo vo);

    @Select("""
            SELECT EMAIL,NO
            FROM EMPLOYEE
            WHERE NAME=#{name} AND ID =#{id}
            """)
    EmployeeVo selectMailToFindPwd(EmployeeVo vo);


    @Update("""
            UPDATE EMPLOYEE
            SET PWD=#{pwd}
            WHERE NO=#{no}
            """)
    @Options(useGeneratedKeys = true, keyProperty = "email", keyColumn = "EMAIL")
    int updatePwd(EmployeeVo vo);
}
