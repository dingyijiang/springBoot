package com.spring.parent.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spring.parent.entity.model.User;

@Mapper
public interface UserMapper {
    User get(int uid);
}
