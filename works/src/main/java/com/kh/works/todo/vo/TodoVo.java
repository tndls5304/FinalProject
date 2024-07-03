package com.kh.works.todo.vo;

import java.util.List;
import lombok.Data;

@Data
public class TodoVo {
    private int todoNo;
    private String todoEmpNo;
    private String title;
    private String content;
    private String delYn;
    private String completedYn;
    private String createDate;
    private String startDate;
    private String endDate;
    private String todoEmpName;
    private String todoManagerNo;




    private int todoNoMan;

    //한 투두에 담당자가 여러명이라 조회해온걸 List에 담아준다
    private List<String> todoManagerList;

}
