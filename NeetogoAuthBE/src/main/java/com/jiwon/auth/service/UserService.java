package com.jiwon.auth.service;

import com.jiwon.auth.entity.User;
import com.jiwon.auth.repository.UserRepository;
import com.jiwon.auth.response.DefaultRes;
import com.jiwon.auth.response.ResponseMessage;
import com.jiwon.auth.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User signup(final User account) {
        User savingUser = new User(account.getUid(), account.getPassword(), account.getEmail(), account.getPhone(), account.getAuthority());
        return userRepository.save(savingUser);
    }

    public boolean isDuplicatedId(final String uid) {
        if (getUserByUid(uid) == null) return false;
        return true;
    }

    public User getUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    @Transactional
    public DefaultRes signin(String uid, String password) {
        User user = userRepository.findByUid(uid);
        if (user == null)
            return DefaultRes.res(StatusCode.OK, ResponseMessage.INVALID_UID);

        if (!user.getPassword().equals(password))
            return DefaultRes.res(StatusCode.OK, ResponseMessage.INVALID_PASSWORD);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.SUCCESSFUL_LOGIN);
    }
}
