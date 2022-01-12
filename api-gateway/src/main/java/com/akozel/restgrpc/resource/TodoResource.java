package com.akozel.restgrpc.resource;

import java.util.List;
import java.util.UUID;

import com.akozel.restgrpc.client.TodoClient;
import com.akozel.restgrpc.dto.TodoItemDto;
import com.akozel.restgrpc.dto.TodoStatus;
import com.akozel.restgrpc.dto.UpdateTodoDto;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/api/todos")
public class TodoResource {

  private final TodoClient todoClient;

  @Inject
  public TodoResource(TodoClient todoClient) {
    this.todoClient = todoClient;
  }

  @Get("{id}")
  public TodoItemDto getTodoItem(@PathVariable final UUID id) {
    return todoClient.getTodoItem(id);
  }

  @Get
  public List<TodoItemDto> findTodos(@QueryValue("status") final TodoStatus status) {
    return todoClient.findTodos(status);
  }

  @Post
  public TodoItemDto createTodo(@Body final TodoItemDto body) {
    return todoClient.createTodo(body);
  }

  @Put("{id}")
  public TodoItemDto updateTodo(@PathVariable final UUID id, @Body final UpdateTodoDto body) {
    return todoClient.updateTodo(id, body);
  }

}
