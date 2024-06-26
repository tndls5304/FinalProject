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
    @Select("SELECT E.NAME AS name, P.NAME AS positionNo, D.NAME AS deptNo " +
            "FROM EMPLOYEE E " +
            "JOIN POSITION P ON E.POSITION_NO = P.NO " +
            "JOIN DEPARTMENT D ON E.DEPT_NO = D.NO")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "positionNo", column = "positionNo"),
            @Result(property = "deptNo", column = "deptNo")
    })
    List<EmployeeVo> getEmployeeList();

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
