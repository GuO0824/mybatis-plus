package com.feicui.mybatisplus.plus.contants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComAnnocation {
  /**
   * 字段名
   *
   * @return
   */
  String descString() default "";

  /**
   * 字段序号
   */
  int index();

}
