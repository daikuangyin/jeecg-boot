package org.jeecg.modules.recycle.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecyclePlanOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_plan_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecyclePlanOrderMapper extends BaseMapper<RecyclePlanOrder> {

    JSONObject getRecycleUserById(@Param("id") String id);

    List<JSONObject> listOrderWithPage(JSONObject param);

    int count(JSONObject param);
}
