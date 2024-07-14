package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminScheduleDao;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminScheduleService {
    private final AdminScheduleDao dao;

    public List<EmployeeVo> empList(String deptNo) {
        return dao.empList(deptNo);
    }

    @Transactional
    public int insertSchedule(CalendarVo vo) {
        int result = 0;
        //캘린더 작성
        result = dao.insertSchedule(vo);
        if (result == 0) {
            throw new RuntimeException("캘린더 등록실패!");
        }
        //브라우저에서 받은 partnerList
        List<PartnerVo> partnerList = vo.getPartnerList();
        for (PartnerVo partnerVo : partnerList) {
            result = dao.insertPartner(partnerVo);
            if (result == 0) {
                throw new RuntimeException("참여자 등록실패!");
            }
        }
        return result;
    }


    public List<CalendarVo> selectScheduleList(String no) {
        return dao.selectScheduleList(no);
    }

    public List<PartnerVo> selectPartnerList(String no) {
        return  dao.selectPartnerList(no);
    }

    //캘린더수정
    @Transactional
    public int updateCalendar(CalendarVo calendarVo) {
        int result=0;
        //파트너테이블이 존재할수 있으니  삭제해주자(확인하고 삭제하려 했는데 기록이 필요없어서 바로 삭제)
        dao.deleteBeforePartner(calendarVo);
        result=dao.updateCalendar(calendarVo);
        if(result==0){
            throw new RuntimeException("캘린더 수정 실패!");
        }
        //캘린더 수정할때 만약에 공개범위가 참여자(2번)지정이라면
        if(("2").equals(calendarVo.getOpenRangeNo())){
            //브라우저한테 받은 참여자리스트
            List<PartnerVo> partnerList = calendarVo.getPartnerList();
            for (PartnerVo partnerVo : partnerList) {
                result = dao.insertNewPartner(partnerVo);
                if (result == 0) {
                    throw new RuntimeException("참여자 수정 실패!");
                }
            }
        }


        return result;
    }

}
