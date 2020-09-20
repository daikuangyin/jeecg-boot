package org.jeecg.modules.recycle.service.impl;

import org.jeecg.modules.recycle.entity.RecycleUserWallet;
import org.jeecg.modules.recycle.mapper.RecycleUserWalletMapper;
import org.jeecg.modules.recycle.service.IRecycleUserWalletService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: recycle_user_wallet
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecycleUserWalletServiceImpl extends ServiceImpl<RecycleUserWalletMapper, RecycleUserWallet> implements IRecycleUserWalletService {
    @Resource
    private RecycleUserWalletMapper recycleUserWalletMapper;
    @Override
    public RecycleUserWallet getByUserId(String userId) {
        return recycleUserWalletMapper.getByUserId(userId);
    }
}
