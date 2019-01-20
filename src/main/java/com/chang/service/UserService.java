package com.chang.service;


import com.chang.dao.UserDao;
import com.chang.domain.User;
import com.chang.exception.UserExistException;
import com.chang.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public int getTotal() {
        return userDao.getTotal();
    }

    @Override
    public void register(User user) throws UserExistException {
        boolean b = userDao.getByUsername(user.getUsername()) == null;
        if(b){
            user.setPassword(MD5Utils.md5(user.getPassword()));
            userDao.add(user);
        }else{
            throw new UserExistException();
        }
    }

    @Override
    public User login(String username, String password){
        password = MD5Utils.md5(password);
        return userDao.getByUsernameAndPassword(username,password);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(String id) {
        return userDao.getById(id);
    }

    @Override
    public void deleteUser(String id) {

        userDao.delete(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteAllUser() {
        userDao.deleteAll();
    }

    public List<User> list(int start, int count) {
        return userDao.list(start, count);
    }
}
