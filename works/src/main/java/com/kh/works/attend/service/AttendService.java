package com.kh.works.attend.service;

import com.kh.works.attend.dao.AttendDao;
import com.kh.works.attend.vo.AttendVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendDao dao;


    public List<AttendVo> myAttendList(String empNo) {
        return dao.myAttendList(empNo);
    }

    public List<AttendVo> searchByDate(String dateSearch) {
        return dao.searchByDate(dateSearch);
    }

    public List<AttendVo> showAllList() {
        return dao.showAllList();
    }

    public List<AttendVo> search(String deptSearch, String nameSearch) {
        if (nameSearch != null && !nameSearch.isEmpty() && deptSearch != null && !deptSearch.isEmpty()) {
            return dao.searchByNameAndDepartment(nameSearch, deptSearch);
        } else if (nameSearch != null && !nameSearch.isEmpty()) {
            return dao.searchByName(nameSearch);
        } else if (deptSearch != null && !deptSearch.isEmpty()) {
            return dao.searchByDepartment(deptSearch);
        } else {
            return dao.searchByNameAndDepartment("", "");
        }
    }
}
