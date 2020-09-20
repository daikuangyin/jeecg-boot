package org.jeecg.modules.recycle.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecyclePlanOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.recycle.utils.PageBean;

/**
 * @Description: recycle_plan_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecyclePlanOrderService extends IService<RecyclePlanOrder> {

    PageBean<JSONObject> listOrderWithPage(JSONObject param);

    JSONObject getRecycleUserById(String id);

    boolean cancelOrder(String id);
}
