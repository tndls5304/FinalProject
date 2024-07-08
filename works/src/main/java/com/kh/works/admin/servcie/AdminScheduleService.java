package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminScheduleDao;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminScheduleService {
    private final AdminScheduleDao dao;

    public List<EmployeeVo> empList(String deptNo) {
        return dao.empList(deptNo);
    }

    public int insertSchedule(CalendarVo vo) {
        return dao.insertSchedule(vo);
    }
}
