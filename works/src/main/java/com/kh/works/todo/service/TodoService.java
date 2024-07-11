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
        // 받은 endDate 값 출력
        System.out.println("@@@@@@@@Received endDate: " + vo.getEndDate());

        vo.setEndDate(calculateEndDate(vo));

        System.out.println("@@@@@@@@@@@@Calculated endDate: " + vo.getEndDate());

        //todo작성 실행
        int result1 = todoDao.todowrite(vo);

       List<String>todoManageList = vo.getTodoManagerList();
       int result2 = 1;
        for (String manVo : todoManageList) {
            result2 *= todoDao.todoManager(manVo);
        }

        if(result1 * result2 != 1){
            throw new RuntimeException();
        }

        if(result1 * result2 == 1){
            String notificationMessage = "✅" + vo.getTodoEmpName() + "님이 할일 담당자로 지정하였습니다.";
//            todoDao.saveAlarm(vo.getTodoManagerNo(), notificationMessage);
//            notificationHandler.sendNotification(notificationMessage);
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
    public int read(String todoManagerNo) {
        return todoDao.read(todoManagerNo);
    }

    public EmployeeVo getEmpInfo(EmployeeVo empVo) {
        return todoDao.getEmpInfo(empVo);
    }
}
