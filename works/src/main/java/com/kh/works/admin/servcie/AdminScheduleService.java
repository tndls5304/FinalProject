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

    @Transactional
    public int updateCalendar(CalendarVo vo) {
        int result=0;

        //캘린더 수정하기
        result=dao.updateCalendar(vo);
       if(result==0){
           throw new RuntimeException("캘린더 수정 실패!");
       }

       //브라우저한테 받은 참여자리스트
        List<PartnerVo> partnerList = vo.getPartnerList();
        for (PartnerVo partnerVo : partnerList) {
            result = dao.updatePartner(partnerVo);
            if (result == 0) {
                throw new RuntimeException("참여자 수정 실패!");
            }
        }
        return result;

    }
}
