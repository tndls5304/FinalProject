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

    public List<AttendVo> searchByNameAndDepartment(String nameSearch, String deptSearch) {
        return mapper.searchByNameAndDepartment(nameSearch, deptSearch);
    }

    public List<AttendVo> searchByName(String nameSearch) {
        return mapper.searchByName(nameSearch);
    }

    public List<AttendVo> searchByDepartment(String deptSearch) {
        return mapper.searchByDepartment(deptSearch);
    }
}
