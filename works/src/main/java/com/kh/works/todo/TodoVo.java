package com.kh.works.todo;


import lombok.Data;

@Data
public class TodoVo {

    private String todoNo;
    private String todoEmpNo;
    private String title;
    private String content;
    private String delYn;
    private String completedYn;
    private String createDate;
    private String startDate;
    private String endDate;

    private String todoManagerNo;

}
