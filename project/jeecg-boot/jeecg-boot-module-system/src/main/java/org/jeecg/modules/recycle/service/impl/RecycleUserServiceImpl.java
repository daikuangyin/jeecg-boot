package org.jeecg.modules.recycle.service.impl;

import org.jeecg.modules.recycle.entity.RecycleUser;
import org.jeecg.modules.recycle.mapper.RecycleUserMapper;
import org.jeecg.modules.recycle.service.IRecycleUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: recycle_user
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Service
public class RecycleUserServiceImpl extends ServiceImpl<RecycleUserMapper, RecycleUser> implements IRecycleUserService {
    @Resource
    private RecycleUserMapper recycleUserMapper;
    @Override
    public RecycleUser findByOpenid(String openid) {
        return null;
    }

    @Override
    public RecycleUser getUserById(String userId) {
        return recycleUserMapper.getUserById(userId);
    }
}
