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


    //검색하는 기능 - 인사부에서 보는 전체 사원 근태기록 목록에서 검색하기
    public List<AttendVo> search(String deptSearch, String nameSearch) {

        //비즈니스 로직 처리
        //이름 || 부서 || 이름&&부서 -> 검색할 수 있어야 하기 위해.

        if (nameSearch != null && !nameSearch.isEmpty() && deptSearch != null && !deptSearch.isEmpty()) {
                //이름 && 부서 로 검색
                return dao.searchByNameAndDept(nameSearch, deptSearch);

        } else if (nameSearch != null && !nameSearch.isEmpty()) {
            //이름 으로 검색
            return dao.searchByName(nameSearch);

        } else if (deptSearch != null && !deptSearch.isEmpty()) {
            //뷰에서 전체선택을 클릭하면 jsp에서 value가 5인 애를 데리고 온다.
            //전체 사원 검색이므로 '부서'로만 검색하도록 한다.
            if("5".equals(deptSearch)){
                return dao.searchAllDept();

            }else{
                //부서 로 검색 (5가 아닌 DB에 있는 value값을 데리고 올 때)
                return dao.searchByDepartment(deptSearch);
            }

        } else {
            //해당X
            return dao.searchByNameAndDept("", "");
        }
    }
}
