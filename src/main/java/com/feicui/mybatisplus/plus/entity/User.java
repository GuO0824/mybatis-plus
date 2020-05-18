package com.feicui.mybatisplus.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.feicui.mybatisplus.plus.contants.ComAnnocation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: guojing
 * @Date: ${DATA}
 */
@Data
@ApiModel(value = "用户对象模型")
public class User implements Serializable {

  @ComAnnocation(descString = "主键id", index = 1)
  @ApiModelProperty(value = "主键ID")
  @TableId(value = "id", type = IdType.NONE)
  private Long id;

  @ComAnnocation(descString = "姓名", index = 2)
  @ApiModelProperty(value = "姓名")
  @TableField("name")
  private String name;

  @ComAnnocation(descString = "年龄", index = 3)
  @ApiModelProperty(value = "年龄")
  @TableField("age")
  private Integer age;

  @ComAnnocation(descString = "email", index = 4)
  @ApiModelProperty(value = "email")
  @TableField("email")
  private String email;

}