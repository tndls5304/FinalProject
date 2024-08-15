package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminCalendarMapper;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminCalendarDao {
    private final AdminCalendarMapper mapper;

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

    public int insertNewPartner(PartnerVo partnerVo) {
        return mapper.insertNewPartner(partnerVo);
    }

    public int deleteBeforePartner(CalendarVo vo) {
        return mapper.deleteBeforePartner(vo);
    }

    public int deleteCalendar(String adminNo, String calendarNo) {
        return mapper.deleteCalendar(adminNo,calendarNo);
    }

    public int deletePartner(String calendarNo) {
        return mapper.deletePartner(calendarNo);
    }

    public String checkAuthYnForInsertCalendar() {
        return mapper.checkAuthYnForInsertCalendar();
    }

    public String checkAuthYnForUpdateCalendar() {
        return mapper.checkAuthYnForUpdateCalendar();
    }

    public String checkAuthYnForDeleteCalendar() {
        return mapper.checkAuthYnForDeleteCalendar();
    }
}
