package com.kh.works.board.mapper;

import com.kh.works.board.vo.BoardVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT \n" +
            "    B.BOARD_NO\n" +
            "    ,E.NAME\n" +
            "    ,B.TITLE\n" +
            "    ,B.CRTN_DATE\n" +
            "    ,B.VIEW_COUNT\n" +
            "FROM BOARD B\n" +
            "JOIN EMPLOYEE E\n" +
            "ON B.EMP_NO = E.NO\n")
    List<BoardVo> getBoardList();

    @Select("SELECT \n" +
            "    B.BOARD_NO\n" +
            "    ,E.NAME\n" +
            "    ,B.TITLE\n" +
            "    ,B.CRTN_DATE\n" +
            "    ,B.VIEW_COUNT\n" +
            "FROM BOARD B\n" +
            "JOIN EMPLOYEE E\n" +
            "ON B.EMP_NO = E.NO\n" +
            "WHERE b.emp_no = #{empNo}")
    List<BoardVo> myBoardList(String empNo);

    @Select("SELECT * FROM BOARD WHERE BOARD_NO = #{boardNo}")
    BoardVo getdetailBoard(String boardNo);
}
