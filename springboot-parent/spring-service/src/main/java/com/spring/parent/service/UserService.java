package com.spring.parent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.parent.dao.mapper.UserMapper;
import com.spring.parent.entity.model.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(int id) {
    	return userMapper.get(id);
    }
}
