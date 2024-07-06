package com.kh.works.rent.service;

import com.kh.works.rent.dao.RentDao;
import com.kh.works.rent.vo.CarVo;
import com.kh.works.rent.vo.MeetingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentDao dao;

    public int meetingRent(MeetingVo vo) {
        return dao.meetingRent(vo);
    }

    public int carRent(CarVo vo) {
        return dao.carRent(vo);
    }

    public List<MeetingVo> meetingList() {
        return dao.meetingList();
    }

    public List<CarVo> carList() {
        return dao.carList();
    }

    public CarVo detailCar(String no) {
        return dao.detailCar(no);
    }

    public int updateMeeting(MeetingVo vo, String no) {
        return dao.updateMeeting(vo,no);
    }

    public int updateCar(CarVo vo, String no) {
        return dao.updateCar(vo, no);
    }

    public List<MeetingVo> metList() {
        return dao.metList();
    }

    public List<CarVo> listCar() {
        return dao.listCar();
    }

    public int adminFail(String no) {
        return dao.adminFail(no);
    }

    public int adminApprove(String no) {
        return dao.adminApprove(no);
    }

    public int cancle(String no) {
        return dao.cancle(no);
    }

    public List<CarVo> adminList() {
        return dao.adminList();
    }
}
