package com.kh.works.employee.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeVo {

    private String no;
    private String email;
    private String name;
    private String  id;
    private String pwd;
    private String pwdCheck;
    private MultipartFile profile;
    private String changeProfileName;
    private String phone;
    private String  hireDate;
    private String entYn;
    private String  entDate;
    private String loginFailNum;
    private String  lockYn;
    private String deptNo;
    private String positionNo;
}
