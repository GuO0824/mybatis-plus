package com.feicui.mybatisplus.plus.controller;

import com.feicui.mybatisplus.plus.UserMapper.TuserMapper;
import com.feicui.mybatisplus.plus.domain.Tuser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
@Api(value = "mysql的操作", tags = {"mysql的操作"})
public class AddController {


  @Autowired
  TuserMapper tuserMapper;

  @GetMapping("/add")
  @ApiOperation(value = "多线程批量文件的新增")
  public void add() {
    StringBuffer suffix = new StringBuffer();

    // sql前缀
//    String prefix = "INSERT INTO t_user (id,username,password) VALUES ";
    for (int i = 1; i <= 1000; i++) {
      suffix = new StringBuffer();
      List<Tuser> list = new ArrayList<>();
      // 第j次提交步长
      for (int j = 1; j <= 10000; j++) {
        // 构建SQL后缀
//        suffix.append("('" + i * j + "','郭靖'" + ",'123456'" + "')',");
        Tuser tuser = new Tuser();
        tuser.setId(String.valueOf(i * j));
        tuser.setUsername("郭靖");
        tuser.setPassword("123456");
        list.add(tuser);
      }
      Long begin = new Date().getTime();
      int add = tuserMapper.add(list);//数据时间太长   会报错的
      Long end = new Date().getTime();
      System.out.println(list.size() + "条数据插入花费时间 : " + (end - begin) + " s" + "  插入完成");

// 构建完整SQL
//      String sql = prefix + suffix.substring(0, suffix.length() - 1);
      // 添加执行SQL
//      pst.addBatch(sql);
//      // 执行操作
//      pst.executeBatch();
//      // 提交事务
//      conn.commit();
      // 清空上一次添加的数据
      suffix = new StringBuffer();
    }
    /**
     * 开启线程任务
     */
    //开启线程数
//    int nThreads = 5;
//    int size = list.size();
//    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//    List<Future<Integer>> futures = new ArrayList<Future<Integer>>(nThreads);
//    // 开始时间
    Long begin = new Date().getTime();
////    tuserMapper.insertList(list);
//    for (int i = 0; i < nThreads; i++) {
//       List<Tuser> listOne = list.subList(size / nThreads * i, size / nThreads * (i + 1));
//      Callable<Integer> task1 = () -> {
//        tuserMapper.add(listOne);//可多个list
////        tuserMapper.insertList(listOne);
//        System.out.println(listOne);
//        System.out.println("进行插入操作--------------------------");
//        return 10;
//      };
//      futures.add(executorService.submit(task1));
//    }
//    executorService.shutdown();
//    if (!futures.isEmpty() && futures != null) {
//      System.out.println("==================================");
//    }

//    tuserMapper.add(list);  //数据时间太长   会报错的
    // 结束时间
    Long end = new Date().getTime();
//    System.out.println(list.size());
//    System.out.println(list.size()+"条数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
  }

}
