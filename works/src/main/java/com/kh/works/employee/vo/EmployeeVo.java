package com.kh.works.employee.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class EmployeeVo {
   private String no;
   private String email;
   private String name;
   private String id;
   private String pwd;
   private String profile;
   private String phone;
   private String hireDate;
   private String entYn;
   private String entDate;
   private String loginFailNum;
   private String lockYn;
   private String deptNo;
   private String positionNo;
}
