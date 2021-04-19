package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    /**
     * 登录
     * */
    User findByUsername(String username)throws Exception;
}
