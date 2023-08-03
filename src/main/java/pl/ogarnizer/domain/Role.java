package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString(of = "role")
public class Role {

    Integer roleId;
    String role;
}
