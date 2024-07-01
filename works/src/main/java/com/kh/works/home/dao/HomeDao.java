package com.kh.works.home.dao;

import com.kh.works.attend.vo.AttendVo;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.home.mapper.HomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HomeDao {

    private final HomeMapper mapper;

    public int start(AttendVo vo) {
        return mapper.start(vo);
    }

    public int end(AttendVo vo) {
        return mapper.end(vo);
    }

    public AttendVo getAttendInfo(String empNo) {
        return mapper.getAttendInfo(empNo);
    }


    //*** 수인언니가 작성한 친절한 예시 참고 ***
//    public EmployeeVo selectEmpInfo(String no) {
//        return mapper.selectEmpInfo(no);
//    }
//
//    public int writing(BoardVo boardVo) {
//        return mapper.writing(boardVo);
//    }
}
