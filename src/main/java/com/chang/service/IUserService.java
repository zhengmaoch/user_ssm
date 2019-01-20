package com.chang.service;

import com.chang.domain.User;
import com.chang.exception.UserExistException;

import java.util.List;

public interface IUserService {

    int getTotal();

    void register(User user) throws UserExistException;

    User login(String username, String password);

    List<User> getAllUser();

    List<User> list(int start, int count);

    User getUser(String id);

    void deleteUser(String id);

    void deleteAllUser();

    void updateUser(User user);
}
