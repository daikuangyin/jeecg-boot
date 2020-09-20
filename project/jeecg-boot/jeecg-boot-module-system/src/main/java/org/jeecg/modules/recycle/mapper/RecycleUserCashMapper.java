package org.jeecg.modules.recycle.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleUserCash;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_user_cash
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecycleUserCashMapper extends BaseMapper<RecycleUserCash> {

    int count(JSONObject param);

    List<JSONObject> listWithPage(JSONObject param);
}
