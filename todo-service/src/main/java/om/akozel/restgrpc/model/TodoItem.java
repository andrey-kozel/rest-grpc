package om.akozel.restgrpc.model;

import java.time.Instant;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class TodoItem {

  private final UUID id;
  private final String title;
  private final TodoStatus status;
  private final String description;
  private final Instant createdAt;

}
