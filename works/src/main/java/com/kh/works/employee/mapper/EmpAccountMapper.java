package com.kh.works.employee.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpAccountMapper {

    @Select("SELECT ID,PWD FROM EMPLOYEE WHERE ID=#{id}")
     EmployeeVo empLoginIdMatching(String id);
}
