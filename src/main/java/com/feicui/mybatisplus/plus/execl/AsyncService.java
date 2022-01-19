package com.feicui.mybatisplus.plus.execl;

public interface AsyncService {
  /** * 执行异步任务 * 可以根据需求，自己加参数拟定，我这里就做个测试演示 */
  Integer executeAsync();

  Integer executeAsync2() throws InterruptedException;
  Integer executeAsync3() throws InterruptedException;
}
