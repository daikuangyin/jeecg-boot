package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.recycle.entity.RecycleUserOrder;
import org.jeecg.modules.recycle.mapper.RecycleUserOrderMapper;
import org.jeecg.modules.recycle.service.IRecycleUserOrderService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_user_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecycleUserOrderServiceImpl extends ServiceImpl<RecycleUserOrderMapper, RecycleUserOrder> implements IRecycleUserOrderService {
    @Resource
    private RecycleUserOrderMapper recycleUserOrderMapper;
    @Override
    public PageBean<JSONObject> listWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = recycleUserOrderMapper.Count(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        List<JSONObject> list = recycleUserOrderMapper.listWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public JSONObject getRecycleUserById(String id) {
        JSONObject data = recycleUserOrderMapper.getRecycleUserById(id);
        return data;
    }

    @Override
    public boolean receiveOrder(String userId, String id) {
        return recycleUserOrderMapper.receiveOrder(userId,id);
    }

    @Override
    public boolean pay(String userId, String id) {
        return recycleUserOrderMapper.pay(userId,id);
    }

    @Override
    public boolean cancelOrder(String id) {
        return recycleUserOrderMapper.cancelOrder(id);
    }
}
