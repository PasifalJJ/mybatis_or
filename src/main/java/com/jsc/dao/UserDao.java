package com.jsc.dao;

import com.jsc.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    List<User> findAll();
}
