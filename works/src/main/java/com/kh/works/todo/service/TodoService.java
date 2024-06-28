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

        TodoVo todoVo = new TodoVo();
        todoVo.setTodoEmpNo(allVo.getTodoEmpNo());
        todoVo.setTitle(allVo.getTitle());
        todoVo.setContent(allVo.getContent());
        todoVo.setEndDate(allVo.getEndDate());

        TodoManangerVo manVo = new TodoManangerVo();
        manVo.setTodoManagerNo(allVo.getTodoManagerNo());

        //todo작성
        int result1 = todoDao.todowrite(todoVo);

        //작성 후 할일 담당 테이블에 넣을 todo번호 가져옴
        manVo.setTodoNoMan(allVo.getTodoNo());

        int result2 = manDao.todoWrite(manVo);

        return result1 * result2;

    }
}
