package com.kh.works.rent.mapper;

import com.kh.works.rent.vo.CarVo;
import com.kh.works.rent.vo.MeetingVo;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT R.VHCL_RSV_NO\n" +
            "    ,V.VHCL_NUMBER\n" +
            "    ,T.NAME \n" +
            "    ,A.APPROVAL_STATUS\n" +
            "    ,E.NAME as empName\n" +
            "FROM RESERV_VEHICLE R\n" +
            "JOIN VEHICLE V \n" +
            "ON R.VHCL_NO = V.VHCL_NO\n" +
            "JOIN VEHICLE_TYPE T\n" +
            "ON V.VHCL_TYPE_NO = T.VHCL_TYPE_NO\n" +
            "JOIN APPROVAL_STATUS A\n" +
            "ON R.APPROVAL_NO = A.APPROVAL_NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON R.EMP_NO = E.NO")
    List<CarVo> carList();

    @Select("")
    MeetingVo detailMeeting(String no);

    @Select("SELECT R.VHCL_RSV_NO\n" +
            "    ,TO_CHAR(R.LOAN_DATE, 'YYYY-MM-DD') AS LOAN_DATE\n" +
            "    ,TO_CHAR(R.RETURN_DATE, 'YYYY-MM-DD') AS RETURN_DATE\n" +
            "    ,R.REASON\n" +
            "    ,V.VHCL_NUMBER\n" +
            "    ,T.NAME\n" +
            "    ,A.APPROVAL_STATUS\n" +
            "    ,E.NAME AS empName\n" +
            "    ,R.EMP_NO\n" +
            "FROM RESERV_VEHICLE R\n" +
            "JOIN VEHICLE V \n" +
            "ON R.VHCL_NO = V.VHCL_NO\n" +
            "JOIN VEHICLE_TYPE T\n" +
            "ON V.VHCL_TYPE_NO = T.VHCL_TYPE_NO\n" +
            "JOIN APPROVAL_STATUS A\n" +
            "ON R.APPROVAL_NO = A.APPROVAL_NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON R.EMP_NO = E.NO\n" +
            "WHERE VHCL_RSV_NO = #{no}")
    CarVo detailCar(String no);

    @Update("")
    int updateMeeting(MeetingVo vo, String no);

    @Update("<script>\n" +
            "UPDATE RESERV_VEHICLE SET \n" +
            "\t<if test=\"vo.vhclNo != null\">\n" +
            "\tVHCL_NO = #{vo.vhclNo},\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.loanDate != null\">\n" +
            "\tLOAN_DATE = #{vo.loanDate},\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.returnDate != null\">\n" +
            "\tRETURN_DATE = #{vo.returnDate},\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.reason != null\">\n" +
            "\tREASON = #{vo.reason}\n" +  // 마지막 항목 뒤의 쉼표 제거
            "\t</if>\n" +
            "WHERE VHCL_RSV_NO = #{no}\n" +
            "</script>")
    int updateCar(@Param("vo") CarVo vo, @Param("no") String no);



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
