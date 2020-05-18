package com.feicui.mybatisplus.plus.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.feicui.mybatisplus.plus.contants.LogAnnocation;
import lombok.Data;

/**
 * cg_mat_info
 * @author 
 */
@Data
public class CgMatInfo implements Serializable {
    /**
     * 物资ID
     */
    @LogAnnocation(desc = "郭靖")
    private Long matId;

    /**
     * 物资条码
     */
    private String matBar;

    /**
     * 物资名称
     */
    private String matName;

    /**
     * 图/型号
     */
    private String matCode;

    /**
     * 物资属性编码
     */
    private String matAttrCode;

    /**
     * 物资分类 1器材、2物资
     */
    private Integer matType;

    /**
     * 物资专业类别
     */
    private Long matcType;

    /**
     * 物资单位
     */
    private String unit;

    /**
     * 计划价(元)
     */
    private BigDecimal planPrice;

    /**
     * 执行开始日期
     */
    private Date startDate;

    /**
     * 新增人
     */
    private String userCreate;

    /**
     * 新增时间
     */
    private Date timeCreate;

    /**
     * 修改人
     */
    private String userUpdate;

    /**
     * 修改时间
     */
    private Date timeUpdate;

    /**
     * 新增人ID
     */
    private Long userCreateId;

    /**
     * 修改人Id
     */
    private Long userUpdateId;

    /**
     * 物资状态: -1停用; 1启用
     */
    private Byte status;

    /**
     * 物资是否通用: 1是; 0否
     */
    private Byte matUniversal;

    private static final long serialVersionUID = 1L;
}