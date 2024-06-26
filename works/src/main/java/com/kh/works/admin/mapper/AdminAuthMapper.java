package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.SubAdminMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminAuthMapper {
    @Select("SELECT A.NO menuNo,A.NAME AS menuName ,P.SELECT_YN AS authSelectYn,P.INSERT_YN AS authInsertYn ,P.MODIFY_YN AS authModifyYn ,P.REMOVE_YN AS authRemoveYn\n" +
            "FROM ADMIN_PAGE_MENU A\n" +
            "JOIN ADMIN_PAGE_MENU_AUTHORITY P ON A.NO=P.ADMIN_PAGE_MENU_NO\n" +
            "WHERE P.ADMIN_AUTHORITY_NO='2'")
    List<SubAdminMenuVo> getMenuVoList();
}
