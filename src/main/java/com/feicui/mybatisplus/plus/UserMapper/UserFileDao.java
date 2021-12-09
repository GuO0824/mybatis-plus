package com.feicui.mybatisplus.plus.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feicui.mybatisplus.plus.domain.User;
import com.feicui.mybatisplus.plus.domain.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserFileDao extends BaseMapper<UserFile> {

  //根据Id查询该用户的所有文件
  List<UserFile> findByUserId();
}
