package com.jihun.todolist.web.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jihun.todolist.service.todo.TodoService;
import com.jihun.todolist.web.dto.CMRespDto;
import com.jihun.todolist.web.dto.todo.CreateTodoReqDto;
import com.jihun.todolist.web.dto.todo.TodoListRespDto;

import lombok.RequiredArgsConstructor;

@RestController // 데이터 통신
@RequestMapping("/api/v1/todolist") // 모든 메소드 경로앞에 붙혀줌
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getTodoList(@RequestParam int page, @RequestParam int contentCount) {
		List<TodoListRespDto> list = null;
		try {
			list = todoService.getTodoList(page, contentCount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, page + "page list on load failed", page));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, page + "page list success load",list));
	}
	
	
	
	@PostMapping("/todo")
	public ResponseEntity<?> addContent(@RequestBody CreateTodoReqDto createTodoReqDto) {
		
		try {
			if(!todoService.createTodo(createTodoReqDto)) {
				throw new RuntimeException("DataBase Error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "Adding todo failed", createTodoReqDto)); // 회원가입 실패
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success", createTodoReqDto));  // 회원가입 성공 
	}
}
