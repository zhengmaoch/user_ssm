package com.chang.dao;

import com.chang.domain.User;

import java.util.List;

public interface UserDao {

    int getTotal();

    int delete(String id);

    int deleteAll();

    int add(User record);

    User getById(String id);

    User getByUsernameAndPassword(String username, String password);

    User getByUsername(String username);

    List<User> getAllUsers();

    int update(User record);

    List<User> list(int start, int count);
}