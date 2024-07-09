package com.kh.works.admin.mapper;

import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
