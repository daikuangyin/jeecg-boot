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
 * @Description: recycle_user_cash
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Data
@TableName("recycle_user_cash")
public class RecycleUserCash implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "bannerID(唯一)",required = true)
	private String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID",required = true)
    private String userId;
	/**提现方式 （1.支付宝 2.微信）*/
	@Excel(name = "提现方式 （1.支付宝 2.微信）", width = 15)
	@ApiModelProperty(value = "提现方式（1.支付宝 2.微信）",required = true)
    private Integer cashWay;
	/**提现金额*/
	@Excel(name = "提现金额", width = 15)
	@ApiModelProperty(value = "提现金额",required = true)
	private double cashMoney;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
	@ApiModelProperty(value = "流水号",required = true)
	private String serialNum;
	/**审核状态 0 未审核 1 已审核*/
	@Excel(name = "审核状态 0 未审核 1 已审核", width = 15)
	@ApiModelProperty(value = "审核状态 0 未审核 1 已审核",required = true)
	private int auditStatus;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人",required = true)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间 yyyy-MM-dd",required = true)
    private Date createTime;
}
