package om.akozel.restgrpc.grpc;

import java.util.List;
import java.util.UUID;

import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import om.akozel.restgrpc.mapper.grpc.GrpcTodoMapper;
import om.akozel.restgrpc.model.TodoItem;
import om.akozel.restgrpc.model.TodoStatus;
import om.akozel.restgrpc.model.UpdateTodo;
import om.akozel.restgrpc.service.TodoService;
import todo.CreateItemRequest;
import todo.GetItemRequest;
import todo.ItemResult;
import todo.SearchRequest;
import todo.SearchResult;
import todo.TodoItemDto;
import todo.TodoServiceGrpc.TodoServiceImplBase;
import todo.UpdateItemRequest;

@Singleton
public class TodoEndpoint extends TodoServiceImplBase {

  private final TodoService todoService;
  private final GrpcTodoMapper grpcTodoMapper;

  @Inject
  public TodoEndpoint(
      final TodoService todoService,
      final GrpcTodoMapper grpcTodoMapper
  ) {
    this.todoService = todoService;
    this.grpcTodoMapper = grpcTodoMapper;
  }

  @Override
  public void findAll(final SearchRequest request, final StreamObserver<SearchResult> responseObserver) {
    final TodoStatus status = grpcTodoMapper.convert(request.getStatus());
    final List<TodoItem> todos = todoService.findTodos(status);
    final List<TodoItemDto> dtos = grpcTodoMapper.convert(todos);
    final SearchResult result = SearchResult.newBuilder()
        .addAllResult(dtos)
        .build();

    responseObserver.onNext(result);
    responseObserver.onCompleted();
  }

  @Override
  public void getTodoItem(final GetItemRequest request, final StreamObserver<ItemResult> responseObserver) {
    final UUID id = grpcTodoMapper.convert(request.getId());
    final TodoItem todoItem = todoService.getTodoItem(id);
    final TodoItemDto dto = grpcTodoMapper.convert(todoItem);
    final ItemResult result = ItemResult.newBuilder()
        .setItem(dto)
        .build();

    responseObserver.onNext(result);
    responseObserver.onCompleted();
  }

  @Override
  public void createTodo(CreateItemRequest request, StreamObserver<ItemResult> responseObserver) {
    final TodoItem item = grpcTodoMapper.convert(request.getItem());
    final TodoItem todoItem = todoService.saveTodo(item);
    final TodoItemDto dto = grpcTodoMapper.convert(todoItem);
    final ItemResult result = ItemResult.newBuilder()
        .setItem(dto)
        .build();

    responseObserver.onNext(result);
    responseObserver.onCompleted();
  }

  @Override
  public void updateTodo(UpdateItemRequest request, StreamObserver<ItemResult> responseObserver) {
    final UpdateTodo update = grpcTodoMapper.convert(request.getItem());
    final UUID id = grpcTodoMapper.convert(request.getId());
    final TodoItem todoItem = todoService.updateTodos(id, update);
    final TodoItemDto dto = grpcTodoMapper.convert(todoItem);
    final ItemResult result = ItemResult.newBuilder()
        .setItem(dto)
        .build();

    responseObserver.onNext(result);
    responseObserver.onCompleted();
  }
}
