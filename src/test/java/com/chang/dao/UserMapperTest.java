package com.chang.dao;

import com.chang.domain.User;
import com.chang.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class UserMapperTest {

    @Test
    public void testInsert(){

        String userid = UUID.randomUUID().toString().replace("-","");
        User user = new User();
        user.setId(userid);
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setNickname("张三");
        user.setEmail("zhangsan@qq.com");
        user.setBirthday(new Date());
        user.setCreatedtime(new Date());

        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            UserMapper dao = sqlSession.getMapper(UserMapper.class);
            int result = dao.insert(user);
            User user1 = dao.selectByPrimaryKey(user.getId());
            sqlSession.commit();
            Assert.assertTrue(user1.getId().equals(user.getId()));
        } catch (Exception e){
            System.out.println(e);
            sqlSession.rollback();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

//    @Test
//    public void testUpdate(){
//        User user = new User();
//        user.setUsername("zhangsan");
//        user.setPassword("123456");
//        user.setNikename("张三");
//        user.setEmail("zhangsan@qq.com");
//        user.setBirthday(new Date());
//        user.setCreatedTime(new Date());
//
//        UserDaoImpl dao = new UserDaoImpl();
//        dao.add(user);
//
//        User user1 = dao.find("zhangsan");
//        user1.setUsername("lisi");
//        dao.update(user1);
//
//        User user2 = dao.find("lisi");
//        Assert.assertTrue(user2 != null);
//        dao.delete(user2);
//    }
//
//    @Test
//    public void testFind(){
//
//        User user = new User();
//        user.setUsername("zhangsan");
//        user.setPassword("123456");
//        user.setNikename("张三");
//        user.setEmail("zhangsan@qq.com");
//        user.setBirthday(new Date());
//        user.setCreatedTime(new Date());
//
//        UserDaoImpl dao = new UserDaoImpl();
//        dao.add(user);
//
//        User user1 = dao.find("xxx");
//        Assert.assertTrue(user1 == null);
//
//        User user2 = dao.find("zhangsan");
//        Assert.assertTrue(user2 != null);
//
//        User user3 = dao.find("zhangsan","123456");
//        Assert.assertTrue(user3 != null);
//
//        dao.delete(user3);
//
//    }
//
//    @Test
//    public void testDelete(){
//
//        User user = new User();
//        user.setUsername("zhangsan");
//        user.setPassword("123456");
//        user.setNikename("张三");
//        user.setEmail("zhangsan@qq.com");
//        user.setBirthday(new Date());
//        user.setCreatedTime(new Date());
//
//        UserDaoImpl dao = new UserDaoImpl();
//        dao.add(user);
//
//        User user1 = dao.find("zhangsan");
//        dao.delete(user1);
//        User user2 = dao.find("zhangsan");
//        Assert.assertTrue(user2 == null);
//    }
}
