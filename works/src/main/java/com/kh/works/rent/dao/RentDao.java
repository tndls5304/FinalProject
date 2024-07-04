package com.kh.works.rent.dao;

import com.kh.works.rent.mapper.RentMapper;
import com.kh.works.rent.vo.CarVo;
import com.kh.works.rent.vo.MeetingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentDao {

    private final RentMapper mapper;


    public int meetingRent(MeetingVo vo) {
        return mapper.meetingRent(vo);
    }

    public int carRent(CarVo vo) {
        return mapper.carRent(vo);
    }

    public List<MeetingVo> meetingList() {
        return mapper.meetingList();
    }

    public List<CarVo> carList() {
        return mapper.carList();
    }

    public MeetingVo detailMeeting(String no) {
        return mapper.detailMeeting(no);
    }

    public CarVo detailCar(String no) {
        return mapper.detailCar(no);
    }

    public int updateMeeting(MeetingVo vo, String no) {
        return mapper.updateMeeting(vo , no);
    }

    public int updateCar(CarVo vo, String no) {
        return mapper.updateCar(vo, no);
    }

    public List<MeetingVo> metList() {
        return mapper.metList();
    }

    public List<CarVo> listCar() {
        return mapper.listCar();
    }
}
