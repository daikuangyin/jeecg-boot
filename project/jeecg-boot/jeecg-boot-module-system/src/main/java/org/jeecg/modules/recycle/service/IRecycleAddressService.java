package org.jeecg.modules.recycle.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.recycle.utils.PageBean;

/**
 * @Description: recycle_address
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecycleAddressService extends IService<RecycleAddress> {

    PageBean<JSONObject> listWithPage(JSONObject param);

    boolean resetDefault(String userId);

    RecycleAddress getDefault(String userId);
}
