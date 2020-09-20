package org.jeecg.modules.recycle.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.recycle.entity.RecycleUser;
import org.jeecg.modules.recycle.entity.RecycleUserWallet;
import org.jeecg.modules.recycle.service.IRecycleUserService;
import org.jeecg.modules.recycle.service.IRecycleUserWalletService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/wx")
@Slf4j
public class UserController {
    @Resource
    private IRecycleUserService userService;
    @Resource
    private IRecycleUserWalletService recycleUserWalletService;
//    private static final String APPID = "wx688b90baff0369d8";
//    private static final String SECRET = "ac6167a3e3e4716f0317023822fb6fa4";

    private static final String APPID = "wxcceee42df1cda916";
    private static final String SECRET = "4aeb99ac5b3aeccd36013aa84b1f2b01";
    @Resource
    private RedisUtil redisUtil;
    //获取凭证校检接口
    @PostMapping("/login")
    public Result<?> login(@RequestParam(value = "code",required = false) String code)
    {
        if("".equals(code)){
            return Result.error(500,"code is empty, login failure ");
        }
        //微信的接口
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();

        String session_key = "";
        String openid = "";
     //进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK)
        {
            String sessionData = responseEntity.getBody();
            JSONObject object = JSONObject.parseObject(sessionData);
            //获取用户的唯一标识
            openid = object.getString("openid");
            //获取会话秘钥
            session_key = object.getString("session_key");

            if(userService.getUserById(openid)==null){
                //下面就可以写自己的业务代码了
                RecycleUser recycleUser = new RecycleUser();
                recycleUser.setUid(openid);
                recycleUser.setNickname(openid);
                recycleUser.setCreateTime(new Date());
                userService.save(recycleUser);

                RecycleUserWallet recycleUserWallet = new RecycleUserWallet();
                recycleUserWallet.setMoneyOutAmount(0);
                recycleUserWallet.setUserId(openid);
                recycleUserWallet.setCreateTime(new Date());
                recycleUserWalletService.save(recycleUserWallet);
            }
            redisUtil.set(session_key,openid,86400);
            //最后要返回一个自定义的登录态,用来做后续数据传输的验证
        }
        if(openid != null){
            JSONObject result = new JSONObject();
            result.put("session_key",session_key);
            result.put("openid",openid);
            return Result.ok(result);
        }else{
            return Result.error(500,"login failure ");
        }

    }

}
