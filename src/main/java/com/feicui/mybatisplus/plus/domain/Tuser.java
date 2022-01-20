package com.feicui.mybatisplus.plus.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class Tuser {

  private String id;

  private String username;

  private String password;

}
