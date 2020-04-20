package com.jiwon.auth.controller;

import com.jiwon.auth.entity.User;
import com.jiwon.auth.jwt.JwtService;
import com.jiwon.auth.response.DefaultRes;
import com.jiwon.auth.response.ResponseMessage;
import com.jiwon.auth.response.StatusCode;
import com.jiwon.auth.service.UserService;
import com.jiwon.auth.dto.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 일반 계정 관련 기능을 위한 controller
 * 로그인
 * 로그아웃
 * 회원가입
 */

@Slf4j
@RestController
public class UserController {

    final JwtService jwtService;
    final UserService userService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/join")
    public ResponseEntity joinUser(@RequestBody UserForm userForm) {
        try {
            if (userService.isDuplicatedId(userForm.getUid())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ALREADY_EXISTED_USER), HttpStatus.OK);
            }
            User newUser = userService.signup(userForm.toUser());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_NEW_USER), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserForm userForm) {
        try {
            return new ResponseEntity(userService.signin(userForm.getUid(), userForm.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new DefaultRes(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
