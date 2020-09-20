package org.jeecg.modules.recycle.service;

import org.jeecg.modules.recycle.entity.RecycleUserWallet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: recycle_user_wallet
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecycleUserWalletService extends IService<RecycleUserWallet> {

    RecycleUserWallet getByUserId(String userId);
}
