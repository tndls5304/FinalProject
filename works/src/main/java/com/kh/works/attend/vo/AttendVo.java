package com.kh.works.attend.vo;

import lombok.Data;

@Data
public class AttendVo {

    private String attendNo;
    private String empNo;
    private String startTime;
    private String endTime;
    private String delYn;

    //나의 근태 리스트에서 주차별로 나타내기 위해서 weekNum 변수를 만들어주었다.
    private String weekNum;

}
