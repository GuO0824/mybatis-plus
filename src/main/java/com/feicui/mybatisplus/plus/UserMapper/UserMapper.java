package com.feicui.mybatisplus.plus.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feicui.mybatisplus.plus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @Author: guojing
 * @Date: ${DATA}
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
