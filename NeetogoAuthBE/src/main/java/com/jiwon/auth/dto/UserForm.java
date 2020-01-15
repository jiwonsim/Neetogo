package com.jiwon.auth.dto;

import com.jiwon.auth.entity.User;
import lombok.Data;

@Data
public class UserForm extends AccountForm {
    private String uid;
    private String password;
    private String email;
    private String phone;

    public User toUser() {
        return User.builder()
                .uid(uid)
                .password(password)
                .email(email)
                .phone(phone)
                .authority("ROLE_USER")
                .build();
    }
}
