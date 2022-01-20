package com.feicui.mybatisplus.plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feicui.mybatisplus.Conv;
import com.feicui.mybatisplus.plus.UserMapper.CgMatInfoDao;
import com.feicui.mybatisplus.plus.UserMapper.TuserMapper;
import com.feicui.mybatisplus.plus.contants.LogAnnocation;
import com.feicui.mybatisplus.plus.contants.UserEnum;
import com.feicui.mybatisplus.plus.domain.Tuser;
import com.feicui.mybatisplus.plus.entity.CgMatInfo;
import com.feicui.mybatisplus.plus.entity.User;
import com.feicui.mybatisplus.plus.service.UserService;
import com.feicui.mybatisplus.plus.unit.TestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
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

  @Autowired
  private CgMatInfoDao cgMatInfoDao;

  @Autowired
  TuserMapper tuserMapper;


  @GetMapping("/mybatis")
  @ApiOperation(value = "查询user表的基本信息")
  @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "姓名", required = true, defaultValue = "swagger描述")
  })
  public void show() {
    List<User> show = userService.show();
    show.forEach(System.err::println);
    Conv.initGetMethodName(User.class);
  }

  @GetMapping("/select")
  @ApiOperation(value = "物资表信息查询")
  public List<CgMatInfo> get(@ApiParam(value = "物资id") @RequestParam("matId") Integer matId,
                             @ApiParam(value = "name") @RequestParam(value = "name", required = false) Integer name) throws NoSuchFieldException, IllegalAccessException {
    List<CgMatInfo> mats = cgMatInfoDao.selectList(new QueryWrapper<CgMatInfo>().eq("mat_id", matId));
    System.out.println(mats + "-------------------------" + name);

    CgMatInfo cgMatInfo = new CgMatInfo();
//    Field mat_id = cgMatInfo.getClass().getDeclaredField("matId");
//    mat_id.setAccessible(true);//暴力反射
//    mat_id.set(cgMatInfo,1111L);
//    System.out.println(cgMatInfo);
    TestAnnotation.TestFanShe(cgMatInfo);
    System.out.println(cgMatInfo);
    cgMatInfoDao.insertSelective(cgMatInfo);
    return mats;
  }

  @GetMapping("/getUser")
  @ApiOperation(value = "tUser表的查询")
  public List<Tuser> getUser() {
    return tuserMapper.selectList(null);
  }

}
