package com.kh.works.messenger.mapper;

import com.kh.works.messenger.vo.MessengerVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MessengerMapper {

    @Insert("INSERT INTO MESSENGER (MESSEN_NO, SENDER_EMP_NO, RECEIVER_EMP_NO, TITLE, CONTENT, SEND_DATE) VALUES (SEQ_MESSENGER.NEXTVAL, #{senderEmpNo}, #{receiverEmpNo}, #{title}, #{content}, SYSDATE)")
    int write(MessengerVo vo);

    @Select("SELECT E.NAME , P.NAME , D.NAME FROM EMPLOYEE E JOIN POSITION P ON E.POSITION_NO = P.NO JOIN DEPARTMENT D ON E.DEPT_NO = D.NO")
    List<MessengerVo> getEmployeeList();

    @Select("SELECT M.MESSEN_NO, '받은 쪽지' AS MSG_TYPE, E.NAME AS EMP_NAME, M.TITLE, M.CONTENT, M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO UNION ALL SELECT M.MESSEN_NO, '보낸 쪽지' AS MSG_TYPE, E.NAME AS EMP_NAME, M.TITLE, M.CONTENT, M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.SENDER_EMP_NO = E.NO ORDER BY SEND_DATE DESC")
    List<MessengerVo> getMessengerList();

    @Select("SELECT E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO ORDER BY M.SEND_DATE DESC")
    List<MessengerVo> getReceivedList();

    @Select("SELECT E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.SENDER_EMP_NO = E.NO ORDER BY M.SEND_DATE DESC")
    List<MessengerVo> getSentList();

    @Select("SELECT E.NAME, M.TITLE, M.CONTENT, M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO WHERE M.READ_YN = 'N' ORDER BY M.SEND_DATE DESC")
    List<MessengerVo> getUnreadList();

    @Update("UPDATE MESSENGER SET READ_YN = 'Y' WHERE MESSEN_NO = #{messenNo}")
    int read(int messenNo);

}
