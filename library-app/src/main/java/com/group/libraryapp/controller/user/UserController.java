package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    // 19강
    // 1. stacic이 아닌 코드를 사용하려면 인스턴스화가 필요하다.
    // -> 인스턴스화를 하기 위해서는 생성자를 호출하게 된다.
    // 2. UserController는 JdbcTemplete이 필요하고, JdbcTemplate에 의존하고 있다.
    // -> JdbcTemplate가 없다면, 동작하지 않는다.
    // 3. JdbcTemplate의 클래스를 설정해준 적은 없고, Spring Bean에 등록되어 있다.
    public UserController(JdbcTemplate jdbcTemplate) {
        this.userService = new UserService(jdbcTemplate);
    }

    @PostMapping("/user") // POST /USER
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user") // POST /USER
    public List<UserResponse> getUsers() {
        return userService.getUser();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }


}
