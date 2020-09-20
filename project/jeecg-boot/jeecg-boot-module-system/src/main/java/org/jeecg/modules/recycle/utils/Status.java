package org.jeecg.modules.recycle.utils;

/**
 * Created by X1C on 2018/5/29.
 */
public enum Status {
    BLANKTOKEN(5000,"token为空"),
    INVALIDTOKEN(5001,"无效的token"),
    DATAREPEAT(5004,"数据重复"),
    NODEMOUNT(5005,"已被挂载"),
    DATAEXISTED(5006,"数据已存在"),
    ACOUNTENABLED(5003,"账户被禁用，登录失败，请联系管理员"),
    LOGINFAIL(5007,"登录失败"),
    UORPERROR(5002,"用户名或密码输入错误，登录失败"),
    PASSWORERROR(5008,"原密码错误"),
    PASSNORULE(5009,"密码不符合规则"),
    EPCNORULE(5010,"新的epc不符合规则"),
    PASSWORDUPDATESUCCESS(5011,"密码修改成功"),
    PASSWORDUPDATEFAIL(5012,"密码修改失败"),
    SUCCESS(5013,"成功"),
    FAIL(5014,"失败"),
    NOTNULL(5016,"不能为空"),
    LOWSTOCKS(5015,"库存不足"),
    PARAMEEXCEPTION(5017,"参数异常"),
    DATAHASCHILD(5018,"数据有子节点"),
    DATAINUSE(5019,"数据正在使用中"),
    TASKEXECUTED(5020,"任务已执行");

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
