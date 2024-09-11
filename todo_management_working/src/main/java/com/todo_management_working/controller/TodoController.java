package com.todo_management_working.controller;


import com.todo_management_working.dto.TodoDto;
import com.todo_management_working.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/todo")
public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        System.out.println("reached post mapping create");
        TodoDto savedTodoDto = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("get/{id}")
    public ResponseEntity<TodoDto> getTodoByid(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getall")
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todoDtos =  todoService.getAllTodos();
        return ResponseEntity.ok(todoDtos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatetodo/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id){
        TodoDto todoDto1 = todoService.updateTodo(todoDto,id);

        return ResponseEntity.ok(todoDto1);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletetodo/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id){
        todoService.deleteTodo(id);

        return ResponseEntity.ok("Todo Deleted Successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> markTodoAsComplete(@PathVariable Long id){
        TodoDto todoDto1 = todoService.completeTodo(id);

        return ResponseEntity.ok(todoDto1);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> markTodoAsInComplete(@PathVariable Long id){
        TodoDto todoDto1 = todoService.inCompleteTodo(id);

        return ResponseEntity.ok(todoDto1);
    }
}
