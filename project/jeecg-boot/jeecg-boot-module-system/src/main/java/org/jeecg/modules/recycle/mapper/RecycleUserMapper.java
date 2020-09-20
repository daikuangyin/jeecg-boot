package org.jeecg.modules.recycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleUser;

/**
 * @Description: recycle_user
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecycleUserMapper extends BaseMapper<RecycleUser> {

    RecycleUser getUserById(@Param("userId") String userId);
}
