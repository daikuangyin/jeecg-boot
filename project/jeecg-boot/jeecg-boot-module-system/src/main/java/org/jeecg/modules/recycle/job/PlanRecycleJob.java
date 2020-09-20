package org.jeecg.modules.recycle.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.recycle.entity.RecyclePlanOrder;
import org.jeecg.modules.recycle.entity.RecycleUserOrder;
import org.jeecg.modules.recycle.service.IRecyclePlanOrderService;
import org.jeecg.modules.recycle.service.IRecycleUserOrderService;
import org.jeecg.modules.recycle.utils.IDHelper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Slf4j
public class PlanRecycleJob implements Job {
    @Autowired
    private IRecyclePlanOrderService iRecyclePlanOrderService;
    @Autowired
    private IRecycleUserOrderService iRecycleUserOrderService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String dateKey = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        log.info("PlanRecycleJob[{}] execute", dateKey);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<RecyclePlanOrder> list = iRecyclePlanOrderService.list();
        list.forEach(recyclePlanOrder -> {
            int cycle = recyclePlanOrder.getCycle();
            if(recyclePlanOrder.getLastupdateTime() != null ){
                if(System.currentTimeMillis() - recyclePlanOrder.getLastupdateTime().getTime() > cycle * 86400000){
                    RecycleUserOrder recycleUserOrder = new RecycleUserOrder();
                    recycleUserOrder.setUserId(recyclePlanOrder.getUserId());
                    recycleUserOrder.setEstimatWeight(recyclePlanOrder.getEstimatWeight());
                    recycleUserOrder.setRecycleAddressId(recyclePlanOrder.getRecycleAddressId());
                    recycleUserOrder.setImage(recyclePlanOrder.getImage());
                    recycleUserOrder.setMemo(recyclePlanOrder.getMemo());
                    recycleUserOrder.setCreateBy(recyclePlanOrder.getCreateBy());
                    recycleUserOrder.setCreateTime(new Date());
                    recycleUserOrder.setTakeGoodsTime(recyclePlanOrder.getTakeGoodsTime());
                    recycleUserOrder.setSubCategoryId(recyclePlanOrder.getSubCategoryId());
                    recycleUserOrder.setOrderCode(IDHelper.getInfoId(redisUtil,"D"));
                    iRecycleUserOrderService.save(recycleUserOrder);
                }
            }
        });
    }
}
