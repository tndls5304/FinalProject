package com.kh.works.employee.service;

import com.kh.works.employee.dao.EmpAccountDao;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpAccountService {

    private final EmpAccountDao dao;

    public EmployeeVo empLoginMatching(EmployeeVo vo) {

        return dao.empLoginMatching(vo);
    }

    public int empJoinDuplicateTest(String id) {

       return dao.empJoinDuplicateTest(id);
    }


    public int join(EmployeeVo vo) throws Exception {
        if (vo.getId().length() > 30) {
            throw new Exception("아이디가 너무 깁니다 30글자이하로 적어주세요");
        }
        if (vo.getId().length() < 4) {
            throw new Exception("아이디가 너무 짧습니다 4글자 이상 적어주세요");
        }
        if (vo.getPwd().length() > 100) {
            throw new Exception("비밀번호가 너무 깁니다");
        }
        if (vo.getPwd().length() < 4) {
            throw new Exception("비밀번호가 너무 짧습니다");
        }
        if(!vo.getPwd().equals(vo.getPwdCheck())){
            throw new Exception("비밀번호란과 비밀번호 확인란이 다릅니다");
        }
        if (vo.getName().length() > 30) {
            throw new Exception("이름이 너무 깁니다 ");
        }
        if (vo.getPhone().length() != 11) {
            throw new Exception("휴대폰번호는 11글자로 적어주세요 ");
        }
        if (vo.getProfile() == null) {
            throw new Exception("프로필사진이 없습니다 등록해주세요");
        }
      return dao.join(vo);
    }
}
