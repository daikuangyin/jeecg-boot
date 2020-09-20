package org.jeecg.modules.recycle.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecyclePromotion;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.recycle.utils.PageBean;

import java.util.List;

/**
 * @Description: recycle_promotion
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecyclePromotionService extends IService<RecyclePromotion> {

    PageBean<JSONObject> listWithPage(JSONObject param);
}
