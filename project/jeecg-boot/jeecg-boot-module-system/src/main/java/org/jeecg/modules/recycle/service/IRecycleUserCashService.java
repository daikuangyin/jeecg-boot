package org.jeecg.modules.recycle.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleUserCash;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.recycle.utils.PageBean;

import java.util.List;

/**
 * @Description: recycle_user_cash
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecycleUserCashService extends IService<RecycleUserCash> {

    PageBean<JSONObject> listWithPage(JSONObject param);
}
