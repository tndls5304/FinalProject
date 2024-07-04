package com.kh.works.rent.vo;

import lombok.Data;

@Data
public class CarVo {

    private String vhclNo;
    private String vhclTypeNo;
    private String vhclNumber;
    private String name;
    private String vhclRsvNo;
    private String empNo;
    private String approvalNo;
    private String cancelReservationYn;
    private String loanDate;
    private String returnDate;
    private String reason;

}
