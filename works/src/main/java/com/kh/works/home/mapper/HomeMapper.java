package com.kh.works.home.mapper;

import com.kh.works.board.vo.BoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeMapper {

    @Select("SELECT E.EMAIL,E.NAME,E.ID,E.PROFILE,E.PHONE,D.NAME,P.NAME FROM EMPLOYEE E JOIN DEPARTMENT D ON E.DEPT_NO=D.NO " +
            "JOIN POSITION P ON E.POSITION_NO= P.NO WHERE E.NO=#{no}")
    EmployeeVo selectEmpInfo(String no);

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
    int writing(BoardVo vo);
}
