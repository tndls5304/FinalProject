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
        todoVo.setTodoNo(todoVo.getTodoNo());
        todoVo.setTodoEmpNo(todoVo.getTodoEmpNo());
        todoVo.setTitle(todoVo.getTitle());
        todoVo.setContent(todoVo.getContent());

        TodoManangerVo manVo = new TodoManangerVo();
        manVo.setTodoNoMan(manVo.getTodoManagerNo());
        manVo.setTodoNoMan(manVo.getTodoNoMan());


        int result1 = todoDao.todowrite(todoVo);
        int result2 = manDao.todoWritr(manVo);

        return result1 * result2;

    }
}
