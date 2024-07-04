package com.kh.works.attend.vo;

import lombok.Data;

@Data
public class AttendVo {

    private String attendNo;
    private String empNo;
    private String startTime;
    private String endTime;
    private String delYn;

    //출퇴근 주차 계산하는 쿼리문에서 주차별로 나타내기 위해 작성한 변수.
    private int weekNum;
    //출퇴근 주차 계산하는 쿼리문에서 (해당 출퇴근기록이) 몇 월인지 알기 위해 작성한 변수.
    private String monthNum;

}
