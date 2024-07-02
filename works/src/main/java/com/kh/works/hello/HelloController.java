package com.kh.works.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService service;
    
    @PostMapping
    public String m01(HelloVo vo){
        int result = service.m01(vo);
        return "result : " + result;
    }
    
}
