package org.jeecg.modules.recycle.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleUserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_user_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecycleUserOrderMapper extends BaseMapper<RecycleUserOrder> {

    int Count(JSONObject param);

    List<JSONObject> listWithPage(JSONObject param);

    JSONObject getRecycleUserById(@Param("id") String id);

    boolean pay(@Param("userId") String userId,@Param("id") String id);

    boolean receiveOrder(@Param("userId") String userId,@Param("id") String id);

    boolean cancelOrder(@Param("id")String id);
}
