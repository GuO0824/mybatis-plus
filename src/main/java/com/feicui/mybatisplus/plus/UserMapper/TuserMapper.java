package com.feicui.mybatisplus.plus.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feicui.mybatisplus.plus.domain.Tuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Mapper
@Repository
public interface TuserMapper extends BaseMapper<Tuser> {

  int add(@Param("list") List<Tuser> list);
}
