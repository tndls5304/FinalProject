package com.kh.works.todo.dao;

import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.mapper.TodoMapper;
import com.kh.works.todo.vo.TodoVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final TodoMapper mapper;

    //할일 작성
    public int todowrite(TodoVo todoVo) {
        return mapper.todoWrite(todoVo);
    }


    public int todoManager(String todoManagerNo) {
        return  mapper.todoManager(todoManagerNo);
    }

    //상세조회
    public List<TodoVo> getTodoByNo(int todoNo) {
        return mapper.getTodoByNo(todoNo);
    }


    //모든 할일 조회
    public List<TodoVo> getTodoListAll(TodoVo vo) {
        return mapper.getTodoListAll(vo);
    }

    //참여자인 할일 조회
    public List<TodoVo> getTodoListPar(TodoVo vo) {
        return mapper.getTodoListPar(vo);
    }


    //할일 수정
    public int todoEdit(TodoVo vo) {
        return mapper.todoEdit(vo);
    }


    //할일 검색
    public List<TodoVo> todoSearch(TodoVo vo ){
        return mapper.todoSearch(vo);
    }

    //할일 삭제
    public int todoDelete(String todoNo) {
        return mapper.todoDelete(todoNo);
    }

    //담당자 리스트 가져오기
    public List<EmployeeVo> getManagerList() {
        return mapper.getManagerList();
    }


    //할일 완료
    public int todocomplete(String todoNo) {
        return mapper.todoComplete(todoNo);
    }

    //받은 알람 리스트로 가져오기
    public List<AlarmVo> getTodoAlarm(String todoManagerNo) {
        return mapper.getTodoAlarm(todoManagerNo);
    }

    //읽은 알람 처리
    public int read(int alarmNo) {
        return mapper.read(alarmNo);
    }

    //알람 저장하기
    public void saveAlarm(String todoManagerNo, String notificationMessage) {
        mapper.saveAlarm(todoManagerNo, notificationMessage);

    }

    public EmployeeVo getEmpInfo(EmployeeVo empVo) {
        return mapper.getEmpInfo(empVo);
    }


    //최신 작성순
    public List<TodoVo> getTodoListCreateDate(TodoVo vo) {
        return mapper.getTodoListCreateDate(vo);    }


    //기한 마감순
    public List<TodoVo> getTodoListEndDate(TodoVo vo) {
        return mapper.getTodoListEndDate(vo);
    }

    public void selectDelete(Integer todoNo) {
         mapper.selectDelete(todoNo);
    }
}
