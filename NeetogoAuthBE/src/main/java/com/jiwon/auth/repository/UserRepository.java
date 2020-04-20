package com.jiwon.auth.repository;


import com.jiwon.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(String uid);
    User findByNum(long num);

}
