package org.jeecg.modules.recycle.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: recycle_record
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
public interface RecycleRecordMapper extends BaseMapper<RecycleRecord> {

    int count(JSONObject param);

    List<RecycleRecord> listByUserId(JSONObject param);
}
