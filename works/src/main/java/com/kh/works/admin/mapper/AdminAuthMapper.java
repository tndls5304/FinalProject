package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.SubAdminMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminAuthMapper {

    //서브어드민 권한 조회해오기
    @Select("""
            SELECT A.NO AS menuNo,A.NAME AS menuName
                    ,P.SELECT_YN AS authSelectYn
                    ,P.INSERT_YN AS authInsertYn
                    ,P.MODIFY_YN AS authModifyYn
                    ,P.REMOVE_YN AS authRemoveYn
            FROM ADMIN_PAGE_MENU A
            JOIN ADMIN_PAGE_MENU_AUTHORITY P
            ON A.NO=P.ADMIN_PAGE_MENU_NO
            WHERE P.ADMIN_AUTHORITY_NO='2'
            """)
    List<SubAdminMenuVo> getMenuVoList();


    //서브어드민 권한 수정하기
    @Update("""
            <script>
             UPDATE ADMIN_PAGE_MENU_AUTHORITY
                    SET
                        <if test="authSelectYn != null">
                            SELECT_YN = #{authSelectYn},
                        </if>
                        <if test="authInsertYn != null">
                            INSERT_YN = #{authInsertYn},
                        </if>
                        <if test="authModifyYn != null">
                            MODIFY_YN = #{authModifyYn},
                        </if>
                        <if test="authRemoveYn != null">
                            REMOVE_YN = #{authRemoveYn}
                        </if>
                    WHERE ADMIN_PAGE_MENU_NO = #{menuNo} AND ADMIN_AUTHORITY_NO = '2'
            </script>
            """)
    int updateAuth(SubAdminMenuVo vo);

    /*1번 메뉴바인 권한수정 메뉴의 수정할 수 있기전에 서브관리자라면 권한 체크해주기
       쿼리 의미 : A.NO='2'는 서브관리자    P.ADMIN_PAGE_MENU_NO='1'는 메뉴바 1번 */
    @Select("""
            SELECT MODIFY_YN
            FROM ADMIN A
            JOIN ADMIN_AUTHORITY U ON A.ADMIN_AUTHORITY_NO=U.NO
            JOIN ADMIN_PAGE_MENU_AUTHORITY P ON U.NO=P.ADMIN_AUTHORITY_NO
            WHERE A.NO='2' AND P.ADMIN_PAGE_MENU_NO='1'
            """)
    String checkAuthYn();
}
