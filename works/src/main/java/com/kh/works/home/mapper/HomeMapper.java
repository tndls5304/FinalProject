package com.kh.works.home.mapper;

import com.kh.works.employee.vo.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeMapper {

    @Select("SELECT E.EMAIL,E.NAME,E.ID,E.PROFILE,E.PHONE,D.NAME,P.NAME FROM EMPLOYEE E JOIN DEPARTMENT D ON E.DEPT_NO=D.NO " +
            "JOIN POSITION P ON E.POSITION_NO= P.NO WHERE E.NO=#{no}")
    EmployeeVo selectEmpInfo(String no);
}
