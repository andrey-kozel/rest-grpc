package com.akozel.restgrpc.factory;

import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import jakarta.inject.Singleton;
import todo.TodoServiceGrpc;
import todo.TodoServiceGrpc.TodoServiceBlockingStub;

@Factory
public class GrpcClientFactory {

  @Singleton
  public TodoServiceBlockingStub grpcTodoService(
      @GrpcChannel("${internal.urls.grpc-todo-service}") final ManagedChannel managedChannel
  ) {
    return TodoServiceGrpc.newBlockingStub(managedChannel);
  }

}
