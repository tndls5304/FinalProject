package com.kh.works.board.mapper;

import com.kh.works.board.vo.BoardVo;
import org.apache.ibatis.annotations.Insert;

public interface BoardMapper {

    @Insert("INSERT INTO BOARD\n" +
            "(\n" +
            "    BOARD_NO\n" +
            "    ,EMP_NO\n" +
            "    ,TITLE\n" +
            "    ,CONTENT\n" +
            "    ,VIEW_COUNT\n" +
            "    ,CRTN_DATE\n" +
            "    ,MDFD_DATE\n" +
            "    ,FILE_NAME\n" +
            "    ,IMG\n" +
            ")VALUES\n" +
            "(\n" +
            "    SEQ_BOARD.NEXTVAL\n" +
            "    ,#{empNo}\n" +
            "    ,'${title}'\n" +
            "    ,'${content}'\n" +
            "    ,NULL\n" +
            "    ,SYSTIMESTAMP\n" +
            "    ,NULL\n" +
            "    ,NULL\n" +
            "    ,NULL\n" +
            "    )")
    int write(BoardVo vo);
}
