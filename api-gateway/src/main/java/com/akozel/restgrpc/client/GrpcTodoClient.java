package com.akozel.restgrpc.client;

import java.util.List;
import java.util.UUID;

import com.akozel.restgrpc.mapper.GrpcTodoMapper;
import com.akozel.restgrpc.model.TodoItem;
import com.akozel.restgrpc.model.TodoStatus;
import com.akozel.restgrpc.model.UpdateTodo;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import todo.CreateItemRequest;
import todo.GetItemRequest;
import todo.ItemResult;
import todo.SearchRequest;
import todo.SearchResult;
import todo.TodoItemDto;
import todo.TodoServiceGrpc.TodoServiceBlockingStub;
import todo.UpdateItemDto;
import todo.UpdateItemRequest;

@Singleton
public class GrpcTodoClient {

  public final TodoServiceBlockingStub client;
  public final GrpcTodoMapper grpcTodoMapper;

  @Inject
  public GrpcTodoClient(
      final TodoServiceBlockingStub client,
      final GrpcTodoMapper grpcTodoMapper
  ) {
    this.client = client;
    this.grpcTodoMapper = grpcTodoMapper;
  }

  public TodoItem getTodoItem(final UUID id) {
    final GetItemRequest request = GetItemRequest.newBuilder()
        .setId(id.toString())
        .build();

    final ItemResult result = client.getTodoItem(request);
    return grpcTodoMapper.convert(result.getItem());
  }

  public List<TodoItem> findTodos(final TodoStatus status) {
    final SearchRequest request = SearchRequest.newBuilder()
        .setStatus(todo.TodoStatus.valueOf(status.name()))
        .build();

    final SearchResult result = client.findAll(request);
    return grpcTodoMapper.convert(result.getResultList());
  }

  public TodoItem createTodo(final TodoItem item) {
    final TodoItemDto dto = grpcTodoMapper.convert(item);
    final CreateItemRequest request = CreateItemRequest.newBuilder()
        .setItem(dto)
        .build();

    final ItemResult result = client.createTodo(request);
    return grpcTodoMapper.convert(result.getItem());
  }

  public TodoItem updateTodo(final UUID id, final UpdateTodo update) {
    final UpdateItemDto dto = grpcTodoMapper.convert(update);
    final UpdateItemRequest request = UpdateItemRequest.newBuilder()
        .setId(id.toString())
        .setItem(dto)
        .build();

    final ItemResult result = client.updateTodo(request);
    return grpcTodoMapper.convert(result.getItem());
  }

}
