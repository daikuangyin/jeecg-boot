package org.jeecg.modules.recycle.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: recycle_plan_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Data
@TableName("recycle_plan_order")
public class RecyclePlanOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "订单号(唯一)",required = true)
    private String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID",required = true)
    private String userId;
	/**服务类型*/
	@Excel(name = "服务类型", width = 15)
	@ApiModelProperty(value = "服务类型",required = true)
    private String subCategoryId;
	/**上门提货时间*/
	@Excel(name = "上门提货时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "上门提货时间 （yyyy-MM-dd hh:mm:ss）",required = true)
    private Date takeGoodsTime;
	/**预估重量*/
	@Excel(name = "预估重量", width = 15)
	@ApiModelProperty(value = "预估重量 ",required = true)
	private String estimatWeight;
	/**图片*/
	@Excel(name = "图片", width = 15)
	@ApiModelProperty(value = "图片",required = true)
	private String image;
	/**备注描述*/
	@Excel(name = "备注描述", width = 15)
	@ApiModelProperty(value = "备注描述",required = true)
    private String memo;
	/**周期*/
	@Excel(name = "周期", width = 15)
	@ApiModelProperty(value = "周期",required = true)
	private Integer cycle;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人",required = true)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间 （yyyy-MM-dd hh:mm:ss）",required = true)
	private Date createTime;
	/**最后执行时间*/
	@Excel(name = "最后执行时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最后执行时间 （yyyy-MM-dd hh:mm:ss）",required = true)
	private Date lastupdateTime;
	/**联系人地址*/
	@Excel(name = "联系人地址", width = 15)
	@ApiModelProperty(value = "联系人地址 （yyyy-MM-dd hh:mm:ss）",required = true)
	private String recycleAddressId;

}
