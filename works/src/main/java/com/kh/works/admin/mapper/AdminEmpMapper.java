package com.kh.works.admin.mapper;

import com.kh.works.admin.vo.AdminVo;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
            WHERE E.ENT_YN='N'
            ORDER BY E.POSITION_NO ASC, E.DEPT_NO
            """
    )
    List<EmployeeVo> getAllEmpList();

    //사원상세조회
    @Select("""
                SELECT NO,NAME,EMAIL,PWD,PROFILE,PHONE,HIRE_DATE,LOGIN_FAIL_NUM,LOCK_YN
                FROM EMPLOYEE
                WHERE NO=#{no} AND ENT_YN='N'
            """)
    EmployeeVo getEmpByNo(String no);
}
