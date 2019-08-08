package org.spring.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.spring.entity.model.User;

@Mapper
public interface UserMapper {
    User get(int uid);
}
