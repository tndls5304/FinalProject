package com.kh.works.messenger.mapper;

import com.kh.works.messenger.vo.MessengerVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessengerMapper {

    @Insert("INSERT INTO MESSENGER (MESSEN_NO, RECEIVER_EMP_NO, TITLE, CONTENT, SEND_DATE) VALUES (SEQ_MESSENGER.NEXTVAL, #{receiverEmpNo}, #{title}, #{content}, SYSDATE)")
    int write(MessengerVo vo);

    @Select("SELECT E.NAME , M.TITLE , M.CONTENT , M.SEND_DATE FROM MESSENGER M JOIN EMPLOYEE E ON M.RECEIVER_EMP_NO = E.NO")
    List<MessengerVo> getMessengerList();
}
