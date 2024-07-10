package com.kh.works.admin.mapper;

import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.*;

import java.util.Calendar;
import java.util.List;

@Mapper
public interface AdminScheduleMapper {

    @Select("""
                   SELECT   NO,NAME
                            FROM EMPLOYEE
                            WHERE DEPT_NO=#{deptNo}
            """)
     List<EmployeeVo> empList(String deptNo);

    //캘린더작성
    @Insert("""
            INSERT INTO CALENDAR(
            NO,
            ADMIN_NO,
            TITLE,
            START_DATE,
            END_DATE,
            OPEN_RANGE_NO,
            CONTENT,
            PLACE_NAME,
            LATITUDE,
            LONGITUDE
            )VALUES
            (SEQ_CALENDAR.NEXTVAL,
            #{adminNo},
            #{title},
            #{startDate},
            #{endDate},
            #{openRangeNo},
            COALESCE(#{content}, NULL),
            COALESCE(#{placeName}, NULL),
            COALESCE(#{latitude}, NULL),
           COALESCE(#{longitude}, NULL)
            )
           """)
    int insertSchedule(CalendarVo vo);

    //참여자 지정하기
    @Insert("INSERT INTO CALENDAR_PARTNER(CALENDAR_NO,EMP_NO)VALUES(SEQ_CALENDAR.CURRVAL,#{empNo})")
    int insertPartner(PartnerVo partnerVo);

    //로그인된 관리자에 따라 모든 캘린더목록 다가져오기
    @Select("""
            SELECT
            NO,
            START_DATE,
            END_DATE,
            TITLE,
            INSERT_DATE,
            UPDATE_DATE,
            CONTENT,
            PLACE_NAME,
            LATITUDE,
            LONGITUDE,
            OPEN_RANGE_NO
            FROM CALENDAR
            WHERE ADMIN_NO=#{adminNo} AND DEL_YN='N'
            """)
    List<CalendarVo> selectScheduleList(@Param("adminNo") String no);

    @Select("""
            SELECT E.NO AS empNo,E.NAME AS empName
            FROM CALENDAR_PARTNER C
            JOIN EMPLOYEE E ON C.EMP_NO=E.NO
            WHERE CALENDAR_NO=#{no}
            """)
    List<PartnerVo> selectPartnerList(String no);

    @Update("""
            UPDATE CALENDAR
            SET
                TITLE=#{title},
                START_DATE=#{startDate},
                END_DATE=#{endDate},
                OPEN_RANGE_NO=#{openRangeNo},
                CONTENT=#{content},
                PLACE_NAME=#{placeName},
                LATITUDE=#{latitude},
                LONGITUDE=#{longitude}
            WHERE
                ADMIN_NO=#{adminNo} AND  NO=#{no}
            """)
    int updateCalendar(CalendarVo vo);

    @Update("""
            UPDATE CALENDAR_PARTNER
            SET EMP_NO=#{empNo}
            WHERE CALENDAR_NO=#{calendarNo}
            """)
    int updatePartner(PartnerVo partnerVo);
}
