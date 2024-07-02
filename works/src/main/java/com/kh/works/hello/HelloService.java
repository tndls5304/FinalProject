package com.kh.works.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HelloService {

    private final HelloDao dao;


    public int m01(HelloVo vo) {
        int result01 = dao.m01(vo);

        List<String> managerNoList = vo.getManagerNoList();
        int result02 = 1;
        for (String mno : managerNoList) {
            result02 *= dao.m02( mno );
        }

        if(result01 * result02 != 1){
            throw new RuntimeException();
        }

        return result01 * result02;
    }
}
