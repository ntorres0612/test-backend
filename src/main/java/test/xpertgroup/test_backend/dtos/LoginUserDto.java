package test.xpertgroup.test_backend.dtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;

}