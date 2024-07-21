package com.kh.works.todo.service;


import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.handler.NotificationHandler;
import com.kh.works.todo.dao.TodoDao;
//import com.kh.works.todo.dao.TodoManagerDao;
//import com.kh.works.todo.vo.TodoAllVo;
//import com.kh.works.todo.vo.TodoManangerVo;
import com.kh.works.todo.vo.TodoVo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoDao todoDao;
//    private final TodoManagerDao manDao;
    private final NotificationHandler notificationHandler;


    //todo작성 쿼리 2개 insert
    public int todoWrite(TodoVo vo) {
        vo.setEndDate(calculateEndDate(vo));

        //todo insert
        int result1 = todoDao.todowrite(vo);

        //todo_manager insert : 여러명의 담당자를 한명씩 꺼내 쿼리를 실행.
       List<String>todoManageList = vo.getTodoManagerList();
       int result2 = 1;
        for (String manVo : todoManageList) {
            result2 *= todoDao.todoManager(manVo);
        }

        if(result1 * result2 != 1){
            throw new RuntimeException();
        }

        //할일 담당자 지정하면 담당자에게 알림
        if(result1 * result2 == 1){
            String notificationMessage = "✅" + vo.getTodoEmpName() + "님이 할일 담당자로 \n 지정하였습니다.";
        //여기서도 담당자가 여러명이기 때문에 여러번의 알람을 insert
        for (String managerNo : todoManageList) {
            todoDao.saveAlarm(managerNo, notificationMessage);
            notificationHandler.sendNotification(notificationMessage);
        }
        }



        return result1 * result2;

    }

    //할일 담당자 조회해서 화면에 보여주기
    public List<EmployeeVo> getMangerList() {
        return todoDao.getManagerList();
    }


    public String calculateEndDate(TodoVo vo) {
        LocalDate currentDate = LocalDate.now();

        //오늘, 내일, 다음주 버튼 날짜형식으로 넘겨주기
        //value값을 문자열로 받아와서 계산
        String inputDate = vo.getEndDate();
        if ("today".equalsIgnoreCase(inputDate)) {
            return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if ("tomorrow".equalsIgnoreCase(inputDate)) {
            LocalDate tomorrowDate = currentDate.plusDays(1);
            return tomorrowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if ("nextWeek".equalsIgnoreCase(inputDate)) {
            LocalDate nextWeekDate = currentDate.plusWeeks(1);
            return nextWeekDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            throw new IllegalArgumentException("Invalid endDate value: " + inputDate);
        }
    }

    //상세조회
    public List<TodoVo> getTodoByNo(int todoNo) {
        return todoDao.getTodoByNo(todoNo);
    }


    //모든 할일 조회
    public List<TodoVo> getTodoListAll(TodoVo vo) {
        return todoDao.getTodoListAll(vo);
    }


    //참여자인 할일 조회
    public List<TodoVo> getTodoListPar(TodoVo vo) {
        return todoDao.getTodoListPar(vo);
    }


    //할일 수정
    public int todoEdit(TodoVo vo) {
        return todoDao.todoEdit(vo);
    }


    //할일 검색
    public List<TodoVo> todoSearch(TodoVo vo){
        return todoDao.todoSearch(vo);
    }

    //할일 삭제
    public int todoDelete(String todoNo) {
        return todoDao.todoDelete(todoNo);

    }


    //할일 완료
    public int todoComplete(String todoNo) {
        return todoDao.todocomplete(todoNo);
    }


    //받은알람 가져오기
    public List<AlarmVo> getTodoAlarm(String todoManagerNo) {
        return todoDao.getTodoAlarm(todoManagerNo);
    }

    //읽은 알람 처리
    public int read(int alarmNo) {
        return todoDao.read(alarmNo);
    }

    public EmployeeVo getEmpInfo(EmployeeVo empVo) {
        return todoDao.getEmpInfo(empVo);
    }


    //최신 작성순
    public List<TodoVo> getTodoListCreateDate(TodoVo vo) {
        return todoDao.getTodoListCreateDate(vo);
    }

    //기한 마감순
    public List<TodoVo> getTodoListEndDate(TodoVo vo) {
        return todoDao.getTodoListEndDate(vo);
    }

    //선택삭제
    public void selectDelete(List<Integer> todoNoList) {
        for (Integer todoNo : todoNoList) {
             todoDao.selectDelete(todoNo);
        }
    }
}
