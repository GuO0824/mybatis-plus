package com.feicui.mybatisplus;

import com.feicui.mybatisplus.plus.contants.ComAnnocation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 工具类
 */
public class Conv {


  public static List<LinkedHashMap<String, Object>> initGetMethodName(Class clazz) {
    //用于存储字段和中文值的集合
    LinkedHashMap<String, Object> fieldList[] = new LinkedHashMap[100];

    //获取所有的fields
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      //用于存储实体类字段(key)和中文名(value)
      LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();

      if (field.isAnnotationPresent(ComAnnocation.class)) {
        //获取字段名
        String name = field.getName();
        //获取字段注解
        ComAnnocation annotation = field.getAnnotation(ComAnnocation.class);
        //判断是否已经获取过该code的字典数据 避免重复获取
        if (linkedHashMap.get("name") == null) {
          linkedHashMap.put("name", name);
          linkedHashMap.put(name, annotation.descString());
          fieldList[annotation.index()] = linkedHashMap;
        }
        System.err.println();

        System.err.println(fieldList);
      }
    }

    return new ArrayList<>();
  }

}
