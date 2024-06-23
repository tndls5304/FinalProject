package com.kh.works.security;

import com.kh.works.employee.dao.EmpAccountDao;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//로그인 버튼을 누르면 스프링시큐리티가 loadUserByUsername메소드를 실행시킴
//UserDetailsService : 로그인 submit되면 실행됨(사용자정보 조회해오는 인터페이스)
//이때 화면에 쓴 아이디값만을 인자값으로 username에 전달

@Service
@RequiredArgsConstructor
public class EmpUserDetailsService implements UserDetailsService {

private final EmpAccountDao dao;



    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        
        // db 호출
       EmployeeVo employeeVo =dao.empLoginIdMatching(id);


//UserDetails: 아이디비밀번호 비교를 위한 객체
//User.builder()를 하면 UserBuilder()가 생기고 . 앞에는 이 객체가 생략되어 있는거
       UserDetails userDetails =  User.builder()
                .username(employeeVo.getId())
                .password(employeeVo.getPwd())
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }


    //12345를 했을때 비밀번호 어덯게 나오는지 확인하는거
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("12345"));
//        //$2a$10$OAPVtkEugfw2Xwc2QR59s.loFms9sR71rGMP8SfLKkXuhVHPFi5py
//    }
}
