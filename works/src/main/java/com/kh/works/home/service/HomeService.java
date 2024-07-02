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


    //*** 수인언니가 작성한 친절한 예시 참고 ***
//    public EmployeeVo selectEmpInfo(String no) {
//        return dao.selectEmpInfo(no);
//    }
//
//    public int writing(BoardVo boardVo) {
//        return dao.writing(boardVo);
//    }
}
