package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminCalendarDao;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCalendarService {
    private static final Logger log = LoggerFactory.getLogger(AdminCalendarService.class);
    private final AdminCalendarDao dao;

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
        return dao.selectPartnerList(no);
    }

    //캘린더수정
    @Transactional
    public int updateCalendar(CalendarVo calendarVo) {
        int result = 0;
        //수정 하기 전에 파트너테이블이 존재할수 있으니 삭제(확인하고 삭제하려 했는데 기록이 필요없어서 바로 삭제)
        dao.deleteBeforePartner(calendarVo);
        result = dao.updateCalendar(calendarVo);
        if (result == 0) {
            throw new RuntimeException("캘린더 수정 실패!");
        }
        //캘린더 수정할때 만약에 공개범위가 참여자(2번)지정이라면
        if ("2".equals(calendarVo.getOpenRangeNo())) {
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

    @Transactional
    public int deleteCalendar(String adminNo, String calendarNo) {
        //캘린더에 참여자 지정시에는 참여자들을 삭제
        dao.deletePartner(calendarNo);
        //캘린더삭제
        int result = dao.deleteCalendar(adminNo, calendarNo);
        if (result == 0) {
            //  @Transactional 있기에 result가 0이면 dao.deletePartner(calendarNo); 이 부분을 롤백처리함
            throw new RuntimeException("캘린더 삭제 실패");
        }
        return result;
    }
}
