package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecyclePromotion;
import org.jeecg.modules.recycle.mapper.RecyclePromotionMapper;
import org.jeecg.modules.recycle.service.IRecyclePromotionService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_promotion
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecyclePromotionServiceImpl extends ServiceImpl<RecyclePromotionMapper, RecyclePromotion> implements IRecyclePromotionService {
    @Resource
    private RecyclePromotionMapper recyclePromotionMapper;
    @Override
    public PageBean<JSONObject> listWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        Integer count = recyclePromotionMapper.count(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        List<JSONObject> list = recyclePromotionMapper.listWithPage(param);
        pb.setList(list);
        return pb;
    }
}
