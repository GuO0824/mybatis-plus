package com.feicui.mybatisplus.plus.unit;

import com.feicui.mybatisplus.plus.contants.LogAnnocation;

import java.lang.reflect.Field;

public class TestAnnotation {
  public static <T> void TestFanShe(final T t) {
    Field[] fields = t.getClass().getDeclaredFields();
    Field field = null;
    for(int i =0; i < fields.length ; i ++) {
      try {
        field =  fields[i];
        if(field.isAnnotationPresent(LogAnnocation.class)){
          field.setAccessible(true); // 开启访问权限
          String val = String.valueOf(field.get(t)); // 拿出属性值
          System.out.println("属性名称：" + field.getName() +" ; 值为： " + val);
          System.out.println(field.getType());
          if(field.getType() ==  Long.class) {
            field.set(t,102145148624L); // 给属性重新赋值
//            mat_id.setAccessible(true);//暴力反射
//            mat_id.set(cgMatInfo, 1111L);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
