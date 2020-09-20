package org.jeecg.modules.recycle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecyclePaymentTerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_payment_term
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecyclePaymentTermMapper extends BaseMapper<RecyclePaymentTerm> {

    List<RecyclePaymentTerm> listByUserId(@Param("userId") String userId,@Param("type") Integer type);
}
