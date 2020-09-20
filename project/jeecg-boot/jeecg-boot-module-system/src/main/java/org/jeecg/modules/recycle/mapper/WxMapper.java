package org.jeecg.modules.recycle.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.recycle.entity.RecycleCategory;
import org.jeecg.modules.recycle.entity.RecycleSubCategory;

import java.util.List;

@Mapper
public interface WxMapper {
    List<JSONObject> listBanner();

    List<RecycleCategory> listCategory();

    int countOrder(JSONObject param);

    List<JSONObject> listOrderWithPage(JSONObject param);

    int countPromotion(JSONObject param);

    List<JSONObject> listPromotionWithPage(JSONObject param);

    List<JSONObject> listCashWithPage(JSONObject param);

    int countCash(JSONObject param);

    int addressCount(JSONObject param);

    List<JSONObject> listAddressWithPage(JSONObject param);

    double userBalance(JSONObject param);

    String totalCash(JSONObject param);

    List<RecycleSubCategory> listSubCategory(@Param("categoryId") Integer categoryId);

    int planRecoveryNum(JSONObject param);

    int recoveryNum(JSONObject param);

    boolean inserOrder(JSONObject param);

    boolean insertPlanOrder(JSONObject param);

    JSONObject getWeightByUserId(JSONObject param);
}




