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
 * @Description: recycle_user_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Data
@TableName("recycle_user_order")
public class RecycleUserOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
	private java.lang.String orderCode;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	private java.lang.String userId;
	/**回收人员*/
	@Excel(name = "回收人员", width = 15)
	private java.lang.String recycleUserId;
	/**服务类型*/
	@Excel(name = "服务类型", width = 15)
	private java.lang.String subCategoryId;
	/**上门提货时间*/
	@Excel(name = "上门提货时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date takeGoodsTime;
	/**预估重量*/
	@Excel(name = "预估重量", width = 15)
	private String estimatWeight;
	/**实际重量*/
	@Excel(name = "实际重量", width = 15)
	private double actualWeight;
	/**凭证*/
	@Excel(name = "凭证", width = 15)
	private java.lang.String voucher;
	/**需支付价格*/
	@Excel(name = "需支付价格", width = 15)
	private double price;
	/**完成时间*/
	@Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date compleTime;
	/**图片*/
	@Excel(name = "图片", width = 15)
	private java.lang.String image;
	/**备注描述*/
	@Excel(name = "备注描述", width = 15)
	private java.lang.String memo;
	/**订单类型 （1 单次 2 定期）*/
	@Excel(name = "订单类型 （1 单次 2 定期）", width = 15)
	private java.lang.Integer type;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createTime;
	@Excel(name = "订单状态  0 未接单 1 已接单 2已完成 3已取消", width = 15)
	private int orderStatus;

	/**联系人地址*/
	@Excel(name = "联系人地址", width = 15)
	@ApiModelProperty(value = "联系人地址 （yyyy-MM-dd hh:mm:ss）",required = true)
	private String recycleAddressId;
}
