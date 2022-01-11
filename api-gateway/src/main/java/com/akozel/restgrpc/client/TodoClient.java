package com.akozel.restgrpc.client;

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
  String getTodoItem(@PathVariable String id);

  @Get
  String findTodos(@QueryValue String status);

  @Post
  String createTodo(@Body String body);

  @Put("{id}")
  String updateTodo(@PathVariable String id, @Body String body);
}
