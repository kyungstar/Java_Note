package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    /***
     UserResponse 생성자에 new 키워드를 사용하는 이유는 객체 지향 프로그래밍(OOP)의 핵심 개념인 생성자와 객체 생성에 대한 이해가 필요합니다.

     1. 생성자란 무엇인가?
     - 클래스의 특수한 메서드입니다.
     - 객체를 생성할 때 사용됩니다.
     - 객체의 초기 속성 값을 설정하는 역할을 합니다.
     */
    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }
}
