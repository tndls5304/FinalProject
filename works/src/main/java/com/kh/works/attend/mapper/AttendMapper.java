package com.kh.works.attend.mapper;

import com.kh.works.attend.vo.AttendVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendMapper {

    @Select("""
            WITH WEEK_CALCUL AS (
                SELECT
                    TRUNC(START_TIME, 'IW') AS WEEK,
                    ATTEND_NO,
                    EMP_NO,
                    START_TIME,
                    END_TIME,
                    DENSE_RANK() OVER (ORDER BY TRUNC(START_TIME, 'IW')) AS weekNum
                FROM ATTEND
                WHERE EMP_NO = #{empNo}
            )
            SELECT
                WEEK,
                weekNum,
                ATTEND_NO,
                EMP_NO,
                START_TIME,
                END_TIME
            FROM WEEK_CALCUL
            WHERE weekNum = #{weekNum}\s
            ORDER BY WEEK, START_TIME
            """)
    @Results({
            @Result(property = "weekNum", column = "weekNum"),
    })
    List<AttendVo> myAttendList(String empNo);
}
