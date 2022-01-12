package om.akozel.restgrpc.resource;

import java.util.List;
import java.util.UUID;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import om.akozel.restgrpc.dto.TodoItemDto;
import om.akozel.restgrpc.dto.UpdateTodoDto;
import om.akozel.restgrpc.mapper.TodoMapper;
import om.akozel.restgrpc.model.TodoItem;
import om.akozel.restgrpc.model.TodoStatus;
import om.akozel.restgrpc.model.UpdateTodo;
import om.akozel.restgrpc.service.TodoService;

@Slf4j
@Controller("/todos")
public class TodoResource {

  private final TodoService todoService;
  private final TodoMapper todoMapper;

  @Inject
  public TodoResource(
      final TodoService todoService,
      final TodoMapper todoMapper
  ) {
    this.todoService = todoService;
    this.todoMapper = todoMapper;
  }

  @Get("{id}")
  public TodoItemDto getTodoItem(@PathVariable final UUID id) {
    log.info("Loading todo item. Id:{}", id);
    final TodoItem todoItem = todoService.getTodoItem(id);
    return todoMapper.convert(todoItem);
  }

  @Get
  public List<TodoItemDto> findTodos(@QueryValue("status") final TodoStatus status) {
    log.info("Find all todos. Status:{}", status);
    final List<TodoItem> todos = todoService.findTodos(status);
    return todoMapper.convert(todos);
  }

  @Post
  public TodoItemDto createTodo(@Body final TodoItemDto dto) {
    log.info("Creating new item. Todo:{}", dto);
    final TodoItem item = todoMapper.convert(dto);
    final TodoItem savedItem = todoService.saveTodo(item);
    return todoMapper.convert(savedItem);
  }

  @Put("{id}")
  public TodoItemDto updateTodo(@PathVariable final UUID id, @Body final UpdateTodoDto dto) {
    log.info("Updating item. Id:{}, Todo:{}", id, dto);
    final UpdateTodo updateRequest = todoMapper.convert(dto);
    final TodoItem updatedItem = todoService.updateTodos(id, updateRequest);
    return todoMapper.convert(updatedItem);
  }

}
