package org.spring.service;

import org.spring.dao.mapper.UserMapper;
import org.spring.entity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(int id) {
    	return userMapper.get(id);
    }
}
