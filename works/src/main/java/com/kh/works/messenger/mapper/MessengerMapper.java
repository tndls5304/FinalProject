package com.kh.works.messenger.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.messenger.vo.MessengerVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessengerMapper {

    @Insert("INSERT INTO MESSENGER (MESSEN_NO, SENDER_EMP_NO, RECEIVER_EMP_NO, TITLE, CONTENT, SEND_DATE) VALUES (SEQ_MESSENGER.NEXTVAL, #{senderEmpNo}, #{receiverEmpNo}, #{title}, #{content}, SYSDATE)")
    int write(MessengerVo vo);

    //아래와 같이 작성하지 못하는 이유 : employee 테이블에서 가져오는 건데 employeeVo에 E.NAME, P.NAME, D.NAME에 해당하는 칼럼이 없다.
    //@Select("SELECT E.NAME , P.NAME , D.NAME FROM EMPLOYEE E JOIN POSITION P ON E.POSITION_NO = P.NO JOIN DEPARTMENT D ON E.DEPT_NO = D.NO")

    //그런데 employeeVo에서 positionName, deptName을 생성하면 되는데, 다른 분 코드를 건드리는 거니까 가급적 피한다.
    //그렇기 때문에 별칭을 이미 employeeVo에 있는 positionNo와 deptNo로 작성한다.
    //EmployeeVo를 가져와, write.jsp에서 positionNo와 deptNo로 사용하기 위해 "@Results"를 사용해서 칼럼명을 지정해 준다.
    @Select("SELECT E.NO , E.NAME , P.NAME AS positionNO , D.NAME AS deptNO FROM EMPLOYEE E JOIN POSITION P ON E.POSITION_NO = P.NO JOIN DEPARTMENT D ON E.DEPT_NO = D.NO")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "positionNo", column = "positionNo"),
            @Result(property = "deptNo", column = "deptNo")
    })
    List<EmployeeVo> getEmployeeList();

    @Select("SELECT M.MESSEN_NO, '받은 쪽지' AS MSG_TYPE, E.NAME AS name, M.TITLE, M.CONTENT, M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO WHERE M.SENDER_EMP_NO = #{senderEmpNo} UNION ALL SELECT M.MESSEN_NO, '보낸 쪽지' AS MSG_TYPE, E.NAME AS EMP_NAME, M.TITLE, M.CONTENT, M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.SENDER_EMP_NO = E.NO WHERE M.RECEIVER_EMP_NO = #{receiverEmpNo} ORDER BY SEND_DATE DESC")
    @Results({
            @Result(property = "name", column = "name"),
    })
    List<MessengerVo> getMessengerList(@Param("senderEmpNo") String senderEmpNo, @Param("receiverEmpNo") String receiverEmpNo);

    @Select("SELECT M.MESSEN_NO, E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.SENDER_EMP_NO = E.NO WHERE M.RECEIVER_EMP_NO = #{receiverEmpNo} ORDER BY M.SEND_DATE DESC")
    List<MessengerVo> getReceivedList(@Param("receiverEmpNo") String receiverEmpNo);

    @Select("SELECT M.MESSEN_NO, E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO WHERE M.SENDER_EMP_NO = #{senderEmpNo} ORDER BY M.SEND_DATE DESC")
    List<MessengerVo> getSentList(@Param("senderEmpNo") String senderEmpNo);

    @Select("SELECT M.MESSEN_NO, M.TITLE , E1.NAME AS senderName , E2. NAME As receiverName , M.SEND_DATE , M.CONTENT FROM MESSENGER M JOIN EMPLOYEE E1 ON M.SENDER_EMP_NO = E1.NO JOIN EMPLOYEE E2 ON M.RECEIVER_EMP_NO = E2.NO WHERE M.MESSEN_NO = #{messenNo}")
    @Results({
            @Result(property = "senderName", column = "senderName"),
            @Result(property = "receiverName", column = "receiverName"),
    })
    List<MessengerVo> getDetailPage(@Param("messenNo") String messenNo);

    @Select("SELECT M.MESSEN_NO, E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.SENDER_EMP_NO = E.NO WHERE M.READ_YN = 'N' AND M.RECEIVER_EMP_NO = #{receiverEmpNo} ORDER BY M.SEND_DATE DESC")
    List<MessengerVo> getUnreadList(@Param("receiverEmpNo") String receiverEmpNo);

    @Update("UPDATE MESSENGER SET READ_YN = 'Y' WHERE MESSEN_NO = #{messenNo}")
    int read(@Param("messenNo") int messenNo);

    @Select("SELECT MESSEN_NO, SENDER_EMP_NO , RECEIVER_EMP_NO , TITLE , CONTENT , SEND_DATE FROM MESSENGER WHERE MESSEN_NO = #{messenNo}")
    MessengerVo getMessengerById(int messenNo);

    @Select("SELECT M.MESSEN_NO, E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.SENDER_EMP_NO = E.NO WHERE M.IMPORTANT_YN = 'Y' AND M.RECEIVER_EMP_NO = #{receiverEmpNo} UNION ALL SELECT M.MESSEN_NO, E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO WHERE M.IMPORTANT_YN = 'Y' AND M.SENDER_EMP_NO = #{senderEmpNo} ORDER BY SEND_DATE DESC")
    List<MessengerVo> getImportantList(@Param("receiverEmpNo") String receiverEmpNo, @Param("senderEmpNo") String senderEmpNo);

    @Update("UPDATE MESSENGER SET IMPORTANT_YN = 'Y' WHERE MESSEN_NO = #{messenNo}")
    int importantStatus(@Param("messenNo") int messenNo);


    List<MessengerVo> searchByKeyWord(String keyWord);


}
