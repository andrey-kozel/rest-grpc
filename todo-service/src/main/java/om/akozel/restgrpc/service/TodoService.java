package om.akozel.restgrpc.service;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import om.akozel.restgrpc.model.TodoItem;
import om.akozel.restgrpc.model.TodoStatus;
import om.akozel.restgrpc.model.UpdateTodo;
import om.akozel.restgrpc.repository.TodoRepository;

@Singleton
public class TodoService {

  private final TodoRepository todoRepository;

  @Inject
  public TodoService(final TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public TodoItem getTodoItem(final String id) {
    return todoRepository.getTodoItem(id);
  }

  public List<TodoItem> findTodos(final TodoStatus status) {
    return todoRepository.findTodos(status);
  }

  public TodoItem saveTodo(final TodoItem item) {
    return todoRepository.saveTodo(item);
  }

  public TodoItem updateTodos(final UUID id, final UpdateTodo updateRequest) {
    return todoRepository.updateTodo(id, updateRequest);
  }
}
