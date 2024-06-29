package com.kh.works.notice.mapper;

import com.kh.works.notice.vo.NoticeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("INSERT INTO NOTICE_BOARD\n" +
            "(\n" +
            "    NOTICE_NO\n" +
            "    ,NO\n" +
            "    ,TITLE\n" +
            "    ,CONTENT\n" +
            "    ,VIEW_COUNT\n" +
            "    ,CRTN_DATE\n" +
            "    ,MDFD_DATE\n" +
            "    ,IMG\n" +
            ")VALUES\n" +
            "(\n" +
            "    SEQ_NOTICE_NO.NEXTVAL\n" +
            "    ,#{no}\n" +
            "    ,#{title}\n" +
            "    ,#{content}\n" +
            "    ,SYSTIMESTAMP\n" +
            "    ,NULL\n" +
            "    ,NULL\n" +
            ");")
    int write(NoticeVo vo);


    @Select("SELECT *\n" +
            "FROM NOTICE \n" +
            "WHERE DEL_YN = 'N'")
    List<NoticeVo> list();


    @Select("SELECT * FROM NOTICE WHERE NOTICE_NO = #{noticeNo}")
    NoticeVo detail(String noticeNo);

    @Update("UPDATE SET DEL_YN = 'Y' WHERE NOTICE_NO =#{noticeNo}")
    int delete(String noticeNo);
}
