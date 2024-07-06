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

    @Select("SELECT R.MET_RSV_NO, " +
            "       M.NAME, " +
            "       M.FLOOR, " +
            "       E.NAME AS empName, " +
            "       R.EMP_NO, " +
            "       TO_CHAR(R.RSV_DATE, 'YYYY-MM-DD') AS RSV_DATE, " +
            "       TO_CHAR(R.START_DATE, 'HH24:MI') AS START_DATE, " +
            "       TO_CHAR(R.END_DATE, 'HH24:MI') AS END_DATE " +
            "FROM RESERV_MEETING R " +
            "JOIN MEETING_ROOM M ON R.MET_ROOM_NO = M.MET_ROOM_NO " +
            "JOIN EMPLOYEE E ON R.EMP_NO = E.NO")
    List<MeetingVo> meetingList();

    @Select("SELECT R.VHCL_RSV_NO\n" +
            "    ,TO_CHAR(R.LOAN_DATE, 'YYYY-MM-DD') AS LOAN_DATE\n" +
            "    ,TO_CHAR(R.RETURN_DATE, 'YYYY-MM-DD') AS RETURN_DATE\n" +
            "    ,V.VHCL_NUMBER\n" +
            "    ,T.NAME \n" +
            "    ,A.APPROVAL_STATUS\n" +
            "    ,E.NAME as empName\n" +
            "    ,R.REASON\n" +
            "FROM RESERV_VEHICLE R\n" +
            "JOIN VEHICLE V \n" +
            "ON R.VHCL_NO = V.VHCL_NO\n" +
            "JOIN VEHICLE_TYPE T\n" +
            "ON V.VHCL_TYPE_NO = T.VHCL_TYPE_NO\n" +
            "JOIN APPROVAL_STATUS A\n" +
            "ON R.APPROVAL_NO = A.APPROVAL_NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON R.EMP_NO = E.NO\n" +
            "WHERE CANCEL_RESERVATION_YN= 'N'")
    List<CarVo> carList();

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

    @Update("<script>\n" +
            "UPDATE RESERV_MEETING \n" +
            "<set>\n" +
            "\t<if test=\"vo.metRoomNo != null and vo.metRoomNo != ''\">\n" +
            "\t\tMET_ROOM_NO = #{vo.metRoomNo},\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.rsvDate != null\">\n" +
            "\t\tRSV_DATE = TO_DATE(#{vo.rsvDate}, 'YYYY-MM-DD'),\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.startDate != null\">\n" +
            "\t\tSTART_DATE = TO_TIMESTAMP(#{vo.startDate}, 'HH24:MI'),\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.endDate != null\">\n" +
            "\t\tEND_DATE = TO_TIMESTAMP(#{vo.endDate}, 'HH24:MI')\n" +
            "\t</if>\n" +
            "</set>\n" +
            "WHERE MET_RSV_NO = #{no}\n" +
            "</script>")
    int updateMeeting(@Param("vo") MeetingVo vo, @Param("no") String no);


    @Update("<script>\n" +
            "UPDATE RESERV_VEHICLE \n" +
            "<set>\n" +
            "\t<if test=\"vo.vhclNo != null\">\n" +
            "\tVHCL_NO = #{vo.vhclNo},\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.loanDate != null\">\n" +
            "\tLOAN_DATE = TO_DATE(#{vo.loanDate}, 'YYYY-MM-DD'),\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.returnDate != null\">\n" +
            "\tRETURN_DATE = TO_DATE(#{vo.returnDate}, 'YYYY-MM-DD'),\n" +
            "\t</if>\n" +
            "\t<if test=\"vo.reason != null and vo.reason != ''\">\n" +
            "\tREASON = #{vo.reason}\n" +
            "\t</if>\n" +
            "</set>\n" +
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

    @Update("UPDATE RESERV_VEHICLE SET APPROVAL_NO = 3 WHERE VHCL_RSV_NO = #{no}")
    int adminFail(@Param("no") String no);

    @Update("UPDATE RESERV_VEHICLE SET APPROVAL_NO = 2 WHERE VHCL_RSV_NO = #{no}")
    int adminApprove(@Param("no")String no);

    @Update("UPDATE RESERV_VEHICLE SET CANCEL_RESERVATION_YN= 'Y' WHERE VHCL_RSV_NO = #{no}")
    int cancle(@Param("no") String no);

    @Select("SELECT R.VHCL_RSV_NO\n" +
            "    ,TO_CHAR(R.LOAN_DATE, 'YYYY-MM-DD') AS LOAN_DATE\n" +
            "    ,TO_CHAR(R.RETURN_DATE, 'YYYY-MM-DD') AS RETURN_DATE\n" +
            "    ,V.VHCL_NUMBER\n" +
            "    ,T.NAME \n" +
            "    ,A.APPROVAL_STATUS\n" +
            "    ,E.NAME as empName\n" +
            "    ,R.REASON\n" +
            "    ,R.CANCEL_RESERVATION_YN\n" +
            "FROM RESERV_VEHICLE R\n" +
            "JOIN VEHICLE V \n" +
            "ON R.VHCL_NO = V.VHCL_NO\n" +
            "JOIN VEHICLE_TYPE T\n" +
            "ON V.VHCL_TYPE_NO = T.VHCL_TYPE_NO\n" +
            "JOIN APPROVAL_STATUS A\n" +
            "ON R.APPROVAL_NO = A.APPROVAL_NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON R.EMP_NO = E.NO")
    List<CarVo> adminList();

}
