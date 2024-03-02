package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    // JdbcTemplate은 스프링 프레임워크에서 제공하는 JDBC(Java Database Connectivity)의 편리한 기능을 활용하는데 사용됩니다.
    // JdbcTemplate은 JDBC의 일반적인 작업을 보다 간결하고 간편하게 수행할 수 있도록 도와줍니다.
    // final 키워드로 선언된 JdbcTemplate 필드는 한 번 초기화되면 값을 변경할 수 없습니다.
    private final JdbcTemplate jdbcTemplate;

    // UserController 클래스의 생성자입니다. 생성자를 통해 JdbcTemplate을 주입받아 필드에 할당합니다.
    // 이를 통해 의존성 주입(Dependency Injection)을 사용하여 클래스 간의 결합도를 낮출 수 있습니다.
    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostMapping("/user") // POST /USER
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user (name, age) VALUES (?,?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
        // jdbcTemplate.update > INSERT, UPDATE, DELETE 모두 가능
    }

    @GetMapping("/user") // POST /USER
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            /***
             * mapRow 메서드 내에서 새로운 인스턴스를 만드는 이유는 다음과 같습니다.
             *
             * 1. 데이터베이스 결과를 API 응답에 매핑
             * - mapRow 메서드는 데이터베이스 결과 세트의 각 행을 API 응답에 적합한 형식으로 변환하는 역할을 합니다.
             * - ResultSet에서 필요한 사용자 데이터 (id, name, age)를 추출합니다.
             * - 이 데이터를 보관하기 위해 새로운 UserResponse 객체를 생성하여 응답 구조의 일관성과 제어를 유지합니다.
             * 2. 관심사 분리
             * - API 응답 구조와 내부 도메인 모델을 분리하면 다음과 같은 장점이 있습니다.
             * - 유지보수성: 내부 모델의 변경이 API 응답에 직접적인 영향을 미치지 않으며, 반대도 마찬가지입니다.
             * - 유연성: 기본 데이터 모델을 공유하더라도 서로 다른 API 엔드포인트에 대해 각기 다른 DTO를 사용할 수 있습니다.
             */
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id =rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                /***
                 객체 생성 과정:
                 1. new 키워드를 사용하여 생성자를 호출합니다.
                 2. 생성자는 새로운 객체를 메모리에 할당합니다.
                 3. 생성자는 객체의 속성 값을 초기화합니다.
                 4. 초기화된 객체는 코드에서 사용될 수 있습니다.

                 new UserResponse 사용 이유
                 1. UserResponse 생성자를 호출하여 새로운 UserResponse 객체를 메모리에 생성합니다.
                 2. 생성자는 id, name, age 매개변수를 사용하여 객체의 속성 값을 설정합니다.
                 3. 생성된 UserResponse 객체는 return 문을 통해 메서드 호출자에게 반환됩니다.


                 new 키워드의 중요성:
                 1.new 키워드는 객체 생성 과정을 시작하는 필수적인 요소입니다.
                 2. new 키워드 없이는 객체를 생성할 수 없습니다.
                 */
                return new UserResponse(id, name, age);
            }
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        String readlSql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readlSql, (rs, rowNum) -> 0, request.getId()).isEmpty();

        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String readlSql = "SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readlSql, (rs, rowNum) -> 0, name).isEmpty();

        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }


}
