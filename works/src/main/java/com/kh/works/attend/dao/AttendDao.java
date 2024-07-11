package com.kh.works.attend.dao;

import com.kh.works.attend.mapper.AttendMapper;
import com.kh.works.attend.vo.AttendVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttendDao {

    private final AttendMapper mapper;

    public List<AttendVo> myAttendList(String empNO) {
        return mapper.myAttendList(empNO);
    }

    public List<AttendVo> searchByDate(String dateSearch) {
        return mapper.searchByDate(dateSearch);
    }

    public List<AttendVo> showAllList() {
        return mapper.showAllList();
    }

    //검색하는 기능 - 인사부에서 보는 전체 사원 근태기록 목록에서 검색하기
    public List<AttendVo> searchByNameAndDept(String nameSearch, String deptSearch) {
        return mapper.searchByNameAndDept(nameSearch, deptSearch);
    }

    public List<AttendVo> searchByName(String nameSearch) {
        return mapper.searchByName(nameSearch);
    }

    public List<AttendVo> searchByDepartment(String deptSearch) {
        return mapper.searchByDepartment(deptSearch);
    }

    //jsp에서 value가 5일 때, 전체 사원이 조회되도록 설정하는 메서드
    public List<AttendVo> searchAllDept() {
        return mapper.searchAllDept();
    }
}
