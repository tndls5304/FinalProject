package com.kh.works.home.controller;

import com.kh.works.board.service.BoardService;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.security.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService service;


    //그냥 홈화면 보여주는곳
    @GetMapping("home")
    public String getHomePage (){
        return "home/home";
    }




    //세션안의 인증객체에서 no를 받아와서 -> 홈화면에 보내주기
    @GetMapping("emp/info")
    @ResponseBody

    public EmployeeVo selectEmpInfo( @AuthenticationPrincipal EmpSessionVo loginEmployeeVo){

        String no=loginEmployeeVo.getNo();       // loginEmployeeVo.getNo()를 호출하여 직원 번호(no)를 가져옴
        return  service.selectEmpInfo(no);      //직원번호에 해당하는 이름,아이디를 가져옴
    }



    @PostMapping("writing")

    public String writing(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo , BoardVo boardVo){

        String no=loginEmployeeVo.getNo();
        boardVo.setEmpNo(no);
        int result=service.writing(boardVo);
        if (result != 1){
            return "common/error";
        }
        return "redirect:home";

    }
    }












   /*
         @AuthenticationPrincipal EmpSessionVo loginEmployeeVo)뜻?? 현재 로그인한 사용자의 세션 정보를 loginEmployeeVo라는 객체에 담아주는 역할을함
         loginEmployeeVo는 현재 로그인된 사용자의 세션 정보를 담고 있는 객체 (여기에 로그인한 직원 번호가 담겨져 있음)



        session 에서 인증한 회원 정보를 가져오려면 아래 처럼 작성해야함

        로그인시 원래는 아이디 , 비밀번호와 일치하는 회원의 정보를 다 조회 해와서
        loginMemberVo에 이 정보들을 다 넣었음
         그리고 세션에 session.setAttribute("loginMemberVo",loginMemberVo); 이렇게 넣었고
         로그인한 멤버의 번호가 필요할때는
        MemberVo loginMemberVo= (MemberVo) session.getAttribute("loginMemberVo");
        String no =loginMemberVo.getNo();
        이렇게 회원의 넘버를 들고와서 디비에서 조회 했음

        그런데 이제는  @AuthenticationPrincipal 어노테이션을 써서   ( @AuthenticationPrincipal MemberVo loginMemberVo ) 이렇게 해주면
         MemberVo loginMemberVo= (MemberVo) session.getAttribute("loginMemberVo"); 이작업을 대신 해준다


        로그인한 회원의 번호를 얻어와서
         String no= loginMemberVo.getNO(); 필요한 번호를 꺼내면 된다.


         */





