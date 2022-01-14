package com.akozel.restgrpc.client;

import java.util.List;
import java.util.UUID;

import com.akozel.restgrpc.model.TodoItem;
import com.akozel.restgrpc.model.TodoStatus;
import com.akozel.restgrpc.model.UpdateTodo;
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
  TodoItem getTodoItem(@PathVariable UUID id);

  @Get
  List<TodoItem> findTodos(@QueryValue TodoStatus status);

  @Post
  TodoItem createTodo(@Body TodoItem body);

  @Put("{id}")
  TodoItem updateTodo(@PathVariable UUID id, @Body UpdateTodo body);
}
