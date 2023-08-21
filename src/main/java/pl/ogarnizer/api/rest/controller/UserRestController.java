package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.api.dto.mapper.UserMapper;
import pl.ogarnizer.api.rest.dto.UsersDTO;
import pl.ogarnizer.business.UserService;

@RestController
@AllArgsConstructor
@RequestMapping(UserRestController.API_USER)
public class UserRestController {

    public static final String API_USER = "/api/user";
    public static final String API_USER_ID = "/{userId}";

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public UsersDTO getUsers(){
        return UsersDTO.builder()
                .users(userService.findUsers().stream()
                        .map(userMapper::map).toList())
                .build();
    }

    @PostMapping
    public UsersDTO addUser(
            @Valid @RequestBody UserDTO userDTO
    ) {
        userService.addUser(userDTO);
        return UsersDTO.builder()
                .users(userService.findUsers().stream()
                        .map(userMapper::map).toList())
                .build();
    }

    @DeleteMapping(API_USER_ID)
    public ResponseEntity<?> deleteUser(
            @PathVariable Integer userId
    ){
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
