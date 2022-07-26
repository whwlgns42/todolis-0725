package com.jihun.todolist.service.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jihun.todolist.domain.todo.Todo;
import com.jihun.todolist.domain.todo.TodoRepository;
import com.jihun.todolist.web.dto.todo.CreateTodoReqDto;
import com.jihun.todolist.web.dto.todo.TodoListRespDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todoRepository;

	@Override
	public boolean createTodo(CreateTodoReqDto createTodoReqDto) throws Exception {
		Todo todoEntity = createTodoReqDto.toEntity();
		String content = todoEntity.getTodo_content();
		
		for(int i = 0; i < 100; i++) {
			todoEntity.setTodo_content(content + "_" + (i + 1));
			if(i % 2 == 0) {
				todoEntity.setImportance_flag(1);
			}else {
				todoEntity.setImportance_flag(0);
			}
			todoRepository.save(todoEntity);
		}
		return true;
		
//		return todoRepository.save(todoEntity) > 0;
	}
@Override
	public List<TodoListRespDto> getTodoList(int page, int contentCount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", (page - 1) * contentCount);
		map.put ("count", contentCount);
		
		List<Todo> todoList = todoRepository.getTodoListOfIndex(map);
		
		List<TodoListRespDto> todoListRespDtos = new ArrayList<TodoListRespDto>();
		todoList.forEach(todo -> {
			todoListRespDtos.add(todo.toListDto());
		});
		return todoListRespDtos;
	}
	

//	
//	@Override
//	public boolean createTodo(CreateTodoReqDto createTodoReqDto) throws Exception {
//		Todo todoEntity = createTodoReqDto.toEntity();
//		boolean saveResult = todoRepository.save(todoEntity) > 0;
//		return saveResult;
//	}

	
}
