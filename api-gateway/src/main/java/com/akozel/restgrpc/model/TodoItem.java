package com.akozel.restgrpc.model;

import java.time.Instant;
import java.util.UUID;

import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
@Introspected
public class TodoItem {

  private final UUID id;
  private final String title;
  private final TodoStatus status;
  private final String description;
  private final Instant createdAt;

}
