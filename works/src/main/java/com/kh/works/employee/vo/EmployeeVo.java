package com.kh.works.employee.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeVo {

    private String no;
    private String email;
    private String name;
    private String id;
    private String pwd;
    private String pwdCheck;
    private String profile;
    private MultipartFile profileInfo;
    private String phone;
    private String hireDate;
    private String retireYn;
    private String retireDate;
    private String loginFailNum;
    private String lockYn;
    private String deptNo;
    private String positionNo;
    private String positionName;
    private String deptName;
    private String joinKey;
}
