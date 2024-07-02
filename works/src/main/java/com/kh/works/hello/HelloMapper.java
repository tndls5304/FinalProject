package com.kh.works.hello;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloMapper {

    @Insert("INSERT INTO HELLO (NO, TITLE, CONTENT) VALUES(SEQ_HELLO.NEXTVAL, #{title} , #{content})")
    int m01(HelloVo vo);

    @Insert("INSERT INTO WORLD(HELLO_NO , MANAGER_NO) VALUES( SEQ_HELLO.CURRVAL , #{managerNo})")
    int m02(String managerNo);

}
