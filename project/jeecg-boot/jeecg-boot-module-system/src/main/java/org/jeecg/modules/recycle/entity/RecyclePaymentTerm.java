package org.jeecg.modules.recycle.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: recycle_payment_term
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Data
@TableName("recycle_payment_term")
public class RecyclePaymentTerm implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    private String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
    private String userId;
	/**类型 （1.支付宝 2.微信）*/
	@Excel(name = "类型 （1.支付宝 2.微信）", width = 15)
    private Integer type;
	/**账号信息*/
	@Excel(name = "账号信息", width = 15)
    private String account;
	@Excel(name = "名称", width = 15)
	private String name;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
}
