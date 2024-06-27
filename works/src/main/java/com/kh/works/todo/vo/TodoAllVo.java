package com.kh.works.todo.vo;


import lombok.Data;

@Data
public class TodoAllVo {
    private String todoNo1;
    private String todoEmpNo;
    private String title;
    private String content;
    private String delYn;
    private String completedYn;
    private String createDate;
    private String startDate;
    private String endDate;


    private String todoNo2;
    private String todoManagerNo;


}
