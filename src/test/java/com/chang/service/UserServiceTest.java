package com.chang.service;

import com.chang.domain.User;
import com.chang.exception.UserExistException;
import org.junit.Test;
import java.util.Date;
import java.util.UUID;

public class UserServiceTest {

//    @Test
//    public void testRegister(){
//        String userid = UUID.randomUUID().toString().replace("-","");
//        User user = new User();
//        user.setId(userid);
//        user.setUsername("zhangsan");
//        user.setPassword("123456");
//        user.setNickname("张三");
//        user.setEmail("zhangsan@qq.com");
//        user.setBirthday(new Date());
//        user.setCreatedtime(new Date());
//
//        UserService service = new UserService();
//
//        try {
//            service.register(user);
//            System.out.println("注册成功！");
//        } catch (UserExistException e) {
//            System.out.println("用户已存在！");
//        }
//    }
//
//    @Test
//    public void testLogin(){
//        UserService service = new UserService();
//        User user = service.login("zhangsan","123456");
//        System.out.println(user);
//    }
}
