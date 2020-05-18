package com.feicui.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feicui.mybatisplus.plus.UserMapper.UserMapper;
import com.feicui.mybatisplus.plus.contants.UserEnum;
import com.feicui.mybatisplus.plus.entity.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;

@SpringBootTest
class MybatisPlusApplicationTests {

  @Autowired
  private UserMapper userMapper;

  @Test
  void contextLoads() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "Tom");
    List<User> users = userMapper.selectList(queryWrapper);
    //Assert.assertEquals(5, users.size());

    users.forEach(System.err::println);
    users.forEach(item -> System.err.println(item));
  }

  @SneakyThrows
  @Test
  void test01() {
    Field name = UserEnum.class.getDeclaredField("name");
    System.err.println(name.getName());

  }

}
