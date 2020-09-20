package org.jeecg.modules.recycle.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecyclePromotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_promotion
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Mapper
public interface RecyclePromotionMapper extends BaseMapper<RecyclePromotion> {

    List<JSONObject> listWithPage(JSONObject param);

    Integer count(JSONObject param);
}
