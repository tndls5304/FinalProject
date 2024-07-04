package com.kh.works.rent.mapper;

import com.kh.works.rent.vo.CarVo;
import com.kh.works.rent.vo.MeetingVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RentMapper {

    @Insert("INSERT INTO RESERV_MEETING(MET_RSV_NO, MET_ROOM_NO, EMP_NO, RSV_DATE, START_DATE, END_DATE) \n" +
            "VALUES (SEQ_RESERV_MEETING.NEXTVAL, #{metRoomNo}, #{empNo},  TO_DATE(#{rsvDate}, 'YYYY-MM-DD'), TO_TIMESTAMP(#{startDate}, 'YYYY-MM-DD HH24:MI'), TO_TIMESTAMP(#{endDate}, 'YYYY-MM-DD HH24:MI'))\n")
    int meetingRent(MeetingVo vo);

    @Insert("INSERT INTO RESERV_VEHICLE (\n" +
            "    VHCL_RSV_NO\n" +
            "    ,VHCL_NO\n" +
            "    ,EMP_NO\n" +
            "    ,LOAN_DATE\n" +
            "    ,RETURN_DATE\n" +
            "    ,REASON\n" +
            ")VALUES \n" +
            "(\n" +
            "    SEQ_RESERV_VEHICLE.NEXTVAL\n" +
            "    ,#{vhclNo}\n" +
            "    ,#{empNo}\n" +
            "    ,TO_DATE(#{loanDate}, 'YYYY-MM-DD')" +
            "    ,TO_DATE(#{returnDate}, 'YYYY-MM-DD')" +
            "    ,#{reason}\n" +
            ")")
    int carRent(CarVo vo);

    @Select("")
    List<MeetingVo> meetingList();

    @Select("")
    List<CarVo> carList();

    @Select("")
    MeetingVo detailMeeting(String no);

    @Select("")
    CarVo detailCar(String no);

    @Update("")
    int updateMeeting(MeetingVo vo, String no);

    @Update("")
    int updateCar(CarVo vo, String no);

    @Select("SELECT * FROM MEETING_ROOM ")
    List<MeetingVo> metList();

    @Select("SELECT N.VHCL_NO\n" +
            "    ,N.VHCL_NUMBER\n" +
            "    ,T.NAME\n" +
            "FROM VEHICLE N\n" +
            "JOIN VEHICLE_TYPE T\n" +
            "ON N.VHCL_TYPE_NO = T.VHCL_TYPE_NO")
    List<CarVo> listCar();

}
