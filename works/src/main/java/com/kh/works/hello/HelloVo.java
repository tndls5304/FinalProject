package com.kh.works.hello;

import lombok.Data;

import java.util.List;

@Data
public class HelloVo {

    private String no;
    private String title;
    private String content;
    private List<String> managerNoList;

}
