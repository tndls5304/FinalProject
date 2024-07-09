package com.kh.works.calendar.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.Data;

import java.util.List;

@Data
public class CalendarVo {
  private String  no;
  private String  start;      //시작날짜
  private String  end;        //종료날짜
  private String  title;
  private String  content;
  private String  placeName;        //장소이름
  private String  delYn ;
  private String  insertDate;
  private String  updateDate;
  private String  empNo;          //작성자가 사원일때
  private String  openRangeNo;
  private String  latitude;      //위도 (후순위)
  private String  longitude;     //경도 (후순위)
  private String  adminNo;      //작성자가 관리자일때
  private List<PartnerVo> partnerList;
  private String  partnerName;      //캘린더 파트너 이름
  private String   partnerNo;       //파트너 empNo




}