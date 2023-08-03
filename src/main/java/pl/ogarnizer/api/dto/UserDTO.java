package pl.ogarnizer.api.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Integer userId;
    String userName;
    String login;
    String password;
    List<String> roles;
}