package com.kh.works.calendar.vo;

import com.kh.works.employee.vo.EmployeeVo;
import lombok.Data;

import java.util.List;

@Data
public class CalendarVo {
  private String  no;
  private String  startDate;
  private String  endDate;
  private String  title;
  private String  content;
  private String  placeName;
  private String  delYn ;
  private String  insertDate;
  private String  updateDate;
  private String  empNo;
  private String  openRangeNo;
  private String  latitude;      //위도 (후순위)
  private String  longitude;     //경도 (후순위)
  private String  adminNo;
  private List<PartnerVo> partnerList;
}