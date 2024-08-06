package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.AdminPageMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminCommonMapper {

 //관리자 번호에 따라 사이드바 메뉴 url 골라서  주기
 @Select("""
         SELECT P.NO,P.NAME,p.URL,A.NAME AS adminType
         FROM ADMIN_AUTHORITY A
              JOIN ADMIN_PAGE_MENU_AUTHORITY M ON A.NO=M.ADMIN_AUTHORITY_NO
              JOIN ADMIN_PAGE_MENU P ON  M.ADMIN_PAGE_MENU_NO=P.NO
         WHERE A.NO=#{adminAuthNo} AND M.SELECT_YN='Y'
         """)
 List<AdminPageMenuVo> selectSidePageComponent(String adminAuthNo);
}


