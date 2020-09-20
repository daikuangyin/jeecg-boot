package org.jeecg.modules.recycle.service.impl;

import org.jeecg.modules.recycle.entity.RecyclePaymentTerm;
import org.jeecg.modules.recycle.entity.RecycleUser;
import org.jeecg.modules.recycle.mapper.RecyclePaymentTermMapper;
import org.jeecg.modules.recycle.service.IRecyclePaymentTermService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: recycle_payment_term
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecyclePaymentTermServiceImpl extends ServiceImpl<RecyclePaymentTermMapper, RecyclePaymentTerm> implements IRecyclePaymentTermService {
    @Resource
    private RecyclePaymentTermMapper recyclePaymentTermMapper;
    @Override
    public List<RecyclePaymentTerm> listByUserId(String userId,Integer type) {
        return recyclePaymentTermMapper.listByUserId(userId,type);
    }
}
