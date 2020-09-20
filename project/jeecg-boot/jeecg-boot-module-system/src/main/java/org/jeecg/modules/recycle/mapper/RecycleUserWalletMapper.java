package org.jeecg.modules.recycle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleUserWallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_user_wallet
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecycleUserWalletMapper extends BaseMapper<RecycleUserWallet> {

    RecycleUserWallet getByUserId(String userId);
}
