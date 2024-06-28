package com.kh.works.employee.mapper;

import com.kh.works.security.EmpSessionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpAccountMapper {

    @Select("SELECT ID,PWD,NO,NAME FROM EMPLOYEE WHERE ID=#{id}")
    EmpSessionVo empLoginIdMatching(String id);



}
