package com.kh.works.todo.service;


import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.dao.TodoDao;
import com.kh.works.todo.dao.TodoManagerDao;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoManangerVo;
import com.kh.works.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoDao todoDao;
    private final TodoManagerDao manDao;


    public int todoWrite(TodoAllVo allVo) {

        //todoVo로 받아온 데이터 넣어주기
        TodoVo todoVo = new TodoVo();
        todoVo.setTodoEmpNo(allVo.getTodoEmpNo());
        todoVo.setTitle(allVo.getTitle());
        todoVo.setContent(allVo.getContent());
        todoVo.setEndDate(allVo.getEndDate());

        //todo작성 실행
        int result1 = todoDao.todowrite(todoVo);

        //
        TodoManangerVo manVo = new TodoManangerVo();
        manVo.setTodoManagerNo(allVo.getTodoManagerNo());
        //todo insert후 todoNo생성되기 때문에 할일 담당 테이블에 넣을 todoNo을 todoVo에서 가져옴
        manVo.setTodoNoMan(todoVo.getTodoNo());

        //todo_manager테이블 insert실행
        int result2 = manDao.todoWrite(manVo);


        return result1 * result2;

    }

    //상세조회
    public TodoVo getTodoByNo(String no) {
        return todoDao.getTodoByNo(no);
    }
}
