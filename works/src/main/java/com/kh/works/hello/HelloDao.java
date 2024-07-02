package com.kh.works.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HelloDao {

    private final HelloMapper mapper;

    public int m01(HelloVo vo) {
        return mapper.m01(vo);
    }

    public int m02(String managerNo){
        return mapper.m02(managerNo);
    }
}
