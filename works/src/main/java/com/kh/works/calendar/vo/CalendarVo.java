package com.kh.works.calendar.vo;

import com.kh.works.employee.vo.EmployeeVo;
import lombok.Data;

import java.util.List;

@Data
public class CalendarVo {
   private String no;
   private String startDate;
   private String  endDate;
   private String  title;
   private String  content;
   private String  address;
   private String  delYn;
   private String  insertDate;
   private String  updateDate;
   private String  openRangeNo;
   private String  frontColor;
   private String  empNo;
   private String  empName;
   private String  adminNo;
   private String  adminName;
   private List<EmployeeVo> partner;
}