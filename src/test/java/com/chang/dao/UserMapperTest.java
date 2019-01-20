package com.chang.dao;

import com.chang.domain.User;
import com.chang.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserMapperTest {

    @Test
    public void testAdd(){

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
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.add(user);
            user.setUsername("lisi");
            mapper.update(user);
            User user1 = mapper.getByUsername("lisi").get(0);
            mapper.delete(user1.getId());
            sqlSession.commit();
            Assert.assertTrue(user1.getId().equals(userid));
        } catch (Exception e){
            System.out.println(e);
            sqlSession.rollback();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
