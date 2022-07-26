package com.jihun.todolist.domain.todo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 이걸 쓰려면 mybatis 라이브러리가 필요 이게있어야 xml연결이됨
public interface TodoRepository {
	public int save(Todo todo) throws Exception;
	public List<Todo> getTodoListOfIndex(Map<String, Object> map) throws Exception;
}
