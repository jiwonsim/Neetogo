package com.jiwon.auth.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@DiscriminatorValue("user")
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends Account {

    private String uid;
    private String password;

    private String email;
    private String phone;

    @Builder
    public User(String uid, String password, String email, String phone, String authority) {
        super(authority);
        this.uid = uid;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
