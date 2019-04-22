package com.jsc.test;

import com.jsc.dao.UserDao;
import com.jsc.domain.User;
import mybatis_orign.*;
import org.junit.Test;

import java.util.List;

public class MybatisTest {
    @Test
    public void SqlSessionTest() {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilderImpl().builder(Resources.getResourceAsFile("SqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSqlSession();
        UserDao mapper = sqlSession.getMapper(new UserDao(){
            @Override
            public List<User> findAll() {
                return null;
            }
        }.getClass());
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void SqlSessionTest1() {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilderImpl().builder(Resources.getResourceAsFile("SqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }


}
