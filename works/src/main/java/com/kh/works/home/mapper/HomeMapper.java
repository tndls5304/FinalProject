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

    @Insert("INSERT INTO BOARD ( BOARD_CATG_NO,BOARD_NO,TITLE, CONTENT,CRTN_DATE,EMP_NO)VALUES ('1',SEQ_BOARD.NEXTVAL,#{title},#{content},TO_DATE('2024-06-26', 'YYYY-MM-DD'),#{empNo})")
    int writing(BoardVo boardVo);
}
