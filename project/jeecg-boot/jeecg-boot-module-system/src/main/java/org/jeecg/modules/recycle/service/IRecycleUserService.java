package org.jeecg.modules.recycle.service;

import org.jeecg.modules.recycle.entity.RecycleUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: recycle_user
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface IRecycleUserService extends IService<RecycleUser> {

    RecycleUser findByOpenid(String openid);

    RecycleUser getUserById(String userId);
}
