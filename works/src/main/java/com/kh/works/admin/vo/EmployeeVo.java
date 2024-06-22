package com.kh.works.admin.vo;

import jakarta.mail.Multipart;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeVo {
    private String no;
    private String email;
    private String name;
    private String id;
    private String pwd;
    private MultipartFile profile;
    private String phone;
    private String hire_date;
    private String entYn;
    private String ent_date;
    private String loginFailNum;
    private String lockYn;
    private String deptNo;
    private String positionNo;
}
