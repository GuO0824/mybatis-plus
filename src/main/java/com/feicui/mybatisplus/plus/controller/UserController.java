package com.feicui.mybatisplus.plus.controller;

import com.feicui.mybatisplus.Conv;
import com.feicui.mybatisplus.plus.contants.UserEnum;
import com.feicui.mybatisplus.plus.entity.User;
import com.feicui.mybatisplus.plus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guojing
 * @Date: ${DATA}
 */
@RestController
@Api(value = "mybatis-plus 检测swagger的功能", tags = {"信息"})
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/mybatis")
  @ApiOperation(value = "查询user表的基本信息")
  @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "姓名", required = true, defaultValue = "swagger描述")
  })
  public void show() {
    List<User> show = userService.show();
    show.forEach(System.err::println);
    Conv.initGetMethodName(User.class);
  }



}
