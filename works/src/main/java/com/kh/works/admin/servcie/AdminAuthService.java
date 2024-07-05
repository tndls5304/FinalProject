package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminAuthDao;
import com.kh.works.admin.vo.SubAdminMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AdminAuthService {

    private final AdminAuthDao dao;

    public List<SubAdminMenuVo> getMenuVoList() {
        return  dao.getMenuVoList();
    }


    @Transactional
    public int updateAuth(List<SubAdminMenuVo> list) {
        int result=0;

        for(SubAdminMenuVo vo:list){
            result= dao.updateAuth(vo);
        }
        return result;
    }

    public String checkAuthYn() {
        return  dao.checkAuthYn();
    }
}
