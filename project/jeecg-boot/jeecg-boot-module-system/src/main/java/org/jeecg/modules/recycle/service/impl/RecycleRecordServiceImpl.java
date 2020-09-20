package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleRecord;
import org.jeecg.modules.recycle.mapper.RecycleRecordMapper;
import org.jeecg.modules.recycle.service.IRecycleRecordService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_record
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecycleRecordServiceImpl extends ServiceImpl<RecycleRecordMapper, RecycleRecord> implements IRecycleRecordService {
    @Resource
    private RecycleRecordMapper recycleRecordMapper;

    @Override
    public PageBean<RecycleRecord> listByUserId(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        String orderId = param.getString("orderId");
        int count = recycleRecordMapper.count(param);
        PageBean<RecycleRecord> pb = new PageBean<>(pageNum,pageSize,count);
        param.put("pageIndex",pb.getStartIndex());
        param.put("orderId",orderId);
        List<RecycleRecord> list = recycleRecordMapper.listByUserId(param);
        pb.setList(list);
        return pb;

    }
}
