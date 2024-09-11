package com.todo_management_working.service.impl;

import com.todo_management_working.dto.TodoDto;
import com.todo_management_working.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import com.todo_management_working.entity.Todo;
import com.todo_management_working.repository.TodoRepository;
import com.todo_management_working.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.title());
//        todo.setDescription(todoDto.description());
//        todo.setCompleted(todoDto.completed());

        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

//        TodoDto todoDto1 = new TodoDto(savedTodo.getId(), savedTodo.getTitle(), savedTodo.getDescription(), savedTodo.isCompleted());

        TodoDto todoDto1 =  modelMapper.map(savedTodo, TodoDto.class);

        return todoDto1;
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("To do not found beta i am sorry"));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        todos = todoRepository.findAll();

        List<TodoDto> todoDtos = new ArrayList<>();
        for(Todo todo:todos){
            todoDtos.add(modelMapper.map(todo, TodoDto.class));
        }

        return todoDtos;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("todo not found beta i am sorry"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("todo not found"));

        todoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("todo not found"));

        if(!todo.isCompleted()){
            todo.setCompleted(Boolean.TRUE);
        }

        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("todo not found"));

        if(todo.isCompleted()){
            todo.setCompleted(Boolean.FALSE);
        }

        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(todo, TodoDto.class);
    }
}
