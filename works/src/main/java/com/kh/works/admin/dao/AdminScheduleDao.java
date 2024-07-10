package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminScheduleMapper;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public List<CalendarVo> selectScheduleList(String no) {
        return mapper.selectScheduleList(no);
    }

    public List<PartnerVo> selectPartnerList(String no) {
    return mapper.selectPartnerList(no);
    }

    public int updateCalendar(CalendarVo vo) {
        return mapper.updateCalendar(vo);
    }

    public int updatePartner(PartnerVo partnerVo) {
        return mapper.updatePartner(partnerVo);
    }
}
