package com.kh.works.home.service;

import com.kh.works.attend.vo.AttendVo;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.home.dao.HomeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {

    private final HomeDao dao;

    public int start(AttendVo vo) {
        return dao.start(vo);
    }

    public int end(AttendVo vo) {
        return dao.end(vo);
    }

    public AttendVo getAttendInfo(String empNo) {
        return dao.getAttendInfo(empNo);
    }

    //출근버튼을 찍었는데 퇴근버튼을 찍지 않았으면 출근버튼 다시 찍지 못하도록 막는 메서드
    public boolean alreadyStart(String empNo) {
        return dao.alreadyStart(empNo) > 0;
    }

    public boolean alreadyAttend(String empNo) {
        return dao.alreadyAttend(empNo) > 0;
    }





    //*** 수인언니가 작성한 친절한 예시 참고 ***
//    public EmployeeVo selectEmpInfo(String no) {
//        return dao.selectEmpInfo(no);
//    }
//
//    public int writing(BoardVo boardVo) {
//        return dao.writing(boardVo);
//    }
}
