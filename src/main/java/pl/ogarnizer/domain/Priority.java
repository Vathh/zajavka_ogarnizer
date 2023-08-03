package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString(of = "name")
public class Priority {

    Integer priorityId;
    String name;
}
