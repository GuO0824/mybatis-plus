package com.feicui.mybatisplus.plus.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feicui.mybatisplus.plus.entity.CgMatInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CgMatInfoDao extends BaseMapper<CgMatInfo> {
    int insert(CgMatInfo record);

    int insertSelective(CgMatInfo record);
}