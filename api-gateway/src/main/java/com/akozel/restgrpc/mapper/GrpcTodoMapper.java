package com.akozel.restgrpc.mapper;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.akozel.restgrpc.model.TodoItem;
import com.akozel.restgrpc.model.TodoStatus;
import com.akozel.restgrpc.model.UpdateTodo;
import org.mapstruct.Mapper;
import todo.TodoItemDto;
import todo.UpdateItemDto;

@Mapper
public interface GrpcTodoMapper {

  TodoItemDto convert(TodoItem todos);

  TodoItem convert(TodoItemDto todos);

  UpdateItemDto convert(UpdateTodo item);

  List<TodoItem> convert(List<TodoItemDto> todos);

  default TodoStatus convert(final todo.TodoStatus source) {
    return TodoStatus.valueOf(source.name());
  }

  default Instant convert(final Long source) {
    return Optional.ofNullable(source)
        .map(Instant::ofEpochMilli)
        .orElse(null);
  }

  default UUID convert(final String source) {
    return Optional.ofNullable(source)
        .map(UUID::fromString)
        .orElse(null);
  }

  default String convert(final UUID source) {
    return Optional.ofNullable(source)
        .map(UUID::toString)
        .orElse(null);
  }

  default Long convert(final Instant source) {
    return Optional.ofNullable(source)
        .map(Instant::toEpochMilli)
        .orElse(null);
  }
}
