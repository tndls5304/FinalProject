package com.kh.works.messenger.vo;

import lombok.Data;

@Data
public class MessengerVo {

    private String no;
    private String senderEmpNo;
    private String receiverEmpNo;
    private String messenboxTypeNo;
    private String title;
    private String content;
    private String sendDate;
    private String readYn;
    private String isSave;
    private String updateDate;
    private String delYn;

    //EMPLOYEE 테이블에서 name을 가지고 오기 위해 생성
    private String name;
}