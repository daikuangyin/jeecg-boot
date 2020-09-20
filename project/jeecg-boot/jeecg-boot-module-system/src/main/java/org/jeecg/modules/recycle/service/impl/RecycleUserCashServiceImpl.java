package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleUserCash;
import org.jeecg.modules.recycle.mapper.RecycleUserCashMapper;
import org.jeecg.modules.recycle.service.IRecycleUserCashService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_user_cash
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecycleUserCashServiceImpl extends ServiceImpl<RecycleUserCashMapper, RecycleUserCash> implements IRecycleUserCashService {
    @Resource
    private RecycleUserCashMapper recycleUserCashMapper;
    @Override
    public PageBean<JSONObject> listWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = recycleUserCashMapper.count(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        List<JSONObject> list = recycleUserCashMapper.listWithPage(param);
        pb.setList(list);
        return pb;
    }
}
