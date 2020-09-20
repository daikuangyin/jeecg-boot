package org.jeecg.modules.recycle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: recycle_user
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Data
@TableName("recycle_user")
public class RecycleUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    private String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	private String name;
	/**用户ID*/
	@Excel(name = "用户微信ID", width = 15)
	private String uid;
	@Excel(name = "用户头像", width = 15)
	private String headportrait;
	/**用户ID*/
	@Excel(name = "用户昵称", width = 15)
	private  String nickname;
	/**用户类型 1 普通用户 2 回收员*/
	@Excel(name = "用户类型 1 普通用户 2 回收员", width = 15)
    private Integer type;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    private String phone;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    private String idCard;
	/**地址*/
	@Excel(name = "地址", width = 15)
    private String address;
	/**图片*/
	@Excel(name = "图片", width = 15)
    private String image;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
}
