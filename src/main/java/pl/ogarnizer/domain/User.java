package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@Builder
@With
@ToString(of = {"userName"})
public class User {

    Integer userId;
    String userName;
    String password;
    List<Role> roles;
    Boolean active;
}
