package com.kh.works.alarm.vo;

import lombok.Data;

//알림 Socket 기능을 위한 Vo입니다.
@Data
public class AlarmVo {

    private String alarmNo;
    private String empNo;
    private String message;
    private String isRead;
    private String createDate;

}
