package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminScheduleMapper;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminScheduleDao {
    private final AdminScheduleMapper mapper;

    public List<EmployeeVo> empList(String deptNo) {
        return mapper.empList(deptNo);
    }

    public int insertSchedule(CalendarVo vo) {
        return mapper.insertSchedule(vo);
    }

    public int insertPartner(PartnerVo partnerVo) {
      return mapper.insertPartner(partnerVo);
    }

}
