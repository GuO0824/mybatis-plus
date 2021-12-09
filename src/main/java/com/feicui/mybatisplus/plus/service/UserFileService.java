package com.feicui.mybatisplus.plus.service;

import com.feicui.mybatisplus.plus.UserMapper.UserFileDao;
import com.feicui.mybatisplus.plus.domain.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserFileService {

  @Autowired
  UserFileDao userFileDao;

  public Integer save(UserFile userFile){
   return userFileDao.insert(userFile);
  }

  public List<UserFile> findByUserId() {
    return userFileDao.findByUserId();
  }



}
