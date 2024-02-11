package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; // Integer > null을 표현할 수 있다.

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
