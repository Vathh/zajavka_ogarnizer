package pl.ogarnizer.api.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer userId;
    private String userName;
    private String password;
    private List<String> roles;
}