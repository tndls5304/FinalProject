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
    @Insert("INSERT INTO EMPLOYEE (NO, EMAIL, NAME, DEPT_NO, POSITION_NO)VALUES(SEQ_EMPLOYEE.NEXTVAL, #{email},#{name}, #{deptNo}, #{positionNo})")
    @Options(useGeneratedKeys = true, keyProperty = "no", keyColumn = "NO")
    void insertEmp(EmployeeVo employeeVo);


    //전체사원조회 :직위높은순으로 그리고 부서별로
    @Select("""
            SELECT E.NO,E.NAME,E.ID,P.NAME AS POSITION_NAME,D.NAME AS DEPT_NAME,E.LOCK_YN
            FROM EMPLOYEE E
            LEFT JOIN (
                SELECT DEPT_NO, COUNT(*) AS EMPLOYEE_COUNT
                FROM EMPLOYEE
                GROUP BY DEPT_NO
            ) D ON E.DEPT_NO = D.DEPT_NO
            LEFT JOIN DEPARTMENT D ON E.DEPT_NO=D.NO
            JOIN POSITION P ON E.POSITION_NO=P.NO
            WHERE E.RETIRE_YN='N'
            ORDER BY E.POSITION_NO ASC, E.DEPT_NO
            """
    )
    List<EmployeeVo> getAllEmpList();

    //사원상세조회
    @Select("""
                SELECT NO,NAME,EMAIL,PWD,PROFILE,PHONE,HIRE_DATE,LOGIN_FAIL_NUM,LOCK_YN
                FROM EMPLOYEE
                WHERE NO=#{no} AND RETIRE_YN='N'
            """)
    EmployeeVo getEmpByNo(String no);

    //사원정보수정
    @Update("""
            UPDATE EMPLOYEE
            SET NAME=#{name},PHONE=#{phone},PWD=#{pwd},LOGIN_FAIL_NUM=#{loginFailNum},LOCK_YN=#{lockYn},EMAIL=#{email}
            WHERE NO=#{no}
            """)
    int editEmp(EmployeeVo vo);

    //퇴사처리 TODO 퇴사처리에  퇴사 날짜 넣기
    @Update("""
            UPDATE EMPLOYEE
            SET RETIRE_YN='Y'
            WHERE NO=#{no}
            """)
    int resignEmp(String no);

    //조건부 사원검색

    @Select("""
            <script>
                   SELECT E.NO,E.NAME,E.ID,P.NAME AS POSITION_NAME,D.NAME AS DEPT_NAME,E.LOCK_YN
                    FROM EMPLOYEE E JOIN POSITION P ON  E.POSITION_NO=P.NO
                                    JOIN DEPARTMENT D ON E.DEPT_NO=D.NO
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


//서브관리자의 2번메뉴바 신규사원등록 권한체크
    @Select("""
            SELECT INSERT_YN
            FROM ADMIN A
            JOIN ADMIN_AUTHORITY U ON A.ADMIN_AUTHORITY_NO=U.NO
            JOIN ADMIN_PAGE_MENU_AUTHORITY P ON U.NO=P.ADMIN_AUTHORITY_NO
            WHERE A.NO='2'AND P.ADMIN_PAGE_MENU_NO='2'
            """)
    String checkAuthYn();
//TODO 집에서해야함
    @Select("")
    String checkAuthYnForUpdateEmpInfo();
}

