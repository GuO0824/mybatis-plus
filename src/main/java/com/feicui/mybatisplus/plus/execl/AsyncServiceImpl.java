package com.feicui.mybatisplus.plus.execl;

import com.feicui.mybatisplus.plus.UserMapper.TuserMapper;
import com.feicui.mybatisplus.plus.domain.Tuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class AsyncServiceImpl implements AsyncService {

  private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

  @Autowired
  TuserMapper tuserMapper;

  @Override
//  @Async("asyncServiceExecutor")
  public Integer executeAsync() {


    List<Tuser> list = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) {
      // 第j次提交步长
      for (int j = 1; j <= 1000; j++) {
        Tuser tuser = new Tuser();
        tuser.setId(UUID.randomUUID().toString());
        tuser.setUsername("郭靖");
        tuser.setPassword("123456");
        list.add(tuser);
      }
    }

    int nThreads = 50;
    int size = list.size();
    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    List<Future<Integer>> futures = new ArrayList<Future<Integer>>(nThreads);
    Long begin = new Date().getTime();
    for (int i = 0; i < nThreads; i++) {
      //list集合分批切割
      final List<Tuser> EnrollStudentEntityImputList = list.subList(size / nThreads * i, size / nThreads * (i + 1));
      Thread task1 = new Thread(new Runnable() {
        @Override
        public void run() {
          tuserMapper.add(EnrollStudentEntityImputList);
          logger.info("===========-------------------==========================");
          System.out.println("这是一个测试");
        }
      });

//      Future<Integer> submit = executorService.execute(task1);
      executorService.execute(task1);
//      futures.add(submit);
    }
    executorService.shutdown();
//    if (!futures.isEmpty() && futures != null) {
//      System.out.println("11111111==============1111111111111");
//      return 10;
//    }

//    tuserMapper.add(list);//可多个list
    Long end = new Date().getTime();

    logger.info("start executeAsync");

    System.out.println("异步线程要做的事情");
    System.out.println("可以在这里执行批量插入等耗时的事情");

    logger.info("end executeAsync");
    System.out.println(list.size() + "条数据插入花费时间 : " + (end - begin) + " s" + "  插入完成");
//    System.out.println((end - begin) / 1000 + " s" + "  插入完成");
    return -10;
  }

  @Override
  public Integer executeAsync2() throws InterruptedException {
    List<Tuser> list = new ArrayList<>();
    for (int i = 1; i <= 1; i++) {
      // 第j次提交步长
      for (int j = 1; j <= 5; j++) {
        Tuser tuser = new Tuser();
        tuser.setId(UUID.randomUUID().toString());
        tuser.setUsername("郭靖");
        tuser.setPassword("123456");
        list.add(tuser);
      }
    }

    int nThreads = 5;
    int size = list.size();
    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    List<Future<Integer>> futures = new ArrayList<Future<Integer>>(nThreads);
    Long begin = new Date().getTime();
    for (int i = 0; i < nThreads; i++) {
      //list集合分批切割
      final List<Tuser> EnrollStudentEntityImputList = list.subList(size / nThreads * i, size / nThreads * (i + 1));

      Callable<Integer> task1 = () -> {
        tuserMapper.add(EnrollStudentEntityImputList);
        logger.info("===========-------------------==========================");
        System.out.println("这是一个测试");
        return 1;
      };
      futures.add(executorService.submit(task1));
//      Future<Integer> integerFuture = futures.get(2);

    }
    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES); // 或者更长时间（这行代码是关键）
    Long end = new Date().getTime();

//    if (!futures.isEmpty() && futures != null) {
//      System.out.println("11111111==============1111111111111");
//      return 10;
//    }
    System.out.println("线程耗时=====" + (end - begin));
    return -10;
  }

  /**
   * https://www.cnblogs.com/hsug/p/13303018.html
   * 这只是这个方法是异步的
   * @return
   * @throws InterruptedException
   */
  @Override
  @Async("asyncServiceExecutor")
  public Integer executeAsync3() throws InterruptedException {
//    List<Tuser> list = new ArrayList<>();
//    for (int i = 1; i <= 1000; i++) {
//      // 第j次提交步长
//      for (int j = 1; j <= 1000; j++) {
//        Tuser tuser = new Tuser();
//        tuser.setId(UUID.randomUUID().toString());
//        tuser.setUsername("郭靖");
//        tuser.setPassword("123456");
//        list.add(tuser);
//      }
//    }
//    int nThreads = 50;
//    int size = list.size();
//    for (int i = 0; i < nThreads; i++) {
//      //list集合分批切割
//      final List<Tuser> EnrollStudentEntityImputList = list.subList(size / nThreads * i, size / nThreads * (i + 1));
//      tuserMapper.add(EnrollStudentEntityImputList);
//      Thread.sleep(2000); // 模拟耗时
//      System.out.println("插入成功");
//      logger.info("----------------------插入成功-------------------------");
//      logger.info("--------start-service2------------");
//      Thread.sleep(2000); // 模拟耗时
//      logger.info("--------end-service2------------");
//    }
    return -10;
  }
}
