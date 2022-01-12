package om.akozel.restgrpc.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import jakarta.inject.Singleton;
import om.akozel.restgrpc.model.TodoItem;
import om.akozel.restgrpc.model.TodoStatus;
import om.akozel.restgrpc.model.UpdateTodo;

@Singleton
public class InmemoryTodoRepository implements TodoRepository {

  private static final Map<UUID, TodoItem> itemsStorage = new ConcurrentHashMap<>();

  @Override
  public TodoItem getTodoItem(final UUID id) {
    return itemsStorage.get(id);
  }

  @Override
  public List<TodoItem> findTodos(final TodoStatus status) {
    return itemsStorage.values()
        .stream()
        .filter(item -> item.getStatus().equals(status))
        .collect(Collectors.toList());
  }

  @Override
  public TodoItem saveTodo(TodoItem item) {
    final TodoItem newItem = TodoItem.builder()
        .createdAt(item.getCreatedAt())
        .description(item.getDescription())
        .id(UUID.randomUUID())
        .status(item.getStatus())
        .title(item.getTitle())
        .build();
    itemsStorage.put(newItem.getId(), newItem);
    return newItem;
  }

  @Override
  public TodoItem updateTodo(UUID id, UpdateTodo updateRequest) {
    final TodoItem item = itemsStorage.get(id);
    final TodoItem newItem = TodoItem.builder()
        .id(id)
        .createdAt(item.getCreatedAt())
        .description(updateRequest.getDescription())
        .status(updateRequest.getStatus())
        .title(updateRequest.getTitle())
        .build();
    itemsStorage.put(id, newItem);
    return newItem;
  }
}
