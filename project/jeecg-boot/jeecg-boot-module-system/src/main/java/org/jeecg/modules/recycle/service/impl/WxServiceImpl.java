package org.jeecg.modules.recycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.recycle.entity.RecycleCategory;
import org.jeecg.modules.recycle.entity.RecycleSubCategory;
import org.jeecg.modules.recycle.mapper.WxMapper;
import org.jeecg.modules.recycle.service.IWxService;
import org.jeecg.modules.recycle.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxServiceImpl implements IWxService {
    @Resource
    private WxMapper wxMapper;

    @Override
    public List<JSONObject> listBanner() {
        return  wxMapper.listBanner();
    }

    @Override
    public List<RecycleCategory> listCategory() {
        return wxMapper.listCategory();
    }

    @Override
    public PageBean<JSONObject> listOrderWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = wxMapper.countOrder(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        param.put("pageIndex",pb.getStartIndex());
        List<JSONObject> list = wxMapper.listOrderWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public PageBean<JSONObject> listPromotionWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = wxMapper.countPromotion(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        param.put("pageIndex",pb.getStartIndex());
        List<JSONObject> list = wxMapper.listPromotionWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public PageBean<JSONObject> listCashWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = wxMapper.countCash(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        param.put("pageIndex",pb.getStartIndex());
        List<JSONObject> list = wxMapper.listCashWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public PageBean<JSONObject> listAddressWithPage(JSONObject param) {
        int pageSize = param.getInteger("pageSize");
        int pageNum = param.getInteger("pageNum");
        int count = wxMapper.addressCount(param);
        PageBean<JSONObject> pb = new PageBean<>(pageNum,pageSize,count);
        param.put("pageIndex",pb.getStartIndex());
        List<JSONObject> list = wxMapper.listAddressWithPage(param);
        pb.setList(list);
        return pb;
    }

    @Override
    public double userBalance(JSONObject param) {
        double totalCash = wxMapper.userBalance(param);
        return totalCash;
    }

    @Override
    public JSONObject getWeightByUserId(JSONObject param) {
        return wxMapper.getWeightByUserId(param);
    }

    @Override
    public JSONObject userRecyle(JSONObject param) {
        JSONObject result = new JSONObject();
        Integer planRecoveryNum = wxMapper.planRecoveryNum(param);
        Integer recoveryNum = wxMapper.recoveryNum(param);

        result.put("planRecoveryNum",planRecoveryNum);
        result.put("recoveryNum",recoveryNum);
        return result;
    }

    @Override
    public List<RecycleSubCategory> listSubCategory(Integer categoryId) {
        return wxMapper.listSubCategory(categoryId);
    }

    @Override
    public boolean addOrder(JSONObject param) {
        return wxMapper.inserOrder(param);
    }

    @Override
    public boolean addPlanOrder(JSONObject param) {
        return wxMapper.insertPlanOrder(param);
    }
}
