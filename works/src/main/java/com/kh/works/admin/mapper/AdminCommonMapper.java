package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.AdminPageMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminCommonMapper {

 @Select(" SELECT P.NO,P.NAME,p.URL\n" +
         "   FROM ADMIN_AUTHORITY A\n" +
         "   JOIN ADMIN_PAGE_MENU_AUTHORITY M ON A.NO=M.ADMIN_AUTHORITY_NO\n" +
         "   JOIN ADMIN_PAGE_MENU P ON  M.ADMIN_PAGE_MENU_NO=P.NO\n" +
         "   WHERE A.NO=#{adminAuthNo} AND M.SELECT_YN='Y'")
 List<AdminPageMenuVo> selectSidePageComponent(String adminAuthNo);
}
