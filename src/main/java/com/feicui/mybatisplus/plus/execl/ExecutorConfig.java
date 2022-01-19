package com.feicui.mybatisplus.plus.execl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *    使用多线程的时候，往往需要创建Thread类，或者实现Runnable接口，如果要使用到线程池，我们还需要来创建Executors，
 * 在使用spring中，已经给我们做了很好的支持。只要要@EnableAsync就可以使用多线程
 * 通过spring给我们提供的ThreadPoolTaskExecutor就可以使用线程池。
 * */
//@Configuration 表示该类是一个配置类

@Configuration
@EnableAsync
public class ExecutorConfig {


  private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

  @Value("${async.executor.thread.core_pool_size}")
  private int corePoolSize;
  @Value("${async.executor.thread.max_pool_size}")
  private int maxPoolSize;
  @Value("${async.executor.thread.queue_capacity}")
  private int queueCapacity;
  @Value("${async.executor.thread.name.prefix}")
  private String namePrefix;

  @Bean(name = "asyncServiceExecutor")
  public Executor asyncServiceExecutor() {
    logger.info("start asyncServiceExecutor");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //配置核心线程数0
    executor.setCorePoolSize(corePoolSize);
    //配置最大线程数
    executor.setMaxPoolSize(maxPoolSize);
    //配置队列大小
    executor.setQueueCapacity(queueCapacity);
    //配置线程池中的线程的名称前缀
    executor.setThreadNamePrefix(namePrefix);

    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //执行初始化
    executor.initialize();
    return executor;
  }


}
