package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.AdminVo;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.sun.tools.javac.Main;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
public interface AdminEmpMapper {


    //신규사원 등록하는 페이지에서 옵션에 고를 수 있게 부서 조회해오기
    @Select("SELECT NO,NAME FROM DEPARTMENT")
    List<DeptVo> selectDeptList();

    //신규사원 등록하는 페이지에서 옵션에 고를 수 있게 직위 조회해오기
    @Select("SELECT NO,NAME\n" +
            "FROM POSITION")
    List<PositionVo> selectPosition();

    //신규사원등록하고 사원넘버 가져오기
    @Insert("INSERT INTO EMPLOYEE (NO, EMAIL, NAME, DEPT_NO, POSITION_NO,JOIN_KEY)VALUES(#{no}, #{email},#{name}, #{deptNo}, #{positionNo}, #{joinKey})")
    int insertEmp(EmployeeVo employeeVo);

    //사원상세조회
    @Select("""
                SELECT NO,NAME,EMAIL,PWD,PROFILE,PHONE,HIRE_DATE,LOGIN_FAIL_NUM,LOCK_YN
                FROM EMPLOYEE
                WHERE NO=#{empNo} AND RETIRE_YN='N'
            """)
    EmployeeVo getEmpByNo(String empNo);

    //사원정보수정
    @Update("""
            UPDATE EMPLOYEE
            SET NAME=#{name},PHONE=#{phone},PWD=#{pwd},LOGIN_FAIL_NUM=#{loginFailNum},LOCK_YN=#{lockYn},EMAIL=#{email}
            WHERE NO=#{no}
            """)
    int editEmp(EmployeeVo vo);

    //퇴사처리
    @Update("""
            UPDATE EMPLOYEE
            SET RETIRE_YN='Y', RETIRE_DATE=SYSDATE
            WHERE NO=#{empNo}
            """)
    int resignEmp(String empNo);

    //조건부 사원검색

    @Select("""
               <script>
                      SELECT E.NO,E.NAME,E.ID,P.NAME AS POSITION_NAME,D.NAME AS DEPT_NAME,E.LOCK_YN
                       FROM EMPLOYEE E JOIN POSITION P ON  E.POSITION_NO=P.NO
                            LEFT JOIN DEPARTMENT D ON E.DEPT_NO=D.NO
                       <where>
                             <if test="retireYn != null and retireYn != ''">
                                AND  E.RETIRE_YN=#{retireYn}
                             </if>
                             <if test="deptNo != null and deptNo != ''">
                                AND  E.DEPT_NO=#{deptNo}
                             </if>
                             <if test="positionNo != null and positionNo != ''">
                                AND  E.POSITION_NO=#{positionNo}
                             </if>
                             <if test="name != null and name != ''">
                                AND  E.NAME LIKE '%' || #{name} || '%'
                             </if>
                       </where>
               </script>
            """)
    List<EmployeeVo> selectEmpByCondition(EmployeeVo vo);


    // 신규사원등록하기전에  서브관리자 권한체크
    @Select("""
            SELECT INSERT_YN
            FROM ADMIN A
            JOIN ADMIN_AUTHORITY U ON A.ADMIN_AUTHORITY_NO=U.NO
            JOIN ADMIN_PAGE_MENU_AUTHORITY P ON U.NO=P.ADMIN_AUTHORITY_NO
            WHERE A.NO='2'AND P.ADMIN_PAGE_MENU_NO='2'
            """)
    String checkAuthYnForInsertEmp();


    //회원정보수정하기전에 서브관리자 권한체크
    @Select("""
            SELECT MODIFY_YN
            FROM ADMIN A
            JOIN ADMIN_AUTHORITY U ON A.ADMIN_AUTHORITY_NO=U.NO
            JOIN ADMIN_PAGE_MENU_AUTHORITY P ON U.NO=P.ADMIN_AUTHORITY_NO
            WHERE A.NO='2' AND P.ADMIN_PAGE_MENU_NO='3'
            """)
    String checkAuthYnForUpdateEmpInfo();

    //사원퇴사처리 전에 서브관리자 권한체크
    @Select("""
            SELECT REMOVE_YN
            FROM ADMIN A
            JOIN ADMIN_AUTHORITY U ON A.ADMIN_AUTHORITY_NO=U.NO
            JOIN ADMIN_PAGE_MENU_AUTHORITY P ON U.NO=P.ADMIN_AUTHORITY_NO
            WHERE A.NO='2' AND P.ADMIN_PAGE_MENU_NO='3'
            """)
    String checkAuthYnForResignEmp();

    @Select("""
            SELECT SEQ_EMPLOYEE.NEXTVAL
            FROM DUAL
            """)
    String getEmpSeqNo();
}

