package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecyclePlanOrder;
import org.jeecg.modules.recycle.mapper.RecyclePlanOrderMapper;
import org.jeecg.modules.recycle.service.IRecyclePlanOrderService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_plan_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecyclePlanOrderServiceImpl extends ServiceImpl<RecyclePlanOrderMapper, RecyclePlanOrder> implements IRecyclePlanOrderService {
    @Resource
    private RecyclePlanOrderMapper recyclePlanOrderMapper;
    @Override
    public PageBean<JSONObject> listOrderWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = recyclePlanOrderMapper.count(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        param.put("pageIndex",pb.getStartIndex());
        List<JSONObject> list = recyclePlanOrderMapper.listOrderWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public JSONObject getRecycleUserById(String id) {
        return recyclePlanOrderMapper.getRecycleUserById(id);
    }

    @Override
    public boolean cancelOrder(String id) {
        return removeById(id);
    }
}
