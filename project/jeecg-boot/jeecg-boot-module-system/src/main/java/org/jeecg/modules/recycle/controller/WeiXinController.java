package org.jeecg.modules.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oss.OssBootUtil;
import org.jeecg.modules.oss.service.IOSSFileService;
import org.jeecg.modules.recycle.entity.*;
import org.jeecg.modules.recycle.service.*;
import org.jeecg.modules.recycle.utils.ApiHandler;
import org.jeecg.modules.recycle.utils.IDHelper;
import org.jeecg.modules.recycle.utils.PageBean;
import org.jeecg.modules.recycle.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
* @Description: recycle_address
* @Author: jeecg-boot
* @Date:   2020-08-26
* @Version: V1.0
*/
@RestController
@RequestMapping("/wx")
@Slf4j
public class WeiXinController{
   @Autowired
   private IRecycleAddressService recycleAddressService;

    @Autowired
    private IRecycleUserOrderService iRecycleUserOrderService;

    @Autowired
    private IRecycleUserCashService recycleCashService;

    @Autowired
    private IRecyclePlanOrderService recyclePlanOrderService;

    @Autowired
    private IRecycleUserService recycleUserService;
    @Autowired
    private IRecycleBannerService recycleBannerService;

    @Autowired
    private IRecycleUserWalletService recycleUserWalletService;

    @Autowired
    private IWxService iWxService;
    @Resource
    private ApiHandler apiHandler;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IRecyclePaymentTermService recyclePaymentTermService;
    @Resource
    private IRecycleRecordService recycleRecordService;

    @Autowired
    private IOSSFileService ossFileService;

    @ApiOperation("上传附件")
    @ResponseBody
    @PostMapping("/upload/new")
    public Result uploadnew(HttpServletRequest request) {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = req.getFile("file");
        Result result = new Result();
        try {
            String url = OssBootUtil.upload(multipartFile,"upload/recycle");
            JSONObject data = new JSONObject();
            data.put("url",url);
            result.setResult(url);
            result.success("上传成功！");
        }
        catch (Exception ex) {
            log.info(ex.getMessage(), ex);
            result.error500("上传失败");
        }
        return result;
    }

    @ApiOperation("上传附件")
    @ResponseBody
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") @ApiParam(name="file",value = "文件") MultipartFile multipartFile) {
        Result result = new Result();
        try {
            String url = OssBootUtil.upload(multipartFile,"upload/recycle");
            JSONObject data = new JSONObject();
            data.put("url",url);
            result.setResult(url);
            result.success("上传成功！");
        }
        catch (Exception ex) {
            log.info(ex.getMessage(), ex);
            result.error500("上传失败");
        }
        return result;
    }

    /**
     * banner 列表
     *
     * @return
     */
    @ApiOperation(value = "banner列表", notes = "banner列表", httpMethod = "GET")
    @GetMapping(value = "/banner/list")
    public Result<Object> queryPageList() {
        List<RecycleBanner> list = recycleBannerService.list();
        return Result.ok(list);
    }

    /**
     * 类目列表
     * @return
     */
    @ApiOperation("类目列表")
    @GetMapping(value = "/category/list")
    public Result<?> listCategory() {
        List<RecycleCategory> list = iWxService.listCategory();
        return Result.ok(list);
    }

    /**
     * 类目价目表列表
     * @return
     */
    @ApiOperation("价目列表")

    @GetMapping(value = "/price/list/{categoryId}")
    public Result<?> listPrice(@PathVariable("categoryId") @ApiParam(value = "类目ID") Integer categoryId) {
        List<RecycleSubCategory> list = iWxService.listSubCategory(categoryId);
        return Result.ok(list);
    }


    /**
     * 创建订单根据状态订单列表
     * @return
    0   */
    @ApiOperation("新增用户订单")
    @PostMapping(value = "/order/add")
    public Result<?> createOrder(@RequestBody RecycleUserOrder recycleUserOrder) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                recycleUserOrder.setUserId(userId);
                recycleUserOrder.setOrderCode(IDHelper.getInfoId(redisUtil,"D"));
                boolean result = iRecycleUserOrderService.save(recycleUserOrder);
                return Result.ok(result);
            }
        }
    }

    /**
     * 创建定期回收订单
     * @param
     * @return
    0   */
    @ApiOperation("定期回收")
    @PostMapping(value = "/planorder/add")
    public Result<?> createPlanOrder(@RequestBody RecyclePlanOrder recyclePlanOrder) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                boolean result = recyclePlanOrderService.save(recyclePlanOrder);
                return Result.ok(result);
            }
        }
    }

    /**
     * 根据状态周期订单列表
     * @param param
     * @return
    0   */
    @ApiOperation("根据状态查询周期订单列表")
    @PostMapping(value = "/orderplan/list")
    public Result<?> listPlanOrder(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            param.put("userId",userId);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                PageBean<JSONObject> result = recyclePlanOrderService.listOrderWithPage(param);
                return Result.ok(result);
            }
        }

    }

    /**
     * 周期订单详情
     * @return
     */
    @ApiOperation("周期订单详情")
    @GetMapping(value = "/orderplan/details/{id}" )
    public Result<?> detailsPlanOrder(@PathVariable("id")  @ApiParam(value = "订单计划ID") String id) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                JSONObject data = recyclePlanOrderService.getRecycleUserById(id);
                return Result.ok(data);
            }
        }
    }



    /**
     * 根据状态订单列表
     * @param param
     * @return
0   */
    @ApiOperation("根据状态订单列表")
    @PostMapping(value = "/order/list")
    public Result<?> listOrder(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            param.put("userId",userId);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                PageBean<JSONObject> result = iWxService.listOrderWithPage(param);
                return Result.ok(result);
            }
        }

    }

    /**
     * 订单详情
     * @return
     */
    @ApiOperation("订单详情")
    @GetMapping(value = "/order/details/{id}/{type}" )
    public Result<?> detailsOrder(@PathVariable("id")  @ApiParam(value = "订单") String id,
                                  @PathVariable("type")  Integer type) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                if(type == 1){
                    JSONObject data = iRecycleUserOrderService.getRecycleUserById(id);
                    return Result.ok(data);
                }else {
                    JSONObject data = recyclePlanOrderService.getRecycleUserById(id);
                    return Result.ok(data);
                }
            }
        }
    }

    /**
     * 取消订单 1.单次 2.回收
     * @return
     */
    @ApiOperation("取消订单")
    @GetMapping(value = "/order/cancel/{id}/{type}" )
    public Result<?> cancelOrder(@PathVariable("id")  @ApiParam(value = "订单") String id,
                                  @PathVariable("type")  Integer type) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                if(type == 1){
                    boolean cancelOrder = iRecycleUserOrderService.cancelOrder(id);
                    return Result.ok(cancelOrder);
                }else{
                    boolean cancelOrder = recyclePlanOrderService.cancelOrder(id);
                    return Result.ok(cancelOrder);
                }
            }
        }
    }

    /**
     * 推广记录列表
     * @param param
     * @return
     */
    @ApiOperation("推广记录列表")
    @PostMapping(value = "/promotion/list")
    public Result<?> listPromotion(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                PageBean<JSONObject> result = iWxService.listPromotionWithPage(param);
                return Result.ok(result);
            }
        }
    }

    /**
     * 提现记录
     * @param param
     * @return
     */
    @ApiOperation("提现记录")
    @PostMapping(value = "/cash/list")
    public Result<?> listCash(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                param.put("userId",userId);
                PageBean<JSONObject> result = iWxService.listCashWithPage(param);
                return Result.ok(result);
            }
        }
    }

    /**
     * 提现申请
     *
     * @param param
     * @return
     */
    @ApiOperation("提现申请")
    @PostMapping(value = "/cash/apply")
    public Result<?> applyCash(@RequestBody JSONObject param) {

        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                RecycleUserWallet recycleUserWallet = recycleUserWalletService.getByUserId(userId);
                param.put("userId",userId);
                param.put("createBy",userId);
                param.put("createTime",new Date());
                RecycleUserCash recycleUserCash = JSONObject.toJavaObject(param,RecycleUserCash.class);
                if(recycleUserWallet.getMoneyOutAmount() >= recycleUserCash.getCashMoney()){
                    boolean result = recycleCashService.save(recycleUserCash);
                    recycleUserWallet.setCashWay(recycleUserCash.getCashWay());
                    double moneyOutAmount = recycleUserWallet.getMoneyOutAmount() - recycleUserCash.getCashMoney();
                    recycleUserWallet.setMoneyOutAmount(moneyOutAmount);
                    recycleUserWalletService.saveOrUpdate(recycleUserWallet);
                    return Result.ok(result);
                }else{
                    return Result.error(500,"提现申请金额不能大于可提现金额,请核对金额后重新提交");
                }


            }
        }
    }

    /**
     * 用户余额
     * @return
     */
    @ApiOperation("用户余额")
    @GetMapping(value = "/user/balance")
    public Result<?> userBalance() {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                JSONObject param = new JSONObject();
                param.put("userId",userId);
                double result = iWxService.userBalance(param);
                return Result.ok(result);
            }
        }
    }


    /**
     * 用户回收情况
     * @return
     */
    @ApiOperation("用户回收情况")
    @GetMapping(value = "/user/recyle")
    public Result<?> userRecyle() {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                JSONObject param = new JSONObject();
                param.put("userId",userId);
                return Result.ok(iWxService.userRecyle(param));
            }
        }
    }


    /**
     * 提交预约上门
     * @param param
     * @return
     */
    @ApiOperation("提交预约上门")
    @PostMapping(value = "/recycleplan/apply")
    public Result<?> applyRecycleplan(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = redisUtil.get(token).toString();
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                param.put("userId",userId);
                RecyclePlanOrder recyclePlanOrder = JSONObject.toJavaObject(param,RecyclePlanOrder.class);
                boolean result = recyclePlanOrderService.save(recyclePlanOrder);
                return Result.ok(result);
            }
        }
    }

    /**
     * 添加联系人地址
     * @param param
     * @return
     */
    @ApiOperation("添加联系人地址")
    @PostMapping(value = "/address/add")
    public Result<?> addAddress(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            Object userId = redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                param.put("userId",userId);
                RecycleAddress recycleAddress = JSONObject.toJavaObject(param,RecycleAddress.class);
                if(recycleAddress.getIsDefault() == 1){
                    //重置默认状态
                    recycleAddressService.resetDefault(recycleAddress.getUserId());
                }
                boolean result = recycleAddressService.save(recycleAddress);
                return Result.ok(result);
            }
        }
    }

    /**
     * 10.	用户地址信息列表
     * @return
     */
    @ApiOperation("获取用户默认地址")
    @GetMapping(value = "/address/default")
    public Result<?> defaultAddress() {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                RecycleAddress result = recycleAddressService.getDefault(userId);
                return Result.ok(result);
            }
        }
    }

    /**
     * 10.	地址列表
     * @param param
     * @return
     */
    @ApiOperation("获取用户联系地址列表")
    @PostMapping(value = "/address/list")
    public Result<?> listAddress(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                param.put("userId",userId);
                PageBean<JSONObject> result = iWxService.listAddressWithPage(param);
                return Result.ok(result);
            }
        }
    }

    /**
     * 11.	用户地址更新
     * @param param
     * @return
     */
    @ApiOperation("用户地址更新")
    @PostMapping(value = "/address/update")
    public Result<?> updateAddress(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                param.put("userId",userId);
                RecycleAddress recycleAddress = JSONObject.toJavaObject(param,RecycleAddress.class) ;
                if(recycleAddress.getIsDefault() == 1){
                    //重置默认状态
                    recycleAddressService.resetDefault(recycleAddress.getUserId());
                }
                boolean result = recycleAddressService.updateById(recycleAddress);
                return Result.ok(result);
            }
        }
    }

    /**
     * 11.	回收员入驻信息提交
     * @param param
     * @return
     */
    @ApiOperation("回收员入驻信息提交")
    @PostMapping(value = "/user/collector/add")
    public Result<?> userRegister(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{

                RecycleUser recycleUser = JSONObject.toJavaObject(param,RecycleUser.class);
                recycleUser.setType(2);
                recycleUser.setUid(userId);
                RecycleUser data = recycleUserService.getUserById(userId);
                recycleUser.setId(data.getId());
                boolean result = recycleUserService.updateById(recycleUser);
                return Result.ok(result);
            }
        }
    }

    /**
     * 11.	回收员入驻信息提交
     * @param param
     * @return
     */
    @ApiOperation("修改用户信息")
    @PostMapping(value = "/user/update")
    public Result<?> updateUser(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                RecycleUser recycleUser = JSONObject.toJavaObject(param,RecycleUser.class);
                recycleUser.setUid(userId);
                RecycleUser data = recycleUserService.getUserById(userId);
                recycleUser.setId(data.getId());
                boolean result = recycleUserService.updateById(recycleUser);
                return Result.ok(result);
            }
        }
    }

    /**
     * 回收员回收物品重量
     * @return
     */
    @ApiOperation("回收员回收物品重量")
    @GetMapping(value = "/user/recycle/weight")
    public Result<?> weight() {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                String dateKey = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                JSONObject param = new JSONObject();
                param.put("userId",userId);
                param.put("startTime",dateKey);
                JSONObject result = new JSONObject();
                result = iWxService.getWeightByUserId(param);
                if(result == null){
                    result = new JSONObject();
                    result.put("weight",0);
                }
                return Result.ok(result);
            }
        }
    }
    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping(value = "/user/detail")
    public Result<?> detail() {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                RecycleUser result = recycleUserService.getUserById(userId);
                return Result.ok(result);
            }
        }
    }


    /**
     * 新增收款方式
     * @return
     */
    @ApiOperation("新增收款方式")
    @PostMapping(value = "/payment/add")
    public Result<?>  addPaymentTerm(@RequestBody RecyclePaymentTerm recyclePaymentTerm) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                recyclePaymentTerm.setUserId(userId);
                recyclePaymentTerm.setCreateBy(userId);
                recyclePaymentTerm.setCreateTime(new Date());
                boolean result = recyclePaymentTermService.save(recyclePaymentTerm);
                return Result.ok(result);
            }
        }
    }


    /**
     * 修改收款方式
     * @return
     */
    @ApiOperation("修改收款方式")
    @PostMapping(value = "/payment/update")
    public Result<?>  updatePaymentTerm(@RequestBody RecyclePaymentTerm recyclePaymentTerm) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                boolean result = recyclePaymentTermService.updateById(recyclePaymentTerm);
                return Result.ok(result);
            }
        }
    }

    /**
     * 获取收款方式
     * @return
     */
    @ApiOperation("获取收款方式")
    @GetMapping(value = "/payment/list")
    public Result<?> paymentList() {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                List<RecyclePaymentTerm> result = recyclePaymentTermService.listByUserId(userId,null);
                return Result.ok(result);
            }
        }
    }

    /**
     * 获取收款方式
     * @return
     */
    @ApiOperation("获取收款方式")
    @GetMapping(value = "/payment/list/{type}")
    public Result<?> paymentList(@PathVariable("type") Integer type) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                List<RecyclePaymentTerm> result = recyclePaymentTermService.listByUserId(userId,type);
                return Result.ok(result);
            }
        }
    }


    //支付
    @PostMapping(value = "/pay")
    @Transactional
    public Result<?> payOrder(@RequestBody JSONObject param) {

        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId =  (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                //修改订单状态为已完成
                String id = param.getString("id");
                double price = param.getDouble("price");
                String voucher = param.getString("voucher");
                double actualWeight = param.getDouble("actualWeight");
                //boolean result = iRecycleUserOrderService.pay(userId,id);
                RecycleUserOrder recycleUserOrder = new RecycleUserOrder();
                recycleUserOrder.setRecycleUserId(userId);
                recycleUserOrder.setId(id);
                recycleUserOrder.setPrice(price);
                recycleUserOrder.setVoucher(voucher);
                recycleUserOrder.setActualWeight(actualWeight);
                recycleUserOrder.setOrderStatus(2);
                recycleUserOrder.setCompleTime(new Date());
                iRecycleUserOrderService.updateById(recycleUserOrder);

                JSONObject order = iRecycleUserOrderService.getRecycleUserById(id);
                //添加支付记录
                RecycleRecord recycleRecord = new RecycleRecord();
                recycleRecord.setId(IDHelper.getInfoId(redisUtil,"ZF"));
                recycleRecord.setPayMoney(param.getDouble("price"));
                recycleRecord.setOrderId(id);
                recycleRecord.setUserId(userId);
                recycleRecord.setCreateBy(userId);
                recycleRecord.setCreateTime(new Date());
                recycleRecord.setType(2);
                recycleRecordService.save(recycleRecord);
                //更新钱包金额
                RecycleUserWallet recycleUserWallet = recycleUserWalletService.getByUserId(userId);
                double moneyOutAmount = recycleUserWallet.getMoneyOutAmount();
                moneyOutAmount  = moneyOutAmount-order.getDouble("price");
                log.info("更新用户账户余额 "+moneyOutAmount);
                recycleUserWallet.setMoneyOutAmount(moneyOutAmount);
                recycleUserWalletService.updateById(recycleUserWallet);
                //添加回收员收入记录
                RecycleRecord recycleRecordAdmin = new RecycleRecord();
                recycleRecordAdmin.setId(IDHelper.getInfoId(redisUtil,"SK"));
                recycleRecordAdmin.setPayMoney(order.getDouble("price"));
                recycleRecordAdmin.setOrderId(id);
                recycleRecordAdmin.setUserId(recycleUserOrder.getRecycleUserId());
                recycleRecordAdmin.setCreateBy(recycleUserOrder.getRecycleUserId());
                recycleRecordAdmin.setCreateTime(new Date());
                recycleRecordAdmin.setType(1);
                recycleRecordService.save(recycleRecordAdmin);

                //更新钱包金额
                RecycleUserWallet recycleUserWalletAdmin = recycleUserWalletService.getByUserId(recycleUserOrder.getRecycleUserId());
                double moneyOutAmountAdmin = recycleUserWalletAdmin.getMoneyOutAmount();
                double priceAdmin = order.getDouble("price");
                moneyOutAmountAdmin  = moneyOutAmount + priceAdmin;
                log.info("更新回收员账户余额 "+moneyOutAmountAdmin);
                recycleUserWalletAdmin.setMoneyOutAmount(moneyOutAmountAdmin+priceAdmin);
                recycleUserWalletService.updateById(recycleUserWalletAdmin);
                return Result.ok(true);
            }
        }
    }

    /**
     * 接单
     * @param id
     * @return
     */
    @ApiOperation("回收员接单")
    @GetMapping(value = "/order/receive/{id}")
    @Transactional
    public Result<?> receiveOrder(@PathVariable("id") String id) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = (String) redisUtil.get(token);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                RecycleUserOrder recycleUserOrder = new RecycleUserOrder();
                recycleUserOrder.setId(id);
                recycleUserOrder.setRecycleUserId(userId);
                recycleUserOrder.setOrderStatus(1);
                boolean result = iRecycleUserOrderService.updateById(recycleUserOrder);
                return Result.ok(result);
            }
        }
    }


    /**
     * 接单
     * @return
     */
    @ApiOperation("资金记录")
    @PostMapping(value = "/price/record")
    @Transactional
    public Result<?> priceRecord(@RequestBody JSONObject param) {
        String token = apiHandler.getToken();
        if(token == null){
            return Result.error(Status.BLANKTOKEN.getCode(),Status.BLANKTOKEN.getMessage());
        }else{
            String userId = (String) redisUtil.get(token);
            param.put("userId",userId);
            if(userId == null){
                return Result.error(Status.INVALIDTOKEN.getCode(),Status.INVALIDTOKEN.getMessage());
            }else{
                PageBean<RecycleRecord> list = recycleRecordService.listByUserId(param);
                return Result.ok(list);
            }
        }
    }
}
