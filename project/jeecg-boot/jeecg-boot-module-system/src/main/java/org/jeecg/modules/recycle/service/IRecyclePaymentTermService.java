package org.jeecg.modules.recycle.service;

import org.jeecg.modules.recycle.entity.RecyclePaymentTerm;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.recycle.entity.RecycleUser;

import java.util.List;

/**
 * @Description: recycle_payment_term
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecyclePaymentTermService extends IService<RecyclePaymentTerm> {

    List<RecyclePaymentTerm> listByUserId(String userId,Integer type);
}
