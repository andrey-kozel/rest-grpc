package om.akozel.restgrpc.repository;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Singleton;
import om.akozel.restgrpc.model.TodoItem;
import om.akozel.restgrpc.model.TodoStatus;
import om.akozel.restgrpc.model.UpdateTodo;

@Singleton
public interface TodoRepository {

  TodoItem getTodoItem(String id);

  List<TodoItem> findTodos(TodoStatus status);

  TodoItem saveTodo(TodoItem item);

  TodoItem updateTodo(UUID id, UpdateTodo updateRequest);
}
