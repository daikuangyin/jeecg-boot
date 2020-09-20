package org.jeecg.modules.recycle.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleCategory;
import org.jeecg.modules.recycle.entity.RecycleSubCategory;
import org.jeecg.modules.recycle.utils.PageBean;

import java.util.List;

public interface IWxService {
    List<JSONObject> listBanner();

    List<RecycleCategory> listCategory();

    PageBean<JSONObject> listOrderWithPage(JSONObject param);


    PageBean<JSONObject> listPromotionWithPage(JSONObject param);

    PageBean<JSONObject> listCashWithPage(JSONObject param);

    PageBean<JSONObject> listAddressWithPage(JSONObject param);

    double userBalance(JSONObject param);

    List<RecycleSubCategory> listSubCategory(Integer categoryId);

    boolean addOrder(JSONObject param);

    boolean addPlanOrder(JSONObject param);

    JSONObject userRecyle(JSONObject param);

    JSONObject getWeightByUserId(JSONObject param);
}
