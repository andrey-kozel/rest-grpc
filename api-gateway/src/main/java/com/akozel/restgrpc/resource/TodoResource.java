package com.akozel.restgrpc.resource;

import com.akozel.restgrpc.client.TodoClient;
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
@Controller("todos")
public class TodoResource {

  private final TodoClient todoClient;

  @Inject
  public TodoResource(TodoClient todoClient) {
    this.todoClient = todoClient;
  }

  @Get("{id}")
  public String getTodoItem(@PathVariable final String id) {
    return todoClient.getTodoItem(id);
  }

  @Get
  public String findTodos(@QueryValue("status") final String status) {
    return todoClient.findTodos(status);
  }

  @Post
  public String createTodo(@Body final String body) {
    return todoClient.createTodo(body);
  }

  @Put("{id}")
  public String updateTodo(@PathVariable final String id, @Body final String body) {
    return todoClient.updateTodo(id, body);
  }

}
