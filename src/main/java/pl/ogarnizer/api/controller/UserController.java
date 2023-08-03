package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.api.dto.mapper.RoleMapper;
import pl.ogarnizer.api.dto.mapper.UserMapper;
import pl.ogarnizer.business.RoleService;
import pl.ogarnizer.business.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    static final String USER = "/user";
    static final String ADD_USER = "/user/add";
    static final String DELETE_USER = "/user/delete/{userId}";
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @GetMapping(value = USER)
    public String userPage(Model model){
        var userDTOs = userService.findUsers().stream().map(userMapper::map);
        var roleDTOs = roleService.findRoles().stream().map(roleMapper::map);
        var userDTO = new UserDTO();

        model.addAttribute("userDTOs", userDTOs);
        model.addAttribute("roleDTOs", roleDTOs);
        model.addAttribute("userDTO", userDTO);

        return "user";
    }

    @PostMapping(value = ADD_USER)
    public String addUser(
            @Valid @ModelAttribute("userDTO") UserDTO userDTO,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "user";
        }

        userService.addUser(userDTO);

        return "redirect:/user";
    }

    @DeleteMapping(value = DELETE_USER)
    public String deleteUser(
            @PathVariable("userId") Integer userId
    ){
        userService.deleteUser(userId);
        return "redirect:/user";
    }
}
