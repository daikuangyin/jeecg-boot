package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleAddress;
import org.jeecg.modules.recycle.mapper.RecycleAddressMapper;
import org.jeecg.modules.recycle.service.IRecycleAddressService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_address
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecycleAddressServiceImpl extends ServiceImpl<RecycleAddressMapper, RecycleAddress> implements IRecycleAddressService {
    @Resource
    private RecycleAddressMapper recycleAddressMapper;
    @Override
    public PageBean<JSONObject> listWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = recycleAddressMapper.count(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        List<JSONObject> list = recycleAddressMapper.listWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public boolean resetDefault(String userId) {
        return recycleAddressMapper.resetDefault(userId);
    }

    @Override
    public RecycleAddress getDefault(String userId) {
        return recycleAddressMapper.getDefault(userId);
    }
}
