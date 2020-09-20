package org.jeecg.modules.recycle.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_address
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecycleAddressMapper extends BaseMapper<RecycleAddress> {

    List<JSONObject> listWithPage(JSONObject param);

    int count(JSONObject param);

    boolean resetDefault(@Param("userId") String userId);

    RecycleAddress getDefault(@Param("userId") String userId);
}
