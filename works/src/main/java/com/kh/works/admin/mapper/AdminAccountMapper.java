package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminAccountMapper {

//어드민로그인
    @Select("SELECT ID,PWD,ADMIN_AUTHORITY_NO FROM ADMIN WHERE ID=#{id} AND PWD=#{pwd}")
    AdminVo adminLoginMatching(AdminVo vo);
}
