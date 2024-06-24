package com.kh.works.employee.vo;
// ---------------------------------------세션에 들어갈 정보
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString
@Data
public class EmpSessionVo implements UserDetails {

   private String no;
   private String id;
   private String pwd;
   private String entYn;
   private String loginFailNum;
   private String lockYn;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return AuthorityUtils.createAuthorityList("ROLE_EMP");
   }

   @Override
   public String getPassword() {
      return pwd;
   }

   @Override
   public String getUsername() {
      return id;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
