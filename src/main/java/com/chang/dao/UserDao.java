package com.chang.dao;

import com.chang.domain.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getAllUser();

    User find(String username, String password);

    User find(String username);

    User getUser(String id);

    void update(User user);

    void delete(String id);

    void delete(User user);
}
