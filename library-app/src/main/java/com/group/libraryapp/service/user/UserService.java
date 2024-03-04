package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

// 현재 유저가 있는지, 없는지 등을 확인하고 예외 처리를 해준다.
public class UserService {

    private final UserRepository userRepository;


    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUser() {
        return userRepository.getUsers();
    }


    public void updateUser(UserUpdateRequest request) {

        if(userRepository.isUserNotExist(request.getId())){
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());

    }

    public void deleteUser(String name) {

        if(userRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

        userRepository.deleteUser(name);
    }



}
