package com.kh.works.admin.mapper;

import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Calendar;
import java.util.List;

@Mapper
public interface AdminScheduleMapper {

    @Select("""
                   SELECT   NO,NAME
                            FROM EMPLOYEE
                            WHERE DEPT_NO=#{deptNo}
            """)
     List<EmployeeVo> empList(String deptNo);

    
    @Insert("")
    int insertSchedule(CalendarVo vo);
}
