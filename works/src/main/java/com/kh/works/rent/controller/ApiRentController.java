package com.kh.works.rent.controller;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.rent.service.RentService;
import com.kh.works.rent.vo.CarVo;
import com.kh.works.rent.vo.MeetingVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rent")
@RequiredArgsConstructor
public class ApiRentController {

    private final RentService service;

    //회의실 예약작성하기
    @PostMapping("meeting")
    public String meetingRent(MeetingVo vo , HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();
        vo.setEmpNo(empNo);
        System.out.println(vo + "vo 뭐뭐가 안들어온거야~~~~~~~~~~~~~" );
        int result = service.meetingRent(vo);

        return "rent/home";
    }

    //차량 예약작성하기
    @PostMapping("car")
    public int carRent(CarVo vo , HttpSession session){
        int result = service.carRent(vo);
        EmployeeVo loginEmpVo = (EmployeeVo)session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();
        vo.setEmpNo(empNo);
        return result;
    }

    //회의실 예약 리스트 //이거 나중에 관리자도 가져가야함
    @GetMapping("meeting")
    public List<MeetingVo> meetingList(){
        List<MeetingVo> voList = service.meetingList();
        return voList;
    }

    //차량 예약 리스트 //이것도
    @GetMapping("car")
    public List<CarVo> carList(){
        List<CarVo> voList = service.carList();
        return voList;
    }

    @GetMapping("detail/meeting")
    //회의실 상세조회
    public MeetingVo detailMeeting(@RequestParam("no") String no){
        MeetingVo vo = service.detailMeeting(no);
        return vo;
    }

    @GetMapping("detail/car")
    //차량 상세조회
    public CarVo detailCar(@RequestParam("no") String no){
        CarVo vo = service.detailCar(no);
        return vo;
    }

    //회의실 예약 변경하기
    @PutMapping("meeting")
    public int updateMeeting(MeetingVo vo , @RequestParam("no") String no){
        int result = service.updateMeeting(vo , no);
        return result;
    }

    @PutMapping("car")
    public int updateCar(CarVo vo , @RequestParam("no") String no){
        int result = service.updateCar(vo , no);
        return result;
    }

    //회의실 옵션 꺼내오기
    @GetMapping("option/meeting")
    public List<MeetingVo> metList(){

        List<MeetingVo> voList = service.metList();
        System.out.println(voList + "voList~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        return voList;
    }

    //차량 옵션 꺼내기
    @GetMapping("option/car")
    public List<CarVo> listCar(){

        List<CarVo> voList = service.listCar();
        System.out.println("voList~~~~~~~~~~~~~~~~~~~~~" + voList);

        return voList;
    }


}
