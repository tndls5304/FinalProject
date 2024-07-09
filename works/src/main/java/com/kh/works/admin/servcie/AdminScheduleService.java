package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminScheduleDao;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
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
        //스케줄 작성
        int resultForSchedule=  dao.insertSchedule(vo);

        //브라우저에서 받은 partnerList
        List<PartnerVo>partnerList=vo.getPartnerList();

        int resultForPartner=1;
//여기서 객체를 넘겨주는지 no만 넘겨주는지..?
       for(PartnerVo partnerVo:partnerList){
          resultForPartner=dao.insertPartner(partnerVo);
       }

       if(resultForSchedule * resultForPartner !=1){
           throw  new RuntimeException();
       }

       return  resultForSchedule*resultForPartner;
    }
}
