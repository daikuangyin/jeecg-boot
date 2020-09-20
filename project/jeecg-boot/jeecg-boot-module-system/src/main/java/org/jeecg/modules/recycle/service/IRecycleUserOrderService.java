package org.jeecg.modules.recycle.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleUserOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.recycle.utils.PageBean;

/**
 * @Description: recycle_user_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecycleUserOrderService extends IService<RecycleUserOrder> {

    PageBean<JSONObject> listWithPage(JSONObject param);

    JSONObject getRecycleUserById(String id);

    boolean receiveOrder(String userId, String id);

    boolean pay(String userId, String id);

    boolean cancelOrder(String id);
}
