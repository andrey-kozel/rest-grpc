package om.akozel.restgrpc.mapper.rest;

import java.util.List;

import om.akozel.restgrpc.dto.TodoItemDto;
import om.akozel.restgrpc.dto.UpdateTodoDto;
import om.akozel.restgrpc.model.TodoItem;
import om.akozel.restgrpc.model.UpdateTodo;
import org.mapstruct.Mapper;

@Mapper
public interface RestTodoMapper {

  TodoItemDto convert(TodoItem todoItem);

  List<TodoItemDto> convert(List<TodoItem> source);

  TodoItem convert(TodoItemDto dto);

  UpdateTodo convert(UpdateTodoDto dto);
}
