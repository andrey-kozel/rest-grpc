package com.akozel.restgrpc.client;

import java.util.List;
import java.util.UUID;

import com.akozel.restgrpc.dto.TodoItemDto;
import com.akozel.restgrpc.dto.TodoStatus;
import com.akozel.restgrpc.dto.UpdateTodoDto;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

@Client("${internal.urls.todo-service}/todos")
public interface TodoClient {

  @Get("{id}")
  TodoItemDto getTodoItem(@PathVariable UUID id);

  @Get
  List<TodoItemDto> findTodos(@QueryValue TodoStatus status);

  @Post
  TodoItemDto createTodo(@Body TodoItemDto body);

  @Put("{id}")
  TodoItemDto updateTodo(@PathVariable UUID id, @Body UpdateTodoDto body);
}
